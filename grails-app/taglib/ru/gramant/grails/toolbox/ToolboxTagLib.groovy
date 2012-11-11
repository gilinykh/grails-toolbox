/*
 * ToolboxTagLib
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.gramant.grails.toolbox

import org.joda.time.format.DateTimeFormat
import org.joda.time.DateTimeZone

class ToolboxTagLib {

    /** default date time formatter that will be used if pattern is not specified */
    private static defaultDateTimeFormatter = DateTimeFormat.forPattern('yyyy-MM-dd HH:mm').withZone(DateTimeZone.UTC)

    static namespace = "tb"

    def formatDateTime = { attrs ->
        def dateTime = attrs.value
        def pattern = attrs.pattern

        if (dateTime) {
            def formatter = (pattern) ? DateTimeFormat.forPattern(pattern).withZone(DateTimeZone.UTC) : defaultDateTimeFormatter
            out << formatter.print(dateTime)
        }
    }
}
