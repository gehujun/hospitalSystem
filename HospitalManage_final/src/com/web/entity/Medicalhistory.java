package com.web.entity;

public class Medicalhistory {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicalhistory.medhId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer medhid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicalhistory.medhPatId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer medhpatid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicalhistory.medhType
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String medhtype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicalhistory.medhHistory
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String medhhistory;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicalhistory.docaId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer docaid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column medicalhistory.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer isdelete;

    private Patient patient;
    
    public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicalhistory.medhId
     *
     * @return the value of medicalhistory.medhId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getMedhid() {
        return medhid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicalhistory.medhId
     *
     * @param medhid the value for medicalhistory.medhId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setMedhid(Integer medhid) {
        this.medhid = medhid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicalhistory.medhPatId
     *
     * @return the value of medicalhistory.medhPatId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getMedhpatid() {
        return medhpatid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicalhistory.medhPatId
     *
     * @param medhpatid the value for medicalhistory.medhPatId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setMedhpatid(Integer medhpatid) {
        this.medhpatid = medhpatid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicalhistory.medhType
     *
     * @return the value of medicalhistory.medhType
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getMedhtype() {
        return medhtype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicalhistory.medhType
     *
     * @param medhtype the value for medicalhistory.medhType
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setMedhtype(String medhtype) {
        this.medhtype = medhtype == null ? null : medhtype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicalhistory.medhHistory
     *
     * @return the value of medicalhistory.medhHistory
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getMedhhistory() {
        return medhhistory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicalhistory.medhHistory
     *
     * @param medhhistory the value for medicalhistory.medhHistory
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setMedhhistory(String medhhistory) {
        this.medhhistory = medhhistory == null ? null : medhhistory.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicalhistory.docaId
     *
     * @return the value of medicalhistory.docaId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getDocaid() {
        return docaid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicalhistory.docaId
     *
     * @param docaid the value for medicalhistory.docaId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setDocaid(Integer docaid) {
        this.docaid = docaid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column medicalhistory.isDelete
     *
     * @return the value of medicalhistory.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column medicalhistory.isDelete
     *
     * @param isdelete the value for medicalhistory.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}