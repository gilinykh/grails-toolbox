/*
 * TestController
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

class ParseController {

    def scrapeService

    def index() {
        scrapeService.parseSO()
        render 'ok'
    }

    def pluginTags() {
        PopularTags.POPULAR_TAGS.each { tag ->
            scrapeService.parsePluginsForTag(tag)
        }
        render 'ok'
    }

}
