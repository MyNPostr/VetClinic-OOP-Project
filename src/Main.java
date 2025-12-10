public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Everyone!\nThis application is designed for veterinary clinics to manage clients, veterinarians, pets, appointments, and treatments.");
        Pet mops = new Pet("Beksultan", "Мопс", 4, Gender.FEMALE, false);
        Pet mops2 = new Pet("Arlan", "Scotland Cat", 7, Gender.MALE, true);
        Owner arystan = new Owner("Arystan", "+77769799777", "254290@astanait.edu.kz", "Kabanbay Batyr Avenue", Gender.MALE);
        Veterenarian sherly = new Veterenarian("Sherly", "+7776979777", "Home Pets", 10);

        System.out.println("Testing Pet Class:");
        System.out.println(mops.getName() + " is " + mops.getAge() + " years old");
        System.out.println("Pet's name " + mops.getName());
        if (mops.isVaccinated()){
            System.out.println(mops.getName() + " is vaccinated");
        }
        else{
            System.out.println(mops.getName() + " isn't vaccinated");
        }
        System.out.println(mops.getName() + "'s Gender is " + mops.getGender());
        mops.setName("Gena");
        mops.setAge(6);
        mops.vaccinate();
        System.out.println(mops.getName() + " is " + mops.getAge() + " years old");
        mops.birthday();
        System.out.println(mops.getName() + " is " + mops.getAge() + " years old");
        System.out.println("Pet's name " + mops.getName());
        mops.vaccinate();
        System.out.println(mops);

        System.out.println("\nTesting Owner Class:");
        arystan.addpet(mops);
        arystan.petList();
        arystan.addpet(mops2);
        arystan.petList();
        System.out.println("Owner's name is " + arystan.getName());
        System.out.println(arystan.getName() + "'s home adress is " + arystan.getAddress());
        System.out.println(arystan.getName() + "'s email is " + arystan.getEmail());
        System.out.println(arystan.getName() + "'s phone number is " + arystan.getPhone());
        System.out.println(arystan);

        System.out.println("\nTesting Veterenarian Class:");
        System.out.println("Veterenarian name is " + sherly.getName());
        System.out.println(sherly.getName() + "'s Phone Number is " + sherly.getPhone());
        System.out.println(sherly.getName() + " is on work: " + sherly.isOnWork());
        sherly.startWork();
        System.out.println(sherly.getName() + " is on work: " + sherly.isOnWork());
        sherly.addRating(5);
        sherly.addRating(4);
        sherly.addRating(6);
        System.out.println(sherly.getName() + "'s rating is " + sherly.getRating());
        System.out.println(sherly);
    }
}