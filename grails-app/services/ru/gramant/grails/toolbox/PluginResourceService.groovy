/*
 * ResourceService
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

import org.hibernate.FetchMode

class PluginResourceService {

    List getPluginResources(Plugin plugin) {
        def resources = []
        def matchers = ResourceMatcher.withCriteria {
            eq "plugin", plugin
            fetchMode "resource", FetchMode.JOIN
        }

        matchers.each { matcher ->
            resources << [resource: matcher.resource, matchedByUser: (matcher.matchLevel == MatchLevel.USER)]
        }

        return resources
    }

    List getRecentResources() {
        Resource.findAll('from Resource where (status =:grabbed or status =:confirmed) and (type !=:userType) order by publishedDate desc',
            [grabbed: ResourceStatus.GRABBED, confirmed: ResourceStatus.CONFIRMED, userType: ResourceType.USER],
            [max: 100]
        )
    }


}
