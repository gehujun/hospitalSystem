package com.web.entity;

import java.util.Date;

public class Surgeryproject {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column surgeryproject.surId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer surid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column surgeryproject.maId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer maid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column surgeryproject.name
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column surgeryproject.surRoomId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String surroomid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column surgeryproject.patientName
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String patientname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column surgeryproject.inpatId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer inpatid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column surgeryproject.chiefSurgeon
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private int chiefsurgeon;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column surgeryproject.surDate
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Date surdate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column surgeryproject.Anesthesia
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String anesthesia;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column surgeryproject.incisionCon
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String incisioncon;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column surgeryproject.duration
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String duration;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column surgeryproject.result
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String result;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column surgeryproject.surCost
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Float surcost;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column surgeryproject.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer isdelete;

    private Medicaladvice medicaladvice;
    
    private Surgerroom surgerroom;
    
    public Medicaladvice getMedicaladvice() {
		return medicaladvice;
	}
 
	public void setMedicaladvice(Medicaladvice medicaladvice) {
		this.medicaladvice = medicaladvice;
	}

	public Surgerroom getSurgerroom() {
		return surgerroom;
	}

	public void setSurgerroom(Surgerroom surgerroom) {
		this.surgerroom = surgerroom;
	}

	public Inpatient getInpatient() {
		return inpatient;
	}

	public void setInpatient(Inpatient inpatient) {
		this.inpatient = inpatient;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	private Inpatient inpatient;
    
    private Employee employee;
    
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column surgeryproject.surId
     *
     * @return the value of surgeryproject.surId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getSurid() {
        return surid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column surgeryproject.surId
     *
     * @param surid the value for surgeryproject.surId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setSurid(Integer surid) {
        this.surid = surid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column surgeryproject.maId
     *
     * @return the value of surgeryproject.maId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getMaid() {
        return maid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column surgeryproject.maId
     *
     * @param maid the value for surgeryproject.maId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setMaid(Integer maid) {
        this.maid = maid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column surgeryproject.name
     *
     * @return the value of surgeryproject.name
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column surgeryproject.name
     *
     * @param name the value for surgeryproject.name
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column surgeryproject.surRoomId
     *
     * @return the value of surgeryproject.surRoomId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getSurroomid() {
        return surroomid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column surgeryproject.surRoomId
     *
     * @param surroomid the value for surgeryproject.surRoomId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setSurroomid(String surroomid) {
        this.surroomid = surroomid == null ? null : surroomid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column surgeryproject.patientName
     *
     * @return the value of surgeryproject.patientName
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getPatientname() {
        return patientname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column surgeryproject.patientName
     *
     * @param patientname the value for surgeryproject.patientName
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setPatientname(String patientname) {
        this.patientname = patientname == null ? null : patientname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column surgeryproject.inpatId
     *
     * @return the value of surgeryproject.inpatId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getInpatid() {
        return inpatid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column surgeryproject.inpatId
     *
     * @param inpatid the value for surgeryproject.inpatId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setInpatid(Integer inpatid) {
        this.inpatid = inpatid;
    }
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column surgeryproject.surDate
     *
     * @return the value of surgeryproject.surDate
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Date getSurdate() {
        return surdate;
    }

    public int getChiefsurgeon() {
		return chiefsurgeon;
	}

	public void setChiefsurgeon(int chiefsurgeon) {
		this.chiefsurgeon = chiefsurgeon;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column surgeryproject.surDate
     *
     * @param surdate the value for surgeryproject.surDate
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setSurdate(Date surdate) {
        this.surdate = surdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column surgeryproject.Anesthesia
     *
     * @return the value of surgeryproject.Anesthesia
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getAnesthesia() {
        return anesthesia;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column surgeryproject.Anesthesia
     *
     * @param anesthesia the value for surgeryproject.Anesthesia
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setAnesthesia(String anesthesia) {
        this.anesthesia = anesthesia == null ? null : anesthesia.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column surgeryproject.incisionCon
     *
     * @return the value of surgeryproject.incisionCon
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getIncisioncon() {
        return incisioncon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column surgeryproject.incisionCon
     *
     * @param incisioncon the value for surgeryproject.incisionCon
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setIncisioncon(String incisioncon) {
        this.incisioncon = incisioncon == null ? null : incisioncon.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column surgeryproject.duration
     *
     * @return the value of surgeryproject.duration
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getDuration() {
        return duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column surgeryproject.duration
     *
     * @param duration the value for surgeryproject.duration
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column surgeryproject.result
     *
     * @return the value of surgeryproject.result
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getResult() {
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column surgeryproject.result
     *
     * @param result the value for surgeryproject.result
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column surgeryproject.surCost
     *
     * @return the value of surgeryproject.surCost
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Float getSurcost() {
        return surcost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column surgeryproject.surCost
     *
     * @param surcost the value for surgeryproject.surCost
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setSurcost(Float surcost) {
        this.surcost = surcost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column surgeryproject.isDelete
     *
     * @return the value of surgeryproject.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getIsdelete() {
        return isdelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column surgeryproject.isDelete
     *
     * @param isdelete the value for surgeryproject.isDelete
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }
}