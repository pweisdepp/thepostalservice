package com.serverdemo.myserver;

import java.util.Map;

import org.springframework.stereotype.Component;

enum CountryCode {
    DEFAULT, US, DE, AU;
}

@Component
public interface SearchService {
    boolean hasCountry(CountryCode countryCode);

    Iterable<Address> findAddresses(Address address);

    Iterable<Address> findAddresses(Address address, CountryCode countryCode);

    Iterable<ValidationError> validate(Map<?, ?> body, CountryCode countryCode);
}
