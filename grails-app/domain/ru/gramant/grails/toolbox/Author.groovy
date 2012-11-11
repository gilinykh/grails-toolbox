/*
 * Author
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

/**
 * Plugin author
 */
class Author {

    String username
    String mail

    static constraints = {
        username(nullable: false, blank: false)
        mail(nullable: true, blank: false, unique: true)
    }

    String toString() {
        username
    }
}
