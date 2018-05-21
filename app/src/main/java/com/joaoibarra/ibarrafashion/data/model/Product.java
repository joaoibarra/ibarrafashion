package com.joaoibarra.ibarrafashion.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {
    String name;
    String color;
    String style;

    @SerializedName("code_color")
    String codeColor;

    @SerializedName("code_slug")
    String codeSlug;

    @SerializedName("on_sale")
    boolean onSale;

    @SerializedName("regular_price")
    String regularPrice;

    @SerializedName("actual_price")
    String actualPrice;

    @SerializedName("discount_percentage")
    String discountPercentage;

    String installments;

    String image;

    List<Size> sizes;

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getStyle() {
        return style;
    }

    public String getCodeColor() {
        return codeColor;
    }

    public String getCodeSlug() {
        return codeSlug;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public String getRegularPrice() {
        return regularPrice;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public String getInstallments() {
        return installments;
    }

    public String getImage() {
        return image;
    }

    public List<Size> getSizes() {
        return sizes;
    }
}
