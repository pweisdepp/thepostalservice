package com.serverdemo.myserver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

@Service("searchServiceImpl")
public class SearchServiceImpl implements SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    ResourceLoader resourceLoader;

    Map<CountryCode, CountryFormat> countryMetadata = new HashMap<>();
    Map<String, Set<String>> enumerations = new HashMap<>();

    @Override
    public boolean hasCountry(CountryCode countryCode) {
        return countryMetadata.containsKey(countryCode);
    }

    public List<ValidationError> validate(Map<?, ?> body, CountryCode country) {
        CountryFormat format = countryMetadata.get(country);
        List<ValidationError> errors = new ArrayList<>();
        for (Object key : body.keySet()) {
            if (format.formats.containsKey(key)) {
                // TODO verify the field matches the format
                String fieldValue = (String) body.get(key);
                FieldFormat field = format.formats.get(key);
                String formatValue = field.getFormat();
                if (field.getEnumerated()) {
                    if (!enumerations.get(formatValue).contains(fieldValue)) {
                        errors.add(new ValidationError(
                                "ValidationError: " + key + ":" + fieldValue + " not in enumeration"));

                    }
                } else if (!Pattern.matches(formatValue, fieldValue)) {
                    errors.add(new ValidationError(
                            "ValidationError: " + key + ":" + fieldValue + " does not match pattern " + formatValue));
                }
            } else {
                errors.add(new ValidationError("ValidationError: " + key + " not in " + country + " formats"));
            }
        }

        return errors;
    }

    public Iterable<Address> findAddresses(Address address) {
        return addressRepository.findAddresses(address);
    }

    public Iterable<Address> findAddresses(Address address, CountryCode countryCode) {
        return addressRepository.findAddresses(address, countryCode.name());
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
            while (itr.hasNext()) { // to get the key fields
                String countryCode = itr.next();

                if (countryCode == "enumerations") {
                    JsonNode enumerationsNode = metadataJson.get(countryCode);
                    Iterator<String> enumItr = enumerationsNode.fieldNames();
                    while (enumItr.hasNext()) {
                        String enumName = enumItr.next();
                        Set<String> enumerationSet = new HashSet<>();
                        ArrayNode enumerationList = (ArrayNode) enumerationsNode.get(enumName);
                        for (int i = 0; i < enumerationList.size(); i++) {
                            enumerationSet.add(enumerationList.get(i).asText());
                        }
                        enumerations.put(enumName, enumerationSet);
                    }
                } else {
                    JsonNode countryNode = metadataJson.get(countryCode);
                    CountryFormat format = mapper.treeToValue(countryNode, CountryFormat.class);
                    countryMetadata.put(CountryCode.valueOf(countryCode), format);
                }
            }

        } catch (Exception e) {
            // TODO: handle.
            e.printStackTrace(System.err);
        }
    }
}