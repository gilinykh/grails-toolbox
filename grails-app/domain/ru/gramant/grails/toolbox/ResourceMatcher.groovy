/*
 * ResourceMatcher
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes="plugin,resource,matchLevel")
class ResourceMatcher {

    Plugin plugin
    Resource resource
    MatchLevel matchLevel

    String toString() {
        "$plugin : $resource"
    }
}
