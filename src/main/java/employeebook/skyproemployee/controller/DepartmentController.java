package employeebook.skyproemployee.controller;

import employeebook.skyproemployee.service.DepartmentServiceImpl;
import employeebook.skyproemployee.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentServiceImpl departmentService;

    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/{id}/max")
    public Employee getEmployeeWithMaxSalaryOfDepartment(@PathVariable("id") int id) {
        return departmentService.getEmployeeWithMaxSalaryOfDepartment(id);
    }

    @GetMapping("/{id}/min")
    public Employee getEmployeeWithMinSalaryOfDepartment(@PathVariable("id") int id) {
        return departmentService.getEmployeeWithMinSalaryOfDepartment(id);
    }

    @GetMapping("/{id}/sum")
    public double getSumSalaryEmployeesOfDepartment(@PathVariable("id") int id) {
        return departmentService.getSumSalaryEmployeesOfDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAllEmployees() {
        return departmentService.getAllEmployees();
    }

    @GetMapping("/{id}/employees")
    public Collection<Employee> getDepartmentEmployees(@PathVariable("id") int id) {
        return departmentService.getDepartmentEmployees(id);
    }
}
