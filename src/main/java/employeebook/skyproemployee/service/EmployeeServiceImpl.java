package employeebook.skyproemployee.service;

import employeebook.skyproemployee.Employee;
import employeebook.skyproemployee.exception.EmployeeAlreadyAddedException;
import employeebook.skyproemployee.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public static final int maxListSize = 10;

    List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Ivan", "Ivanov", 1, 20000),
            new Employee("Oleg", "Ivanov", 2, 25000),
            new Employee("Ivan", "Fedorov", 1, 23000),
            new Employee("Oleg", "Fedorov", 3, 27000),
            new Employee("Ivan", "Sidorov", 1, 30000),
            new Employee("Oleg", "Sidorov", 3, 24000),
            new Employee("Ivan", "Petrenko", 2, 26000),
            new Employee("Oleg", "Petrenko", 2, 31000),
            new Employee("Roman", "Polyakov", 3, 19000),
            new Employee("Gennadiy", "Polyakov", 2, 32000)
    ));

    @Override
    public Employee addEmployee(String firstName, String lastName, int department, int salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует!");
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName, int department, int salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException("Такой сотрудник не найден");
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName, int department, int salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException("Такой сотрудник не найден");
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableCollection(employees);
    }
}