package com.company;

import java.math.BigDecimal;

public abstract class Role {
    public static final String intern = "Intern";
    public static final String floorWorker = "FloorWorker";
    public static final String supervisor = "Supervisor";
    public static final String manager = "Manager";
    public static final String executive = "Executive";

    public static BigDecimal calculateHourlyRate(String role){
        BigDecimal hourlyRate = BigDecimal.ZERO;
        switch (role){
            case intern:
                hourlyRate = BigDecimal.valueOf(10);
                break;
            case floorWorker:
                hourlyRate = BigDecimal.valueOf(20);
                break;
            case supervisor:
                hourlyRate = BigDecimal.valueOf(25);
                break;
            case manager:
                hourlyRate = BigDecimal.valueOf(35);
                break;
            case executive:
                hourlyRate = BigDecimal.valueOf(50);
                break;
        }
        return hourlyRate;
    }
    public static BigDecimal calculateWeeklyPay(Employee employee){
        BigDecimal hourlyRate = calculateHourlyRate(employee.role);
        BigDecimal totalWeeklyPay = BigDecimal.ZERO;
        BigDecimal adjustedHoursWorked = BigDecimal.ZERO;


        // Full time workers get paid overtime (time and a half) for any hours worked in a week over 40hrs.
        BigDecimal hoursWorked = BigDecimal.valueOf(employee.hoursWorked);
        if (employee.fullTime && employee.hoursWorked > 40){
            BigDecimal overtimeHours = BigDecimal.valueOf(employee.hoursWorked - 40);
            adjustedHoursWorked = hoursWorked.add(overtimeHours.multiply(BigDecimal.valueOf(.5)));
        } else {
            adjustedHoursWorked = hoursWorked;
        }

        totalWeeklyPay = totalWeeklyPay.add(adjustedHoursWorked.multiply(hourlyRate));

        // Management Level employees (Managers and Executives) get a $50 bonus every week
        if (employee.role.equals(executive) || employee.role.equals(manager)){
            totalWeeklyPay = totalWeeklyPay.add(BigDecimal.valueOf(50));
        }

        return totalWeeklyPay;
    }

    public static BigDecimal calculateBasePay(Employee employee) {
        BigDecimal hourlyRate = calculateHourlyRate(employee.role);
        BigDecimal baseWeeklyPay = BigDecimal.ZERO;

        BigDecimal hoursWorked = BigDecimal.valueOf(employee.hoursWorked);

        return hoursWorked.multiply(hourlyRate);
    }
}
