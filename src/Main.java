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
            mainMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> petMenu();
                case 2 -> ownerMenu();
                case 3 -> vetMenu();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    static void mainMenu() {
        System.out.println("\n=================================");
        System.out.println("        VET CLINIC SYSTEM");
        System.out.println("=================================");
        System.out.println("1. Pets Menu");
        System.out.println("2. Owners Menu");
        System.out.println("3. Veterinarians Menu");
        System.out.println("0. Exit System");
        System.out.print("Enter choice: ");
    }

    public static void petMenu() {
        int choice;
        do {
            System.out.println("\n=================================");
            System.out.println("              PET MENU");
            System.out.println("=================================");
            System.out.println("1. Add Pet");
            System.out.println("2. View All Pets");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addPet();
                case 2 -> viewAllPets();
                case 0 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    public static void ownerMenu() {
        int choice;
        do {
            System.out.println("\n=================================");
            System.out.println("              OWNER MENU");
            System.out.println("=================================");
            System.out.println("1. Add Owner");
            System.out.println("2. View All Owners");
            System.out.println("3. Add Pet to Owner");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addOwner();
                case 2 -> viewAllOwners();
                case 3 -> addPetToOwner();
                case 0 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    public static void vetMenu() {
        int choice;
        do {
            System.out.println("\n=================================");
            System.out.println("              VET MENU");
            System.out.println("=================================");
            System.out.println("1. Add Veterinarian");
            System.out.println("2. View All Veterinarians");
            System.out.println("3. Rate Veterinarian");
            System.out.println("4. Book Veterinarian");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addVeterinarian();
                case 2 -> viewAllVets();
                case 3 -> rateVet();
                case 4 -> bookVet();
                case 0 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    static void addPet () {
        System.out.print("Enter pet ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (!Validating.isPetIdUnique(id)) {
            System.out.println("ID already exists!");
            return;
        }

        System.out.print("Enter pet name: ");
        String name = scanner.nextLine();

        System.out.println("Choose animal type:");
        System.out.println("1. Dog");
        System.out.println("2. Cat");
        System.out.println("3. Bird");

        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Is vaccinated (true/false)?: ");
        boolean vaccinated = scanner.nextBoolean();
        scanner.nextLine();

        Animal animal = null;

        if (type == 1) {
            System.out.print("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter gender (MALE/FEMALE): ");
            Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Enter breed: ");
            String breed = scanner.nextLine();

            System.out.print("Is trained (true/false)?: ");
            boolean trained = scanner.nextBoolean();
            scanner.nextLine();

            animal = new Dog(age, breed, gender, trained);

        } else if (type == 2) {
            System.out.print("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter breed: ");
            String breed = scanner.nextLine();

            System.out.print("Enter gender (MALE/FEMALE): ");
            Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Is indoor (true/false)?: ");
            boolean indoor = scanner.nextBoolean();
            scanner.nextLine();

            animal = new Cat(age, breed, gender, indoor);

        } else if (type == 3) {
            System.out.print("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter breed: ");
            String breed = scanner.nextLine();

            System.out.print("Enter gender (MALE/FEMALE): ");
            Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Does they have teeth (true/false)?: ");
            boolean hasteeth = scanner.nextBoolean();

            animal = new Bird(age, breed, gender, hasteeth);
        }

        Pet pet = new Pet(id, name, vaccinated, animal);
        pets.add(pet);
        System.out.println("Pet added successfully!");
    }

    static void viewAllPets () {
        System.out.println("\n============ ALL PETS ===========");
        for (Pet pet : pets) {
            System.out.println(pet);
        }
        System.out.println("Press Enter...");
        scanner.nextLine();
    }

    static void addOwner () {
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

        if (Validating.isValidStr(email) && email.contains("@")) {
            System.out.println("Invalid email!");
            return;
        }

        System.out.print("Gender (MALE/FEMALE): ");
        Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());

        owners.add(new Owner(id, name, phone, address, email, gender));
        System.out.println("Owner added!");
    }

    static void viewAllOwners () {
        System.out.println("\n=========== ALL OWNERS ==========");
        for (Owner owner : owners) {
            System.out.println(owner);
        }
        System.out.println("Press Enter...");
        scanner.nextLine();
    }

    static void addPetToOwner () {

        System.out.print("Enter Owner ID: ");
        int OwnerId = scanner.nextInt();

        Owner selectedOwner = null;
        for (Owner owner : owners) {
            if (owner.getId() == OwnerId) {
                selectedOwner = owner;
                break;
            }
        }

        if (selectedOwner == null) {
            System.out.println("Owner not found");
            return;
        }

        System.out.print("Enter Pet ID: ");
        int petId = scanner.nextInt();

        for (Pet pet : pets) {
            if (pet.getId() == petId) {
                selectedOwner.addownpet(pet);
                System.out.println("Pet " + pet.getName() +
                        " is added to " + selectedOwner.getName());
                return;
            }
        }
        System.out.println("Pet not found");
    }

    static void addVeterinarian () {
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

    static void viewAllVets () {
        System.out.println("\n======= ALL VETERINARIANS =======");
        for (Veterenarian vet : vets) {
            System.out.println(vet);
        }
    }

    static void rateVet () {
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

    static void bookVet () {

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