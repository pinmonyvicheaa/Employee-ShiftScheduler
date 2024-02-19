import java.util.*;

class Employee {
    private String id;
    private String name;
    private String position;
    private String shift;

    public Employee(String id, String name, String position, String shift) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.shift = shift;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Position: " + position + ", Shift: " + shift;
    }
}

public class ShiftScheduler {
    private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("+--------------------------------+");
            System.out.println("|    Welcome to Rose's Coffeé:   |");
            System.out.println("+--------------------------------+");
            System.out.println("| 1. Show Registered Employees   |");
            System.out.println("| 2. Register Employee           |");
            System.out.println("| 3. Search Employees By Name    |");
            System.out.println("| 4. Delete Existing Shift       |");
            System.out.println("| 5. Shift Employee              |");
            System.out.println("| 6. Exit                        |");
            System.out.println("+--------------------------------+");
            System.out.print("Choose one: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showRegisteredEmployees();
                    break;
                case 2:
                    registerEmployee(scanner);
                    break;
                case 3:
                    searchEmployeesByName(scanner);
                    break;
                case 4:
                    deleteExistingShift(scanner);
                    break;
                case 5:
                    shiftEmployee(scanner);
                    break;
                case 6:
                    System.out.println("Exit. Arigato <3");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void showRegisteredEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees registered yet.");
        } else {
            System.out.println("Registered Employees:");
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    private static void registerEmployee(Scanner scanner) {
        String id;
        boolean validId = false;

        do {
            System.out.print("Enter employee ID (numeric only): ");
            id = scanner.nextLine();

            if (id.matches("\\d+")) {
                // Check if the ID is already registered
                boolean isDuplicate = false;
                for (Employee emp : employees) {
                    if (emp.getId().equals(id)) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (!isDuplicate) {
                    validId = true;
                } else {
                    System.out.println("This ID is already registered. Please try another number.");
                }
            } else {
                System.out.println("Invalid ID. Please enter a valid numeric ID.");
            }
        } while (!validId);

        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();

        String position;
        do {
            System.out.print("Enter position (Accountant|Sale Manager|Cleaner|Cashier): ");
            position = scanner.nextLine();
            if (!isValidPosition(position)) {
                System.out.println("Invalid position. Please enter a valid position.");
            }
        } while (!isValidPosition(position));

        String shift;
        do {
            System.out.print("Enter shift (Morning, Afternoon, Night): ");
            shift = scanner.nextLine();
            if (!isValidShift(shift)) {
                System.out.println("Invalid shift. Please enter Morning, Afternoon, or Night.");
            }
        } while (!isValidShift(shift));

        Employee employee = new Employee(id, name, position, shift);
        employees.add(employee);
        System.out.println("Employee registered successfully.");
    }

    private static boolean isValidPosition(String position) {
        return position.equalsIgnoreCase("Accountant") ||
                position.equalsIgnoreCase("Sale Manager") ||
                position.equalsIgnoreCase("Cleaner") ||
                position.equalsIgnoreCase("Cashier");
    }

    private static boolean isValidShift(String shift) {
        return shift.equalsIgnoreCase("Morning") ||
                shift.equalsIgnoreCase("Afternoon") ||
                shift.equalsIgnoreCase("Night");
    }

    private static void searchEmployeesByName(Scanner scanner) {
        System.out.print("Enter employee name to search: ");
        String name = scanner.nextLine();
        boolean found = false;

        System.out.println("Employees with the name '" + name + "':");
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                System.out.println(employee);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No employees found with the name '" + name + "'.");
        }
    }

    private static void deleteExistingShift(Scanner scanner) {
        String id;
        boolean validId = false;

        do {
            System.out.print("Enter employee ID to delete existing shift (numeric only): ");
            id = scanner.nextLine();

            if (id.matches("\\d+")) {
                validId = true;
            } else {
                System.out.println("Invalid ID. Please enter a valid numeric ID.");
            }
        } while (!validId);

        boolean deleted = false;

        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getId().equals(id)) {
                iterator.remove();
                deleted = true;
                System.out.println("Existing shift deleted successfully.");
                break;
            }
        }

        if (!deleted) {
            System.out.println("Employee ID not found.");
        }
    }

    private static void shiftEmployee(Scanner scanner) {
        String id;
        boolean validId = false;

        do {
            System.out.print("Enter employee ID to shift (numeric only): ");
            id = scanner.nextLine();

            if (id.matches("\\d+")) {
                validId = true;
            } else {
                System.out.println("Invalid ID. Please enter a valid numeric ID.");
            }
        } while (!validId);

        boolean shifted = false;

        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                String newShift;
                do {
                    System.out.print("Enter new shift (Morning, Afternoon, Night): ");
                    newShift = scanner.nextLine();
                    if (!isValidShift(newShift)) {
                        System.out.println("Invalid preference. Please enter Morning, Afternoon, or Night.");
                    }
                } while (!isValidShift(newShift));

                employee.setShift(newShift);
                System.out.println("Shift updated successfully.");
                shifted = true;
                break;
            }
        }

        if (!shifted) {
            System.out.println("Employee ID not found.");
        }
    }

}