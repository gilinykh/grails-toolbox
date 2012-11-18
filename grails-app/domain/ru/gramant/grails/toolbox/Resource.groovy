package ru.gramant.grails.toolbox

import org.joda.time.DateTime
/*
 * Resource
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */


abstract class Resource {

    static hasMany = [plugins: ResourceMatcher]

    ResourceType type
    ResourceStatus status = ResourceStatus.GRABBED
    DateTime publishedDate = DateTime.now()
    String link
    String title

    static mapping = {
        tablePerHierarchy false
    }

    abstract List<String> getMajorText()

    abstract List<String> getMinorText()

}
