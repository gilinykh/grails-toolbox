/*
 * TestController
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

class ParseController {

    def scrapeService

    def index() {
        int pages = 5
        if (params.pages) {
            try {
                pages = params.int('pages')
            } catch (Exception e) {
                // do nothing
            }

        }
        List questions = []
        (1..pages).each{ pageNum ->
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
