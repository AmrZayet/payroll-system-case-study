package Payroll;

import java.util.HashMap;
import java.util.Map;

public class PayrollDatabase {
    public static Map<Integer, Employee> employeesList = new HashMap<Integer, Employee>();

    public static void addEmployee(int employeeId, Employee employee)
    {
        employeesList.put(employeeId, employee);
    }

    public static void deleteEmployee(int employeeId) {
        employeesList.remove(employeeId);
    }

    public static Employee getEmployee(int employeeId) {
        return employeesList.get(employeeId);
    }

    public static void clearDatabase() {
        employeesList.clear();
    }
}
