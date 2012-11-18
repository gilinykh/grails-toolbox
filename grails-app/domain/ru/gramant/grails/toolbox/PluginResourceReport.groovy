/*
 * PluginResourceReport
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

class PluginResourceReport {

    Plugin plugin
    Resource resource
    String comment

    static constraints = {
        comment(nullable: true, blank: true)
    }

}
