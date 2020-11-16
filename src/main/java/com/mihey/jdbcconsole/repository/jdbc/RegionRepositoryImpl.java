package com.mihey.jdbcconsole.repository.jdbc;

import com.mihey.jdbcconsole.model.Region;
import com.mihey.jdbcconsole.repository.RegionRepository;
import com.mihey.jdbcconsole.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RegionRepositoryImpl implements RegionRepository {


    @Override
    public List<Region> getAll() {
        return null;
    }

    @Override
    public Region getById(Integer integer) {
        return null;
    }

    @Override
    public Region save(Region region) {
//        dbUtil.setConnection();
        String reg = "INSERT IGNORE INTO Region(name) VALUES ('" + region.getName() + "');";
        DBUtil.executeStatement(reg);
        String regionId = "SELECT id FROM Region WHERE region='" + region.getName() + "';";
        ResultSet resultSet = DBUtil.retrieveData(regionId);
        int id = 0;
        try {
            resultSet.next();
            id = resultSet.getInt("id");
        } catch (SQLException e) {
            System.out.println("something wrong");
            e.printStackTrace();
        }
//        dbUtil.closeConnection();
        region.setId(id);
        return region;
    }

    @Override
    public Region update(Region region) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
