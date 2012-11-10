/*
 * Plugin
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

class Plugin {

    String name
    PluginRelease latestRelease
    Category category

    static hasMany = [releases: PluginRelease]

    static constraints = {
        name(nullable: false, blank: false, unique: true)
        latestRelease(nullable: false)
    }

}
