package com.employeeservice.bean;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author 邵鑫雨
 * @version 1.0
 * @description: TODO
 * @date 2022/9/2 20:33
 */
@Entity
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "job")
    private String job;
    @Basic
    @Column(name = "manager_id")
    private Long managerId;
    @Basic
    @Column(name = "hire_date")
    private Date hireDate;
    @Basic
    @Column(name = "salary")
    private Integer salary;
    @Basic
    @Column(name = "commission")
    private Integer commission;
    @Basic
    @Column(name = "department_id")
    private Long departmentId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (job != null ? !job.equals(employee.job) : employee.job != null) return false;
        if (managerId != null ? !managerId.equals(employee.managerId) : employee.managerId != null) return false;
        if (hireDate != null ? !hireDate.equals(employee.hireDate) : employee.hireDate != null) return false;
        if (salary != null ? !salary.equals(employee.salary) : employee.salary != null) return false;
        if (commission != null ? !commission.equals(employee.commission) : employee.commission != null) return false;
        if (departmentId != null ? !departmentId.equals(employee.departmentId) : employee.departmentId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        result = 31 * result + (managerId != null ? managerId.hashCode() : 0);
        result = 31 * result + (hireDate != null ? hireDate.hashCode() : 0);
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (commission != null ? commission.hashCode() : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        return result;
    }
}
