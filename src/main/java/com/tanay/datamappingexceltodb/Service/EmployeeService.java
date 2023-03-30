package com.tanay.datamappingexceltodb.Service;

import com.tanay.datamappingexceltodb.Entity.EmployeeEntity;
import com.tanay.datamappingexceltodb.Repository.EmployeeRepository;
import com.tanay.datamappingexceltodb.Utility.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public void save(MultipartFile file) {

        try {
            List<EmployeeEntity> employees = ExcelHelper.convertExcelToEmployee(file.getInputStream());
            this.employeeRepository.saveAll(employees);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<EmployeeEntity> getAllEmployees() throws NullPointerException {
        if (this.employeeRepository!=null)
            return this.employeeRepository.findAll();

        return null;
    }
    public Optional<EmployeeEntity> getEmployeesById(int id) throws NullPointerException {
        if (this.employeeRepository!=null)
            return this.employeeRepository.findById(id);

        return Optional.empty();
    }


}
