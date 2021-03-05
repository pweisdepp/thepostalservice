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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service("searchServiceImpl")
public class SearchServiceImpl implements SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Autowired
    ResourceLoader resourceLoader;

    Map countryMetadata = new HashMap<CountryCode, CountryFormat>();

    @PostConstruct
    public void init() {
        LOGGER.debug("Loading metatdata...");

        try {

            Resource metadataResource = resourceLoader.getResource("classpath:" + "metadata.json");
            File file = metadataResource.getFile();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode metadataJson = mapper.readTree(file);

            // TODO: load metadata from json file into "countryMetadata";
            //  we can automate copying the resource to server with maven build.

            Iterator<String> itr = metadataJson.fieldNames();
            while (itr.hasNext()) {  //to get the key fields
                String countryCode = itr.next();

                JsonNode countryNode = metadataJson.get(countryCode);
                CountryFormat format = mapper.treeToValue(countryNode, CountryFormat.class);
                countryMetadata.put(countryCode, format);
            }

        } catch (Exception e) {
            // TODO: handle.
        }
    }
}