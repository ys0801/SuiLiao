package com.example.orangesale_05.entity;

import java.math.BigDecimal;

public class Product {

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getImageUrlId() {
        return imageUrlId;
    }

    public void setImageUrlId(Integer imageUrlId) {
        this.imageUrlId = imageUrlId;
    }

    private Integer imageUrlId;
    private String productName;
    private BigDecimal productPrice;

    @Override
    public String toString() {
        return "Product{" +
                "imageUrlId=" + imageUrlId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
