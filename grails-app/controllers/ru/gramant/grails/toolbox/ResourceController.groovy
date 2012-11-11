/*
 * ResourceController
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

class ResourceController {

    def pluginResourceService

    def recent = {
        def resources = pluginResourceService.recentResources

        render view: '/toolbox/recent', model: [resources: resources]
    }

}
