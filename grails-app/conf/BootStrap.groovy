import grails.converters.JSON
import grails.util.Environment
import ru.gramant.grails.toolbox.Feed
import ru.gramant.grails.toolbox.ResourceType

class BootStrap {

    def init = { servletContext ->

        Environment.executeForCurrentEnvironment {
            development {
            }
            
            staging {
                createFeeds()
            }
            
            production {
                printCloudFoundryInfo()
                createFeeds()
            }
        }
    }
    def destroy = {
    }

    private createFeeds() {
        Feed.findOrCreateWhere(type: ResourceType.FEED, title: 'grails.ru', url: 'http://grails.ru/feed/').save()
        Feed.findOrCreateWhere(type: ResourceType.STACKOVERFLOW, title: 'stackoverflow grails', url: 'http://stackoverflow.com/feeds/tag?tagnames=grails&sort=newest').save()
        Feed.findOrCreateWhere(type: ResourceType.MAIL, title: 'grails - user - with replies', url: 'http://grails.1312388.n4.nabble.com/Grails-user-f1312389.xml').save()
    }

    private printCloudFoundryInfo() {
        String VCAP_SERVICES = System.getenv('VCAP_SERVICES')
        println "VCAP_SERVICES: ${System.getenv('VCAP_SERVICES')}n"

        try {
            def servicesMap = JSON.parse(VCAP_SERVICES)
            servicesMap.each { key, services ->
                if (key.startsWith('mysql')) {
                    for (service in services) {
                        print "MySQL service $service.name: "
                        print "url='jdbc:mysql://$service.credentials.hostname:$service.credentials.port/$service.credentials.name', "
                        print "user='$service.credentials.user', "
                        println "password='$service.credentials.password'n"
                    }
                }
                else if (key.startsWith('postgresql')) {
                    for (service in services) {
                        print "PostgreSQL service $service.name: "
                        print "url='jdbc:postgresql://$service.credentials.hostname:$service.credentials.port/$service.credentials.name', "
                        print "user='$service.credentials.user', "
                        println "password='$service.credentials.password'n"
                    }
                }
            }
        }
        catch (e) {
            println "Error occurred parsing VCAP_SERVICES: $e.message"
        }
    }
}
