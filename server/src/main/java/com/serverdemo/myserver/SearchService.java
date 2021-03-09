package com.serverdemo.myserver;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

enum CountryCode {
    DEFAULT, US, DE, AU;
}

@Component
public interface SearchService {
    boolean hasCountry(String countryCode);

    Iterable<Address> findAddresses(Address address);

    Iterable<Address> findAddresses(Address address, CountryCode countryCode);

    List<ValidationError> validate(Map<?, ?> body, CountryCode countryCode);
}
