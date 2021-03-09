package com.serverdemo.myserver;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Component
public interface AddressRepository extends CrudRepository<Address, Integer> {
    @Query("select id, country from addresses a where a.country=:countryCode")
    public Iterable<Address> findByCountry(@Param("countryCode") String countryCode);

    @Query(value="select id, number, street, unit, city, district, post_code, country from addresses a " +
            "where a.number LIKE CONCAT('%', :#{#address.number}, '%')" +
            "and a.street LIKE CONCAT('%', :#{#address.street}, '%')" +
            "and (a.unit is null or a.unit = '' or :#{#address.unit} is null or :#{#address.unit} = '' or a.unit LIKE CONCAT('%', :#{#address.unit}, '%')) " +
            "and (a.city is null or a.city = '' or :#{#address.city} is null or :#{#address.city} = '' or a.city LIKE CONCAT('%', :#{#address.city}, '%')) " +
            "and (a.district is null or a.district = '' or :#{#address.district} is null or :#{#address.district} = '' or a.district LIKE CONCAT('%', :#{#address.district}, '%')) " +
            "and (a.post_code is null or a.post_code = '' or :#{#address.post_code} is null or :#{#address.post_code} = '' or a.post_code LIKE CONCAT('%', :#{#address.post_code}, '%')) " +
            "LIMIT 10", nativeQuery=true)
    public Iterable<Address> findAddresses(@Param("address") Address address);

    @Query(value="select id, number, street, unit, city, district, post_code, country from addresses a " +
            "where a.number LIKE CONCAT('%', :#{#address.number}, '%')" +
            "and a.street LIKE CONCAT('%', :#{#address.street}, '%')" +
            "and (a.unit is null or a.unit = '' or :#{#address.unit} is null or :#{#address.unit} = '' or a.unit LIKE CONCAT('%', :#{#address.unit}, '%')) " +
            "and (a.city is null or a.city = '' or :#{#address.city} is null or :#{#address.city} = '' or a.city LIKE CONCAT('%', :#{#address.city}, '%')) " +
            "and (a.district is null or a.district = '' or :#{#address.district} is null or :#{#address.district} = '' or a.district LIKE CONCAT('%', :#{#address.district}, '%')) " +
            "and (a.post_code is null or a.post_code = '' or :#{#address.post_code} is null or :#{#address.post_code} = '' or a.post_code LIKE CONCAT('%', :#{#address.post_code}, '%')) " +
            "and a.country=:countryCode LIMIT 10", nativeQuery=true)
    public Iterable<Address> findAddresses(@Param("address") Address address,
                                                  @Param("countryCode") String countryCode);
}