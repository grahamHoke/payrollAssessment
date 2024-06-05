package com.company;

import java.security.PublicKey;

public class Employee {
    // represents an employee's work week.

    int employeeId;
    String name;
    String department;
    String role;
    Double hoursWorked;
    Boolean fullTime;

    public Employee (int employeeId, String name, String department, String role,
                      double hoursWorked, boolean fullTime){
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.role = role;
        this.hoursWorked = hoursWorked;
        this.fullTime = fullTime;
    }

    public static Employee employeeFromString(String employeeLine){
        String[] employeeLineTokens = employeeLine.split(",");
        if (employeeLineTokens[0].matches("-?\\d+")) {
            return new Employee(Integer.parseInt(employeeLineTokens[0]), employeeLineTokens[1], employeeLineTokens[2],
                    employeeLineTokens[3], Double.parseDouble(employeeLineTokens[4]), employeeLineTokens[5].equals("FullTime"));

        } else {
            return null;
        }
    }
}
