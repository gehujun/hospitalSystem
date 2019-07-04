package com.web.entity;

public class Bed {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bed.bedId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer bedid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bed.wardName
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String wardname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bed.empIdDoc
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer empiddoc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bed.empIdNur
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer empidnur;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bed.isEmptyBed
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String isemptybed;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bed.bedRent
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Float bedrent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bed.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer isdelete;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bed.bedId
     *
     * @return the value of bed.bedId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getBedid() {
        return bedid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bed.bedId
     *
     * @param bedid the value for bed.bedId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setBedid(Integer bedid) {
        this.bedid = bedid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bed.wardName
     *
     * @return the value of bed.wardName
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getWardname() {
        return wardname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bed.wardName
     *
     * @param wardname the value for bed.wardName
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setWardname(String wardname) {
        this.wardname = wardname == null ? null : wardname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bed.empIdDoc
     *
     * @return the value of bed.empIdDoc
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getEmpiddoc() {
        return empiddoc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bed.empIdDoc
     *
     * @param empiddoc the value for bed.empIdDoc
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setEmpiddoc(Integer empiddoc) {
        this.empiddoc = empiddoc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bed.empIdNur
     *
     * @return the value of bed.empIdNur
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getEmpidnur() {
        return empidnur;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bed.empIdNur
     *
     * @param empidnur the value for bed.empIdNur
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setEmpidnur(Integer empidnur) {
        this.empidnur = empidnur;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bed.isEmptyBed
     *
     * @return the value of bed.isEmptyBed
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getIsemptybed() {
        return isemptybed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bed.isEmptyBed
     *
     * @param isemptybed the value for bed.isEmptyBed
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setIsemptybed(String isemptybed) {
        this.isemptybed = isemptybed == null ? null : isemptybed.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bed.bedRent
     *
     * @return the value of bed.bedRent
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Float getBedrent() {
        return bedrent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bed.bedRent
     *
     * @param bedrent the value for bed.bedRent
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setBedrent(Float bedrent) {
        this.bedrent = bedrent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bed.isDelete
     *
     * @return the value of bed.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bed.isDelete
     *
     * @param isdelete the value for bed.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}