/*
 * PluginMatcherService
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

class PluginMatcherService {

    /**
     * @param resource
     * @param majorText - titles, tags, categories
     * @param minorText - content, comments
     */
    void matchResource(Resource resource) {
        def plugins = getPluginsInfo()
        plugins.each { plugin ->
            matchPluginWithResource(plugin, resource)
        }
    }

    void matchPlugin(Plugin plugin) {
        Resource.list().each { resource ->
            matchPluginWithResource(plugin, resource)
        }
    }

    private getPluginsInfo() {
        return Plugin.executeQuery('select id, name from Plugin').collect { [id: it[0], name: it[1]] }
    }

    private boolean isMatched(String pluginName, String text) {
        return text.contains(pluginName)
    }

    private matchPluginWithResource(plugin, Resource resource) {
        //match major text
        for (def text : resource.majorText) {
            if (isMatched(plugin.name, text)) {
                //create new matcher
                ResourceMatcher.findOrCreateWhere([plugin: Plugin.load(plugin.id), resource: resource, matchLevel: MatchLevel.MAJOR]).save()
                break
            }
        }

        //match minor text... same code but it's too boring to extract it as method
        for (def text : resource.minorText) {
            if (isMatched(plugin.name, text)) {
                //create new matcher
                ResourceMatcher.findOrCreateWhere([plugin: Plugin.load(plugin.id), resource: resource, matchLevel: MatchLevel.MINOR]).save()
                break
            }
        }
    }

}
