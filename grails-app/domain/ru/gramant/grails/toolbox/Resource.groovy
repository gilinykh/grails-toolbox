package ru.gramant.grails.toolbox

import org.joda.time.DateTime
/*
 * Resource
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */


abstract class Resource {

    static hasMany = [plugins: ResourceMatcher]

    ResourceType type
    DateTime publishedDate

    static mapping = {
        tablePerHierarchy false
    }

    abstract List<String> getMajorText()

    abstract List<String> getMinorText()

}
