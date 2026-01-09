import java.util.ArrayList;

public class Main {

    static ArrayList<Pet> pets = new ArrayList<>();
    static ArrayList<Owner> owners = new ArrayList<>();
    static ArrayList<Veterenarian> vets = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Hello Everyone!\nThis application is designed for veterinary clinics to manage clients, veterinarians, pets, appointments, and treatments.");
        pets.add(new Pet(1, "Beksultan", "Мопс", 4, Gender.FEMALE, false));
        pets.add(new Pet(2, "Arlan", "Scotland Cat", 7, Gender.MALE, true));
        owners.add(new Owner(1 , "Arystan", "+77769799777", "254290@astanait.edu.kz", "Kabanbay Batyr Avenue", Gender.MALE));
        vets.add(new Veterenarian(1, "Sherly", "+7776979777", "Home Pets", 10));

        System.out.println("Testing Pet Class:");
        System.out.println(pets.get(0).getName() + " is " + pets.get(0).getAge() + " years old");
        System.out.println("Pet's name " + pets.get(0).getName());
        if (pets.get(0).isVaccinated()){
            System.out.println(pets.get(0).getName() + " is vaccinated");
        }
        else{
            System.out.println(pets.get(0).getName() + " isn't vaccinated");
        }
        System.out.println(pets.get(0).getName() + "'s Gender is " +pets.get(0).getGender());
        pets.get(0).setName("Gena");
        pets.get(0).setAge(6);
        pets.get(0).vaccinate();
        System.out.println(pets.get(0).getName() + " is " + pets.get(0).getAge() + " years old");
        pets.get(0).birthday();
        System.out.println(pets.get(0).getName() + " is " + pets.get(0).getAge() + " years old");
        System.out.println("Pet's name " + pets.get(0).getName());
        pets.get(0).vaccinate();
        System.out.println(pets.get(0));

        System.out.println("\nTesting Owner Class:");
        owners.get(0).addpet(pets.get(0));
        owners.get(0).petList();
        owners.get(0).addpet(pets.get(1));
        owners.get(0).petList();
        System.out.println("Owner's name is " + owners.get(0).getName());
        System.out.println(owners.get(0).getName() + "'s home adress is " + owners.get(0).getAddress());
        System.out.println(owners.get(0).getName() + "'s email is " + owners.get(0).getEmail());
        System.out.println(owners.get(0).getName() + "'s phone number is " + owners.get(0).getPhone());
        System.out.println(owners.get(0));

        System.out.println("\nTesting Veterenarian Class:");
        System.out.println("Veterenarian name is " + vets.get(0).getName());
        System.out.println(vets.get(0).getName() + "'s Phone Number is " + vets.get(0).getPhone());
        System.out.println(vets.get(0).getName() + " is on work: " + vets.get(0).isOnWork());
        vets.get(0).startWork();
        System.out.println(vets.get(0).getName() + " is on work: " + vets.get(0).isOnWork());
        vets.get(0).addRating(5);
        vets.get(0).addRating(4);
        vets.get(0).addRating(6);
        System.out.println(vets.get(0).getName() + "'s rating is " + vets.get(0).getRating());
        System.out.println(vets.get(0));
    }
}