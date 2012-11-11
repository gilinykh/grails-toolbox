package ru.gramant.grails.toolbox

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class FeedsUpdateJob {

    private static final Logger LOG = LoggerFactory.getLogger(this)

    def feedService

    //don't run this job concurrently
    def concurrent = false

    static triggers = {
        simple name: 'feedsUpdateJobTrigger', startDelay: 40*1000, repeatInterval: 15*60*1000
    }

    def group = "toolbox"

    def execute(){
        LOG.info("STARTING FeedsUpdateJob (load resources from feeds)")

        feedService.parseAllFeedsAndMatch()

        LOG.info("FINISHED FeedsUpdateJob (load resources from feeds)")
    }
}