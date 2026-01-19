package menu;

import exception.InvalidInputException;
import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class VetMenu implements Menu {

    private final Scanner scanner = new Scanner(System.in);

    private final ArrayList<Pet> pets = new ArrayList<>();
    private final ArrayList<Owner> owners = new ArrayList<>();
    private final ArrayList<Veterenarian> vets = new ArrayList<>();

    @Override
    public void displayMenu() {
        System.out.println("\n=================================");
        System.out.println("        VET CLINIC SYSTEM");
        System.out.println("=================================");
        System.out.println("1. Pets Menu");
        System.out.println("2. Owners Menu");
        System.out.println("3. Veterinarians Menu");
        System.out.println("0. Exit System");
        System.out.print("Enter choice: ");
    }

    @Override
    public void run() {
        System.out.println("Hello Everyone!\nThis application is designed for veterinary clinics to manage clients, veterinarians, pets, appointments, and treatments.");

        int choice = -1;

        while (choice != 0) {
            try {
                displayMenu();
                choice = readInt();

                switch (choice) {
                    case 1 -> petMenu();
                    case 2 -> ownerMenu();
                    case 3 -> vetMenu();
                    case 0 -> System.out.println("Exiting...");
                    default -> throw new InvalidInputException("Invalid choice: " + choice);
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a NUMBER.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void petMenu() throws InvalidInputException {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n=================================");
            System.out.println("              PET MENU");
            System.out.println("=================================");
            System.out.println("1. Add Pet");
            System.out.println("2. View All Pets");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            try {
                choice = readInt();

                switch (choice) {
                    case 1 -> addPet();
                    case 2 -> viewAllPets();
                    case 0 -> System.out.println("Returning to Main Menu...");
                    default -> throw new InvalidInputException("Invalid choice: " + choice);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a NUMBER.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void ownerMenu() throws InvalidInputException {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n=================================");
            System.out.println("              OWNER MENU");
            System.out.println("=================================");
            System.out.println("1. Add Owner");
            System.out.println("2. View All Owners");
            System.out.println("3. Add Pet to Owner");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            try {
                choice = readInt();

                switch (choice) {
                    case 1 -> addOwner();
                    case 2 -> viewAllOwners();
                    case 3 -> addPetToOwner();
                    case 0 -> System.out.println("Returning to Main Menu...");
                    default -> throw new InvalidInputException("Invalid choice: " + choice);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a NUMBER.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void vetMenu() throws InvalidInputException {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n=================================");
            System.out.println("              VET MENU");
            System.out.println("=================================");
            System.out.println("1. Add Veterinarian");
            System.out.println("2. View All Veterinarians");
            System.out.println("3. Rate Veterinarian");
            System.out.println("4. Book Veterinarian");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            try {
                choice = readInt();

                switch (choice) {
                    case 1 -> addVeterinarian();
                    case 2 -> viewAllVets();
                    case 3 -> rateVet();
                    case 4 -> bookVet();
                    case 0 -> System.out.println("Returning to Main Menu...");
                    default -> throw new InvalidInputException("Invalid choice: " + choice);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a NUMBER.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addPet() throws InvalidInputException {
        System.out.print("Enter pet ID: ");
        int id = readInt();

        if (!Validating.isPetIdUnique(id, pets)) {
            throw new InvalidInputException("ID already exists!");
        }

        System.out.print("Enter pet name: ");
        String name = readNonEmpty();

        System.out.println("Choose animal type:");
        System.out.println("1. Dog");
        System.out.println("2. Cat");
        System.out.println("3. Bird");
        int type = readInt();

        System.out.print("Is vaccinated (true/false)?: ");
        boolean vaccinated = readBoolean();

        Animal animal;

        if (type == 1) {
            System.out.print("Enter age: ");
            int age = readInt();

            System.out.print("Enter gender (MALE/FEMALE): ");
            Gender gender = Gender.valueOf(readNonEmpty().toUpperCase());

            System.out.print("Enter breed: ");
            String breed = readNonEmpty();

            System.out.print("Is trained (true/false)?: ");
            boolean trained = readBoolean();

            animal = new Dog(age, breed, gender, trained);

        } else if (type == 2) {
            System.out.print("Enter age: ");
            int age = readInt();

            System.out.print("Enter breed: ");
            String breed = readNonEmpty();

            System.out.print("Enter gender (MALE/FEMALE): ");
            Gender gender = Gender.valueOf(readNonEmpty().toUpperCase());

            System.out.print("Is indoor (true/false)?: ");
            boolean indoor = readBoolean();

            animal = new Cat(age, breed, gender, indoor);

        } else if (type == 3) {
            System.out.print("Enter age: ");
            int age = readInt();

            System.out.print("Enter breed: ");
            String breed = readNonEmpty();

            System.out.print("Enter gender (MALE/FEMALE): ");
            Gender gender = Gender.valueOf(readNonEmpty().toUpperCase());

            System.out.print("Does they have teeth (true/false)?: ");
            boolean hasTeeth = readBoolean();

            animal = new Bird(age, breed, gender, hasTeeth);

        } else {
            throw new InvalidInputException("Unknown animal type: " + type);
        }

        Pet pet = new Pet(id, name, vaccinated, animal);
        pets.add(pet);

        System.out.println("Pet added successfully!");
    }

    private void viewAllPets() {
        System.out.println("\n============ ALL PETS ===========");

        for (Pet pet : pets) {
            System.out.println(pet);

            Animal animal = pet.getAnimal();
            animal.makeSound();
            animal.eat();

            if (animal instanceof Dog dog) {
                dog.fetch();
            } else if (animal instanceof Cat cat) {
                cat.climb();
            } else if (animal instanceof Bird bird) {
                bird.sing();
            }

            System.out.println("--------------------------------");
        }

        System.out.println("Press Enter...");
        scanner.nextLine();
    }

    private void addOwner() throws InvalidInputException {
        System.out.print("Enter Owner ID: ");
        int id = readInt();

        if (!Validating.isOwnerIdUnique(id, owners)) {
            throw new InvalidInputException("Owner with this ID already exists!");
        }

        System.out.print("Name: ");
        String name = readNonEmpty();
        if (!Validating.isValidStr(name)) {
            throw new InvalidInputException("Name shouldn't be blank!");
        }

        System.out.print("Phone: ");
        String phone = readNonEmpty();
        if (!Validating.isNumber(phone)) {
            throw new InvalidInputException("Invalid phone number!");
        }

        System.out.print("Address: ");
        String address = readNonEmpty();

        System.out.print("Email: ");
        String email = readNonEmpty();


        System.out.print("Gender (MALE/FEMALE): ");
        Gender gender = Gender.valueOf(readNonEmpty().toUpperCase());

        owners.add(new Owner(id, name, phone, address, email, gender));
        System.out.println("Owner added!");
    }

    private void viewAllOwners() {
        System.out.println("\n=========== ALL OWNERS ==========");
        for (Owner owner : owners) {
            System.out.println(owner);
        }
        System.out.println("Press Enter...");
        scanner.nextLine();
    }

    private void addPetToOwner() throws InvalidInputException {
        System.out.print("Enter Owner ID: ");
        int ownerId = readInt();

        Owner selectedOwner = null;
        for (Owner owner : owners) {
            if (owner.getId() == ownerId) {
                selectedOwner = owner;
                break;
            }
        }
        if (selectedOwner == null) {
            throw new InvalidInputException("Owner not found");
        }

        System.out.print("Enter Pet ID: ");
        int petId = readInt();

        for (Pet pet : pets) {
            if (pet.getId() == petId) {
                selectedOwner.addownpet(pet);
                System.out.println("Pet " + pet.getName() + " is added to " + selectedOwner.getName());
                return;
            }
        }

        throw new InvalidInputException("Pet not found");
    }

    private void addVeterinarian() throws InvalidInputException {
        System.out.print("Veterinarian ID: ");
        int id = readInt();

        if (!Validating.isVetIdUnique(id, vets)) {
            throw new InvalidInputException("Veterinarian with this ID already exists!");
        }

        System.out.print("Name: ");
        String name = readNonEmpty();
        if (!Validating.isValidStr(name)) {
            throw new InvalidInputException("Name shouldn't be blank!");
        }

        System.out.print("Phone: ");
        String phone = readNonEmpty();
        if (!Validating.isNumber(phone)) {
            throw new InvalidInputException("Invalid phone number!");
        }

        System.out.print("Animal speciality: ");
        String speciality = readNonEmpty();

        System.out.print("Experience years: ");
        int exp = readInt();

        System.out.print("Work Status (true/false): ");
        boolean onwork = readBoolean();

        vets.add(new Veterenarian(id, name, phone, speciality, exp, onwork));
        System.out.println("Veterinarian added!");
    }

    private void viewAllVets() {
        System.out.println("\n======= ALL VETERINARIANS =======");
        for (Veterenarian vet : vets) {
            System.out.println(vet);
        }
        System.out.println("Press Enter...");
        scanner.nextLine();
    }

    private void rateVet() throws InvalidInputException {
        System.out.print("Enter Veterinarian ID: ");
        int id = readInt();

        for (Veterenarian vet : vets) {
            if (vet.getId() == id) {
                System.out.print("Enter rating (1-5): ");
                int rate = readInt();
                vet.addRating(rate);
                System.out.println("Rated!");
                return;
            }
        }

        throw new InvalidInputException("Veterinarian not found!");
    }

    private void bookVet() throws InvalidInputException {
        System.out.print("Enter Veterinarian ID: ");
        int vetId = readInt();

        Veterenarian selectedVet = null;
        for (Veterenarian vet : vets) {
            if (vet.getId() == vetId) {
                selectedVet = vet;
                break;
            }
        }

        if (selectedVet == null) throw new InvalidInputException("Veterinarian not found");
        if (!selectedVet.isOnWork()) throw new InvalidInputException("Veterinarian is not on work today");

        System.out.print("Enter Pet ID: ");
        int petId = readInt();

        for (Pet pet : pets) {
            if (pet.getId() == petId) {
                System.out.println("Pet " + pet.getName() + " is booked with Dr. " + selectedVet.getName());
                return;
            }
        }

        throw new InvalidInputException("Pet not found");
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

    private String readNonEmpty() throws InvalidInputException {
        String s = scanner.nextLine();
        if (s == null || s.trim().isEmpty()) {
            throw new InvalidInputException("Input cannot be empty!");
        }
        return s.trim();
    }
}
