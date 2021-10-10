import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class FuturevsCompletableFuture {
    public static Employee fetchEmployee(int id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Employee(1,"srini");
    }

    public static int fetchTaxRate(Employee employee) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int calculateTax(int taxRate) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void sendEmail(int taxValue) {
        System.out.println("taxValue = " + taxValue);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Employee1"),
                new Employee(2, "Employee2"),
                new Employee(3, "Employee3"),
                new Employee(4, "Employee4"),
                new Employee(5, "Employee5"),
                new Employee(6, "Employee6"),
                new Employee(7, "Employee7"),
                new Employee(8, "Employee8"),
                new Employee(9, "Employee9"),
                new Employee(10, "Employee10"),
                new Employee(11, "Employee11"),
                new Employee(12, "Employee12"),
                new Employee(13, "Employee13"),
                new Employee(14, "Employee14"),
                new Employee(15, "Employee15"),
                new Employee(16, "Employee16"),
                new Employee(17, "Employee17")
        );

        List<Integer> employeeIds = employees.stream().map(Employee::getId).collect(Collectors.toList());

//        ExecutorService service = Executors.newFixedThreadPool(2);
//        Long start = System.currentTimeMillis();
//        for (Integer id : employeeIds) {
//            Future<Employee> future = service.submit(() -> fetchEmployee(id));
//            Employee employee = future.get();
//
//            Future<Integer> rateFuture = service.submit(() -> fetchTaxRate(employee));
//            Integer taxRate = rateFuture.get();
//
//            int tax = calculateTax(taxRate);
//
//            sendEmail(tax);
//        }
//        Long end = System.currentTimeMillis();
//        System.out.println((end - start));

       Long start = System.currentTimeMillis();
        for (Integer id : employeeIds) {
            CompletableFuture.supplyAsync(() -> fetchEmployee(id))
                    .thenApplyAsync(employee -> fetchTaxRate(employee))
                    .thenApplyAsync(taxRate -> calculateTax(taxRate))
                    .thenAcceptAsync(taxValue -> sendEmail(taxValue));
        }
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

class Employee {
    int id;
    String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}