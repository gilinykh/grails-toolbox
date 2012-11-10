/*
 * FeedEntry
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

import org.joda.time.DateTime

class FeedEntry extends Resource {

    Feed feed
    DateTime publishedDate
    String author
    String link
    String title
    String description
    ResourceType type = ResourceType.FEED

    static hasMany = [categories: FeedCategory]

    static constraints = {
        description(nullable: true)
        author(nullable: true)
    }

    static mapping = {
        description(type: 'text')
        publishedDate(type: org.joda.time.contrib.hibernate.PersistentDateTime) //WTF?! Why should I declare this when I use inheritance?!
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
}