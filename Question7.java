import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Question7 {
    
    public static void main(String[] args){
    //     double avg = 0;
    //     int iter = 5000;
    //    for(int i=0; i<iter; ++i){
    //     avg += (simmulatedAnnealing(i));

    //    }
    //    System.out.println(avg/iter);
    System.out.println(simmulatedAnnealing(50));


        
    }

    public static int[] randomPermutation(){
        int[] permutationArray = new int[8];
        Set<Integer> allowedNumbers = new HashSet<Integer>();
        for(int i=0; i<8; ++i){
            allowedNumbers.add(i);
        }
        Random rand = new Random();
        int count = 0;
        while(allowedNumbers.size() > 0){
            int x = rand.nextInt(0,8);
            if(allowedNumbers.contains(x)){
                permutationArray[count] = x;
                allowedNumbers.remove(x);
                count += 1;
            }
        }
        return permutationArray;
    }

    public static int[] smallChange(int[] board){
        int[] x = board.clone();
        Random rand = new Random();
        int y = rand.nextInt(0,8);
        int z = rand.nextInt(0,8);
        while(z==y){
            z = rand.nextInt(0,8);
        }
        int temp = x[y];
        x[y] = x[z];
        x[z] = temp;
        return x;
    }

    public static int fitnessFunction(int[] integerValues){
        int fitness = 56;  
        for(int i=0; i<8; ++i){


            for(int j=0; j<8; ++j){
                if(i!=j){
                    if(integerValues[i]==integerValues[j]){
                        fitness -=1;
                    }
                    int rowdiff = Math.abs(i-j);
                    int coldiff = Math.abs(integerValues[i]-integerValues[j]);
                    if(rowdiff == coldiff){
                        fitness -= 1;
                    }
                }
            }
        }
        return fitness;

    }

    public static String hillClimb(int n){
        String binary = "";
        int[] solution = randomPermutation();
        for(int i=0; i<n; ++i){
            int[] potSol = smallChange(solution);
            if(fitnessFunction(potSol)>fitnessFunction(solution)){
                solution = potSol.clone();
            }

        }
        for(int i=0; i<solution.length; ++i){
            String temp = Integer.toBinaryString(solution[i]);
            temp = String.format("%3s", temp).replaceAll(" ", "0");
            binary = binary + temp;
        }

        return binary;
    }

    public static double simmulatedAnnealing(int iter){
        double temp_0 = 3.532742;
        String binary = "";
        int[] solution = randomPermutation();
        int[] BestSol = solution.clone();
        double currentTemp = temp_0;
        double x = 0.001/temp_0;
        double coolingRate = Math.pow(x,1.0/iter);
        for(int i=0; i<iter; ++i){
            int[] potSol = smallChange(solution);
            if(fitnessFunction(potSol)>fitnessFunction(solution)){
                solution = potSol.clone();
                if(fitnessFunction(BestSol)<fitnessFunction(potSol)){
                    BestSol = potSol.clone();

                }
            }
            else{
                double p = PR((double)fitnessFunction(solution), (double)fitnessFunction(potSol), currentTemp);
                Random rand = new Random();
                double ur = rand.nextDouble(0,1);
                if(p>ur){
                    solution = potSol.clone();
                }
            }
            currentTemp = temp_0 * Math.pow(coolingRate,i);
        }
        for(int i=0; i<BestSol.length; ++i){
            String temp = Integer.toBinaryString(BestSol[i]);
            temp = String.format("%3s", temp).replaceAll(" ", "0");
            binary = binary + temp;
        }
        for(int i=0; i<BestSol.length; ++i){
        }
        return fitnessFunction(BestSol);        
    }

    public static double PR(double oldFitness,double newFitness,double currentTemp){
        double difference = Math.abs(oldFitness-newFitness);
        double variable = ((difference * -1)/currentTemp);
        double p = Math.exp(variable);
        return p;
    }

    public static double averageWorseChange(double iter){
        double av = 0;
        for(int i=0; i<iter; i++){
            int[] sol = randomPermutation();
            int[] potSol = smallChange(sol);
            double x = Math.abs(fitnessFunction(sol)-fitnessFunction(potSol));
            av += x;

        }
        av = av/iter;
        return av;

    }
}
