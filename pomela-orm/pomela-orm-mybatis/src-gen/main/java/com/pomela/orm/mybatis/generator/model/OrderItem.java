package com.pomela.orm.mybatis.generator.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderItem {
    /**
     * 内部id
     * 所属表字段order_item.id
     */
    private Long id;

    /**
     * 用户id
     * 所属表字段order_item.user_id
     */
    private Long userId;

    /**
     * 创建时间
     * 所属表字段order_item.create_time
     */
    private Long createTime;

    /**
     * 更新时间戳
     * 所属表字段order_item.update_time
     */
    private Long updateTime;

    /**
     * 原始商品单价
     * 所属表字段order_item.origin_goods_price
     */
    private BigDecimal originGoodsPrice;

    /**
     * 原始商品总价
     * 所属表字段order_item.origin_goods_total_amount
     */
    private BigDecimal originGoodsTotalAmount;

    /**
     * 原始应支付金额
     * 所属表字段order_item.origin_pay_total_amount
     */
    private BigDecimal originPayTotalAmount;

    /**
     * 实际应支付金额
     * 所属表字段order_item.pay_total_amount
     */
    private BigDecimal payTotalAmount;

    /**
     * 关税金额
     * 所属表字段order_item.tax_amount
     */
    private BigDecimal taxAmount;

    /**
     * 使用优惠券金额
     * 所属表字段order_item.coupon_amount
     */
    private BigDecimal couponAmount;

    /**
     * 各项服务管理费、研发费用。分账使用、不计入支付费用
     * 所属表字段order_item.other_service_fee
     */
    private BigDecimal otherServiceFee;

    /**
     * 订单id
     * 所属表字段order_item.order_id
     */
    private Long orderId;

    /**
     * 商品快照id。物流模块的商品快照表
     * 所属表字段order_item.goods_snapshot_id
     */
    private Long goodsSnapshotId;

    /**
     * 美美商品id
     * 所属表字段order_item.mm_prct_id
     */
    private Long mmPrctId;

    /**
     * 商品数量
     * 所属表字段order_item.goods_count
     */
    private Integer goodsCount;

    /**
     * 库存释放状态（0初始化1已锁库存 2已释放库存）
     * 所属表字段order_item.store_release_state
     */
    private Integer storeReleaseState;

    /**
     * 活动id
     * 所属表字段order_item.activity_id
     */
    private Long activityId;

    /**
     * 是否可以退货：0否1是
     * 所属表字段order_item.refund_enable
     */
    private Integer refundEnable;

    /**
     * 最晚退货时间
     * 所属表字段order_item.refund_expire_time
     */
    private Long refundExpireTime;

    /**
     * 对外id
     * 所属表字段order_item.out_id
     */
    private String outId;

    /**
     * 成本价金额
     * 所属表字段order_item.cost_amount
     */
    private BigDecimal costAmount;

    /**
     * sku id
     * 所属表字段order_item.sku_id
     */
    private String skuId;

    /**
     * 美美商品后台id
     * 所属表字段order_item.mm_prct_backend_id
     */
    private Long mmPrctBackendId;

    /**
     * 数据仓库定时导出依据字段
     * 所属表字段order_item.db_update_time
     */
    private Date dbUpdateTime;

    /**
     * sku实时平均国际费用 / RMB
     * 所属表字段order_item.international_cost_amount
     */
    private BigDecimal internationalCostAmount;

    /**
     * sku实时平均转关费用 / RMB
     * 所属表字段order_item.transit_cost_amount
     */
    private BigDecimal transitCostAmount;

    /**
     * sku实时平均佣金费用 / RMB
     * 所属表字段order_item.commission_cost_amount
     */
    private BigDecimal commissionCostAmount;

    /**
     * 商品指导价 / RMB
     * 所属表字段order_item.guide_amount
     */
    private BigDecimal guideAmount;

    /**
     * 国内运费
     * 所属表字段order_item.express_china_fee
     */
    private BigDecimal expressChinaFee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getOriginGoodsPrice() {
        return originGoodsPrice;
    }

    public void setOriginGoodsPrice(BigDecimal originGoodsPrice) {
        this.originGoodsPrice = originGoodsPrice;
    }

    public BigDecimal getOriginGoodsTotalAmount() {
        return originGoodsTotalAmount;
    }

    public void setOriginGoodsTotalAmount(BigDecimal originGoodsTotalAmount) {
        this.originGoodsTotalAmount = originGoodsTotalAmount;
    }

    public BigDecimal getOriginPayTotalAmount() {
        return originPayTotalAmount;
    }

    public void setOriginPayTotalAmount(BigDecimal originPayTotalAmount) {
        this.originPayTotalAmount = originPayTotalAmount;
    }

    public BigDecimal getPayTotalAmount() {
        return payTotalAmount;
    }

    public void setPayTotalAmount(BigDecimal payTotalAmount) {
        this.payTotalAmount = payTotalAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getOtherServiceFee() {
        return otherServiceFee;
    }

    public void setOtherServiceFee(BigDecimal otherServiceFee) {
        this.otherServiceFee = otherServiceFee;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsSnapshotId() {
        return goodsSnapshotId;
    }

    public void setGoodsSnapshotId(Long goodsSnapshotId) {
        this.goodsSnapshotId = goodsSnapshotId;
    }

    public Long getMmPrctId() {
        return mmPrctId;
    }

    public void setMmPrctId(Long mmPrctId) {
        this.mmPrctId = mmPrctId;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Integer getStoreReleaseState() {
        return storeReleaseState;
    }

    public void setStoreReleaseState(Integer storeReleaseState) {
        this.storeReleaseState = storeReleaseState;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Integer getRefundEnable() {
        return refundEnable;
    }

    public void setRefundEnable(Integer refundEnable) {
        this.refundEnable = refundEnable;
    }

    public Long getRefundExpireTime() {
        return refundExpireTime;
    }

    public void setRefundExpireTime(Long refundExpireTime) {
        this.refundExpireTime = refundExpireTime;
    }

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId == null ? null : outId.trim();
    }

    public BigDecimal getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(BigDecimal costAmount) {
        this.costAmount = costAmount;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId == null ? null : skuId.trim();
    }

    public Long getMmPrctBackendId() {
        return mmPrctBackendId;
    }

    public void setMmPrctBackendId(Long mmPrctBackendId) {
        this.mmPrctBackendId = mmPrctBackendId;
    }

    public Date getDbUpdateTime() {
        return dbUpdateTime;
    }

    public void setDbUpdateTime(Date dbUpdateTime) {
        this.dbUpdateTime = dbUpdateTime;
    }

    public BigDecimal getInternationalCostAmount() {
        return internationalCostAmount;
    }

    public void setInternationalCostAmount(BigDecimal internationalCostAmount) {
        this.internationalCostAmount = internationalCostAmount;
    }

    public BigDecimal getTransitCostAmount() {
        return transitCostAmount;
    }

    public void setTransitCostAmount(BigDecimal transitCostAmount) {
        this.transitCostAmount = transitCostAmount;
    }

    public BigDecimal getCommissionCostAmount() {
        return commissionCostAmount;
    }

    public void setCommissionCostAmount(BigDecimal commissionCostAmount) {
        this.commissionCostAmount = commissionCostAmount;
    }

    public BigDecimal getGuideAmount() {
        return guideAmount;
    }

    public void setGuideAmount(BigDecimal guideAmount) {
        this.guideAmount = guideAmount;
    }

    public BigDecimal getExpressChinaFee() {
        return expressChinaFee;
    }

    public void setExpressChinaFee(BigDecimal expressChinaFee) {
        this.expressChinaFee = expressChinaFee;
    }
}