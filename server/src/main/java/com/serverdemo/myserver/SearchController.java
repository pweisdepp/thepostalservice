package com.serverdemo.myserver;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/greeting")
    @ResponseBody
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @GetMapping("/all")
//    @ResponseBody
//    public Iterable<Address> getAllAddresses() {
//        return searchService.findAllAddresses();
//    }
//
//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @GetMapping("/{countryCode}")
//    @ResponseBody
//    public Iterable<Address> getAddressesByCountry(@PathVariable CountryCode countryCode){
//        return searchService.findAddresses(countryCode);
//    }
}