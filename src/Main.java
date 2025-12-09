public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Everyone!\nThis application is designed for veterinary clinics to manage clients, veterinarians, pets, appointments, and treatments.");
        Pet mops = new Pet("Ars", "Мопс", 4, false);

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
    }
}