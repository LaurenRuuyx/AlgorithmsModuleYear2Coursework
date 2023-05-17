public class Question5 {
    
    public static void main(String[] args){
        System.out.println(fitnessFunction("001001001001001001001001"));        
    }

    public static Integer fitnessFunction(String binary){
        if(binary == null){
            return null;
        }
        if(binary.length() != 24){
            return null;
        }
        String[] results = binary.split("(?<=\\G.{" + 3 + "})");
        int fitness = 56;
        int[] integerValues = new int[8];
        for(int i=0; i<8; i++){
            integerValues[i] = Integer.parseInt(results[i],2);
        }  
        for(int i=0; i<8; ++i){
            for(int j=0; j<8; ++j){
                if(i!=j){
                    if(integerValues[i]==integerValues[j]){
                        fitness -= 1;
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

    // public static int fitnessFunction(String binary){
    //     String[] results = binary.split("(?<=\\G.{" + 3 + "})");
    //     int fitness = 56;
    //     int[] integerValues = new int[8];
    //     for(int i=0; i<8; i++){
    //         integerValues[i] = Integer.parseInt(results[i],2);
    //     }
    //     for(int i=0; i<8; ++i){
    //         boolean columntop = false;
    //         boolean columnbottom = false;
    //         boolean diag1 = false;
    //         boolean diag2 = false;
    //         boolean diag3 = false;
    //         boolean diag4 = false;

    //         for(int j=0; j<8; ++j){
    //             if(i!=j){
    //                 if(integerValues[i]==integerValues[j]){
    //                     if(i>j && columnbottom == false){
    //                         fitness -= 1;
    //                         columnbottom = true;
    //                     }
    //                     if(i<j && columntop == false){
    //                         columntop = true;
    //                         fitness -= 1;

    //                     }
    //                 }
    //                 int rowdiff = i-j;
    //                 int coldiff = integerValues[i]-integerValues[j];
    //                 if(Math.abs(rowdiff) == Math.abs(coldiff)){
    //                     if(rowdiff>0 && coldiff>0 && diag1==false){
    //                         diag1=true;
    //                         fitness -=1;
    //                     }
    //                     if(rowdiff>0 && coldiff<0 && diag2==false){
    //                         diag2=true;
    //                         fitness -=1;
    //                     }
    //                     if(rowdiff<0 && coldiff<0 && diag3==false){
    //                         diag3=true;
    //                         fitness -=1;
    //                     }
    //                     if(rowdiff<0 && coldiff>0 && diag4==false){
    //                         diag4=true;
    //                         fitness -=1;
    //                     }
                        
    //                 }
    //             }
    //         }
    //     }
    //     return fitness;

    // }
}
