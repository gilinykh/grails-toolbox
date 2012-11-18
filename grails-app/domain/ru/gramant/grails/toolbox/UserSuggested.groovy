/*
 * UserSuggested
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

class UserSuggested extends Resource {

    String author
    ResourceType type = ResourceType.USER

    List<String> getMajorText() {
        return [link]
    }

    List<String> getMinorText() {
        return [title]
    }
}
