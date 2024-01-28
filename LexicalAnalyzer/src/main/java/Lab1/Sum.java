package Lab1;

import java.util.Scanner;

public class Sum {
    public static void main(String[] args) {

        int s = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("n = ");
        int n = in.nextInt();
        System.out.println("The numbers are: ");
        while (n != 0){
            int x = in.nextInt();
            s = s + x;
            n = n - 1;
        }
        System.out.println("sum = " + s);
    }
}
