/*
 * PluginRelease
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

import org.joda.time.DateTime
import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes="plugin,releaseVersion")
class PluginRelease {

    String title
    Author author
    String description
    String releaseVersion
    String file
    DateTime lastUpdated

    static belongsTo = [plugin: Plugin]

    static hasMany = [resources: ResourceMatcher]

    static constraints = {
        author(nullable: true)
    }

    static mapping = {
        description(type: 'text')
    }

}
