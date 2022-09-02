package com.employeeservice.dao.mysql;

import com.employeeservice.bean.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 邵鑫雨
 * @version 1.0
 * @description: TODO
 * @date 2022/9/2 20:33
 */
@Repository
public interface EmployeeMysqlDao extends JpaRepository<Employee,Long> {

    Integer deleteByDepartmentId(Long departmentId);
}
