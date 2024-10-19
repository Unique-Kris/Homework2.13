package employeebook.skyproemployee.service;

import employeebook.skyproemployee.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMinSalaryOfDepartment(int department);

    Employee getEmployeeWithMaxSalaryOfDepartment(int department);

    double getSumSalaryEmployeesOfDepartment(int department);

    Map<Integer, List<Employee>> getAllEmployees();

    Collection<Employee> getDepartmentEmployees(int department);

    Collection<Employee> findAll();
}
