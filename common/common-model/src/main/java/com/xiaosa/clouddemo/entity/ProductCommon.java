package com.xiaosa.clouddemo.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProductCommon {
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
     * 成本价格 (单位: 元)
     */
    private BigDecimal costPrice;

    /**
     * 市场价/原价 (单位: 元)
     */
    private BigDecimal marketPrice;

    /**
     * 商品库存数量
     */
    private Integer stock;

    /**
     * 商品重量 (单位: 克)
     */
    private BigDecimal weight;

    /**
     * 商品体积 (单位: 立方厘米)
     */
    private BigDecimal volume;

    /**
     * 商品状态 (0: 下架, 1: 上架)
     */
    private Integer status;

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

    /**
     * 创建人ID
     */
    private Long createdBy;

    /**
     * 最后更新人ID
     */
    private Long updatedBy;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 最后更新时间
     */
    private Date updatedAt;

    /**
     * 软删除标志 (0: 未删除, 1: 已删除)
     */
    private Integer isDeleted;

    /**
     * 软删除时间
     */
    private Date deletedAt;

    /**
     * 商品唯一标识ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 商品唯一标识ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 商品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 商品副标题/卖点
     */
    public String getSubTitle() {
        return subTitle;
    }

    /**
     * 商品副标题/卖点
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    /**
     * 商品编码/货号(SPU)
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 商品编码/货号(SPU)
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 所属分类ID
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 所属分类ID
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 品牌ID
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * 品牌ID
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * 商品主图URL
     */
    public String getMainImageUrl() {
        return mainImageUrl;
    }

    /**
     * 商品主图URL
     */
    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    /**
     * 商品详细描述 (HTML)
     */
    public String getDescription() {
        return description;
    }

    /**
     * 商品详细描述 (HTML)
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 搜索关键词
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 搜索关键词
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 销售价格 (单位: 元)
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 销售价格 (单位: 元)
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 成本价格 (单位: 元)
     */
    public BigDecimal getCostPrice() {
        return costPrice;
    }

    /**
     * 成本价格 (单位: 元)
     */
    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * 市场价/原价 (单位: 元)
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    /**
     * 市场价/原价 (单位: 元)
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * 商品库存数量
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 商品库存数量
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 商品重量 (单位: 克)
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * 商品重量 (单位: 克)
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * 商品体积 (单位: 立方厘米)
     */
    public BigDecimal getVolume() {
        return volume;
    }

    /**
     * 商品体积 (单位: 立方厘米)
     */
    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    /**
     * 商品状态 (0: 下架, 1: 上架)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 商品状态 (0: 下架, 1: 上架)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 是否新品 (0: 否, 1: 是)
     */
    public Integer getIsNew() {
        return isNew;
    }

    /**
     * 是否新品 (0: 否, 1: 是)
     */
    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    /**
     * 是否热销 (0: 否, 1: 是)
     */
    public Integer getIsHot() {
        return isHot;
    }

    /**
     * 是否热销 (0: 否, 1: 是)
     */
    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    /**
     * 是否精品 (0: 否, 1: 是)
     */
    public Integer getIsBest() {
        return isBest;
    }

    /**
     * 是否精品 (0: 否, 1: 是)
     */
    public void setIsBest(Integer isBest) {
        this.isBest = isBest;
    }

    /**
     * 排序权重
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * 排序权重
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * 创建人ID
     */
    public Long getCreatedBy() {
        return createdBy;
    }

    /**
     * 创建人ID
     */
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 最后更新人ID
     */
    public Long getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 最后更新人ID
     */
    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 创建时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 创建时间
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 最后更新时间
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 最后更新时间
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 软删除标志 (0: 未删除, 1: 已删除)
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * 软删除标志 (0: 未删除, 1: 已删除)
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 软删除时间
     */
    public Date getDeletedAt() {
        return deletedAt;
    }

    /**
     * 软删除时间
     */
    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ProductCommon other = (ProductCommon) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getSubTitle() == null ? other.getSubTitle() == null : this.getSubTitle().equals(other.getSubTitle()))
                && (this.getProductCode() == null ? other.getProductCode() == null : this.getProductCode().equals(other.getProductCode()))
                && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
                && (this.getBrandId() == null ? other.getBrandId() == null : this.getBrandId().equals(other.getBrandId()))
                && (this.getMainImageUrl() == null ? other.getMainImageUrl() == null : this.getMainImageUrl().equals(other.getMainImageUrl()))
                && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
                && (this.getKeywords() == null ? other.getKeywords() == null : this.getKeywords().equals(other.getKeywords()))
                && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
                && (this.getCostPrice() == null ? other.getCostPrice() == null : this.getCostPrice().equals(other.getCostPrice()))
                && (this.getMarketPrice() == null ? other.getMarketPrice() == null : this.getMarketPrice().equals(other.getMarketPrice()))
                && (this.getStock() == null ? other.getStock() == null : this.getStock().equals(other.getStock()))
                && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
                && (this.getVolume() == null ? other.getVolume() == null : this.getVolume().equals(other.getVolume()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getIsNew() == null ? other.getIsNew() == null : this.getIsNew().equals(other.getIsNew()))
                && (this.getIsHot() == null ? other.getIsHot() == null : this.getIsHot().equals(other.getIsHot()))
                && (this.getIsBest() == null ? other.getIsBest() == null : this.getIsBest().equals(other.getIsBest()))
                && (this.getSortOrder() == null ? other.getSortOrder() == null : this.getSortOrder().equals(other.getSortOrder()))
                && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
                && (this.getUpdatedBy() == null ? other.getUpdatedBy() == null : this.getUpdatedBy().equals(other.getUpdatedBy()))
                && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
                && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
                && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
                && (this.getDeletedAt() == null ? other.getDeletedAt() == null : this.getDeletedAt().equals(other.getDeletedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSubTitle() == null) ? 0 : getSubTitle().hashCode());
        result = prime * result + ((getProductCode() == null) ? 0 : getProductCode().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
        result = prime * result + ((getMainImageUrl() == null) ? 0 : getMainImageUrl().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getKeywords() == null) ? 0 : getKeywords().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getCostPrice() == null) ? 0 : getCostPrice().hashCode());
        result = prime * result + ((getMarketPrice() == null) ? 0 : getMarketPrice().hashCode());
        result = prime * result + ((getStock() == null) ? 0 : getStock().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getVolume() == null) ? 0 : getVolume().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getIsNew() == null) ? 0 : getIsNew().hashCode());
        result = prime * result + ((getIsHot() == null) ? 0 : getIsHot().hashCode());
        result = prime * result + ((getIsBest() == null) ? 0 : getIsBest().hashCode());
        result = prime * result + ((getSortOrder() == null) ? 0 : getSortOrder().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        result = prime * result + ((getUpdatedBy() == null) ? 0 : getUpdatedBy().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getDeletedAt() == null) ? 0 : getDeletedAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", subTitle=").append(subTitle);
        sb.append(", productCode=").append(productCode);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", brandId=").append(brandId);
        sb.append(", mainImageUrl=").append(mainImageUrl);
        sb.append(", description=").append(description);
        sb.append(", keywords=").append(keywords);
        sb.append(", price=").append(price);
        sb.append(", costPrice=").append(costPrice);
        sb.append(", marketPrice=").append(marketPrice);
        sb.append(", stock=").append(stock);
        sb.append(", weight=").append(weight);
        sb.append(", volume=").append(volume);
        sb.append(", status=").append(status);
        sb.append(", isNew=").append(isNew);
        sb.append(", isHot=").append(isHot);
        sb.append(", isBest=").append(isBest);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", updatedBy=").append(updatedBy);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", deletedAt=").append(deletedAt);
        sb.append("]");
        return sb.toString();
    }
}