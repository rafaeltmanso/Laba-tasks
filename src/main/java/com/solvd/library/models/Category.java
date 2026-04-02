package com.solvd.library.models;

public class Category {
    private String categoryName;
    private String shelfCode;

    public Category(String categoryName, String shelfCode) {
        this.categoryName = categoryName;
        this.shelfCode = shelfCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getShelfCode() {
        return shelfCode;
    }

    public void setShelfCode(String shelfCode) {
        this.shelfCode = shelfCode;
    }
}
