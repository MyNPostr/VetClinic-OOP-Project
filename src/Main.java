import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Pet> pets = new ArrayList<>();
    static ArrayList<Owner> owners = new ArrayList<>();
    static ArrayList<Veterenarian> vets = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Hello Everyone!\nThis application is designed for veterinary clinics to manage clients, veterinarians, pets, appointments, and treatments.");

        int choice;

        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addPet();
                case 2 -> viewAllPets();
                case 3 -> addOwner();
                case 4 -> viewAllOwners();
                case 5 -> addVeterinarian();
                case 6 -> viewAllVets();
                case 0 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid choice! Write numbers from 0 to 6 that you need.");
            }

        } while (choice != 0);
    }

    static void showMenu() {
        System.out.println("\n=================================");
        System.out.println("        VET CLINIC SYSTEM");
        System.out.println("=================================");
        System.out.println("1. Add Pet");
        System.out.println("2. View All Pets");
        System.out.println("3. Add Owner");
        System.out.println("4. View All Owners");
        System.out.println("5. Add Veterinarian");
        System.out.println("6. View All Veterinarians");
        System.out.println("0. Exit");
        System.out.println("=================================");
        System.out.print("Enter your choice: ");
    }

    static void addPet() {
        System.out.print("Enter Pet ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (!Validating.isPetIdUnique(id)) {
            System.out.println("Pet with this ID already exists!");
            return;
        }

        System.out.print("Name: ");
        String name = scanner.nextLine();
        if (!Validating.isValidStr(name)) {
            System.out.println("Name shouldn't be blank!");
            return;
        }

        System.out.print("Species: ");
        String species = scanner.nextLine();
        if (!Validating.isValidStr(species)) {
            System.out.println("Species shouldn't be blank!");
            return;
        }

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Gender (MALE/FEMALE): ");
        Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Vaccinated (true/false): ");
        boolean vaccinated = scanner.nextBoolean();

        pets.add(new Pet(id, name, species, age, gender, vaccinated));
        System.out.println("Pet added!");
    }

    static void viewAllPets() {
        System.out.println("\n============ ALL PETS ===========");
        for (Pet pet : pets) {
            System.out.println(pet);
        }
        System.out.println("Press Enter...");
        scanner.nextLine();
    }

    static void addOwner() {
        System.out.print("Enter Owner ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (!Validating.isOwnerIdUnique(id)) {
            System.out.println("Owner with this ID already exists!");
            return;
        }

        System.out.print("Name: ");
        String name = scanner.nextLine();

        if (!Validating.isValidStr(name)) {
            System.out.println("Name shouldn't be blank!");
            return;
        }

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        if (!Validating.isNumber(phone)) {
            System.out.println("Invalid phone number!");
            return;
        }

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        if (Validating.isValidStr(email) &&  email.contains("@")) {
            System.out.println("Invalid email!");
            return;
        }

        System.out.print("Gender (MALE/FEMALE): ");
        Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());

        owners.add(new Owner(id, name, phone, address, email, gender));
        System.out.println("Owner added!");
    }

    static void viewAllOwners() {
        System.out.println("\n=========== ALL OWNERS ==========");
        for (Owner owner : owners) {
            System.out.println(owner);
        }
        System.out.println("Press Enter...");
        scanner.nextLine();
    }

    static void addVeterinarian() {
        System.out.print("Veterinarian ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (!Validating.isVetIdUnique(id)) {
            System.out.println("Veterinarian with this ID already exists!");
            return;
        }

        System.out.print("Name: ");
        String name = scanner.nextLine();

        if (!Validating.isValidStr(name)) {
            System.out.println("Name shouldn't be blank!");
            return;
        }

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        if (!Validating.isNumber(phone)) {
            System.out.println("Invalid phone number!");
            return;
        }

        System.out.print("Animal speciality: ");
        String speciality = scanner.nextLine();

        System.out.print("Experience years: ");
        int exp = scanner.nextInt();

        System.out.print("Work Status(On work or not - true/false): ");
        boolean onwork = scanner.nextBoolean();

        vets.add(new Veterenarian(id, name, phone, speciality, exp, onwork));
        System.out.println("Veterinarian added!");
    }

    static void viewAllVets() {
        System.out.println("\n======= ALL VETERINARIANS =======");
        for (Veterenarian vet : vets) {
            System.out.println(vet);
        }

        System.out.println("\nActions:");
        System.out.println("1. Rate Veterinarian");
        System.out.println("2. Book Appointment");
        System.out.println("0. Back");
        System.out.print("Choose action: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> rateVet();
            case 2 -> bookVet();
            case 0 -> { }
            default -> System.out.println("Invalid choice");
        }
    }

    static void rateVet() {
        System.out.print("Enter Veterinarian ID: ");
        int id = scanner.nextInt();

        for (Veterenarian vet : vets) {
            if (vet.getId() == id) {
                System.out.print("Enter rating (1-5): ");
                int rate = scanner.nextInt();
                vet.addRating(rate);
                return;
            }
        }
        System.out.println("Veterinarian not found!");
    }

    static void bookVet() {

        System.out.print("Enter Veterinarian ID: ");
        int vetId = scanner.nextInt();

        Veterenarian selectedVet = null;
        for (Veterenarian vet : vets) {
            if (vet.getId() == vetId) {
                selectedVet = vet;
                break;
            }
        }

        if (selectedVet == null) {
            System.out.println("Veterinarian not found");
            return;
        }

        if (!selectedVet.isOnWork()) {
            System.out.println("Veterinarian is not on work today");
            return;
        }

        System.out.print("Enter Pet ID: ");
        int petId = scanner.nextInt();

        for (Pet pet : pets) {
            if (pet.getId() == petId) {
                System.out.println("Pet " + pet.getName() +
                        " is booked with Dr. " + selectedVet.getName());
                return;
            }
        }

        System.out.println("Pet not found");
    }
}