modules = {
    'grails-toolbox-bootswatcher-css' {
        dependsOn 'bootstrap'
//        resource url: 'css/bootswatcher.css', disposition: 'head'
        resource url: 'css/spacelab.css', disposition: 'head'
    }
    
    if (2 * 2 == 4) {
    //if (grails.util.Environment.current == grails.util.Environment.PRODUCTION) {
       'grails-toolbox-css' {
            dependsOn 'grails-toolbox-bootswatcher-css'
            resource url: 'css/grails-toolbox.css', disposition: 'head'
        }            
    } else {
        'grails-toolbox-css' {
            resource url: 'css/grails-toolbox.css', disposition: 'head'
        }                    
    }

    'bootstrap-toolbox' {
        defaultBundle false
        dependsOn 'jquery'
        resource url: 'bootstrap-toolbox/bootstrap.js'
        resource url: 'bootstrap-toolbox/bootstrap.css'
        resource url: 'bootstrap-toolbox/docs.css'
    }

    'tipsy' {
        defaultBundle false
        resource url: 'tipsy/tipsy.css'
        resource url: 'tipsy/jquery.tipsy.js'
    }

    'notify' {
        defaultBundle false
        resource url: 'bootstrap-toolbox/bootstrap.notify.css'
        resource url: 'bootstrap-toolbox/jquery.bootstrap.notify.js'
    }

    'index-page' {
        defaultBundle false
        dependsOn 'common'
        resource url: 'bootstrap-toolbox/custom/index.js'
        resource url: 'bootstrap-toolbox/custom/index.css'
        resource url: 'css/grails-toolbox.css', disposition: 'head'
    }

    'plugin-page' {
        defaultBundle false
        dependsOn 'common', 'font-awesome', 'notify'
        resource url: 'bootstrap-toolbox/custom/plugin.js'
        resource url: 'bootstrap-toolbox/custom/plugin.css'
    }

    'common' {
        resource url: 'bootstrap-toolbox/custom/common.css'
        dependsOn 'bootstrap-toolbox', 'tipsy'
    }

    'font-awesome' {
        resource url: 'bootstrap-toolbox/font-awesome/font-awesome.css', disposition: 'defer'
        resource url: 'bootstrap-toolbox/font-awesome/font-awesome-ie7.css', disposition: 'head', wrapper: { s -> "<!--[if lt IE 8]>$s<![endif]-->" }
    }
}
