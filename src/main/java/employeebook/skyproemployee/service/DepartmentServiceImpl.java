package employeebook.skyproemployee.service;

import employeebook.skyproemployee.Employee;
import employeebook.skyproemployee.exception.EmployeeDepartmentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMinSalaryOfDepartment(int department) {
        return this.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .min(comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeDepartmentNotFoundException("Отдел не найден"));
    }

    @Override
    public Employee getEmployeeWithMaxSalaryOfDepartment(int department) {
        return this.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .max(comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeDepartmentNotFoundException("Отдел не найден"));
    }

    @Override
    public double getSumSalaryEmployeesOfDepartment(int department) {
        return this.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployees() {
        return this.findAll().stream()
                .collect(groupingBy(Employee::getDepartment));
    }

    @Override
    public Collection<Employee> getDepartmentEmployees(int department) {
        return this.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employeeService.getAllEmployees());
    }
}
