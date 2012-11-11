/*
 * FeedService
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

import com.sun.syndication.feed.synd.SyndEntry
import com.sun.syndication.io.SyndFeedInput
import com.sun.syndication.io.XmlReader
import org.joda.time.DateTime
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class FeedService {

    private static final Logger LOG = LoggerFactory.getLogger(this)

    def pluginMatcherService

    List parseFeed(String url) {

        LOG.info("Parsing feed by url ${url}")

        def answer = []

        def feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
        feed?.entries?.each { SyndEntry e ->
            def entry = [:]
            entry.author = e.author
            entry.link = e.link
            entry.title = e.title
            entry.publishedDate = (e.publishedDate) ? new DateTime(e.publishedDate) : null
            entry.categories = e.categories.collect { it.name }

            def description = [e.description?.value]
            if (e.contents) {
                description += e.contents?.collect { it.value }
            }

            entry.description = (description - null).join("\n")

            answer << entry
        }

        return answer
    }

    void parseAllFeedsAndMatch() {
        Feed.list().each { feed ->
            parseFeedAndMatch(feed)
        }
    }

    void parseFeedAndMatch(Feed feed) {
        def entries = parseFeed(feed.url)
        entries.each { e ->
            if (!FeedEntry.findByFeedAndLink(feed, e.link)) {
                def entry = createDomainFromFeedEntry(e, feed).save()
                pluginMatcherService.matchResource(entry)
            }
        }
    }

    private createDomainFromFeedEntry(entry, feed) {
        def answer = new FeedEntry()
        answer.feed = feed
        answer.type = feed.type
//        answer.properties = entry
        answer.link = entry.link
        answer.author = entry.author
        answer.title = entry.title
        answer.publishedDate = entry.publishedDate
        answer.description = entry.description

        entry.categories.each { c ->
            def category = FeedCategory.findByTitle(c)
            if (!category) {
                category = new FeedCategory(title: c).save()
            }

            answer.addToCategories(category)
        }

        return answer
    }

}
