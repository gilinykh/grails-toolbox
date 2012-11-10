/*
 * PluginInfoLoaderService
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

import groovyx.net.http.HTTPBuilder

/**
 * Loads plugins info from grails.org site
 */
class PluginInfoLoaderService {


    def updateInfoFromPluginListFile() {
        def http = new HTTPBuilder( 'http://plugins.grails.org/' )

        http.get( path: '.plugin-meta/plugins-list.xml' ) { resp, xml ->
            xml.plugin.each { p ->
                def name = p.attributes().name
                def latestRelease = p.attributes()['latest-release']

                //0. Find or create plugin
                def plugin = Plugin.findByName(name)
                if (!plugin) {
                    plugin = new Plugin(name: name).save()
                }

                p.release.each { r ->
                    //1. Find or create author
                    def author = findOrCreateAuthorForRelease(r)

                    //2. (Create) Update release
                    def version = r.attributes().version

                    if (!version) {
                        println "Empty version - ${plugin}"
                    }

                    def release = PluginRelease.findByPluginAndReleaseVersion(plugin, version)
                    if (!release) {
                        //create new release
                        release = new PluginRelease()
                        plugin.addToReleases(release)
                    }

                    if (version == latestRelease) {
                        plugin.latestRelease = release
                    }

                    //update release info
                    release.title = r.title.text()
                    release.author = author
                    release.releaseVersion = version
                    release.description = r.description.text()
                    release.file = r.file.text()
                    release.save()
                }
            }
        }
    }

    private def findOrCreateAuthorForRelease(r) {
        def author = null
        def authorName = r.author.text()
        def authorMail = r.authorEmail.text()
        if (authorName || authorMail) {
            author = (authorMail) ? Author.findByMail(authorMail) : Author.findByUsername(authorName)
            if (!author) {
                author = new Author(username: authorName, mail: authorMail).save()
            }
        }

        return author
    }

}
