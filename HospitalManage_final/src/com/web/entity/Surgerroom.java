package com.web.entity;

public class Surgerroom {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column surgerroom.surRoomId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String surroomid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column surgerroom.surRoomAddr
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String surroomaddr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column surgerroom.empId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private Integer empid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column surgerroom.surRoomPre
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    private String surroompre;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column surgerroom.surRoomId
     *
     * @return the value of surgerroom.surRoomId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getSurroomid() {
        return surroomid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column surgerroom.surRoomId
     *
     * @param surroomid the value for surgerroom.surRoomId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setSurroomid(String surroomid) {
        this.surroomid = surroomid == null ? null : surroomid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column surgerroom.surRoomAddr
     *
     * @return the value of surgerroom.surRoomAddr
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getSurroomaddr() {
        return surroomaddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column surgerroom.surRoomAddr
     *
     * @param surroomaddr the value for surgerroom.surRoomAddr
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setSurroomaddr(String surroomaddr) {
        this.surroomaddr = surroomaddr == null ? null : surroomaddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column surgerroom.empId
     *
     * @return the value of surgerroom.empId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public Integer getEmpid() {
        return empid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column surgerroom.empId
     *
     * @param empid the value for surgerroom.empId
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column surgerroom.surRoomPre
     *
     * @return the value of surgerroom.surRoomPre
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public String getSurroompre() {
        return surroompre;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column surgerroom.surRoomPre
     *
     * @param surroompre the value for surgerroom.surRoomPre
     *
     * @mbg.generated Mon Jul 01 11:34:33 CST 2019
     */
    public void setSurroompre(String surroompre) {
        this.surroompre = surroompre == null ? null : surroompre.trim();
    }
}