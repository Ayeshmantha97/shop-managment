package com.devstack.pos.bo.custom.impl;

import com.devstack.pos.bo.ProductBo;
import com.devstack.pos.dao.DaoFactory;
import com.devstack.pos.dao.custom.ProductDao;
import com.devstack.pos.dto.CustomerDto;
import com.devstack.pos.dto.ProductDto;
import com.devstack.pos.entity.Product;
import com.devstack.pos.enums.DaoType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ProductBoImpl implements ProductBo {
    ProductDao productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);

    @Override
    public boolean saveProduct(ProductDto productDto) throws SQLException, ClassNotFoundException {
        return productDao.save(new Product(productDto.getCode(), productDto.getDescription()));
    }

    @Override
    public boolean updateProduct(ProductDto productDto) {
        return false;
    }

    @Override
    public boolean deleteProduct(int code) {
        return false;
    }

    @Override
    public ProductDto findProduct(int code) {
        return null;
    }

    @Override
    public List<ProductDto> findAllProducts() throws SQLException, ClassNotFoundException {
        List<ProductDto> pd = new ArrayList<>();
        for (Product p:productDao.findAll()
             ) {
            pd.add(new ProductDto(p.getCode(),p.getDescription()));

        }
        return pd;
    }

    public int productIDGenerator() throws SQLException, ClassNotFoundException {
        return productDao.productIDGenerator();
    }
}
