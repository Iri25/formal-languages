package Lab1;

public class Cmmdc {
    public static void main(String[] args) {

        int a = 8;
        int b = 9;
        while (a != b) {
            if (a > b)
                a = a - b;
            else
                b = b - a;
        }
        System.out.println("cmmdc = " + a);
    }
}

