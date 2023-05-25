package com.devstack.pos.dao.custom;

import com.devstack.pos.dao.CrudDao;
import com.devstack.pos.entity.Product;
import com.devstack.pos.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao extends CrudDao<Product,Integer> {
    public  boolean save(Product product) throws SQLException, ClassNotFoundException;
    public  boolean update(Product product) throws SQLException, ClassNotFoundException;
    public  boolean delete(Integer code) throws SQLException, ClassNotFoundException;

    public  Product find(Integer code) throws SQLException, ClassNotFoundException;
    public List<Product> findAll() throws SQLException, ClassNotFoundException;
    public  int productIDGenerator() throws SQLException, ClassNotFoundException;
}
