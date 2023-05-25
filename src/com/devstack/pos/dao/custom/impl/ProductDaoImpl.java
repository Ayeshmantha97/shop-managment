package com.devstack.pos.dao.custom.impl;

import com.devstack.pos.dao.CrudUtil;
import com.devstack.pos.dao.custom.ProductDao;
import com.devstack.pos.db.DbConnection;
import com.devstack.pos.entity.Customer;
import com.devstack.pos.entity.Product;
import com.devstack.pos.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {


    @Override
    public boolean save(Product product) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO product VALUES (?,?)",product.getCode(),product.getDescription());
    }

    @Override
    public boolean update(Product product) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute( "UPDATE user SET description=? WHERE code = ?",product.getDescription(),product.getCode());
    }

    @Override
    public boolean delete(Integer code) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM product WHERE code=?",code);
    }


    @Override
    public List<Product> findAll() throws SQLException, ClassNotFoundException {
        List<Product> dtos = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM product ");
        while (resultSet.next()) {
            dtos.add(new Product(
                    resultSet.getInt(1),
                    resultSet.getString(2)

            ));
        }
        return dtos;
    }

    public int productIDGenerator() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT code FROM product ORDER BY code DESC LIMIT 1;");
        if (resultSet.next()) {
            return resultSet.getInt(1) + 1;
        }
        return 1;
    }

    @Override
    public Product find(Integer code) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM product WHERE code=?",code);
        if (resultSet.next()) {
            return new Product(
                    resultSet.getInt(1), resultSet.getString(2)
            );
        }
        return null;
    }
}
