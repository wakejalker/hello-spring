package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("hello")
public class HelloController {

    // Handle request at path http://localhost:8080/hello
    @GetMapping
    public String hello(){
        return "Hello, Spring!";
    }

    // Handle request at path http://localhost:8080/hello/goodbye
    @GetMapping("goodbye")
    public String goodbye(){
        return "Goodbye, Spring!";
    }

    // Handle requests of the form http://localhost:8080/hello/greet?name=LaunchCode
    @RequestMapping(value = "greet", method = {RequestMethod.GET, RequestMethod.POST})
    public String helloWithQueryParam(@RequestParam(required = false) String name){
        if (name == null || name.isEmpty()) {
            return "Hello, Spring!";
        }
        return "Hello, " + name + "!";
    }

    // Handle requests of the form http://localhost:8080/hello/LaunchCode
    @GetMapping("{name}")
    public String helloWithPathParam(@PathVariable String name){
        return "Hello, " + name + "!";
    }

    // Handle requests for http://localhost:8080/hello/form
    @GetMapping("form")
    public String helloForm() {
        return "<html>" +
                "<body>" +
                "<form action='/hello' method='post'>" + // submit a request to /hello
                "<label for='name'>Name:</label>" +
                "<input type='text' name='name'>" +
                "<label for='language'>Language:</label>" +
                "<select name='language'>" +
                "<option value='english'>English</option>" +
                "<option value='french'>French</option>" +
                "<option value='spanish'>Spanish</option>" +
                "<option value='german'>German</option>" +
                "<option value='chinese'>Chinese</option>" +
                "</select>" +
                "<input type='submit' value='Greet Me!'>" +
                "</form>" +
                "</body>" +
                "</html>";
    }

    // Handle the form submission
    @PostMapping
    public String helloWithForm(@RequestParam String name, @RequestParam String language) {
        String greeting = createGreeting(name, language);
        return greeting;
    }

    // Method to create greeting based on language
    private String createGreeting(String name, String language) {
        String greeting;
        switch (language.toLowerCase()) {
            case "french":
                greeting = "Bonjour, " + name + "!";
                break;
            case "spanish":
                greeting = "Hola, " + name + "!";
                break;
            case "german":
                greeting = "Hallo, " + name + "!";
                break;
            case "chinese":
                greeting = "Nǐ hǎo, " + name + "!";
                break;
            default:
                greeting = "Hello, " + name + "!";
                break;
        }
        return greeting;
    }
}
