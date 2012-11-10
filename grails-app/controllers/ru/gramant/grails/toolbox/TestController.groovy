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

}
