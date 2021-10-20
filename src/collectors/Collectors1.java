package collectors;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Collectors1 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Employee1"),
                new Employee(2, "Employee2"),
                new Employee(3, "Employee3"),
                new Employee(1, "Employee4"),
                new Employee(2, "Employee5"),
                new Employee(1, "Employee6")
        );
//        employees.stream().collect(Collectors.toMap(Employee::getId, Employee::getName));
//        employees.stream().forEach(System.out::println)
        Map<Integer, List<Employee>> collect = employees.stream().collect(groupingBy(Employee::getId));
        collect.entrySet().stream().forEach(System.out::println);

        Map<Integer, List<String>> collect1 = employees.stream().collect(groupingBy(
                Employee::getId,
                mapping(Employee::getName, toList())
        ));
        collect1.entrySet().stream().forEach(System.out::println);


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 1, 2, 3, 4, 5);

        Map<Integer, Long> collect2 = numbers.stream().collect(
                groupingBy(Function.identity(),
                        counting()));

        System.out.println(collect2);

        Map<String, Long> collect3 = employees.stream().collect(
                groupingBy(Employee::getName, counting())
        );
        collect3.entrySet().forEach(System.out::println);

        employees.stream().collect(
                groupingBy(Employee::getName,
                        collectingAndThen(counting(), Long::intValue)
                ));



        employees.stream().collect(groupingBy(Employee::getName,collectingAndThen(counting(),Long::intValue)));


        Optional<Employee> collect4 = employees.stream().collect(maxBy(Comparator.comparing(Employee::getId)));

        employees.stream().collect(collectingAndThen(
                maxBy(Comparator.comparing(Employee::getId)),employee -> employee.map(Employee::getName).orElse(""))
        );

        List<String> names = Arrays.asList("Srinivas","Bharathi");

        List<Stream<String>> collect5 = names.stream()
                .map(name -> Stream.of(name.split("")))
                .collect(toList());


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