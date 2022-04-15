package Payroll;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PayrollDatabase {
    public static Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
    public static Map<Integer, Employee> unionMembers = new HashMap<Integer, Employee>();

    public static void addEmployee(int employeeId, Employee employee)
    {
        employees.put(employeeId, employee);
    }

    public static void deleteEmployee(int employeeId) {
        employees.remove(employeeId);
    }

    public static Employee getEmployee(int employeeId) {
        return employees.get(employeeId);
    }

    public static void addUnionMember(int memberId, Employee employee) {
        unionMembers.put(memberId, employee);
    }

    public static void deleteUnionMember(int memberId) {
        unionMembers.remove(memberId);
    }

    public static Employee getUnionMember(int memberId) {
        return unionMembers.get(memberId);
    }

    public static void clearDatabase() {
        employees.clear();
    }

    public static Iterator<Map.Entry<Integer, Employee>> getEmployeesIterator()  {
        return employees.entrySet().iterator();
    }
}
