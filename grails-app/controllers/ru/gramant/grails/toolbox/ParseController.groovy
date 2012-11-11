/*
 * TestController
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

class ParseController {

    def scrapeService

    def index() {
        List questions = []
        (1..5).each{ pageNum ->
            questions << scrapeService.parseSOPage(pageNum)
        }
        
        List saved = scrapeService.storeQuestions(questions.flatten())
        render 'ok'
    }

    def pluginTags() {
        PopularTags.POPULAR_TAGS.each { tag ->
            scrapeService.parsePluginsForTag(tag)
        }
        render 'ok'
    }

}
