package com.web.entity;

import java.util.Date;

public class Medicine {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicine.mediId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer mediid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicine.meLiId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer meliid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicine.mediName
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String mediname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicine.mediSpecifi
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Float medispecifi;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicine.mediUnit
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String mediunit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicine.mediPrice
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Float mediprice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicine.mediExpirationDate
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Date mediexpirationdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicine.mediFirm
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String medifirm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicine.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer isdelete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicine.mediStock
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer medistock;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicine.mediId
     *
     * @return the value of medicine.mediId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getMediid() {
        return mediid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicine.mediId
     *
     * @param mediid the value for medicine.mediId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setMediid(Integer mediid) {
        this.mediid = mediid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicine.meLiId
     *
     * @return the value of medicine.meLiId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getMeliid() {
        return meliid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicine.meLiId
     *
     * @param meliid the value for medicine.meLiId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setMeliid(Integer meliid) {
        this.meliid = meliid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicine.mediName
     *
     * @return the value of medicine.mediName
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getMediname() {
        return mediname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicine.mediName
     *
     * @param mediname the value for medicine.mediName
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setMediname(String mediname) {
        this.mediname = mediname == null ? null : mediname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicine.mediSpecifi
     *
     * @return the value of medicine.mediSpecifi
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Float getMedispecifi() {
        return medispecifi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicine.mediSpecifi
     *
     * @param medispecifi the value for medicine.mediSpecifi
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setMedispecifi(Float medispecifi) {
        this.medispecifi = medispecifi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicine.mediUnit
     *
     * @return the value of medicine.mediUnit
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getMediunit() {
        return mediunit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicine.mediUnit
     *
     * @param mediunit the value for medicine.mediUnit
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setMediunit(String mediunit) {
        this.mediunit = mediunit == null ? null : mediunit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicine.mediPrice
     *
     * @return the value of medicine.mediPrice
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Float getMediprice() {
        return mediprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicine.mediPrice
     *
     * @param mediprice the value for medicine.mediPrice
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setMediprice(Float mediprice) {
        this.mediprice = mediprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicine.mediExpirationDate
     *
     * @return the value of medicine.mediExpirationDate
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Date getMediexpirationdate() {
        return mediexpirationdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicine.mediExpirationDate
     *
     * @param mediexpirationdate the value for medicine.mediExpirationDate
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setMediexpirationdate(Date mediexpirationdate) {
        this.mediexpirationdate = mediexpirationdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicine.mediFirm
     *
     * @return the value of medicine.mediFirm
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getMedifirm() {
        return medifirm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicine.mediFirm
     *
     * @param medifirm the value for medicine.mediFirm
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setMedifirm(String medifirm) {
        this.medifirm = medifirm == null ? null : medifirm.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicine.isDelete
     *
     * @return the value of medicine.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicine.isDelete
     *
     * @param isdelete the value for medicine.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicine.mediStock
     *
     * @return the value of medicine.mediStock
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getMedistock() {
        return medistock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicine.mediStock
     *
     * @param medistock the value for medicine.mediStock
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setMedistock(Integer medistock) {
        this.medistock = medistock;
    }
}