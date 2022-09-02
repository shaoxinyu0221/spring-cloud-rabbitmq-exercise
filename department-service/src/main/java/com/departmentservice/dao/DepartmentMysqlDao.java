package com.departmentservice.dao;

import com.departmentservice.bean.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 邵鑫雨
 * @version 1.0
 * @description: TODO
 * @date 2022/9/1 22:09
 */
@Repository
public interface DepartmentMysqlDao extends JpaRepository<Department,Long> {
}
