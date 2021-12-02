package com.eazybytes.config;

import com.eazybytes.hrms.*;
import com.eazybytes.repo.Employee;
import com.eazybytes.services.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;
import java.util.List;
import java.util.stream.Collectors;

@Endpoint
public class EndPointConfiguration {

    @Autowired
    EmployeeServiceImpl services;



    @PayloadRoot(localPart = "GetEmployeeRequest", namespace = "http://eazybytes.com/hrms")
    @ResponsePayload
    public JAXBElement<GetEmployeeResponse> getEmployeeResponse(@RequestPayload GetEmployeeRequest request) {
        GetEmployeeResponse response = new GetEmployeeResponse();


        Employee employee = services.getEmployee(request.getEmployeeId());
        response.setEmployeeDetails(mapEmployee(employee));
        return new ObjectFactory().createGetEmployeeResponse(response);
    }

    @PayloadRoot(localPart = "GetAllEmployeesRequest", namespace = "http://eazybytes.com/hrms")
    @ResponsePayload
    public JAXBElement<GetAllEmployeeResponse> getAllEmployees(@RequestPayload GetEmployeeRequest request) {
        GetAllEmployeeResponse response = new GetAllEmployeeResponse();
//        List<Employee> allEmployees = services.getAllEmployees();
        List<EmployeeDetails> AllEmployeeDetails = services.getAllEmployees()
                .stream().map(emp -> mapEmployee(emp)).collect(Collectors.toList());
        response.getEmployeeDetails().addAll(AllEmployeeDetails);
        return new ObjectFactory().createGetAllEmployeeResponse(response);
    }

    @PayloadRoot(localPart = "RemoveEmployeeRequest", namespace = "http://eazybytes.com/hrms")
    @ResponsePayload
    public JAXBElement<RemoveEmployeeResponse> removeEmployeeResponse(@RequestPayload RemoveEmployeeRequest request){
        RemoveEmployeeResponse response = new RemoveEmployeeResponse();

        boolean status = services.removeEmployee(request.getEmployeeId());
        response.setStatus(status);

        return new ObjectFactory().createRemoveEmployeeResponse(response);

    }
    private EmployeeDetails mapEmployee(Employee employee) {
        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setEmployeeId(employee.getEmployeeId());
        employeeDetails.setEmployeeName(employee.getEmployeeName());
        employeeDetails.setLocation(employee.getLocation());
        employeeDetails.setZipcode(employee.getZipcode());
        return employeeDetails;
    }
}
