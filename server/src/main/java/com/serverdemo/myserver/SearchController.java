package com.serverdemo.myserver;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        Iterable<Address> addresses = searchService.findAddresses(CountryCode.US);
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/all")
    @ResponseBody
    public Iterable<Address> getAllAddresses() {
        return searchService.findAllAddresses();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/{countryCode}")
    @ResponseBody
    public ResponseEntity<?> getAddressesByCountry(@PathVariable CountryCode countryCode,
            @RequestBody String requestBodyString) throws Exception {
        if (!searchService.hasCountry(countryCode)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Country " + countryCode + " has no known formats.\n");
        }
        Iterable<ValidationError> errors = searchService.validate(parseBody(requestBodyString), countryCode);
        if (errors.iterator().hasNext()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        return ResponseEntity.status(HttpStatus.OK).body(searchService.findAddresses(countryCode));
    }

    private Map<?, ?> parseBody(String body) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(body, Map.class);
    }
}