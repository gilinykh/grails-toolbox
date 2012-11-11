/*
 * TestController
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

class TestController {

    def pluginInfoLoaderService

    def feedService

    def index = {
        pluginInfoLoaderService.updateInfoFromPluginListFile()

        render "ok"
    }

    def feed = {
        feedService.parseAllFeedsAndMatch()

        render "ok"
    }

    def main = {
        def categories = Category.list()
        def plugins = Plugin.list()
        def categoryList = []
        categories.collect { c ->
            def cp = plugins.grep { it.categories.contains(c) }
            def cd = [categoryName: c.title]
            def pluginList = []
            cp.each { p ->
                def pd = [:]
                pd.code = p.name
                pd.release = p.latestRelease?.releaseVersion ?: 'unknown'
                pd.releases = p.releases.size()
                pd.authors = p.author?.username ?: 'unknown'
                int rate5 = (int) (50 * p.rating)
                pd.rate5 = rate5
                pd.ratings = p.ratingCount
                def rml = ResourceMatcher.findAllByPlugin(p)
                def news = []
                rml.each { rm ->
                    def r = rm.resource
                    if (r.instanceOf(FeedEntry)) {
                        def m = [:]
                        def f = (FeedEntry) r
                        m.date = f.publishedDate.toString()
                        m.origin = r.type.toString()
                        m.title = f.title
                        news << m
                    }
                }
                pd.news = news

                pluginList << pd
            }
            cd.pluginList = pluginList.sort { it.rate5 + 1 * Math.log(it.ratings) }.reverse()
            categoryList << cd
        }

//        def acegi = [code: 'acegi', authors: 'Tsuyoshi Yamamoto', release: '0.5.3.2', rating: '4.0/5', ratings: 37,
//                news: [[date: 'Oct 31 09', title: 'Grails Acegi plugin lost password', origin: 'stackoverflow']]]
//
//        def springsec = [code: 'spring-security-core', authors: 'Burt Beckwith', release: '1.2.7.3', rating: '4.5/5', ratings: 66,
//                usage: '66%', news: [[date: 'Sep 19 12', title: 'Grails and redirect with spring security core', origin: 'stackoverflow']]]


        render view: '/toolbox/index', model: [useLargeHeader: true, categoryList: categoryList]
    }

    def plugin = {
        render view: '/toolbox/plugin_static'
    }

    def mailList = {
        def list = []
        new File('data/grails-mailing-list-dump.xml').eachLine { line ->
            list << new String(line.decodeBase64())
        }
        render list
    }

}
