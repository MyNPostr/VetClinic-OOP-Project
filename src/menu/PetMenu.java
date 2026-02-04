package menu;

import database.PetDao;
import exception.InvalidInputException;
import model.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PetMenu implements Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final PetDao petDao = new PetDao();

    private static class CommonAnimalFields {
        String name;
        boolean vaccinated;
        int age;
        String breed;
        Gender gender;
    }

    @Override
    public void displayMenu() {
        System.out.println("\n=================================");
        System.out.println("      PET MANAGEMENT ");
        System.out.println("=================================");
        System.out.println("1. Add Dog");
        System.out.println("2. Add Cat");
        System.out.println("3. Add Bird");
        System.out.println("4. View All Pets");
        System.out.println("5. View Dogs Only");
        System.out.println("6. View Cats Only");
        System.out.println("7. View Birds Only");
        System.out.println("8. Update Pet by ID");
        System.out.println("9. Delete Pet by ID");
        System.out.println("10. Search by Name");
        System.out.println("11. Search by Age Range");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    @Override
    public void run() {
        int choice = -1;

        while (choice != 0) {
            try {
                displayMenu();
                choice = readInt();

                switch (choice) {
                    case 1 -> addDog();
                    case 2 -> addCat();
                    case 3 -> addBird();
                    case 4 -> viewList(petDao.findAll());
                    case 5 -> viewList(petDao.findByType("DOG"));
                    case 6 -> viewList(petDao.findByType("CAT"));
                    case 7 -> viewList(petDao.findByType("BIRD"));
                    case 8 -> updatePet();
                    case 9 -> deletePet();
                    case 10 -> searchByName();
                    case 11 -> searchByAgeRange();
                    case 0 -> System.out.println("Exiting...");
                    default -> throw new InvalidInputException("Invalid choice: " + choice);
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a NUMBER.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("DB Error: " + e.getMessage());
            }
        }
    }

    private int tempAge;
    private String tempBreed;
    private Gender tempGender;

    private CommonAnimalFields readCommonFields() throws InvalidInputException {
        CommonAnimalFields f = new CommonAnimalFields();

        System.out.print("Enter Pet Name: ");
        f.name = readNonEmpty();

        System.out.print("Vaccinated (true/false)?: ");
        f.vaccinated = readBoolean();

        System.out.print("Enter age: ");
        f.age = readInt();

        System.out.print("Enter breed: ");
        f.breed = readNonEmpty();

        System.out.print("Enter gender (MALE/FEMALE): ");
        f.gender = Gender.valueOf(readNonEmpty().toUpperCase());

        return f;
    }

    private void addDog() throws InvalidInputException, SQLException {
        CommonAnimalFields f = readCommonFields();

        System.out.print("Is trained (true/false)?: ");
        boolean trained = readBoolean();

        Pet pet = new Pet(f.name, f.vaccinated,
                new Dog(f.age, f.breed, f.gender, trained));

        boolean ok = petDao.insert(pet);
        System.out.println(ok ? "Inserted!" : "Insert failed!");
    }

    private void addCat() throws InvalidInputException, SQLException {
        CommonAnimalFields f = readCommonFields();

        System.out.print("Is indoor (true/false)?: ");
        boolean indoor = readBoolean();

        Pet pet = new Pet(f.name, f.vaccinated,
                new Cat(f.age, f.breed, f.gender, indoor));

        boolean ok = petDao.insert(pet);
        System.out.println(ok ? "Inserted!" : "Insert failed!");
    }

    private void addBird() throws InvalidInputException, SQLException {
        CommonAnimalFields f = readCommonFields();

        System.out.print("Has teeth (true/false)?: ");
        boolean hasTeeth = readBoolean();

        Pet pet = new Pet(f.name, f.vaccinated,
                new Bird(f.age, f.breed, f.gender, hasTeeth));

        boolean ok = petDao.insert(pet);
        System.out.println(ok ? "Inserted!" : "Insert failed!");
    }

    private void viewList(List<Pet> pets) {
        System.out.println("\n============ PETS ===========");
        if (pets.isEmpty()) {
            System.out.println("No records.");
            return;
        }

        for (Pet pet : pets) {
            System.out.println(pet);

            Animal animal = pet.getAnimal();
            animal.makeSound();
            animal.eat();

            if (animal instanceof Dog dog) dog.fetch();
            else if (animal instanceof Cat cat) cat.climb();
            else if (animal instanceof Bird bird) bird.sing();

            System.out.println("--------------------------------");
        }
    }

    private void updatePet() throws InvalidInputException, SQLException {
        System.out.print("Enter Pet ID to update: ");
        long id = readLong();

        Pet existing = petDao.findById(id);
        if (existing == null) {
            throw new InvalidInputException("Pet not found with ID: " + id);
        }

        System.out.println("Current:");
        System.out.println(existing);

        System.out.print("New name (Enter to keep): ");
        String newName = scanner.nextLine().trim();
        if (newName.isEmpty()) newName = existing.getName();

        System.out.print("Vaccinated (true/false) (Enter to keep): ");
        String vacStr = scanner.nextLine().trim();
        boolean newVac = vacStr.isEmpty() ? existing.isVaccinated() : parseBooleanStrict(vacStr);

        Animal oldA = existing.getAnimal();
        String type = oldA.getClass().getSimpleName().toUpperCase();

        System.out.print("New age (Enter to keep): ");
        String ageStr = scanner.nextLine().trim();
        int newAge = ageStr.isEmpty() ? oldA.getAge() : Integer.parseInt(ageStr);

        System.out.print("New breed (Enter to keep): ");
        String breedStr = scanner.nextLine().trim();
        String newBreed = breedStr.isEmpty() ? oldA.getBreed() : breedStr;

        System.out.print("New gender (MALE/FEMALE) (Enter to keep): ");
        String gStr = scanner.nextLine().trim();
        Gender newGender = gStr.isEmpty() ? oldA.getGender() : Gender.valueOf(gStr.toUpperCase());

        Animal newAnimal;

        if (type.equals("DOG")) {
            boolean trained = (oldA instanceof Dog d) && d.isTrained();
            System.out.print("Trained (true/false) (Enter to keep): ");
            String tStr = scanner.nextLine().trim();
            if (!tStr.isEmpty()) trained = parseBooleanStrict(tStr);
            newAnimal = new Dog(newAge, newBreed, newGender, trained);

        } else if (type.equals("CAT")) {
            boolean indoor = (oldA instanceof Cat c) && c.isIndoor();
            System.out.print("Indoor (true/false) (Enter to keep): ");
            String iStr = scanner.nextLine().trim();
            if (!iStr.isEmpty()) indoor = parseBooleanStrict(iStr);
            newAnimal = new Cat(newAge, newBreed, newGender, indoor);

        } else {
            boolean hasTeeth = (oldA instanceof Bird b) && b.hasTeeth();
            System.out.print("Has teeth (true/false) (Enter to keep): ");
            String hStr = scanner.nextLine().trim();
            if (!hStr.isEmpty()) hasTeeth = parseBooleanStrict(hStr);
            newAnimal = new Bird(newAge, newBreed, newGender, hasTeeth);
        }

        Pet updated = new Pet(id, newName, newVac, newAnimal);

        boolean ok = petDao.update(updated);
        System.out.println(ok ? "Updated!" : "Update failed!");
    }

    private void deletePet() throws InvalidInputException, SQLException {
        System.out.print("Enter Pet ID to delete: ");
        int id = readInt();

        Pet existing = petDao.findById(id);
        if (existing == null) throw new InvalidInputException("Pet not found with ID: " + id);

        System.out.println("Will delete:");
        System.out.println(existing);

        System.out.print("Are you sure? (yes/no): ");
        String ans = readNonEmpty().toLowerCase();

        if (!ans.equals("yes")) {
            System.out.println("Cancelled.");
            return;
        }

        boolean ok = petDao.deleteById(id);
        System.out.println(ok ? "Deleted!" : "Delete failed!");
    }

    private void searchByName() throws InvalidInputException, SQLException {
        System.out.print("Enter name part: ");
        String part = readNonEmpty();
        viewList(petDao.searchByName(part));
    }

    private void searchByAgeRange() throws InvalidInputException, SQLException {
        System.out.print("Min age: ");
        int min = readInt();
        System.out.print("Max age: ");
        int max = readInt();
        viewList(petDao.searchByAgeRange(min, max));
    }

    private int readInt() {
        String s = scanner.nextLine();
        return Integer.parseInt(s.trim());
    }

    private boolean readBoolean() throws InvalidInputException {
        String s = readNonEmpty().toLowerCase();
        if (s.equals("true")) return true;
        if (s.equals("false")) return false;
        throw new InvalidInputException("Enter true or false!");
    }

    private boolean parseBooleanStrict(String s) throws InvalidInputException {
        s = s.trim().toLowerCase();
        if (s.equals("true")) return true;
        if (s.equals("false")) return false;
        throw new InvalidInputException("Enter true or false!");
    }

    private String readNonEmpty() throws InvalidInputException {
        String s = scanner.nextLine();
        if (s == null || s.trim().isEmpty()) {
            throw new InvalidInputException("Input cannot be empty!");
        }
        return s.trim();
    }

    private long readLong() {
        String s = scanner.nextLine();
        return Long.parseLong(s.trim());
    }
}
