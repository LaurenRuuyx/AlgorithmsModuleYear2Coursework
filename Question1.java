public class Question1{

    public static void main(String[] args){
        System.out.println(checkCharacter('.'));

    }
    public static boolean checkCharacter(Character character){
        if (character == null){
            return false;
        }
        if(character == 'Q' || character == '.'){
            return true;
        }
        return false;
    }
}