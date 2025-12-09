public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Everyone!\nThis application is designed for veterinary clinics to manage clients, veterinarians, pets, appointments, and treatments.");
        Pet mops = new Pet("Beks", "Мопс", 4, false);
        Pet mops2 = new Pet("Arlendr", "Мопс", 7, false);
        Owner arystan = new Owner("Arystan", "+77769799777", "254290@astanait.edu.kz", "Kabanbay Batyr Avenue");

        System.out.println(mops.getAge());
        System.out.println(mops.getName());
        System.out.println(mops.isVaccinated());
        mops.setName("Arliondr");
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


    }
}