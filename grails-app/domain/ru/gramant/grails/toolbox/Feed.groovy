/*
 * Feed
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

class Feed {

    String title
    String url
    ResourceType type

    static constraints = {
        title(nullable: false, blank: false)
        url(nullable: false, blank: false)
    }

}
