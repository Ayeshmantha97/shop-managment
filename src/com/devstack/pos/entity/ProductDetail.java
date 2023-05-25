package com.devstack.pos.entity;

public class ProductDetail implements SuperEntity{
    private String code;
    private String barcode;
    private int QtyOnHand;
    private double sellingPrice;
    private boolean isDiscountAvailable;
    private double showPrice;
    private int productCode;
    private double buyingPrice;

    public ProductDetail() {
    }

    public ProductDetail(String code, String barcode, int qtyOnHand, double sellingPrice, boolean isDiscountAvailable, double showPrice, int productCode, double buyingPrice) {
        this.code = code;
        this.barcode = barcode;
        QtyOnHand = qtyOnHand;
        this.sellingPrice = sellingPrice;
        this.isDiscountAvailable = isDiscountAvailable;
        this.showPrice = showPrice;
        this.productCode = productCode;
        this.buyingPrice = buyingPrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        QtyOnHand = qtyOnHand;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public boolean isDiscountAvailable() {
        return isDiscountAvailable;
    }

    public void setDiscountAvailable(boolean discountAvailable) {
        isDiscountAvailable = discountAvailable;
    }

    public double getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(double showPrice) {
        this.showPrice = showPrice;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }
}
