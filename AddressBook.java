import java.util.*;
// Design exercise 2
//Question 1 Design Expectations:
//Address book should be able to search by either name or phone number (hashmap with key and value optimal with O(1) time complexity?)
//number can correspond to multiple people (example=landline)
//Name can correspond to multiple phone numbers (office, two phones, who knows?)
//return names or numbers in alphabetic or numeric order (Lists are ordered)
//Caveat with HashMap in this design: Hashmap is unordered. 
//Or maybe use collections package for sorting: [https://www.youtube.com/watch?v=viTHc_4XfCA]

public class AddressBook {
    // Maps for name to phone number and vice versa
    private Map<String, List<String>> nameToNum; // Map of string (name) to list of phone numbers
    private Map<String, List<String>> numToName; // Map of string (phone number) to list of names

    // Constructor to initialize the maps
    public AddressBook() {
        nameToNum = new HashMap<>();
        numToName = new HashMap<>();
    }

    // Add an entry to the address book
    public void addEntry(String name, String number) {
        if (!nameToNum.containsKey(name)) {
            nameToNum.put(name, new ArrayList<>());
        }
        nameToNum.get(name).add(number);

        if (!numToName.containsKey(number)) {
            numToName.put(number, new ArrayList<>());
        }
        numToName.get(number).add(name);
    }

    public List<String> nameSearch(String name) {
        if (nameToNum.containsKey(name)) {
            return nameToNum.get(name);
        } else {
            return new ArrayList<>(); 
        }
    }

    // Search by phone number and return associated name
    public List<String> numSearch(String number) {
        if (numToName.containsKey(number)) {
            return numToName.get(number);
        } else {
            return new ArrayList<>(); 
        }
    }

    // Get all names sorted alphabetically
    public List<String> getAllNamesSorted() {
        List<String> names = new ArrayList<>(nameToNum.keySet());
        Collections.sort(names);  // Efficient sorting using Collections.sort
        return names;
    }

    // Get all phone numbers sorted numerically
    public List<String> getAllNumbersSorted() {
        List<String> numbers = new ArrayList<>(numToName.keySet());
        Collections.sort(numbers);  // Efficient sorting using Collections.sort
        return numbers;
    }

    public static void main(String[] args) {

        AddressBook a = new AddressBook();//Instance
        
        // Test cases
        a.addEntry("Dibyasha", "555-555-5555");
        a.addEntry("SomeEmployeeName", "126-654-6545");
        a.addEntry("Bob", "123-456-7890");
        a.addEntry("Dibyasha", "987-654-3210");
        a.addEntry("Spacco", "555-555-5555");

        System.out.println("Dibyasha's phone number: " + a.nameSearch("Dibyasha"));
        System.out.println("Bob's phone number: " + a.nameSearch("Bob"));
        
        System.out.println(a.numSearch("123-456-7890"));
        System.out.println(a.numSearch("555-555-5555"));
        
        System.out.println("Names in alphabetical order: " + a.getAllNamesSorted());

        System.out.println("Phone numbers in numeric order: " + a.getAllNumbersSorted());
    }
}

