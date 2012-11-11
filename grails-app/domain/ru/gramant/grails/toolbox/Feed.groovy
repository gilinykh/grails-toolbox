/*
 * Feed
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes="url,type")
class Feed {

    String title
    String url
    ResourceType type

    static constraints = {
        title(nullable: false, blank: false)
        url(nullable: false, blank: false)
    }

    String toString() {
        title
    }
}
