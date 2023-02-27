import java.util.Random;

public class Question6 {
    public static void main(String[] args){
        System.out.println(smallChange("000000000000000000000000"));
        System.out.println("000000000000000000000000");

    }

    public static String smallChange(String binary){
        String binaryCopy = binary;
        Random rand = new Random();
        int x = rand.nextInt(0,24);
        if(binaryCopy.charAt(x)=='0'){
            binaryCopy = binaryCopy.substring(0,x)+'1'+binaryCopy.substring(x+1);

        }
        else{
            binaryCopy = binaryCopy.substring(0,x)+'0'+binaryCopy.substring(x+1);
        }
        return binaryCopy;
    }
}
