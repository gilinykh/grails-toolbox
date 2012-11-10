import grails.util.Environment
import ru.gramant.grails.toolbox.Feed

class BootStrap {

    def init = { servletContext ->

        if (Environment.current.name == 'staging') {
            Feed.findOrCreateWhere(title: 'grails.ru', url: 'http://grails.ru/feed/').save()
        }

    }
    def destroy = {
    }
}
