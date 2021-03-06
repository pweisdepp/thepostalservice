package com.serverdemo.myserver;

import java.util.Map;

import org.springframework.stereotype.Component;

enum CountryCode {
    US, DE, AU;
}

@Component
public interface SearchService {
    boolean hasCountry(CountryCode countryCode);

    Iterable<Address> findAllAddresses();

    Iterable<Address> findAddresses(CountryCode countryCode);

    Iterable<ValidationError> validate(Map<?, ?> body, CountryCode countryCode);
}
