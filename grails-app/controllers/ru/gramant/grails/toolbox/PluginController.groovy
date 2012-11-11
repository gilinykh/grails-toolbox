package ru.gramant.grails.toolbox

/**
 * PluginController
 * A controller class handles incoming web requests and performs actions such as redirects, rendering views and so on.
 */
class PluginController {

    def pluginService

    def show() {
        def pluginInstance = Plugin.get(params.id)
        if (!pluginInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'plugin.label'), params.id])
            redirect(controller: "landing", action: "categories")
            return
        }

        def resources = pluginService.getPluginResources(pluginInstance)
        def plugin = [name: pluginInstance.name, description: pluginInstance?.latestRelease?.description]
            
        render view: '/toolbox/plugin', model: [plugin: plugin, resources: resources]
    }
}
