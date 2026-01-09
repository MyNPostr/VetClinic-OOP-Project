public class Validating {
    public static boolean isNumber(String input){
        char[] chars = input.toCharArray();

        for(int i = 0; i < chars.length; i++){
            char c = chars[i];

            if(i == 0 && c == '+'){
                continue;
            }

            if (!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }

    public static boolean isValidStr(String input){
        if(input != null && !input.isBlank()){
            return true;
        }
        return false;
    }

    public static boolean isPetIdUnique(int id) {
        for (Pet pet : Main.pets) {
            if (pet.getId() == id) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOwnerIdUnique(int id) {
        for (Owner owner : Main.owners) {
            if (owner.getId() == id) {
                return false;
            }
        }
        return true;
    }

    public static boolean isVetIdUnique(int id) {
        for (Veterenarian vet : Main.vets) {
            if (vet.getId() == id) {
                return false;
            }
        }
        return true;
    }
}
