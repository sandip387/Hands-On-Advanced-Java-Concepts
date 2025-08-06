package $02_exception_and_concurrency;

public class VotingSystem {
    static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age " + age + " is not valid for voting");
        }
        System.out.println("Welcome to vote!");
    }

    public static void main(String[] args) {
        System.out.println("--- Custom Exception Demo ---");
        try {
            validateAge(16);
        } catch (InvalidAgeException e) {
            System.out.println("Caught an Exception:" + e.getMessage());
        } finally {
            System.out.println("Validation process completed");
        }

        System.out.println("Trying again with a valid age:");
        try {
            validateAge(25);
        } catch (Exception e) {
            System.out.println("This should not be printed: " + e.getMessage());
        }
    }
}
