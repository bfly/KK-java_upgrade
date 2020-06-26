package interfaces;

public class CompanyEmployee implements Company, Employee {
    String firstName;
    String lastName;

    public CompanyEmployee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getFirst() {
        return firstName;
    }

    @Override
    public String getLast() {
        return lastName;
    }

    @Override
    public void doWork() {
        System.out.println("Doing work...");
    }

    @Override
    public String getName() {
        return String.format("%s works for %s", Employee.super.getName(), Company.getName());
    }
}
