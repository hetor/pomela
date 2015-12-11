package com.pomela.orm.mybatis.generator.model;

import java.math.BigDecimal;
import java.util.Date;

public class Trade {
    /**
     * 内部id
     * 所属表字段trade.id
     */
    private Long id;

    /**
     * 对外id,就是orderId
     * 所属表字段trade.order_id
     */
    private String orderId;

    /**
     * gorderId
     * 所属表字段trade.gorder_id
     */
    private String gorderId;

    /**
     * urs为用户email，其他为对方账户id
     * 所属表字段trade.user_name
     */
    private String userName;

    /**
     * 内部用户id
     * 所属表字段trade.user_id
     */
    private Long userId;

    /**
     * 创建时间
     * 所属表字段trade.create_time
     */
    private Long createTime;

    /**
     * 状态更新时间
     * 所属表字段trade.update_time
     */
    private Long updateTime;

    /**
     * 状态更新时间
     * 所属表字段trade.close_time
     */
    private Long closeTime;

    /**
     * 支付成功时间
     * 所属表字段trade.pay_time
     */
    private Long payTime;

    /**
     * 预设过期时间
     * 所属表字段trade.pre_expire_time
     */
    private Long preExpireTime;

    /**
     * 原始总价：order表相应字段之和
     * 所属表字段trade.origin_total_amount
     */
    private BigDecimal originTotalAmount;

    /**
     * 原始应支付金额：order表 相应字段之和
     * 所属表字段trade.origin_pay_total_amount
     */
    private BigDecimal originPayTotalAmount;

    /**
     * 实际应支付金额：order表相应字段之和
     * 所属表字段trade.pay_total_amount
     */
    private BigDecimal payTotalAmount;

    /**
     * 使用优惠券金额：order表相应字段之和
     * 所属表字段trade.coupon_amount
     */
    private BigDecimal couponAmount;

    /**
     * 活动减免金额（整单类活动）
     * 所属表字段trade.activity_amount
     */
    private BigDecimal activityAmount;

    /**
     * 税款(用于交易中网易宝)
     * 所属表字段trade.tax_amount
     */
    private BigDecimal taxAmount;

    /**
     * 国外运费
     * 所属表字段trade.express_oversea_fee
     */
    private BigDecimal expressOverseaFee;

    /**
     * 国内运费
     * 所属表字段trade.express_china_fee
     */
    private BigDecimal expressChinaFee;

    /**
     * 各项服务管理费、研发费用。分账使用、不计入支付费用
     * 所属表字段trade.other_service_fee
     */
    private BigDecimal otherServiceFee;

    /**
     * 给香港HQG公司成本费用：pay_total_amount –tax_amount -other_service_fee -express_china_fee -express_oversea_fee
     * 所属表字段trade.cost_amount
     */
    private BigDecimal costAmount;

    /**
     * 使用优惠券id
     * 所属表字段trade.coupon_id
     */
    private String couponId;

    /**
     * 大订单标识状态位 0 初始化（等待支付）1 支付成功（等待发货） 4 交易失败（系统关单后用户支付成功）41 退款成功 5 订单关闭 
     * 所属表字段trade.state
     */
    private Integer state;

    /**
     * 实际支付方式
     * 所属表字段trade.real_pay_method
     */
    private String realPayMethod;

    /**
     * 支付接口类型(标识支付调用的接口，0 网易宝或 1 网易封装的支付宝)
     * 所属表字段trade.pay_method
     */
    private Integer payMethod;

    /**
     * 发票
     * 所属表字段trade.invoice
     */
    private String invoice;

    /**
     * 网易宝交易订单ID
     * 所属表字段trade.wyb_order_id
     */
    private String wybOrderId;

    /**
     * 扩展字段，目前存入“补充说明字段”
     * 所属表字段trade.description
     */
    private String description;

    /**
     * 手续费；如果产品指定手续费，这里原样返回，如果是钱包收取，这里返回的是钱包收取的实际手续费。
     * 所属表字段trade.hand_fee
     */
    private BigDecimal handFee;

    /**
     * 订单入口来源：各种web页面，wap，移动app
     * 所属表字段trade.entry_from
     */
    private Integer entryFrom;

    /**
     * 支付宝等实际支付工具订单号
     * 所属表字段trade.real_pay_method_order_id
     */
    private String realPayMethodOrderId;

