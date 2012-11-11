/*
 * PluginService
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

import org.hibernate.FetchMode

class PluginService {


    List getPluginResources(Plugin plugin) {
        def resources = []
        def matchers = ResourceMatcher.withCriteria {
            eq "plugin", plugin
            fetchMode "Resource", FetchMode.JOIN
        }

        matchers.each { matcher ->
            resources << [resource: matcher.resource, matchedByUser: (matcher.matchLevel == MatchLevel.USER)]
        }

        return resources
    }

}
