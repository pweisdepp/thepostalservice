package com.serverdemo.myserver;

import java.util.List;
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
    private final ObjectMapper mapper = new ObjectMapper();

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/greeting")
    @ResponseBody
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/all")
    @ResponseBody
    public ResponseEntity<?> getAllAddresses(@RequestBody String requestBodyString) throws Exception {

        List<ValidationError> errors = searchService.validate(parseBody(requestBodyString), CountryCode.DEFAULT);
        if (errors.size() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        Address address = parseAddress(requestBodyString);
        return ResponseEntity.status(HttpStatus.OK).body(searchService.findAddresses(address));
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
        List<ValidationError> errors = searchService.validate(parseBody(requestBodyString), countryCode);
        if (errors.size() > 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(errors));
        }

        Address address = parseAddress(requestBodyString);
        return ResponseEntity.status(HttpStatus.OK).body(searchService.findAddresses(address, countryCode));
    }

    private Map<?, ?> parseBody(String body) throws Exception {
        return mapper.readValue(body, Map.class);
    }

    private Address parseAddress(String body) throws Exception {
        return mapper.readValue(body, Address.class);
    }
}