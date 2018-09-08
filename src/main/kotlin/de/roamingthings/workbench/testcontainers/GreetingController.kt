package de.roamingthings.workbench.testcontainers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class GreetingController {
    @GetMapping("/")
    fun showRootView(model: Model): String {
        model.addAttribute("greeting", GreetingCommand())
        return "/welcome"
    }

    @PostMapping("/")
    fun sayHello(@ModelAttribute greeting: GreetingCommand, model: Model): String {
        model.addAttribute("name", greeting.name)
        return "/greeting"
    }
}
