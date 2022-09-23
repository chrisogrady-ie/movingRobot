
/*
 * CHristopher O GRady
 * R00067022
 */

//bottom up approach
public class Main {

    public static int size = 5;
    public static double[][] matrix = new double[size][size];
    public static double ZERO = 0;
    public static double R = 1.1; //1.1 or 1.5
    public static double D = 1.3; //1.3 or 1.2
    public static double RD = 2.3; // 2.5 or 2.3
    public static double cost = 0.0;
    public static String route = "0";


    //populating matrix with most efficient cost of each move
    public static double calcCosts(int I, int J) {
        //starting point is 0 cost
        matrix[0][0] = ZERO;
        //cost of first row is all right movements
        for(int j = 1;j <= J; j++) {
            matrix[0][j] = matrix[0][j-1] + R;
        }

        for(int i = 1;i <= I; i++) {
            //cost of first column is all down movements
            matrix[i][0] = matrix[i-1][0] + D;
            //finding the minimum cost from the previous
            for(int j = 1;j <= J; j++) {
                matrix[i][j] = Math.min(Math.min(matrix[i][j-1] + R, matrix[i-1][j] + D),
                        matrix[i-1][j-1] + RD);
            }
        }//end i
        double c = matrix[I][J];
        return c;
    }//end move

    //getting the optimal route from populated matrix
    public static void getRoute() {
        //counting cost so far
        double count = 0;
        //row and column counter
        int r = 1;
        int c = 1;

        while(count != cost) {
            //our most efficient move to move diagonally - R,D / D,R / RD
            double lowest = Math.min(Math.min(matrix[r][c-1] + R, matrix[r-1][c] + D),
                    matrix[r-1][c-1] + RD);
            if(lowest == matrix[r][c-1] + R) {
                route += "+R+D";
                count = (Math.round(lowest*100.0)/100.0);
                r++; c++;
            }else if(lowest == matrix[r-1][c] + D) {
                route += "+D+R";
                count = (Math.round(lowest*100.0)/100.0);
                r++; c++;
            }else {
                route += "+RD";
                count = (Math.round(lowest*100.0)/100.0);
                r++; c++;
            }
        }
    }//end getRoute


    //main
    public static void main(String[] args) {
        cost = calcCosts(4,4);
        getRoute();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                System.out.print(Math.round(matrix[i][j] * 100.0)/100.0 + " ");
            }
            System.out.println();
        }
        System.out.println("\nCost of movement = " + cost);
        System.out.println(route);
    }

}
