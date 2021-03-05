package com.serverdemo.myserver;

import org.springframework.stereotype.Component;

enum CountryCode {
    US, DE, AU;
}

@Component
public interface SearchService {
    Iterable<Address> findAllAddresses();
    Iterable<Address> findAddresses(CountryCode countryCode);
}
