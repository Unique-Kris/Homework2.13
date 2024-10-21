package employeebook.skyproemployee;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import employeebook.skyproemployee.service.DepartmentServiceImpl;
import employeebook.skyproemployee.service.EmployeeService;
import employeebook.skyproemployee.exception.EmployeeDepartmentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DepartmentServiceImplMockTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private List<Employee> employees;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        employees = new ArrayList<>();
        employees.add(new Employee("Ivan", "Ivanov", 1, 20000));
        employees.add(new Employee("Ivan", "Fedorov", 1, 23000));
        employees.add(new Employee("Oleg", "Ivanov", 2, 25000));
        employees.add(new Employee("Ivan", "Petrenko", 2, 26000));

        when(departmentService.findAll()).thenReturn(employees);
    }

    @Test
    void getEmployeeWithMinSalaryOfDepartment_shouldReturnEmployeeWithMinSalary() {
        Employee result = departmentService.getEmployeeWithMinSalaryOfDepartment(1);
        assertEquals("Ivan", result.getFirstName());
        assertEquals(20000, result.getSalary());
    }

    @Test
    void getEmployeeWithMinSalaryOfDepartment_shouldThrowExceptionWhenDepartmentNotFound() {
        Exception exception = assertThrows(EmployeeDepartmentNotFoundException.class, () -> {
            departmentService.getEmployeeWithMinSalaryOfDepartment(3);
        });
        assertEquals("Отдел не найден", exception.getMessage());
    }

    @Test
    void getEmployeeWithMaxSalaryOfDepartment_shouldReturnEmployeeWithMaxSalary() {
        Employee result = departmentService.getEmployeeWithMaxSalaryOfDepartment(2);
        assertEquals("Ivan", result.getFirstName());
        assertEquals(26000, result.getSalary());
    }

    @Test
    void getEmployeeWithMaxSalaryOfDepartment_shouldThrowExceptionWhenDepartmentNotFound() {
        Exception exception = assertThrows(EmployeeDepartmentNotFoundException.class, () -> {
            departmentService.getEmployeeWithMaxSalaryOfDepartment(3);
        });
        assertEquals("Отдел не найден", exception.getMessage());
    }

    @Test
    void getSumSalaryEmployeesOfDepartment_shouldReturnSumOfSalaries() {
        double sum = departmentService.getSumSalaryEmployeesOfDepartment(1);
        assertEquals(43000, sum);
    }

    @Test
    void getAllEmployees_shouldReturnMapOfEmployeesByDepartment() {
        Map<Integer, List<Employee>> result = departmentService.getAllEmployees();
        assertEquals(2, result.size());
        assertTrue(result.containsKey(1));
        assertTrue(result.containsKey(2));
        assertEquals(2, result.get(1).size());
        assertEquals(2, result.get(2).size());
    }

    @Test
    void getDepartmentEmployees_shouldReturnEmployeesOfDepartment() {
        Collection<Employee> result = departmentService.getDepartmentEmployees(2);
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(e -> e.getFirstName().equals("Oleg")));
        assertTrue(result.stream().anyMatch(e -> e.getFirstName().equals("Ivan")));
    }
}