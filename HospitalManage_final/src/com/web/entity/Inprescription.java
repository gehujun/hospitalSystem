package com.web.entity;

public class Inprescription {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column inprescription.inPreId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer inpreid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column inprescription.maId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer maid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column inprescription.medcineName
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Medicaladvice ma;
    
    private Medicine medi;
    
    private Integer medicinenum;
    
	

	public Integer getMedicinenum() {
		return medicinenum;
	}

	public void setMedicinenum(Integer medicinenum) {
		this.medicinenum = medicinenum;
	}

	

	public Medicaladvice getMa() {
		return ma;
	}

	public void setMa(Medicaladvice ma) {
		this.ma = ma;
	}

	public Medicine getMedi() {
		return medi;
	}

	public void setMedi(Medicine medi) {
		this.medi = medi;
	}

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column inprescription.mediid
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer mediid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column inprescription.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer isdelete;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column inprescription.inPreId
     *
     * @return the value of inprescription.inPreId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getInpreid() {
        return inpreid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column inprescription.inPreId
     *
     * @param inpreid the value for inprescription.inPreId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setInpreid(Integer inpreid) {
        this.inpreid = inpreid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column inprescription.maId
     *
     * @return the value of inprescription.maId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getMaid() {
        return maid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column inprescription.maId
     *
     * @param maid the value for inprescription.maId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setMaid(Integer maid) {
        this.maid = maid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column inprescription.medcineName
     *
     * @return the value of inprescription.medcineName
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
  
    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column inprescription.medcineName
     *
     * @param medcinename the value for inprescription.medcineName
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
  

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column inprescription.mediid
     *
     * @return the value of inprescription.mediid
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getmediid() {
        return mediid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column inprescription.mediid
     *
     * @param mediid the value for inprescription.mediid
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setmediid(Integer mediid) {
        this.mediid = mediid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column inprescription.isDelete
     *
     * @return the value of inprescription.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column inprescription.isDelete
     *
     * @param isdelete the value for inprescription.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}