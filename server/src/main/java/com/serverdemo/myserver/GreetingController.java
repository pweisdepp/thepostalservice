package com.serverdemo.myserver;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class GreetingController {
    @Autowired
    private AddressRepository addressRepository;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/all")
    public @ResponseBody Iterable<Address> getAllAddresses() {
        return addressRepository.findAll();
    }
}