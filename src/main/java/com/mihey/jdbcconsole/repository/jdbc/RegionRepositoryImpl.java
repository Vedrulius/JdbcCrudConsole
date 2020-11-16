package com.mihey.jdbcconsole.repository.jdbc;

import com.mihey.jdbcconsole.model.Region;
import com.mihey.jdbcconsole.repository.RegionRepository;
import com.mihey.jdbcconsole.util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegionRepositoryImpl implements RegionRepository {


    @Override
    public List<Region> getAll() {
        List<Region> regions = new ArrayList<>();
        String selectAll = "SELECT * FROM Region;";
        ResultSet resultSet = DBUtil.retrieveData(selectAll);
        try {
            while (resultSet.next()) {
                regions.add(new Region(resultSet.getInt("id"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println("something wrong");
            e.printStackTrace();
        }
        return regions;
    }

    @Override
    public Region getById(Integer id) {
        int regionId=0;
        String name="";
        String selectAll = "SELECT * FROM Region WHERE id=" + id + ";";
        ResultSet resultSet = DBUtil.retrieveData(selectAll);
        try {
            while (resultSet.next()) {
                regionId=resultSet.getInt("id");
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            System.out.println("something wrong");
            e.printStackTrace();
        }
        return new Region(regionId,name);
    }

    @Override
    public Region save(Region region) {
        String reg = "INSERT IGNORE INTO Region(name) VALUES ('" + region.getName() + "');";
        DBUtil.executeStatement(reg);
        String regionId = "SELECT id FROM Region WHERE name='" + region.getName() + "';";
        ResultSet resultSet = DBUtil.retrieveData(regionId);
        int id = 0;
        try {
            while(resultSet.next()){
            id = resultSet.getInt("id");}
        } catch (SQLException e) {
            System.out.println("something wrong");
            e.printStackTrace();
        }
        region.setId(id);
        return region;
    }

    @Override
    public Region update(Region region) {
        String update = "UPDATE Region SET name = '" +
                region.getName() + ";";
        DBUtil.executeStatement(update);

        return getById(region.getId());
    }

    @Override
    public void deleteById(Integer id) {
        String delete = "DELETE FROM Region WHERE id=" + id + ";";
        DBUtil.executeStatement(delete);
    }
}
