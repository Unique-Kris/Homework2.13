package employeebook.skyproemployee;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import employeebook.skyproemployee.exception.EmployeeDepartmentNotFoundException;
import employeebook.skyproemployee.service.DepartmentServiceImpl;
import employeebook.skyproemployee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class DepartmentServiceImplTest {

    private EmployeeService employeeService;
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    void setUp() {
        employeeService = mock(EmployeeService.class);
        departmentService = new DepartmentServiceImpl(employeeService);
    }

    @Test
    void getEmployeeWithMinSalaryOfDepartment_shouldReturnEmployeeWithMinSalary() {
        Employee employee1 = new Employee("Ivan", "Polyakov", 1, 25000);
        Employee employee2 = new Employee("Ivan", "Sidorenko", 1, 28000);
        when(departmentService.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        Employee result = departmentService.getEmployeeWithMinSalaryOfDepartment(1);

        assertEquals(employee1, result);
    }

    @Test
    void getEmployeeWithMinSalaryOfDepartment_shouldThrowExceptionWhenDepartmentNotFound() {
        when(departmentService.findAll()).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(EmployeeDepartmentNotFoundException.class, () -> {
            departmentService.getEmployeeWithMinSalaryOfDepartment(1);
        });

        assertEquals("Отдел не найден", exception.getMessage());
    }

    @Test
    void getEmployeeWithMaxSalaryOfDepartment_shouldReturnEmployeeWithMaxSalary() {
        Employee employee1 = new Employee("Ivan", "Polyakov", 1, 25000);
        Employee employee2 = new Employee("Ivan", "Sidorenko", 1, 28000);
        when(departmentService.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        Employee result = departmentService.getEmployeeWithMaxSalaryOfDepartment(1);

        assertEquals(employee2, result);
    }

    @Test
    void getEmployeeWithMaxSalaryOfDepartment_shouldThrowExceptionWhenDepartmentNotFound() {
        when(departmentService.findAll()).thenReturn(Collections.emptyList());

        Exception exception = assertThrows(EmployeeDepartmentNotFoundException.class, () -> {
            departmentService.getEmployeeWithMaxSalaryOfDepartment(1);
        });

        assertEquals("Отдел не найден", exception.getMessage());
    }

    @Test
    void getSumSalaryEmployeesOfDepartment_shouldReturnSumOfSalaries() {
        Employee employee1 = new Employee("Ivan", "Polyakov", 1, 25000);
        Employee employee2 = new Employee("Ivan", "Sidorenko", 1, 28000);
        when(departmentService.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        double sum = departmentService.getSumSalaryEmployeesOfDepartment(1);

        assertEquals(53000, sum);
    }

    @Test
    void getAllEmployees_shouldReturnEmployeesGroupedByDepartment() {
        Employee employee1 = new Employee("Ivan", "Polyakov", 1, 25000);
        Employee employee2 = new Employee("Ivan", "Sidorenko", 1, 28000);
        when(departmentService.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        Map<Integer, List<Employee>> result = departmentService.getAllEmployees();

        assertTrue(result.containsKey(1));
        assertTrue(result.containsKey(1));
    }

    @Test
    void getDepartmentEmployees_shouldReturnEmployeesOfDepartment() {
        Employee employee1 = new Employee("Ivan", "Polyakov", 1, 25000);
        Employee employee2 = new Employee("Ivan", "Sidorenko", 1, 28000);
        when(departmentService.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        Collection<Employee> result = departmentService.getDepartmentEmployees(1);

        assertTrue(result.contains(employee1));
        assertTrue(result.contains(employee2));
    }
}