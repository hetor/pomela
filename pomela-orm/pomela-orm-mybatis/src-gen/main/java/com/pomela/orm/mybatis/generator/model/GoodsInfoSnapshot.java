package com.pomela.orm.mybatis.generator.model;

import java.math.BigDecimal;
import java.util.Date;

public class GoodsInfoSnapshot {
    /**
     * 自动分配的id, 主键
     * 所属表字段goods_info_snapshot.id
     */
    private Long id;

    /**
     * 订单id, 对应Order表中的out_id
     * 所属表字段goods_info_snapshot.order_id
     */
    private String orderId;

    /**
     * sku Id
     * 所属表字段goods_info_snapshot.sku_id
     */
    private String skuId;

    /**
     * 商品Id
     * 所属表字段goods_info_snapshot.goods_id
     */
    private Long goodsId;

    /**
     * 商品名称
     * 所属表字段goods_info_snapshot.goods_name
     */
    private String goodsName;

    /**
     * 行邮税号
     * 所属表字段goods_info_snapshot.code_ts
     */
    private String codeTs;

    /**
     * 原产地
     * 所属表字段goods_info_snapshot.origin_country
     */
    private String originCountry;

    /**
     * 申报单位
     * 所属表字段goods_info_snapshot.declare_goods_unit
     */
    private String declareGoodsUnit;

    /**
     * 商品规格、型号
     * 所属表字段goods_info_snapshot.goods_model
     */
    private String goodsModel;

    /**
     * 商品货号，保税模式，由仓储企业申请，货号需与电子账册一致（一个sku一个）；直邮模式，直接使用商品id
     * 所属表字段goods_info_snapshot.goods_item_no
     */
    private String goodsItemNo;

    /**
     * 产品国检备案编号，在电子口岸平台备案后取得，一个sku一个
     * 所属表字段goods_info_snapshot.product_record_no
     */
    private String productRecordNo;

    /**
     * 商品在订单中的顺序，用于向电子海关推送订单和申报单时保持商品的顺序一致，从1开始不超过50
     * 所属表字段goods_info_snapshot.goods_order
     */
    private Integer goodsOrder;

    /**
     * 商品原始单价
     * 所属表字段goods_info_snapshot.unit_price
     */
    private BigDecimal unitPrice;

    /**
     * 申报商品数量
     * 所属表字段goods_info_snapshot.declare_goods_count
     */
    private BigDecimal declareGoodsCount;

    /**
     * 第一单位
     * 所属表字段goods_info_snapshot.first_unit
     */
    private String firstUnit;

    /**
     * 第一数量
     * 所属表字段goods_info_snapshot.first_count
     */
    private BigDecimal firstCount;

    /**
     * 数量
     * 所属表字段goods_info_snapshot.count
     */
    private Integer count;

    /**
     * 商品净重
     * 所属表字段goods_info_snapshot.weight
     */
    private Float weight;

    /**
     * 第二单位
     * 所属表字段goods_info_snapshot.second_unit
     */
    private String secondUnit;

    /**
     * 第二数量
     * 所属表字段goods_info_snapshot.second_count
     */
    private BigDecimal secondCount;

    /**
     * 更新时间
     * 所属表字段goods_info_snapshot.update_time
     */
    private Long updateTime;

    /**
     * 数据仓库定时导出依据字段
     * 所属表字段goods_info_snapshot.db_update_time
     */
    private Date dbUpdateTime;

    /**
     * 商品类型，0-正常，1-赠品
     * 所属表字段goods_info_snapshot.goods_type
     */
    private Integer goodsType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId == null ? null : skuId.trim();
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getCodeTs() {
        return codeTs;
    }

    public void setCodeTs(String codeTs) {
        this.codeTs = codeTs == null ? null : codeTs.trim();
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry == null ? null : originCountry.trim();
    }

    public String getDeclareGoodsUnit() {
        return declareGoodsUnit;
    }

    public void setDeclareGoodsUnit(String declareGoodsUnit) {
        this.declareGoodsUnit = declareGoodsUnit == null ? null : declareGoodsUnit.trim();
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel == null ? null : goodsModel.trim();
    }

    public String getGoodsItemNo() {
        return goodsItemNo;
    }

    public void setGoodsItemNo(String goodsItemNo) {
        this.goodsItemNo = goodsItemNo == null ? null : goodsItemNo.trim();
    }

    public String getProductRecordNo() {
        return productRecordNo;
    }

    public void setProductRecordNo(String productRecordNo) {
        this.productRecordNo = productRecordNo == null ? null : productRecordNo.trim();
    }

    public Integer getGoodsOrder() {
        return goodsOrder;
    }

    public void setGoodsOrder(Integer goodsOrder) {
        this.goodsOrder = goodsOrder;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getDeclareGoodsCount() {
        return declareGoodsCount;
    }

    public void setDeclareGoodsCount(BigDecimal declareGoodsCount) {
        this.declareGoodsCount = declareGoodsCount;
    }

    public String getFirstUnit() {
        return firstUnit;
    }

    public void setFirstUnit(String firstUnit) {
        this.firstUnit = firstUnit == null ? null : firstUnit.trim();
    }

    public BigDecimal getFirstCount() {
        return firstCount;
    }

    public void setFirstCount(BigDecimal firstCount) {
        this.firstCount = firstCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getSecondUnit() {
        return secondUnit;
    }

    public void setSecondUnit(String secondUnit) {
        this.secondUnit = secondUnit == null ? null : secondUnit.trim();
    }

    public BigDecimal getSecondCount() {
        return secondCount;
    }

    public void setSecondCount(BigDecimal secondCount) {
        this.secondCount = secondCount;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDbUpdateTime() {
        return dbUpdateTime;
    }

    public void setDbUpdateTime(Date dbUpdateTime) {
        this.dbUpdateTime = dbUpdateTime;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }
}