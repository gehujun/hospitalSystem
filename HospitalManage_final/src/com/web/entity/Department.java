package com.web.entity;

public class Department {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column department.deptId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer deptid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column department.deptName
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String deptname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column department.deptAdr
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String deptadr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column department.deptTel
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer depttel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column department.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer isdelete;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column department.deptId
     *
     * @return the value of department.deptId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getDeptid() {
        return deptid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column department.deptId
     *
     * @param deptid the value for department.deptId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column department.deptName
     *
     * @return the value of department.deptName
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getDeptname() {
        return deptname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column department.deptName
     *
     * @param deptname the value for department.deptName
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column department.deptAdr
     *
     * @return the value of department.deptAdr
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getDeptadr() {
        return deptadr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column department.deptAdr
     *
     * @param deptadr the value for department.deptAdr
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setDeptadr(String deptadr) {
        this.deptadr = deptadr == null ? null : deptadr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column department.deptTel
     *
     * @return the value of department.deptTel
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getDepttel() {
        return depttel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column department.deptTel
     *
     * @param depttel the value for department.deptTel
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setDepttel(Integer depttel) {
        this.depttel = depttel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column department.isDelete
     *
     * @return the value of department.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column department.isDelete
     *
     * @param isdelete the value for department.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}