package com.web.entity;

import java.util.Date;

public class Registrationform {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column registrationform.regId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer regid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column registrationform.regDate
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Date regdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column registrationform.regFee
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Float regfee;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column registrationform.regDeptId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer regdeptid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column registrationform.empDocId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer empdocid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column registrationform.regPatId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer regpatid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column registrationform.empId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer empid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column registrationform.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer isdelete;

    
    public Employee getDoc() {
		return doc;
	}



	public void setDoc(Employee doc) {
		this.doc = doc;
	}



	public Employee getEmp() {
		return emp;
	}



	public void setEmp(Employee emp) {
		this.emp = emp;
	}



	public Department getDept() {
		return dept;
	}



	public void setDept(Department dept) {
		this.dept = dept;
	}



	public Patient getPatient() {
		return patient;
	}



	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	private Employee doc;
    
    private Employee emp;
    
    private Department dept;
    
    private Patient patient;
    
    
    
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column registrationform.regId
     *
     * @return the value of registrationform.regId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getRegid() {
        return regid;
    }

    
    
    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column registrationform.regId
     *
     * @param regid the value for registrationform.regId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setRegid(Integer regid) {
        this.regid = regid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column registrationform.regDate
     *
     * @return the value of registrationform.regDate
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Date getRegdate() {
        return regdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column registrationform.regDate
     *
     * @param regdate the value for registrationform.regDate
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column registrationform.regFee
     *
     * @return the value of registrationform.regFee
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Float getRegfee() {
        return regfee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column registrationform.regFee
     *
     * @param regfee the value for registrationform.regFee
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setRegfee(Float regfee) {
        this.regfee = regfee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column registrationform.regDeptId
     *
     * @return the value of registrationform.regDeptId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getRegdeptid() {
        return regdeptid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column registrationform.regDeptId
     *
     * @param regdeptid the value for registrationform.regDeptId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setRegdeptid(Integer regdeptid) {
        this.regdeptid = regdeptid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column registrationform.empDocId
     *
     * @return the value of registrationform.empDocId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getEmpdocid() {
        return empdocid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column registrationform.empDocId
     *
     * @param empdocid the value for registrationform.empDocId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setEmpdocid(Integer empdocid) {
        this.empdocid = empdocid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column registrationform.regPatId
     *
     * @return the value of registrationform.regPatId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getRegpatid() {
        return regpatid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column registrationform.regPatId
     *
     * @param regpatid the value for registrationform.regPatId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setRegpatid(Integer regpatid) {
        this.regpatid = regpatid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column registrationform.empId
     *
     * @return the value of registrationform.empId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getEmpid() {
        return empid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column registrationform.empId
     *
     * @param empid the value for registrationform.empId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column registrationform.isDelete
     *
     * @return the value of registrationform.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column registrationform.isDelete
     *
     * @param isdelete the value for registrationform.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}