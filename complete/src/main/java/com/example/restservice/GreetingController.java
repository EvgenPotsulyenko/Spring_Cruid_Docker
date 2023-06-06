package com.example.restservice;

//import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import com.example.restservice.Models.Post;
import com.example.restservice.Repository.PostRepository;

//import ch.qos.logback.core.model.Model;

@Controller
public class GreetingController {

	// private static final String template = "Hello, %s!";
	// private final AtomicLong counter = new AtomicLong();

	// @GetMapping("/greeting")
	// public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
	// 	return new Greeting(counter.incrementAndGet(), String.format(template, name));
	// }

	@Autowired
    private PostRepository postrep;

    @GetMapping("/")
    public String blogMain(Model model)
    {
        Iterable<Post> posts = postrep.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String add(Model model)
    {
        return "blog-add";
    }
    @PostMapping("/blog/add")
    public String postadd(@RequestParam String text,Model model)
    {
        Post pst = new Post(text);
        postrep.save(pst);
        return "redirect:/";
    }

    @GetMapping("/blog/delete")
    public String delete(Model model)
    {
        return "blog-delete";
    }
    @PostMapping("/blog/delete")
    public String postdelete(@RequestParam String text,Model model)
    {
        Iterable<Post> posts  = postrep.findAll();
        for (Post post : posts) 
        {    
            if(post.getText() == text)
            {
                postrep.delete(post);
            } 
        }       
        return "redirect:/";
    }
}
