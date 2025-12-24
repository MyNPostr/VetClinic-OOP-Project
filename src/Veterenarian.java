public class Veterenarian {
    private String name;
    private String phone;
    private String animalSpeciality; // home pets, farm animals, exotic animals or all of them
    private int experienceYears;
    private boolean onWork;
    private int rating;
    private int ratingcount;

    public Veterenarian(String name, String phone, String animalspeciality, int experienceyears) {
        setName(name);
        setPhone(phone);
        setAnimalSpeciality(animalspeciality);
        setExperienceYears(experienceyears);
        this.onWork = false;
        setRating(0, 0);
    }

    public String getName() {return name;}
    public String getPhone() {return phone;}
    public String getAnimalSpeciality() {return animalSpeciality;}
    public int getExperienceYears() {return experienceYears;}
    public boolean isOnWork() {return onWork;}
    public double getRating() {
        if(ratingcount == 0){
            return 0;
        }
        else{
            return (double) rating / ratingcount;
        }
    }

    public void setName(String name) {
        if (Validating.isValidStr(name)) {
            this.name = name;
        }
        else {
            throw new IllegalArgumentException("Name is invalid, please try again with another name!");
        }
    }
    public void setPhone(String phone) {
        if (Validating.isValidStr(phone) && Validating.isNumber(phone)) {
            this.phone = phone;
        }
        else {
            throw new IllegalArgumentException("Phone is invalid, please try again with another number!");
        }
    }
    public void setOnWork(boolean onWork){this.onWork = onWork;}
    public void setExperienceYears(int experienceyears) {
        if (experienceYears >= 0) {
            this.experienceYears = experienceyears;
        }
        else {
            throw new IllegalArgumentException("Experience years is invalid!");
        }
    }
    public void setAnimalSpeciality(String animalspeciality){
        if (Validating.isValidStr(animalspeciality)) {
            this.animalSpeciality = animalspeciality;
        }
        else {
            throw new IllegalArgumentException("Animal Speciality is invalid");
        }
    }
    public void setRating(int rating, int ratingcount){
        if (rating >= 0 && ratingcount >= 0) {
            this.rating = rating;
            this.ratingcount = ratingcount;
        }
    }


    public void startWork(){this.onWork = true;}
    public void endWork(){this.onWork = false;}
    public void addRating(int rate){
        if (rate >= 1 & rate <= 5){
            this.rating += rate;
            this.ratingcount += 1;
            System.out.println("Rating changed!");
        }
        else{
            System.out.println("Invalid rating");
        }

    }

    @Override
    public String toString() {
        return name + "(" + animalSpeciality + " veterinarian with " + experienceYears + " years of experience)\nRating: " + getRating() + "\nCurrently at work: " + onWork;
    }
}
