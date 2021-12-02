package com.eazybytes.repo;

import java.util.ArrayList;
import java.util.List;

public class OfflineRepository {
    public static List<Employee> getEmployees() {
        List<Employee> empList = new ArrayList<Employee>();
        empList.add(new Employee(1, "Solana", "Nairobi", 64));
        empList.add(new Employee(2, "Skilachi", "Texas", 384));
        empList.add(new Employee(3, "Kagwe", "Embu", 311));
        empList.add(new Employee(4, "Kena", "Juja", 256));
        return empList;
    }
}
