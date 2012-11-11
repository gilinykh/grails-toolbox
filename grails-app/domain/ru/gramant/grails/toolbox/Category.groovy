/*
 * Category
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */

package ru.gramant.grails.toolbox

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes="title")
class Category {

    String title

    static constraints = {
        title(nullable: false, blank: false, unique: true)
    }
    
    String toString() {
        title
    }

}