package employeebook.skyproemployee.exception;

public class EmployeeDepartmentNotFoundException extends RuntimeException{
    public EmployeeDepartmentNotFoundException(String message) {
        super(message);
    }
}