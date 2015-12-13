package com.pomela.orm.mybatis.generator.model;

import java.util.Date;

public class LogisticInfo {
    /**
     * 自动分配的id, 主键
     * 所属表字段logistic_info.id
     */
    private Long id;

    /**
     * 订单id, 对应Order表中的out_id
     * 所属表字段logistic_info.order_id
     */
    private String orderId;

    /**
     * 收件人姓名
     * 所属表字段logistic_info.consignee_name
     */
    private String consigneeName;

    /**
     * 收件人联系方式
     * 所属表字段logistic_info.consignee_tel
     */
    private String consigneeTel;

    /**
     * 收件人地址
     * 所属表字段logistic_info.consignee_address
     */
    private String consigneeAddress;

    /**
     * 物流企业名称
     * 所属表字段logistic_info.logis_company_name
     */
    private String logisCompanyName;

    /**
     * 物流企业编号
     * 所属表字段logistic_info.logis_company_code
     */
    private String logisCompanyCode;

    /**
     * 快递业务类型，1为标准快递，2为代收货款，3为收件人付费，4为经济快递
     * 所属表字段logistic_info.business_type
     */
    private Integer businessType;

    /**
     * 邮件种类，业务类型传入代收货款和收件人付费的时候，需传入1.标准件2.经济件
     * 所属表字段logistic_info.billno_type
     */
    private Integer billnoType;

    /**
     * 打印类型，1为五联单打印，2为热敏打印
     * 所属表字段logistic_info.print_kind
     */
    private Integer printKind;

    /**
     * 到件省
     * 所属表字段logistic_info.tcust_province
     */
    private String tcustProvince;

    /**
     * 到件市
     * 所属表字段logistic_info.tcust_city
     */
    private String tcustCity;

    /**
     * 到件县
     * 所属表字段logistic_info.tcust_county
     */
    private String tcustCounty;

    /**
     * 寄件重量
     * 所属表字段logistic_info.weight
     */
    private String weight;

    /**
     * 大客户数据的唯一标识，如某电商公司的配货单号
     * 所属表字段logistic_info.big_account_data_id
     */
    private String bigAccountDataId;

    /**
     * 寄件人姓名
     * 所属表字段logistic_info.scust_name
     */
    private String scustName;

    /**
     * 寄件人联系方式
     * 所属表字段logistic_info.scust_tel
     */
    private String scustTel;

    /**
     * 寄件人地址
     * 所属表字段logistic_info.scust_address
     */
    private String scustAddress;

    /**
     * 内件信息
     * 所属表字段logistic_info.cargo_desc
     */
    private String cargoDesc;

    /**
     * 物流公司id
     * 所属表字段logistic_info.logis_company_id
     */
    private Integer logisCompanyId;

    /**
     * 收件人邮编
     * 所属表字段logistic_info.consignee_zipcode
     */
    private String consigneeZipcode;

    /**
     * 运单号
     * 所属表字段logistic_info.billno
     */
    private String billno;

    /**
     * 财务对账批次号
     * 所属表字段logistic_info.express_reconcilition_id
     */
    private Long expressReconcilitionId;

    /**
     * 更新时间
     * 所属表字段logistic_info.update_time
     */
    private Long updateTime;

    /**
     * 数据仓库定时导出依据字段
     * 所属表字段logistic_info.db_update_time
     */
    private Date dbUpdateTime;

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

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName == null ? null : consigneeName.trim();
    }

    public String getConsigneeTel() {
        return consigneeTel;
    }

    public void setConsigneeTel(String consigneeTel) {
        this.consigneeTel = consigneeTel == null ? null : consigneeTel.trim();
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress == null ? null : consigneeAddress.trim();
    }

    public String getLogisCompanyName() {
        return logisCompanyName;
    }

    public void setLogisCompanyName(String logisCompanyName) {
        this.logisCompanyName = logisCompanyName == null ? null : logisCompanyName.trim();
    }

    public String getLogisCompanyCode() {
        return logisCompanyCode;
    }

    public void setLogisCompanyCode(String logisCompanyCode) {
        this.logisCompanyCode = logisCompanyCode == null ? null : logisCompanyCode.trim();
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getBillnoType() {
        return billnoType;
    }

    public void setBillnoType(Integer billnoType) {
        this.billnoType = billnoType;
    }

    public Integer getPrintKind() {
        return printKind;
    }

    public void setPrintKind(Integer printKind) {
        this.printKind = printKind;
    }

    public String getTcustProvince() {
        return tcustProvince;
    }

    public void setTcustProvince(String tcustProvince) {
        this.tcustProvince = tcustProvince == null ? null : tcustProvince.trim();
    }

    public String getTcustCity() {
        return tcustCity;
    }

    public void setTcustCity(String tcustCity) {
        this.tcustCity = tcustCity == null ? null : tcustCity.trim();
    }

    public String getTcustCounty() {
        return tcustCounty;
    }

    public void setTcustCounty(String tcustCounty) {
        this.tcustCounty = tcustCounty == null ? null : tcustCounty.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getBigAccountDataId() {
        return bigAccountDataId;
    }

    public void setBigAccountDataId(String bigAccountDataId) {
        this.bigAccountDataId = bigAccountDataId == null ? null : bigAccountDataId.trim();
    }

    public String getScustName() {
        return scustName;
    }

    public void setScustName(String scustName) {
        this.scustName = scustName == null ? null : scustName.trim();
    }

    public String getScustTel() {
        return scustTel;
    }

    public void setScustTel(String scustTel) {
        this.scustTel = scustTel == null ? null : scustTel.trim();
    }

    public String getScustAddress() {
        return scustAddress;
    }

    public void setScustAddress(String scustAddress) {
        this.scustAddress = scustAddress == null ? null : scustAddress.trim();
    }

    public String getCargoDesc() {
        return cargoDesc;
    }

    public void setCargoDesc(String cargoDesc) {
        this.cargoDesc = cargoDesc == null ? null : cargoDesc.trim();
    }

    public Integer getLogisCompanyId() {
        return logisCompanyId;
    }

    public void setLogisCompanyId(Integer logisCompanyId) {
        this.logisCompanyId = logisCompanyId;
    }

    public String getConsigneeZipcode() {
        return consigneeZipcode;
    }

    public void setConsigneeZipcode(String consigneeZipcode) {
        this.consigneeZipcode = consigneeZipcode == null ? null : consigneeZipcode.trim();
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno == null ? null : billno.trim();
    }

    public Long getExpressReconcilitionId() {
        return expressReconcilitionId;
    }

    public void setExpressReconcilitionId(Long expressReconcilitionId) {
        this.expressReconcilitionId = expressReconcilitionId;
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
}