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
        def acegi = [code: 'acegi', authors: 'Tsuyoshi Yamamoto', release: '0.5.3.2', rating: '4.0/5', ratings: 37,
                news: [[date: 'Oct 31 09', title: 'Grails Acegi plugin lost password', origin: 'stackoverflow']]]

        def springsec = [code: 'spring-security-core', authors: 'Burt Beckwith', release: '1.2.7.3', rating: '4.5/5', ratings: 66,
                usage: '66%', news: [[date: 'Sep 19 12', title: 'Grails and redirect with spring security core', origin: 'stackoverflow']]]


        render view: '/toolbox/index', model: [useLargeHeader: true, pluginData: [acegi, springsec]]
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
