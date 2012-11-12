/*
 * FeedService
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

import com.sun.syndication.feed.synd.SyndEntry
import com.sun.syndication.io.SyndFeedInput
import com.sun.syndication.io.XmlReader
import org.joda.time.DateTime

class FeedService {

    static transactional = false

    def pluginMatcherService

    List parseFeed(String url) {
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

    void parseEmailDump() {
        def mailFeed = Feed.findOrCreateWhere(type: ResourceType.MAIL, title: 'grails - user - with replies', url: 'http://grails.1312388.n4.nabble.com/Grails-user-f1312389.xml').save()

        def count = 0;

        try {

            def plugins = Plugin.list()
            new File('data/grails-mailing-list-dump.xml').eachLine { line ->
                count++;
                if (count % 100 == 0) {
                    System.out.println("###Processed $count")
                }
//                if (count == 100) {
//                    throw new IllegalStateException()
//                }

                def text = new String(line.decodeBase64())

                def linkMatcher = (text =~ /(?m)(?s).*(http:\/\/grails\.\d+\.n\d+\.nabble\.com\/([^\.]+)tp\d+p\d+\.html).*/)

//                System.out.println("Matches: " + linkMatcher.matches())

                if (linkMatcher) {
                    def entry = [:]
                    entry.author = 'Mail'
                    entry.link = linkMatcher[0][1]
                    entry.title = linkMatcher[0][2].replaceAll('-', ' ')
                    entry.publishedDate = new DateTime()
                    entry.categories = []

                    entry.description = text

                    entry.majorText = [entry.title]
                    entry.minorText = [text]

                    if (!FeedEntry.findByFeedAndLink(mailFeed, entry.link)) {
                        def factory = { entryMap ->
                            createDomainFromFeedEntry(entryMap, mailFeed).save()
                        }
                        def matched = pluginMatcherService.matchResourceEntry(plugins, entry, factory)
                        if (matched) {
                            System.out.println "Good job - matched feed!"
                        }
                    }
                }
            }
        } catch (IllegalStateException e) {

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
