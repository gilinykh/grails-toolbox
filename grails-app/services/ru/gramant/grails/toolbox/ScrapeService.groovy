/*
 * FeedService
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

import groovy.json.JsonSlurper


class ScrapeService {

    String URL_CACHE_DIR = 'data/url-cache'

    String readUrlOrCache(String url) {
        def filename = (url =~ /\W/).replaceAll('_') + '.html'
        def file = new File(URL_CACHE_DIR, filename)
        def htmlText
        if (!file.exists()) {
            htmlText = new URL(url).getText('utf-8')
            file.parentFile.mkdirs()
            file.setText(htmlText, 'utf-8')
        } else {
            htmlText = file.getText('utf-8')
        }
        htmlText
    }


    void parsePluginsForTag(String tag) {
        String processedTag = tag.replaceAll(' ', '+')

        String tagUrl = "http://grails.org/plugins/tag/${processedTag}?offset=0&max=100"

        Map result = [:]

        result.tag = tag

        def slurper = new XmlSlurper(new org.ccil.cowan.tagsoup.Parser())
        def html = slurper.parseText(readUrlOrCache(tagUrl))
        def bodyElements = html.depthFirst()

        def withA = { def o, def a, def v, def c ->
            for (s in o.div) {
                if (s."@$a" == v) {
                    c.call(s)
                }
            }
        }

        for (beCa in bodyElements) {
            if (beCa.@id ==~ 'contentArea') {
                withA(beCa, 'id', 'content') { beC ->
                    result.entryCount = beC.strong.collect {it.text()}[-1]
                    withA(beC, 'id', 'currentPlugins') { beCpls ->
                        withA(beCpls, 'class', 'currentPlugin') { beCpl ->
                            def pd = [:]
                            withA(beCpl, 'class', 'header') { beH ->
                                pd.name = beH.h4.a.@href.toString().replaceAll('/plugin/', '')
                                result."plugin-${pd.name}" = pd
                                withA(beH, 'class', 'detail') { beD ->
                                    beD.div.each {bdi ->
                                        if (bdi.span.text() ==~ 'Tags.') {
                                            def t = bdi.text().toString()
                                            t = t.substring(t.indexOf(':') + 1, t.length())
                                            pd.tags = t.trim().split(',')*.trim().grep()
                                        }
                                    }
                                }
                                withA(beH, 'class', 'ratings') { beR ->
                                    def valuation = 0.0
                                    def tdl = []
                                    beR.table.tr.td.each { tdl << it }
                                    tdl.eachWithIndex {starTd, starCount ->
                                        if (starCount < 5) {
                                            def tdStyle = starTd.div.a.@style?.toString()
                                            if (!tdStyle) {
                                                valuation += 0.2
                                            } else {
                                                def m = (tdStyle =~ /width.([0-9\.]+)/)
                                                if (m) {
                                                    valuation += 0.2 * 0.01 * (m.group(1).toString() as BigDecimal)
                                                } else {
                                                    throw new RuntimeException("Unrecognized style: ${tdStyle}".toString())
                                                }
                                            }
                                        } else {
                                            def rcs = starTd.text()
                                            def m = (rcs =~ /(\d+)/)
                                            if (m) {
                                                pd.ratingCount = m.group(1).toString() as int
                                            }
                                        }
                                        starCount++
                                    }
                                    pd.rating = valuation
                                }
                            }
                        }
                    }
                }
            }
        }

        def resFile = new File(URL_CACHE_DIR, 'result.txt')
        resFile.parentFile.mkdirs()
        resFile << (prettyPrint('  ', ''<<'', result)).toString()
        resFile << '\n'
    }

    def prettyPrint(String indent, StringBuffer accumulator, def m) {
        boolean first = true
        m.each { k, v ->
            if (first) {
                first = false
            } else {
                accumulator << ",\n$indent"
            }
            accumulator << "$k:"
            if (v instanceof Map) {
                prettyPrint((indent + '  ').toString(), accumulator, v)
            } else {
                accumulator << v.toString()
            }
        }
        return accumulator.toString()
    }

    def parseSO() {
        def jss = readUrlOrCache('http://api.stackoverflow.com/1.1/search?tagged=grails&page=1&pagesize=100&sort=creation')
        def slurper = new JsonSlurper()
        def result = slurper.parseText(jss)

        def resFile = new File(URL_CACHE_DIR, 'sores.txt')

        jss = readUrlOrCache('http://api.stackoverflow.com/1.1/questions/13325560%3B13323048%3B13322255%3B13319794%3B13319362%3B13319270?answers=true&body=true&page=1&pagesize=100')
        result = slurper.parseText(jss)
//        resFile.text = result.size()
//        resFile.text = ((result.questions*.title).collect {"$it\n"}).toString()
        resFile.text = (result.questions*.body).join('\n')
    }
}
