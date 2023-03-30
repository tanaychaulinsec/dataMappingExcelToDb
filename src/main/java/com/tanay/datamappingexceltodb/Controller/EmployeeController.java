package com.tanay.datamappingexceltodb.Controller;

import com.tanay.datamappingexceltodb.Entity.EmployeeEntity;
import com.tanay.datamappingexceltodb.Service.EmployeeService;
import com.tanay.datamappingexceltodb.Utility.ExcelHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@RestController
@CrossOrigin("*")
public class EmployeeController {


    private final EmployeeService employeeService;

   public  EmployeeController(){
       employeeService = new EmployeeService();
   }

    @PostMapping("/employees/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (ExcelHelper.checkExcelFormat(file)) {
            //true

            this.employeeService.save(file);

            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));


        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }


    @GetMapping("/employees")
    public List<EmployeeEntity> getEmployees() {
        return this.employeeService.getAllEmployees();
    }

}
