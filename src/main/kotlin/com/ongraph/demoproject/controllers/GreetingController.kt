package com.ongraph.demoproject.controllers

import com.ongraph.demoproject.classes.Greeting
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong


@RestController
class GreetingController {
    private val template = "Hello, %s!"
    private val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String): Greeting {
        return Greeting(counter.incrementAndGet(), String.format(template, name))
    }
    @PostMapping("/greeting")
    fun greetings(@RequestBody name: String): Greeting {
        return Greeting(counter.incrementAndGet(), String.format(template, name))
    }

}