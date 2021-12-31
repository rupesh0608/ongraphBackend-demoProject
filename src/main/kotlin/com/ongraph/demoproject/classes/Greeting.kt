package com.ongraph.demoproject.classes

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