    /**
     * 支付方式子类型：0未知、1网易宝pc页面、2网易宝wap、3网易宝银联sdk、4支付宝pc页面、5支付宝移动快捷sdk
     * 所属表字段trade.pay_method_sub_type
     */
    private Integer payMethodSubType;

    /**
     * 数据仓库定时导出依据字段
     * 所属表字段trade.db_update_time
     */
    private Date dbUpdateTime;

    /**
     * 订单归属类型 0考拉 1一元夺宝 2电视快乐购 3方便面渠道
     * 所属表字段trade.owner_type
     */
    private Integer ownerType;

    /**
     * 网易宝支付账号
     * 所属表字段trade.real_pay_method_account
     */
    private String realPayMethodAccount;

    /**
     * 0老系统的单笔支付 1合并支付
     * 所属表字段trade.is_merge_payment
     */
    private Integer isMergePayment;

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

    public String getGorderId() {
        return gorderId;
    }

    public void setGorderId(String gorderId) {
        this.gorderId = gorderId == null ? null : gorderId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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

    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }

    public Long getPreExpireTime() {
        return preExpireTime;
    }

    public void setPreExpireTime(Long preExpireTime) {
        this.preExpireTime = preExpireTime;
    }

    public BigDecimal getOriginTotalAmount() {
        return originTotalAmount;
    }

    public void setOriginTotalAmount(BigDecimal originTotalAmount) {
        this.originTotalAmount = originTotalAmount;
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

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getActivityAmount() {
        return activityAmount;
    }

    public void setActivityAmount(BigDecimal activityAmount) {
        this.activityAmount = activityAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getExpressOverseaFee() {
        return expressOverseaFee;
    }

    public void setExpressOverseaFee(BigDecimal expressOverseaFee) {
        this.expressOverseaFee = expressOverseaFee;
    }

    public BigDecimal getExpressChinaFee() {
        return expressChinaFee;
    }

    public void setExpressChinaFee(BigDecimal expressChinaFee) {
        this.expressChinaFee = expressChinaFee;
    }

    public BigDecimal getOtherServiceFee() {
        return otherServiceFee;
    }

    public void setOtherServiceFee(BigDecimal otherServiceFee) {
        this.otherServiceFee = otherServiceFee;
    }

    public BigDecimal getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(BigDecimal costAmount) {
        this.costAmount = costAmount;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId == null ? null : couponId.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRealPayMethod() {
        return realPayMethod;
    }

    public void setRealPayMethod(String realPayMethod) {
        this.realPayMethod = realPayMethod == null ? null : realPayMethod.trim();
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice == null ? null : invoice.trim();
    }

    public String getWybOrderId() {
        return wybOrderId;
    }

    public void setWybOrderId(String wybOrderId) {
        this.wybOrderId = wybOrderId == null ? null : wybOrderId.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public BigDecimal getHandFee() {
        return handFee;
    }

    public void setHandFee(BigDecimal handFee) {
        this.handFee = handFee;
    }

    public Integer getEntryFrom() {
        return entryFrom;
    }

    public void setEntryFrom(Integer entryFrom) {
        this.entryFrom = entryFrom;
    }

    public String getRealPayMethodOrderId() {
        return realPayMethodOrderId;
    }

    public void setRealPayMethodOrderId(String realPayMethodOrderId) {
        this.realPayMethodOrderId = realPayMethodOrderId == null ? null : realPayMethodOrderId.trim();
    }

    public Integer getPayMethodSubType() {
        return payMethodSubType;
    }

    public void setPayMethodSubType(Integer payMethodSubType) {
        this.payMethodSubType = payMethodSubType;
    }

    public Date getDbUpdateTime() {
        return dbUpdateTime;
    }

    public void setDbUpdateTime(Date dbUpdateTime) {
        this.dbUpdateTime = dbUpdateTime;
    }

    public Integer getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(Integer ownerType) {
        this.ownerType = ownerType;
    }

    public String getRealPayMethodAccount() {
        return realPayMethodAccount;
    }

    public void setRealPayMethodAccount(String realPayMethodAccount) {
        this.realPayMethodAccount = realPayMethodAccount == null ? null : realPayMethodAccount.trim();
    }

    public Integer getIsMergePayment() {
        return isMergePayment;
    }

    public void setIsMergePayment(Integer isMergePayment) {
        this.isMergePayment = isMergePayment;
    }
}