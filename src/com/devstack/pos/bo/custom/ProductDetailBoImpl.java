package com.devstack.pos.bo.custom;

import com.devstack.pos.dao.DaoFactory;
import com.devstack.pos.dao.custom.CustomerDao;
import com.devstack.pos.dao.custom.ProductDetailDao;
import com.devstack.pos.dto.ProductDetailDto;
import com.devstack.pos.entity.ProductDetail;
import com.devstack.pos.enums.DaoType;

import java.sql.SQLException;
import java.util.List;

public class ProductDetailBoImpl implements ProductDetailBo {
    ProductDetailDao productDetailDao =  DaoFactory.getInstance().getDao(DaoType.PRODUCT_DETAILS);
    @Override
    public boolean saveProductDetails(ProductDetailDto productDetailDto) throws SQLException, ClassNotFoundException {
        return productDetailDao.save(new ProductDetail(productDetailDto.getCode(),productDetailDto.getBarcode(),productDetailDto.getQtyOnHand(),productDetailDto.getSellingPrice()
        ,productDetailDto.isDiscountAvailable(),productDetailDto.getShowPrice(),productDetailDto.getProductCode(),productDetailDto.getBuyingPrice()));
    }

    @Override
    public boolean updateProductDetails(ProductDetailDto productDetailDto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteProductDetails(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ProductDetailDto findProductDetails(String code) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<ProductDetailDto> findAllProductDetails() throws SQLException, ClassNotFoundException {
        return null;
    }
}
