package ru.gramant.grails.toolbox
/*
 * Resource
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */


abstract class Resource {

    ResourceType type

    static mapping = {
        tablePerHierarchy false
    }

    abstract List<String> getMajorText()

    abstract List<String> getMinorText()

}
