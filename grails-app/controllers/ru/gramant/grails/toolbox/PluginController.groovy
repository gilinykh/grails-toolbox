package ru.gramant.grails.toolbox

import com.springsource.loaded.Plugins

/**
 * PluginController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
class PluginController {

//	def index = { }

    def show() {
        def pluginInstance = Plugin.list()[0]//Plugin.get(params.id)
        if (!pluginInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'plugin.label', default: 'Plugin'), params.id])
            redirect(controller: "landing", action: "categories")
            return
        }

        def updates = FeedEntry.createCriteria().list([max: 10]){
            plugins {
                eq('id', pluginInstance.id)
            }
        } 
            
        [pluginInstance: pluginInstance, updates: updates, updatesTotal: updates.totalCount]
    }
}
