import java.util.Random;

public class Question4 {

    public static void main(String[] args){
        System.out.println(randomBinaryString());

    }

    public static String randomBinaryString(){
        String binary = "";
        Random rand = new Random();
        for(int i=0; i<24; ++i){
            int x = rand.nextInt(0,2);
            binary += x;
        }
        return binary;
    }
}
