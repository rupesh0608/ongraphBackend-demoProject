package com.ongraph.demoproject.controllers

import com.ongraph.demoproject.ModelClass.GreetingModel
import com.ongraph.demoproject.ModelClass.LoginModel
import com.ongraph.demoproject.ModelClass.UserModel
import com.ongraph.demoproject.classes.Greeting
import com.ongraph.demoproject.classes.User
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
    fun greetings(@RequestBody name: GreetingModel): Greeting {
        return Greeting(counter.incrementAndGet(),name.name)
    }
    @PostMapping("/user/registration")
    fun insert(@RequestBody user: UserModel): HashMap<String, Any> {
            return User().register(user)
    }
    @PostMapping("/user/update")
    fun update(@RequestBody user: UserModel): HashMap<String, Any> {
        return User().update(user)
    }
    @DeleteMapping("/user/delete")
    fun delete(@RequestParam(value = "uid", defaultValue = "")uid:Int): HashMap<String, Any> {
        return User().delete(uid)
    }
    @PostMapping("/user/login")
    fun login(@RequestBody credentials:LoginModel): HashMap<String, Any> {
        return User().login(credentials)
    }

}