package com.xiaosa.clouddemo.dto.product;

import java.math.BigDecimal;

public class ProductDto {

        /**
         * 商品唯一标识ID
         */

        private Long id;

        /**
         * 商品名称
         */
        private String name;

        /**
         * 商品副标题/卖点
         */
        private String subTitle;

        /**
         * 商品编码/货号(SPU)
         */
        private String productCode;

        /**
         * 所属分类ID
         */
        private Long categoryId;

        /**
         * 品牌ID
         */
        private Long brandId;

        /**
         * 商品主图URL
         */
        private String mainImageUrl;

        /**
         * 商品详细描述 (HTML)
         */
        private String description;

        /**
         * 搜索关键词
         */
        private String keywords;

        /**
         * 销售价格 (单位: 元)
         */
        private BigDecimal price;

        /**
         * 商品重量 (单位: 克)
         */
        private BigDecimal weight;

        /**
         * 商品体积 (单位: 立方厘米)
         */
        private BigDecimal volume;

        /**
         * 是否新品 (0: 否, 1: 是)
         */
        private Integer isNew;

        /**
         * 是否热销 (0: 否, 1: 是)
         */
        private Integer isHot;

        /**
         * 是否精品 (0: 否, 1: 是)
         */
        private Integer isBest;

        /**
         * 排序权重
         */
        private Integer sortOrder;

        private Integer stock;

        /**
         * 商品唯一标识ID
         */
        public Long getId() {
            return id;
        }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getIsBest() {
        return isBest;
    }

    public void setIsBest(Integer isBest) {
        this.isBest = isBest;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", productCode='" + productCode + '\'' +
                ", categoryId=" + categoryId +
                ", brandId=" + brandId +
                ", mainImageUrl='" + mainImageUrl + '\'' +
                ", description='" + description + '\'' +
                ", keywords='" + keywords + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", volume=" + volume +
                ", isNew=" + isNew +
                ", isHot=" + isHot +
                ", isBest=" + isBest +
                ", sortOrder=" + sortOrder +
                ", stock=" + stock +
                '}';
    }
}
