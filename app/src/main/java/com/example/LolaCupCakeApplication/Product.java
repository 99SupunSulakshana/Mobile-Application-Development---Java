package com.example.LolaCupCakeApplication;

public class Product {

    private int prdId;
    private String pName;
    private String pPrice;
    private String pCategory;
    private String desc;
    private byte[] img;

    public void setPrdId(int prdId) {
        this.prdId = prdId;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;

    }

    public void setpCategory(String pCategory) {
        this.pCategory = pCategory;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public int getPrdId() {
        return prdId;
    }

    public String getpName() {
        return pName;
    }

    public String getpPrice() {
        return pPrice;
    }

    public String getpCategory() {
        return pCategory;
    }

    public String getDesc() {
        return desc;
    }

    public byte[] getImg() {
        return img;
    }

}
