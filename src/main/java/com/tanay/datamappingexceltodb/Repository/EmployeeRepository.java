package com.tanay.datamappingexceltodb.Repository;

import com.tanay.datamappingexceltodb.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer> {
}
