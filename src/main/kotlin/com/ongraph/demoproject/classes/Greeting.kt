package com.ongraph.demoproject.classes

import org.apache.tomcat.util.json.JSONParser

class Greeting(private var id: Long, private var content: String) {
    @JvmName("getId")
    fun getId(): Long {
        return id
    }

    @JvmName("getContent")
    fun getContent(): String? {
        return content
    }
}