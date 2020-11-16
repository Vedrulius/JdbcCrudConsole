package com.mihey.jdbcconsole.controller;

import com.mihey.jdbcconsole.model.Region;
import com.mihey.jdbcconsole.repository.RegionRepository;
import com.mihey.jdbcconsole.repository.jdbc.RegionRepositoryImpl;

public class RegionController {

    private final RegionRepository regionRepository = new RegionRepositoryImpl();


    public Region save(Region region) {
        return regionRepository.save(region);
    }
}
