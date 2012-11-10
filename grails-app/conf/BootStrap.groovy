import grails.util.Environment
import ru.gramant.grails.toolbox.Feed
import org.joda.time.DateTime
import ru.gramant.grails.toolbox.ResourceType
import ru.gramant.grails.toolbox.FeedEntry

class BootStrap {

    def init = { servletContext ->

        Environment.executeForCurrentEnvironment {
            development {
//                Feed feed
//                DateTime publishedDate
//                String author
//                String link
//                String title
//                String description
//                ResourceType type = ResourceType.FEED   
                Feed feed = new Feed(title: 'grails.ru', url: 'http://grails.ru/feed/').save(failOnError: true)
                (1..100).each{ num ->
                    long millis = System.currentTimeMillis() - (1000 * 60 * 60 * 24 * (10-num))
                    new FeedEntry(feed: feed, publishedDate: new DateTime(millis), link: "Link$num", title: "Title$it").save(failOnError: true)
                }
            }
            
            staging {
                Feed.findOrCreateWhere(title: 'grails.ru', url: 'http://grails.ru/feed/').save()
            }
            
            production {
                
            }
        }
    }
    def destroy = {
    }
}
