/*
 * Plugin
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

import org.joda.time.DateTime

class Plugin {

    String name
    PluginRelease latestRelease
    DateTime dateCreated
    DateTime lastUpdated

    Integer ratingCount
    Double rating
    
    Author author

    Set categories
    
    static hasMany = [
        releases: PluginRelease,
        resources: Resource,
        categories: Category
    ]

    static constraints = {
        name(nullable: false, blank: false, unique: true)
        latestRelease(nullable: true)
        author(nullable: true)
    }

    String toString() {
        name
    }
}
