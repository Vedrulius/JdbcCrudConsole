package com.mihey.jdbcconsole.controller;

import com.mihey.jdbcconsole.model.Post;
import com.mihey.jdbcconsole.model.Region;
import com.mihey.jdbcconsole.repository.RegionRepository;
import com.mihey.jdbcconsole.repository.jdbc.RegionRepositoryImpl;

import java.util.List;

public class RegionController {

    private final RegionRepository regionRepository = new RegionRepositoryImpl();

    public Region saveRegion(Region region) {
        return regionRepository.save(region);
    }

    public Region getRegionById(int id) {
        return regionRepository.getById(id);
    }

    public Region editRegion(Region region) {
        return regionRepository.update(region);
    }

    public void deleteRegionById(int id) {
        regionRepository.deleteById(id);
    }

    public List<Region> getAllRegions() {
        return regionRepository.getAll();
    }
}
