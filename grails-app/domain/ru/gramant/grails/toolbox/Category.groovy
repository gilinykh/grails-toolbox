/*
 * Category
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */

package ru.gramant.grails.toolbox

class Category {

    String title

    static constraints = {
        title(nullable: false, blank: false)
    }

}