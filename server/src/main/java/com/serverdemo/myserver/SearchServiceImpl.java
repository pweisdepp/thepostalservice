package com.serverdemo.myserver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.*;

@Service("searchServiceImpl")
public class SearchServiceImpl implements SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    ResourceLoader resourceLoader;

    Map<CountryCode, CountryFormat> countryMetadata = new HashMap<>();

    @Override
    public boolean hasCountry(CountryCode countryCode) {
        return countryMetadata.containsKey(countryCode);
    }

    public Iterable<ValidationError> validate(Map<?, ?> body, CountryCode country) {
        CountryFormat format = countryMetadata.get(country);
        List<ValidationError> errors = new ArrayList<>();
        for (Object key : body.keySet()) {
            if (format.formats.containsKey(key)) {
                // TODO verify the field matches the format
            } else {
                errors.add(new ValidationError("ValidationError: " + key + " not in " + country + " formats"));
            }
        }

        return errors;
    }

    public Iterable<Address> findAllAddresses() {
        return addressRepository.findAll();
    }

    public Iterable<Address> findAddresses(CountryCode countryCode) {
        return addressRepository.findByCountry(countryCode.name());
    }

    @PostConstruct
    public void init() {
        LOGGER.debug("Loading metatdata...");

        try {

            Resource metadataResource = resourceLoader.getResource("classpath:" + "metadata.json");
            File file = metadataResource.getFile();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode metadataJson = mapper.readTree(file);

            // we can automate copying the resource to server with maven build in the future

            Iterator<String> itr = metadataJson.fieldNames();
            while (itr.hasNext()) {  //to get the key fields
                String countryCode = itr.next();

                JsonNode countryNode = metadataJson.get(countryCode);
                CountryFormat format = mapper.treeToValue(countryNode, CountryFormat.class);
                countryMetadata.put(CountryCode.valueOf(countryCode), format);
            }


        } catch (Exception e) {
            // TODO: handle.
        }
    }
}