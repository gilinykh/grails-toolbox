/*
 * UserController
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

class UserController {

    def pluginResourceReport = {
        def plugin = (params.plugin) ? Plugin.get(params.plugin) : null
        def resource = (params.resource) ? Resource.get(params.resource) : null
        def comment = params.comment

        if (plugin && resource) {
            PluginResourceReport.findOrCreateWhere(plugin: plugin, resource: resource, comment: comment).save()
        }

        render "ok"
    }

    def addResource = {
        def url = params.url
        def title = (params.comment) ?: params.url

        if (url) {
            UserSuggested.findOrCreateWhere(link: url, title: title, author: request.userGeneratedId).save()
        }

        render "ok"
    }
}
