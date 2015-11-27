package com.pomela.orm.mybatis.generator.model;

import java.math.BigDecimal;
import java.util.Date;

public class PayTransferRecord {
    /**
     * 主键id
     * 所属表字段pay_transfer_record.id
     */
    private Long id;

    /**
     * 接入方标识
     * 所属表字段pay_transfer_record.partner
     */
    private Integer partner;

    /**
     * 接入方唯一批次号
     * 所属表字段pay_transfer_record.out_batch_no
     */
    private String outBatchNo;

    /**
     * 内部唯一批次号
     * 所属表字段pay_transfer_record.batch_no
     */
    private String batchNo;

    /**
     * 支付工具
     * 所属表字段pay_transfer_record.pay_method_tool
     */
    private Integer payMethodTool;

    /**
     * 支付工具平台id
     * 所属表字段pay_transfer_record.platform_id
     */
    private String platformId;

    /**
     * 转入帐号，资金需要传入的帐号全名（包含域名）,必须为URS帐号
     * 所属表字段pay_transfer_record.account_in
     */
    private String accountIn;

    /**
     * 转出账号,平台对应钱包帐号全名（转出帐号）,由网易宝指定或者由产品注册URS帐号后告诉支付组
     * 所属表字段pay_transfer_record.account_out
     */
    private String accountOut;

    /**
     * 转账金额
     * 所属表字段pay_transfer_record.amount
     */
    private BigDecimal amount;

    /**
     * 任务状态：0开始 1成功 9失败
     * 所属表字段pay_transfer_record.state
     */
    private Integer state;

    /**
     * 转账时间
     * 所属表字段pay_transfer_record.transfer_time
     */
    private Long transferTime;

    /**
     * 转账备注
     * 所属表字段pay_transfer_record.note
     */
    private String note;

    /**
     * 更新时间
     * 所属表字段pay_transfer_record.db_update_time
     */
    private Date dbUpdateTime;

    /**
     * 创建时间
     * 所属表字段pay_transfer_record.db_create_time
     */
    private Date dbCreateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPartner() {
        return partner;
    }

    public void setPartner(Integer partner) {
        this.partner = partner;
    }

    public String getOutBatchNo() {
        return outBatchNo;
    }

    public void setOutBatchNo(String outBatchNo) {
        this.outBatchNo = outBatchNo == null ? null : outBatchNo.trim();
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    public Integer getPayMethodTool() {
        return payMethodTool;
    }

    public void setPayMethodTool(Integer payMethodTool) {
        this.payMethodTool = payMethodTool;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId == null ? null : platformId.trim();
    }

    public String getAccountIn() {
        return accountIn;
    }

    public void setAccountIn(String accountIn) {
        this.accountIn = accountIn == null ? null : accountIn.trim();
    }

    public String getAccountOut() {
        return accountOut;
    }

    public void setAccountOut(String accountOut) {
        this.accountOut = accountOut == null ? null : accountOut.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Long transferTime) {
        this.transferTime = transferTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getDbUpdateTime() {
        return dbUpdateTime;
    }

    public void setDbUpdateTime(Date dbUpdateTime) {
        this.dbUpdateTime = dbUpdateTime;
    }

    public Date getDbCreateTime() {
        return dbCreateTime;
    }

    public void setDbCreateTime(Date dbCreateTime) {
        this.dbCreateTime = dbCreateTime;
    }
}