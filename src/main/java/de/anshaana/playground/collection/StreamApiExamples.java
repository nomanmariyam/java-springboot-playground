package de.anshaana.playground.collection;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiExamples {

    public void filterList() {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");

        // Filter words starting with 'a' and convert them to uppercase
        List<String> filteredAndUppercase = words.stream()
                .filter(word -> word.startsWith("a"))
                .collect(Collectors.toList());

        System.out.println(filteredAndUppercase);
    }

    public void filterAndTransformToUpperCaseList() {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");

        // Filter words starting with 'a' and convert them to uppercase
        List<String> filteredAndUppercase = words.stream()
                .filter(word -> word.startsWith("a"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(filteredAndUppercase);
    }

    public void convertListToCommaSepratedString() {
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");

        String commaSeparatedString = words.stream()
                .collect(Collectors.joining(", "));

        System.out.println("Comma-separated string: " + commaSeparatedString);
    }

    public void findMaxNumber() {
        List<Integer> numbers = Arrays.asList(5, 3, 8, 2, 10);

        Integer maxNumber = numbers.stream()
                .max(Integer::compareTo)
                .orElseThrow(); // orElseThrow to handle the case when the list is empty

        System.out.println("Maximum number: " + maxNumber);
    }

    public void findMinNumber() {
        List<Integer> numbers = Arrays.asList(5, 3, 8, 2, 10);

        Integer maxNumber = numbers.stream()
                .min(Integer::compareTo)
                .orElseThrow(); // orElseThrow to handle the case when the list is empty

        System.out.println("Minimum number: " + maxNumber);
    }

    public void removeDuplicates() {
        List<Integer> numbers = Arrays.asList(5, 3, 8, 2, 10, 2, 3);

        List<Integer> distinctNumbers = numbers.stream()
                .distinct()
                .toList();

        System.out.println("Distinct numbers: " + distinctNumbers);
    }

    public void convertStudentListToMap() {
        List<Student> students = List.of(
                new Student(1, "Biden"),
                new Student(2, "Trump"),
                new Student(2, "Musk")
                );
        // Convert list to map using student ID as the key,
        Map<Integer, String> studentMap = students.stream()
                .collect(Collectors.toMap(Student::getId, Student::getName));

        // Print the map
        studentMap.forEach((id, name) -> System.out.println("ID: " + id + ", Name: " + name));
    }

    public void concatList() {
        List<String> list1 = List.of("Apple", "Banana", "Orange");

        List<String> list2 = List.of("Mango", "Grapes");

        List<String> concatenatedList = Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toList());

        System.out.println("Concatenated List: " + concatenatedList);
    }
}


class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}