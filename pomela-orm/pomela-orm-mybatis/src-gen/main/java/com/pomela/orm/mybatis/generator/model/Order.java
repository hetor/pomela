package com.pomela.orm.mybatis.generator.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    /**
     * 内部id
     * 所属表字段orders.id
     */
    private Long id;

    /**
     * 对外id
     * 所属表字段orders.out_id
     */
    private String outId;

    /**
     * 用户id
     * 所属表字段orders.user_id
     */
    private Long userId;

    /**
     * 用户账号冗余
     * 所属表字段orders.user_name
     */
    private String userName;

    /**
     * 创建时间
     * 所属表字段orders.create_time
     */
    private Long createTime;

    /**
     * 更新时间戳
     * 所属表字段orders.update_time
     */
    private Long updateTime;

    /**
     * 发货时间。仓库点击发货按钮。
     * 所属表字段orders.deliver_time
     */
    private Long deliverTime;

    /**
     * 网易宝付款时间。冗余trade.pay_time
     * 所属表字段orders.pay_time
     */
    private Long payTime;

    /**
     * 成功交易时间。用户点击已收货。
     * 所属表字段orders.succ_time
     */
    private Long succTime;

    /**
     * 失败、过期等关单时间
     * 所属表字段orders.close_time
     */
    private Long closeTime;

    /**
     * 预设过期时间
     * 所属表字段orders.pre_expire_time
     */
    private Long preExpireTime;

    /**
     * 原始商品总价：order_item表相应字段之和
     * 所属表字段orders.origin_goods_total_amount
     */
    private BigDecimal originGoodsTotalAmount;

    /**
     * 原始应支付金额：order_item表相应字段之和
     * 所属表字段orders.origin_pay_total_amount
     */
    private BigDecimal originPayTotalAmount;

    /**
     * 实际应支付金额：order_item表相应字段之和
     * 所属表字段orders.pay_total_amount
     */
    private BigDecimal payTotalAmount;

    /**
     * 关税金额：order_item表相应字段之和
     * 所属表字段orders.tax_amount
     */
    private BigDecimal taxAmount;

    /**
     * 使用优惠券金额：order_item表相应字段之和
     * 所属表字段orders.coupon_amount
     */
    private BigDecimal couponAmount;

    /**
     * 各项服务管理费、研发费用。分账使用、不计入支付费用。
     * 所属表字段orders.other_service_fee
     */
    private BigDecimal otherServiceFee;

    /**
     * 国内运费
     * 所属表字段orders.express_china_fee
     */
    private BigDecimal expressChinaFee;

    /**
     * 国外运费
     * 所属表字段orders.express_oversea_fee
     */
    private BigDecimal expressOverseaFee;

    /**
     * 给香港HQG公司成本费用
     * 所属表字段orders.cost_amount
     */
    private BigDecimal costAmount;

    /**
     * 状态：0未支付1已支付2已发货3交易成功4发货失败5过期关单6已退款
     * 所属表字段orders.state
     */
    private Integer state;

    /**
     * 支付订单id
     * 所属表字段orders.trade_id
     */
    private Long tradeId;

    /**
     * 物流信息id
     * 所属表字段orders.logistic_info_id
     */
    private Long logisticInfoId;

    /**
     * 送货时间类型：0每日1只工作日2只假日
     * 所属表字段orders.deliver_date_type
     */
    private Integer deliverDateType;

    /**
     * 期望发货时间
     * 所属表字段orders.deliver_expect_time
     */
    private Long deliverExpectTime;

    /**
     * 海关地点类型
     * 所属表字段orders.customs_place
     */
    private Integer customsPlace;

    /**
     * 数据仓库定时导出依据字段
     * 所属表字段orders.db_update_time
     */
    private Date dbUpdateTime;

    /**
     * 国际费用 / RMB
     * 所属表字段orders.international_cost_amount
     */
    private BigDecimal internationalCostAmount;

    /**
     * 转关费用 / RMB
     * 所属表字段orders.transit_cost_amount
     */
    private BigDecimal transitCostAmount;

    /**
     * 佣金费用 / RMB
     * 所属表字段orders.commission_cost_amount
     */
    private BigDecimal commissionCostAmount;

    /**
     * 支付大类，对应支付宝、网易宝的商户
     * 所属表字段orders.pay_method
     */
    private Integer payMethod;

    /**
     * 支付宝跨境分账0.1
     * 所属表字段orders.hqg_extra_fee
     */
    private BigDecimal hqgExtraFee;

    /**
     * 支付工具Order流水号
     * 所属表字段orders.real_pay_method_order_id
     */
    private String realPayMethodOrderId;

    /**
     * 商家id
     * 所属表字段orders.merchant_id
     */
    private Integer merchantId;

    /**
     * 商家名称
     * 所属表字段orders.merchant_name
     */
    private String merchantName;

    /**
     * 是否自营，1自营，0POP商家
     * 所属表字段orders.is_self
     */
    private Integer isSelf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId == null ? null : outId.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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

    public Long getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Long deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }

    public Long getSuccTime() {
        return succTime;
    }

    public void setSuccTime(Long succTime) {
        this.succTime = succTime;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    public Long getPreExpireTime() {
        return preExpireTime;
    }

    public void setPreExpireTime(Long preExpireTime) {
        this.preExpireTime = preExpireTime;
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

    public BigDecimal getExpressChinaFee() {
        return expressChinaFee;
    }

    public void setExpressChinaFee(BigDecimal expressChinaFee) {
        this.expressChinaFee = expressChinaFee;
    }

    public BigDecimal getExpressOverseaFee() {
        return expressOverseaFee;
    }

    public void setExpressOverseaFee(BigDecimal expressOverseaFee) {
        this.expressOverseaFee = expressOverseaFee;
    }

    public BigDecimal getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(BigDecimal costAmount) {
        this.costAmount = costAmount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public Long getLogisticInfoId() {
        return logisticInfoId;
    }

    public void setLogisticInfoId(Long logisticInfoId) {
        this.logisticInfoId = logisticInfoId;
    }

    public Integer getDeliverDateType() {
        return deliverDateType;
    }

    public void setDeliverDateType(Integer deliverDateType) {
        this.deliverDateType = deliverDateType;
    }

    public Long getDeliverExpectTime() {
        return deliverExpectTime;
    }

    public void setDeliverExpectTime(Long deliverExpectTime) {
        this.deliverExpectTime = deliverExpectTime;
    }

    public Integer getCustomsPlace() {
        return customsPlace;
    }

    public void setCustomsPlace(Integer customsPlace) {
        this.customsPlace = customsPlace;
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

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public BigDecimal getHqgExtraFee() {
        return hqgExtraFee;
    }

    public void setHqgExtraFee(BigDecimal hqgExtraFee) {
        this.hqgExtraFee = hqgExtraFee;
    }

    public String getRealPayMethodOrderId() {
        return realPayMethodOrderId;
    }

    public void setRealPayMethodOrderId(String realPayMethodOrderId) {
        this.realPayMethodOrderId = realPayMethodOrderId == null ? null : realPayMethodOrderId.trim();
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public Integer getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(Integer isSelf) {
        this.isSelf = isSelf;
    }
}