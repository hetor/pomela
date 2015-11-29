package com.pomela.orm.mybatis.generator.dao;

import com.pomela.orm.mybatis.generator.model.PayTransferRecord;

public interface PayTransferRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay_transfer_record
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay_transfer_record
     *
     * @mbggenerated
     */
    int insert(PayTransferRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay_transfer_record
     *
     * @mbggenerated
     */
    int insertSelective(PayTransferRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay_transfer_record
     *
     * @mbggenerated
     */
    PayTransferRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay_transfer_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PayTransferRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay_transfer_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PayTransferRecord record);
}