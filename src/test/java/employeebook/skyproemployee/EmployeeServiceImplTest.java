package employeebook.skyproemployee;

import static org.junit.jupiter.api.Assertions.*;

import employeebook.skyproemployee.exception.EmployeeAlreadyAddedException;
import employeebook.skyproemployee.exception.EmployeeNotFoundException;
import employeebook.skyproemployee.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl();
    }

    @Test
    void addEmployee_shouldAddEmployee() {
        Employee employee = new Employee("Ivan", "Polyakov", 1, 25000);
        Employee result = employeeService.addEmployee("Ivan", "Polyakov", 1, 25000);

        assertEquals(employee, result);
        assertTrue(employeeService.getAllEmployees().contains(employee));
    }

    @Test
    void addEmployee_shouldThrowExceptionWhenEmployeeAlreadyExists() {
        employeeService.addEmployee("Ivan", "Polyakov", 1, 25000);

        Exception exception = assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeService.addEmployee("Ivan", "Polyakov", 1, 25000);
        });

        assertEquals("Такой сотрудник уже существует!", exception.getMessage());
    }

    @Test
    void findEmployee_shouldReturnEmployee() {
        employeeService.addEmployee("Ivan", "Polyakov", 1, 25000);

        Employee result = employeeService.findEmployee("Ivan", "Polyakov", 1, 25000);
        assertNotNull(result);
        assertEquals("Ivan", result.getFirstName());
        assertEquals("Polyakov", result.getLastName());
    }

    @Test
    void findEmployee_shouldThrowExceptionWhenEmployeeNotFound() {
        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.findEmployee("Ivan", "Polyakov", 1, 25000);
        });

        assertEquals("Такой сотрудник не найден", exception.getMessage());
    }

    @Test
    void removeEmployee_shouldRemoveEmployee() {
        employeeService.addEmployee("Ivan", "Polyakov", 1, 25000);

        Employee result = employeeService.removeEmployee("Ivan", "Polyakov", 1, 25000);
        assertNotNull(result);
        assertEquals("Ivan", result.getFirstName());
        assertEquals("Polyakov", result.getLastName());
        assertFalse(employeeService.getAllEmployees().contains(result));
    }

    @Test
    void removeEmployee_shouldThrowExceptionWhenEmployeeNotFound() {
        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.removeEmployee("Ivan", "Polyakov", 1, 25000);
        });

        assertEquals("Такой сотрудник не найден", exception.getMessage());
    }

    @Test
    void getAllEmployees_shouldReturnAllEmployees() {
        Employee employee1 = new Employee("Ivan", "Polyakov", 1, 25000);
        Employee employee2 = new Employee("Ivan", "Sidorenko", 2, 28000);

        employeeService.addEmployee("Ivan", "Polyakov", 1, 25000);
        employeeService.addEmployee("Ivan", "Sidorenko", 2, 28000);

        Collection<Employee> employees = employeeService.getAllEmployees();
        assertTrue(employees.contains(employee1));
        assertTrue(employees.contains(employee2));
    }
}