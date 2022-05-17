package com.membership.service;

import com.membership.domain.Location;

import java.util.List;

public interface LocationService {

    public List<Location> findAll();

    public Location findById(long id);

    public Location save(Location location);

    public Location update(long id, Location location);

    public void deleteById(long id);
}
