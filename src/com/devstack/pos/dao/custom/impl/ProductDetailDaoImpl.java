package com.devstack.pos.dao.custom.impl;

import com.devstack.pos.dao.CrudUtil;
import com.devstack.pos.dao.custom.ProductDetailDao;
import com.devstack.pos.entity.ProductDetail;

import java.sql.SQLException;
import java.util.List;

public class ProductDetailDaoImpl implements ProductDetailDao {
    @Override
    public boolean save(ProductDetail productDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO product_details VALUES (?,?,?,?,?,?,?,?)",
                productDetail.getCode(),productDetail.getBarcode(),productDetail.getQtyOnHand(),productDetail.getSellingPrice(),productDetail.isDiscountAvailable()
        ,productDetail.getShowPrice(),productDetail.getProductCode(),productDetail.getBuyingPrice());
    }

    @Override
    public boolean update(ProductDetail productDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE product_details SET ",
                productDetail.getCode(),productDetail.getBarcode(),productDetail.getQtyOnHand(),productDetail.getSellingPrice(),productDetail.isDiscountAvailable()
                ,productDetail.getShowPrice(),productDetail.getProductCode(),productDetail.getBuyingPrice());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ProductDetail find(String code) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<ProductDetail> findAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
