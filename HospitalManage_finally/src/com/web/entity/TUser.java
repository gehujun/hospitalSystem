package com.web.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工（医生、护士、院长、行政人员）
 * @author 
 */
public class TUser implements Serializable {
    /**
     * 主键
     */
    private Integer userId;

    /**
     * 员工名字（医生、护士、院长）
     */
    private String userName;

    /**
     * 密码
     */
    private String userPwd;

    /**
     * 头像
     */
    private String userPhoto;

    /**
     * 性别
     */
    private String userSex;

    /**
     * 生日日期
     */
    private Date userBirthday;

    /**
     * 身份证号
     */
    private String userCard;

    /**
     * 科室职位id   外键
     */
    private Integer poId;

    /**
     * 状态  1：在职  2：离职
     */
    private Integer userState;

    /**
     * 员工简介
     */
    private String desc;

    /**
     * 婚姻状况
     */
    private String hyzk;

    /**
     * 职称
     */
    private String zc;

    /**
     * 出生地
     */
    private String csd;

    /**
     * 民族
     */
    private String mz;

    /**
     * 国籍
     */
    private String gj;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 户口地址
     */
    private String hkdz;

    /**
     * 是否删除   1：已删除  0：未删除
     */
    private Integer isdelete;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    public Integer getPoId() {
        return poId;
    }

    public void setPoId(Integer poId) {
        this.poId = poId;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHyzk() {
        return hyzk;
    }

    public void setHyzk(String hyzk) {
        this.hyzk = hyzk;
    }

    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc;
    }

    public String getCsd() {
        return csd;
    }

    public void setCsd(String csd) {
        this.csd = csd;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getGj() {
        return gj;
    }

    public void setGj(String gj) {
        this.gj = gj;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHkdz() {
        return hkdz;
    }

    public void setHkdz(String hkdz) {
        this.hkdz = hkdz;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TUser other = (TUser) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserPwd() == null ? other.getUserPwd() == null : this.getUserPwd().equals(other.getUserPwd()))
            && (this.getUserPhoto() == null ? other.getUserPhoto() == null : this.getUserPhoto().equals(other.getUserPhoto()))
            && (this.getUserSex() == null ? other.getUserSex() == null : this.getUserSex().equals(other.getUserSex()))
            && (this.getUserBirthday() == null ? other.getUserBirthday() == null : this.getUserBirthday().equals(other.getUserBirthday()))
            && (this.getUserCard() == null ? other.getUserCard() == null : this.getUserCard().equals(other.getUserCard()))
            && (this.getPoId() == null ? other.getPoId() == null : this.getPoId().equals(other.getPoId()))
            && (this.getUserState() == null ? other.getUserState() == null : this.getUserState().equals(other.getUserState()))
            && (this.getDesc() == null ? other.getDesc() == null : this.getDesc().equals(other.getDesc()))
            && (this.getHyzk() == null ? other.getHyzk() == null : this.getHyzk().equals(other.getHyzk()))
            && (this.getZc() == null ? other.getZc() == null : this.getZc().equals(other.getZc()))
            && (this.getCsd() == null ? other.getCsd() == null : this.getCsd().equals(other.getCsd()))
            && (this.getMz() == null ? other.getMz() == null : this.getMz().equals(other.getMz()))
            && (this.getGj() == null ? other.getGj() == null : this.getGj().equals(other.getGj()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getHkdz() == null ? other.getHkdz() == null : this.getHkdz().equals(other.getHkdz()))
            && (this.getIsdelete() == null ? other.getIsdelete() == null : this.getIsdelete().equals(other.getIsdelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserPwd() == null) ? 0 : getUserPwd().hashCode());
        result = prime * result + ((getUserPhoto() == null) ? 0 : getUserPhoto().hashCode());
        result = prime * result + ((getUserSex() == null) ? 0 : getUserSex().hashCode());
        result = prime * result + ((getUserBirthday() == null) ? 0 : getUserBirthday().hashCode());
        result = prime * result + ((getUserCard() == null) ? 0 : getUserCard().hashCode());
        result = prime * result + ((getPoId() == null) ? 0 : getPoId().hashCode());
        result = prime * result + ((getUserState() == null) ? 0 : getUserState().hashCode());
        result = prime * result + ((getDesc() == null) ? 0 : getDesc().hashCode());
        result = prime * result + ((getHyzk() == null) ? 0 : getHyzk().hashCode());
        result = prime * result + ((getZc() == null) ? 0 : getZc().hashCode());
        result = prime * result + ((getCsd() == null) ? 0 : getCsd().hashCode());
        result = prime * result + ((getMz() == null) ? 0 : getMz().hashCode());
        result = prime * result + ((getGj() == null) ? 0 : getGj().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getHkdz() == null) ? 0 : getHkdz().hashCode());
        result = prime * result + ((getIsdelete() == null) ? 0 : getIsdelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", userPwd=").append(userPwd);
        sb.append(", userPhoto=").append(userPhoto);
        sb.append(", userSex=").append(userSex);
        sb.append(", userBirthday=").append(userBirthday);
        sb.append(", userCard=").append(userCard);
        sb.append(", poId=").append(poId);
        sb.append(", userState=").append(userState);
        sb.append(", desc=").append(desc);
        sb.append(", hyzk=").append(hyzk);
        sb.append(", zc=").append(zc);
        sb.append(", csd=").append(csd);
        sb.append(", mz=").append(mz);
        sb.append(", gj=").append(gj);
        sb.append(", phone=").append(phone);
        sb.append(", hkdz=").append(hkdz);
        sb.append(", isdelete=").append(isdelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}