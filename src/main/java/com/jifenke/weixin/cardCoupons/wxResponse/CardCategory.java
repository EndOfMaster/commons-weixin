package com.jifenke.weixin.cardCoupons.wxResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jifenke.weixin.support.WxResponse;

import java.util.List;

/**
 * @author ZM.Wang
 */
public class CardCategory extends WxResponse {

    @JsonProperty("category")
    private List<Category> categories;

    public static class Category {
        @JsonProperty("primary_category_id")
        String primaryCategoryId;

        @JsonProperty("category_name")
        String categoryName;

        @JsonProperty("secondary_category")
        List<SecondaryCategory> secondaryCategories;

        public String getPrimaryCategoryId() {
            return primaryCategoryId;
        }

        public void setPrimaryCategoryId(String primaryCategoryId) {
            this.primaryCategoryId = primaryCategoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public List<SecondaryCategory> getSecondaryCategories() {
            return secondaryCategories;
        }

        public void setSecondaryCategories(List<SecondaryCategory> secondaryCategories) {
            this.secondaryCategories = secondaryCategories;
        }
    }

    public static class SecondaryCategory {
        @JsonProperty("secondary_category_id")
        String secondaryCategoryId;

        @JsonProperty("category_name")
        String categoryName;

        public String getSecondaryCategoryId() {
            return secondaryCategoryId;
        }

        public void setSecondaryCategoryId(String secondaryCategoryId) {
            this.secondaryCategoryId = secondaryCategoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
