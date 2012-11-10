/*
 * PluginRelease
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

class PluginRelease {

    String title
    Author author
    String description
    String version
    String file

    static belongsTo = [plugin: Plugin]

}
