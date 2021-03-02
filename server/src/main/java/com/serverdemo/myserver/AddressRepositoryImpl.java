package com.serverdemo.myserver;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("addressRepositoryImpl")
public class AddressRepositoryImpl implements AddressRepository {
    @Override
    public <S extends Address> S save(S s) {
        return null;
    }

    @Override
    public <S extends Address> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Address> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Address> findAll() {
        return null;
    }

    @Override
    public Iterable<Address> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Address address) {

    }

    @Override
    public void deleteAll(Iterable<? extends Address> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
