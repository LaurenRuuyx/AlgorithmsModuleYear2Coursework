import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TSP {

    public static void main(String[] args){
        int[][] x = {
            {0, 40, 12, 2, 44, 28, 50, 36, 21, 19}
            ,{40, 0, 42, 13, 26, 50, 46, 1, 6, 16}
            ,{12, 42, 0, 30, 11, 40, 33, 3, 3, 14}
            ,{2, 13, 30, 0, 2, 21, 37, 39, 29, 9}
            ,{44, 26, 11, 2, 0, 45, 28, 10, 33, 32}
            ,{28, 50, 40, 21, 45, 0, 44, 3, 43, 18}
            ,{50, 46, 33, 37, 28, 44, 0, 6, 35, 5}
            ,{36, 1, 3, 39, 10, 3, 6, 0, 9, 30},
            {21, 6, 3, 29, 33, 43, 35, 9, 0, 4}
            ,{19, 16, 14, 9, 32, 18, 5, 30, 4, 0}
        };

       System.out.println( averageWorseChange(100, x));

    }

    public static int[][] RandomMatrix(int n,int lower,int upper){
        if(n<=0){
            return null;
        }
        if(lower>upper){
            return null;
        }
        int g[][] = new int[n][n];
        
        for (int i=0; i<n; ++i){
            for (int j=0; j<n; ++j){
                if (j==i){
                    g[i][j] = 0;
                }
                else if (j<i){
                    g[i][j] = g[j][i];
                }
                else{
                    Random r = new Random();
                    g[i][j] = r.nextInt(upper-lower+1) + lower;
                }
            }
        }
        return g;
    }

    public static int distance(int[][] map, int x, int y){
        return(map[x][y]);
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
                if(count != x){
                    permutationArray[count] = x;
                    allowedNumbers.remove(x);
                    count += 1;
                }
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

    public static int fitnessTSP(int[][] map, int[] permutationArray){
        int fitness = 0;
        for(int i=0; i<permutationArray.length; ++i){
            fitness += map[i][permutationArray[i]];
        }
        return fitness;

    }
    public static double averageWorseChange(double iter,int[][] map){
        double av = 0;
        for(int i=0; i<iter; i++){
            int[] sol = randomPermutation(map.length);
            int[] potSol = smallChange(sol);
            double x = Math.abs(fitnessTSP(map,sol)-fitnessTSP(map,potSol));
            av += x;

        }
        av = av/iter;
        return av;

    }
    
    
}
