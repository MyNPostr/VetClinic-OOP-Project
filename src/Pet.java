public class Pet {
    private String name;
    private String species;
    private int age;
    private boolean vaccinated;

    public Pet(String name, String species, int age, boolean vaccinated) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.vaccinated = vaccinated;
    }
    public String getName() {return name;}
    public String getSpecies() {return species;}
    public int getAge() {return age;}
    public boolean isVaccinated() {return vaccinated;}
    public void setName(String name) {this.name = name;}
    public void setAge(int age) {this.age = age;}

    public void birthday(){
        this.age++;
        System.out.printf("%s turned %d!\n", name, age);
    }

    public void vaccinate(){
        if (!vaccinated){
            this.vaccinated = true;
            System.out.printf("%s has been vaccinated!\n", name);
        }
        else{
            System.out.printf("%s is already vaccinated!\n", name);
        }
    }
}
