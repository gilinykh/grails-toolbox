/*
 * PluginMatcherService
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

import java.util.regex.Pattern
import java.util.regex.Matcher

class PluginMatcherService {

    static PrintWriter outFile = new PrintWriter("outFile.txt")

    static matcherMap = [:]

    static transactional = false

    /**
     * @param resource
     * @param majorText - titles, tags, categories
     * @param minorText - content, comments
     */
    void matchResource(Resource resource) {
        def plugins = getPluginsInfo()
        boolean matched = false
        plugins.each { plugin ->
            matched |= matchPluginWithResource(plugin, resource)
        }
        matched
    }

    void matchResourceEntry(def plugins, Map resource, Closure factory) {
        boolean matched = false
        def createdResourceHolder = []
        plugins.each { plugin ->
            matched |= matchPluginWithResource(plugin, resource, factory, createdResourceHolder)
        }
        outFile.flush()
        matched
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
        if (text) {
            Pattern pattern = matcherMap[pluginName]
            if (!pattern) {
                def pluginRe = pluginName.toLowerCase().replaceAll('-', '[\\\\- ]')
                def peString = "\\b${pluginRe}\\b"
                pattern = Pattern.compile(peString)
                matcherMap[pluginName] = pattern
            }

            Matcher matcher = pattern.matcher(text?.toLowerCase())
            return matcher.find()
        } else {
            return false
        }
    }

    private void createResourceMatcher(MatchLevel matchLevel, def plugin, Map resourceMap, Closure factory, List createdResourceHolder) {
        if (!createdResourceHolder) {
            def res = factory.call(resourceMap)
            createdResourceHolder << res
        }
        if (createdResourceHolder) {
            System.out.println("Matched $plugin $resourceMap.title")
            def s = "new ResourceMatcher(plugin: Plugin.findByName('$plugin.name'), matchLevel: MatchLevel.MINOR, resource: focr('$resourceMap.link', '$resourceMap.title')).save()"
            outFile.println s
//            def rm = ResourceMatcher.findByPluginAndResource(plugin, createdResourceHolder[0])
//            if (!rm) {
//                rm = new ResourceMatcher(plugin: plugin, resource: createdResourceHolder[0], matchLevel: matchLevel)
//                rm.save(flush: true)
//            }
//            ResourceMatcher.findOrCreateWhere([plugin: plugin, resource: createdResourceHolder[0], matchLevel: matchLevel]).save(flush: true)
        }
    }

    private matchPluginWithResource(plugin, resource) {
        matchPluginWithResource(plugin, null, null, [resource])
    }

    private matchPluginWithResource(plugin, Map resourceMap, Closure factory, List createdResourceHolder) {
        def matched = false

        def majorText
        if (createdResourceHolder) {
            majorText = createdResourceHolder[0].majorText
        } else {
            majorText = resourceMap.majorText
        }

        //match major text
        for (def text : majorText) {
            if (isMatched(plugin.name, text)) {
                createResourceMatcher(MatchLevel.MAJOR, plugin, resourceMap, factory, createdResourceHolder)
                //create new matcher
                matched = true
                break
            }
        }

        def minorText
        if (createdResourceHolder) {
            minorText = createdResourceHolder[0].minorText
        } else {
            minorText = resourceMap.minorText
        }

        //match major text
        for (def text : minorText) {
            if (isMatched(plugin.name, text)) {
                createResourceMatcher(MatchLevel.MINOR, plugin, resourceMap, factory, createdResourceHolder)
                //create new matcher
                matched = true
                break
            }
        }

        matched
    }

}
