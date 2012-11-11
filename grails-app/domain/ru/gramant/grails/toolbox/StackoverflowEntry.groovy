/*
 * StackoverflowEntry
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

import org.joda.time.DateTime

class StackoverflowEntry extends Resource {

    String author
    String description
    Integer questionId

    static hasMany = [categories: StackoverflowCategory]

    static constraints = {
        description(nullable: true)
        author(nullable: true)
    }

    static mapping = {
        description(type: 'text')
        publishedDate(type: org.joda.time.contrib.hibernate.PersistentDateTime)
    }

    List<String> getMajorText() {
        def answer = []
        answer << title
        answer += categories.collect { it.title }

        return answer
    }

    List<String> getMinorText() {
        return [description]
    }

    String toString() {
        "$type: $title".toString()
    }
}
