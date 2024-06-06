package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

import static com.company.Employee.employeeFromString;

// TODO: Handle duplicate names, input validation, exception handling. Use constants and enums. Beautify output.

public class Main {

    public static void main(String[] args) {

        HashMap<String,BigDecimal> departmentPayTotals = new HashMap<>();
        TreeMap<String,BigDecimal> employeeListAlphabetical = new TreeMap<>();
        BigDecimal totalPay = loadWeeklyDataFromFile( departmentPayTotals, employeeListAlphabetical);

        System.out.println("Total Pay");
        System.out.println("$"+totalPay);

        System.out.println("Department Pay Totals");
        System.out.println(departmentPayTotals);

        System.out.println("Alphabetical Employee List");
        System.out.println(employeeListAlphabetical);
    }

    // Load info from CSV "database"
    private static BigDecimal loadWeeklyDataFromFile( HashMap<String,BigDecimal> departmentPayTotals,
                                       SortedMap<String,BigDecimal> employeeListAlphabetical){

        BigDecimal totalPay = BigDecimal.ZERO;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("weeklyData.csv"));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                Employee currentEmployee = employeeFromString(line);
                if (currentEmployee == null) {continue;}

                // calculate weekly pay
                BigDecimal weeklyPay = Role.calculateWeeklyPay(currentEmployee);

                // Add to total pay Aggregation (1)
                totalPay = totalPay.add(weeklyPay);

                // Add to Department List (2)
                if (departmentPayTotals.containsKey(currentEmployee.department)){
                    BigDecimal currentTotal = departmentPayTotals.get(currentEmployee.department);
                    departmentPayTotals.put(currentEmployee.department, currentTotal.add(weeklyPay));
                } else {
                    departmentPayTotals.put(currentEmployee.department, weeklyPay);
                }

                // Add to Sorted Employee List (3)
                BigDecimal basepay = Role.calculateBasePay(currentEmployee);
                employeeListAlphabetical.put(currentEmployee.name, basepay);

            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPay;
    }

}
