package com.mihey.jdbcconsole.repository.jdbc;

import com.mihey.jdbcconsole.model.Region;
import com.mihey.jdbcconsole.repository.RegionRepository;
import com.mihey.jdbcconsole.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegionRepositoryImpl implements RegionRepository {

    private final Connection connection = DBUtil.getConnection();

    @Override
    public List<Region> getAll() {
        List<Region> regions = new ArrayList<>();
        String selectAll = "SELECT * FROM Regions;";
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(selectAll);
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
        String selectAll = "SELECT * FROM Regions WHERE id=" + id + ";";
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(selectAll);
            if (resultSet.next()) {
                regionId=resultSet.getInt("id");
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            System.out.println("something wrong");
            e.printStackTrace();
            return null;
        }
        return new Region(regionId,name);
    }

    @Override
    public Region save(Region region) {
        String reg = "INSERT IGNORE INTO Regions(name) VALUES ('" + region.getName() + "');";
        String regionId = "SELECT id FROM Regions WHERE name='" + region.getName() + "';";
        int id = 0;
        try {
            connection.createStatement().executeUpdate(reg);
            ResultSet resultSet=connection.createStatement().executeQuery(regionId);
            if(resultSet.next()){
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
        String update = "UPDATE Regions SET name = '" +
                region.getName() + ";";
        try {
            connection.createStatement().executeUpdate(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return region;
    }

    @Override
    public void deleteById(Integer id) {
        String delete = "DELETE FROM Regions WHERE id=" + id + ";";
        try {
            connection.createStatement().executeUpdate(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
