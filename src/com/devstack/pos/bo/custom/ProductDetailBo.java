package com.devstack.pos.bo.custom;

import com.devstack.pos.dto.CustomerDto;
import com.devstack.pos.dto.ProductDetailDto;

import java.sql.SQLException;
import java.util.List;

public interface ProductDetailBo {
    public boolean saveProductDetails(ProductDetailDto productDetailDto) throws SQLException, ClassNotFoundException;
    public boolean updateProductDetails(ProductDetailDto productDetailDto) throws SQLException, ClassNotFoundException;
    public boolean deleteProductDetails(String code) throws SQLException, ClassNotFoundException;
    public ProductDetailDto findProductDetails(String code) throws SQLException, ClassNotFoundException;
    public List<ProductDetailDto> findAllProductDetails() throws SQLException, ClassNotFoundException;
}
