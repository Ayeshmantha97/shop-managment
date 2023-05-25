package com.devstack.pos.dao.custom;

import com.devstack.pos.dao.CrudDao;
import com.devstack.pos.entity.Product;
import com.devstack.pos.entity.ProductDetail;

import java.sql.SQLException;
import java.util.List;

public interface ProductDetailDao extends CrudDao<ProductDetail,String> {
    public  boolean save(ProductDetail productDetail) throws SQLException, ClassNotFoundException;
    public  boolean update(ProductDetail productDetail) throws SQLException, ClassNotFoundException;
    public  boolean delete(String code) throws SQLException, ClassNotFoundException;

    public  ProductDetail find(String code) throws SQLException, ClassNotFoundException;
    public List<ProductDetail> findAll() throws SQLException, ClassNotFoundException;
}
