/*
 * FeedCategory
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes="title")
class FeedCategory {

    String title

    String toString() {
        title
    }
}
