package com.web.entity;

import java.util.Date;

public class Medicaladvice {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicaladvice.maId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer maid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicaladvice.diseaseName
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String diseasename;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicaladvice.maDate
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Date madate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicaladvice.maContent
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String macontent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicaladvice.empId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer empid;
	private Employee emp;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicaladvice.inpatId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer inpatid;
	private Inpatient inp;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicaladvice.isOut
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String isout;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicaladvice.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer isdelete;
	private Professionaltitle prot;
    private Patient pat;
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicaladvice.maId
     *
     * @return the value of medicaladvice.maId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getMaid() {
        return maid;
    }

    public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Inpatient getInp() {
		return inp;
	}

	public void setInp(Inpatient inp) {
		this.inp = inp;
	}

	public Professionaltitle getProt() {
		return prot;
	}

	public void setProt(Professionaltitle prot) {
		this.prot = prot;
	}

	public Patient getPat() {
		return pat;
	}

	public void setPat(Patient pat) {
		this.pat = pat;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicaladvice.maId
     *
     * @param maid the value for medicaladvice.maId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setMaid(Integer maid) {
        this.maid = maid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicaladvice.diseaseName
     *
     * @return the value of medicaladvice.diseaseName
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getDiseasename() {
        return diseasename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicaladvice.diseaseName
     *
     * @param diseasename the value for medicaladvice.diseaseName
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setDiseasename(String diseasename) {
        this.diseasename = diseasename == null ? null : diseasename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicaladvice.maDate
     *
     * @return the value of medicaladvice.maDate
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Date getMadate() {
        return madate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicaladvice.maDate
     *
     * @param madate the value for medicaladvice.maDate
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setMadate(Date madate) {
        this.madate = madate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicaladvice.maContent
     *
     * @return the value of medicaladvice.maContent
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getMacontent() {
        return macontent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicaladvice.maContent
     *
     * @param macontent the value for medicaladvice.maContent
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setMacontent(String macontent) {
        this.macontent = macontent == null ? null : macontent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicaladvice.empId
     *
     * @return the value of medicaladvice.empId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getEmpid() {
        return empid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicaladvice.empId
     *
     * @param empid the value for medicaladvice.empId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicaladvice.inpatId
     *
     * @return the value of medicaladvice.inpatId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getInpatid() {
        return inpatid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicaladvice.inpatId
     *
     * @param inpatid the value for medicaladvice.inpatId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setInpatid(Integer inpatid) {
        this.inpatid = inpatid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicaladvice.isOut
     *
     * @return the value of medicaladvice.isOut
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getIsout() {
        return isout;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicaladvice.isOut
     *
     * @param isout the value for medicaladvice.isOut
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setIsout(String isout) {
        this.isout = isout == null ? null : isout.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicaladvice.isDelete
     *
     * @return the value of medicaladvice.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicaladvice.isDelete
     *
     * @param isdelete the value for medicaladvice.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}