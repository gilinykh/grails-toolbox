/*
 * Feed
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

class Feed {

    String title
    String url

    static constraints = {
        title(nullable: false, blank: false)
        url(nullable: false, blank: false)
    }

    String toString() {
        title
    }
}
