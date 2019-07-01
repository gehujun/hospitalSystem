package com.web.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author 
 */
public class TDepartment implements Serializable {
    /**
     * 科室id  主键
     */
    private Integer deptId;

    /**
     * 科室名称
     */
    private String deptName;

    /**
     * 科室地址
     */
    private String deptAddress;

    /**
     * 科室电话
     */
    private String deptPhone;

    /**
     * 科室负责人  外键   对应用户表
     */
    private Integer userId;
    
    private TUser user;//科室负责人

    /**
     * 是否删除   1：未删除   2：已删除
     */
    private Integer isDelete;
    
    private List<TPosition> positionList;//一对多      一个科室下面有多个岗位

    private static final long serialVersionUID = 1L;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptAddress() {
        return deptAddress;
    }

    public void setDeptAddress(String deptAddress) {
        this.deptAddress = deptAddress;
    }

    public String getDeptPhone() {
        return deptPhone;
    }

    public void setDeptPhone(String deptPhone) {
        this.deptPhone = deptPhone;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    
    public List<TPosition> getPositionList() {
		return positionList;
	}

	public void setPositionList(List<TPosition> positionList) {
		this.positionList = positionList;
	}
	
	

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
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
        TDepartment other = (TDepartment) that;
        return (this.getDeptId() == null ? other.getDeptId() == null : this.getDeptId().equals(other.getDeptId()))
            && (this.getDeptName() == null ? other.getDeptName() == null : this.getDeptName().equals(other.getDeptName()))
            && (this.getDeptAddress() == null ? other.getDeptAddress() == null : this.getDeptAddress().equals(other.getDeptAddress()))
            && (this.getDeptPhone() == null ? other.getDeptPhone() == null : this.getDeptPhone().equals(other.getDeptPhone()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDeptId() == null) ? 0 : getDeptId().hashCode());
        result = prime * result + ((getDeptName() == null) ? 0 : getDeptName().hashCode());
        result = prime * result + ((getDeptAddress() == null) ? 0 : getDeptAddress().hashCode());
        result = prime * result + ((getDeptPhone() == null) ? 0 : getDeptPhone().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deptId=").append(deptId);
        sb.append(", deptName=").append(deptName);
        sb.append(", deptAddress=").append(deptAddress);
        sb.append(", deptPhone=").append(deptPhone);
        sb.append(", userId=").append(userId);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}