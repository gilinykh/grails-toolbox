package ru.gramant.grails.toolbox

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class PluginsUpdateJob {

    private static final Logger LOG = LoggerFactory.getLogger(this)

    def pluginInfoLoaderService

    //don't run this job concurrently
    def concurrent = false

    static triggers = {
        simple name: 'pluginsUpdateJobTrigger', startDelay: 10*1000, repeatInterval: 3*60*60*1000
    }

    def group = "toolbox"

    def execute(){
        LOG.info("STARTING PluginsUpdateJob (update plugins info)")

        pluginInfoLoaderService.updateInfoFromPluginListFile()

        LOG.info("FINISHED PluginsUpdateJob (update plugins info)")
    }
}