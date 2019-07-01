package com.web.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 职位
 * @author 
 */
public class TPosition implements Serializable {
    /**
     * 主键
     */
    private Integer poId;

    /**
     * 职位名称
     */
    private String poName;

    /**
     * 科室id   外键
     */
    private Integer deptId;
    
    /**
     * 科室
     * 职位：科室   多对一
     * 管理员   眼科
     * 医生       眼科
     * 护士     眼科   
     */
    private TDepartment department;
    
   // private List<TDepartment> departments;//职位：科室      一对多

    /**
     * 职位基本薪资
     */
    private Double poSalary;

    /**
     * 是否删除
     */
    private Integer isDelete;

    private static final long serialVersionUID = 1L;

    public Integer getPoId() {
        return poId;
    }

    public void setPoId(Integer poId) {
        this.poId = poId;
    }

    public String getPoName() {
        return poName;
    }

    public void setPoName(String poName) {
        this.poName = poName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Double getPoSalary() {
        return poSalary;
    }

    public void setPoSalary(Double poSalary) {
        this.poSalary = poSalary;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    
    public TDepartment getDepartment() {
		return department;
	}

	public void setDepartment(TDepartment department) {
		this.department = department;
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
        TPosition other = (TPosition) that;
        return (this.getPoId() == null ? other.getPoId() == null : this.getPoId().equals(other.getPoId()))
            && (this.getPoName() == null ? other.getPoName() == null : this.getPoName().equals(other.getPoName()))
            && (this.getDeptId() == null ? other.getDeptId() == null : this.getDeptId().equals(other.getDeptId()))
            && (this.getPoSalary() == null ? other.getPoSalary() == null : this.getPoSalary().equals(other.getPoSalary()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPoId() == null) ? 0 : getPoId().hashCode());
        result = prime * result + ((getPoName() == null) ? 0 : getPoName().hashCode());
        result = prime * result + ((getDeptId() == null) ? 0 : getDeptId().hashCode());
        result = prime * result + ((getPoSalary() == null) ? 0 : getPoSalary().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", poId=").append(poId);
        sb.append(", poName=").append(poName);
        sb.append(", deptId=").append(deptId);
        sb.append(", poSalary=").append(poSalary);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}