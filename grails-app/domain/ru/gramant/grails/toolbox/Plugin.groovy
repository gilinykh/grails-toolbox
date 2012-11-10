/*
 * Plugin
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

import org.joda.time.DateTime

class Plugin {

    String name
    PluginRelease latestRelease
    Category category
    DateTime dateCreated
    DateTime lastUpdated

    static hasMany = [releases: PluginRelease]

    static constraints = {
        name(nullable: false, blank: false, unique: true)
        latestRelease(nullable: true)
        category(nullable: true)
    }

}
