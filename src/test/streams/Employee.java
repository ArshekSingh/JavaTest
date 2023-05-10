package test.streams;

public class Employee {
    private Integer id;
    private String name;
    private Double salary;

    public Employee(Integer id, String name, Double  salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Double getSalary() {
        return this.salary;
    }
    public void checkIncreament(Employee emp) {
        Double salary1 = emp.getSalary();
        if(salary1 > 1000) {
            System.out.println("eligible");
        }
    }
}
