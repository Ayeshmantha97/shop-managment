package com.devstack.pos.bo;

import com.devstack.pos.dto.CustomerDto;
import com.devstack.pos.dto.ProductDto;

import java.sql.SQLException;
import java.util.List;

public interface ProductBo  extends SuperBo{
    public boolean saveProduct(ProductDto productDto) throws SQLException, ClassNotFoundException;
    public boolean updateProduct(ProductDto productDto);
    public boolean deleteProduct(int code);
    public ProductDto findProduct(int code);
    public List<ProductDto> findAllProducts() throws SQLException, ClassNotFoundException;
}
