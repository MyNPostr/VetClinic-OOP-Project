public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Everyone!\nThis application is designed for veterinary clinics to manage clients, veterinarians, pets, appointments, and treatments.");
        Pet mops = new Pet("Beks", "Мопс", 4, Gender.FEMALE, false);
        Pet mops2 = new Pet("Arlendr", "Мопс", 7, Gender.MALE, true);
        Owner arystan = new Owner("Arystan", "+77769799777", "254290@astanait.edu.kz", "Kabanbay Batyr Avenue", Gender.MALE);
        Veterenarian sherly = new Veterenarian("Sherly", "+7776979777", "Home Pets", 10);

        System.out.println(mops.getAge());
        System.out.println(mops.getName());
        System.out.println(mops.isVaccinated());
        System.out.println(mops.getGender());
        mops.setName("Gena");
        mops.setAge(6);
        mops.vaccinate();
        System.out.println(mops.getAge());
        mops.birthday();
        System.out.println(mops.getAge());
        System.out.println(mops.getName());
        System.out.println(mops.isVaccinated());
        mops.vaccinate();

        arystan.addpet(mops);
        arystan.petList();
        arystan.addpet(mops2);
        arystan.petList();
        System.out.println(arystan.getName());
        System.out.println(arystan.getAddress() + " " + arystan.getPhone());
        System.out.println(arystan.getEmail());

        System.out.println(sherly.getName());
        System.out.println(sherly.getPhone());
        System.out.println(sherly.isOnWork());
        sherly.startWork();
        System.out.println(sherly.isOnWork());
        sherly.addRating(5);
        sherly.addRating(4);
        sherly.addRating(6);
        System.out.println(sherly.getRating());
        System.out.println(sherly);
    }
}