import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NQueensProblem {

    public static void main(String[] args){

        System.out.println(simmulatedAnnealing(500, 20));
        System.out.println(hillClimb(500, 20));

        
    }

    public static int[] randomPermutation(int n){
        int[] permutationArray = new int[n];
        Set<Integer> allowedNumbers = new HashSet<Integer>();
        for(int i=0; i<n; ++i){
            allowedNumbers.add(i);
        }
        Random rand = new Random();
        int count = 0;
        while(allowedNumbers.size() > 0){
            int x = rand.nextInt(0,n);
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
        int y = rand.nextInt(0,board.length);
        int z = rand.nextInt(0,board.length);
        while(z==y){
            z = rand.nextInt(0,board.length);
        }
        int temp = x[y];
        x[y] = x[z];
        x[z] = temp;
        return x;
    }

    public static int fitnessFunction(int[] integerValues){
        int fitness = (integerValues.length) * (integerValues.length-1);
        for(int i=0; i<integerValues.length; ++i){
            for(int j=0; j<integerValues.length; ++j){
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

    public static int hillClimb(int n, int nQueens){
        int[] solution = randomPermutation(nQueens);
        for(int i=0; i<n; ++i){
            int[] potSol = smallChange(solution);
            if(fitnessFunction(potSol)>fitnessFunction(solution)){
                solution = potSol.clone();
            }

        }

        return fitnessFunction(solution);
    }

    public static double simmulatedAnnealing(int iter, int nQueens){
        double tempsetter = (5.0/100.0) * iter;
        double fitgetter = iter - tempsetter;
        double temp_0 = 0;
        for(int i=0; i<tempsetter; ++i){
            int[] sol = randomPermutation(nQueens);
            int[] potsol = smallChange(sol);
            double x = Math.abs(fitnessFunction(sol)-fitnessFunction(potsol));
            temp_0 += x;
        }
        temp_0 = (temp_0 / tempsetter);

        int[] solution = randomPermutation(nQueens);
        int[] BestSol = solution.clone();
        double currentTemp = temp_0;
        double x = 0.001/temp_0;
        double coolingRate = Math.pow(x,1.0/fitgetter);
        for(int i=0; i<fitgetter; ++i){
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
        return (double)fitnessFunction(BestSol);        
    }

    public static double PR(double oldFitness,double newFitness,double currentTemp){
        double difference = Math.abs(oldFitness-newFitness);
        double variable = ((difference * -1)/currentTemp);
        double p = Math.exp(variable);
        return p;
    }

    // public static double averageWorseChange(double iter){
    //     double av = 0;
    //     for(int i=0; i<iter; i++){
    //         int[] sol = randomPermutation();
    //         int[] potSol = smallChange(sol);
    //         double x = Math.abs(fitnessFunction(sol)-fitnessFunction(potSol));
    //         av += x;

    //     }
    //     av = av/iter;
    //     return av;

    // }


    
}
