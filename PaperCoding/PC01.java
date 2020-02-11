package PaperCoding;
import java.util.*;

public class PC01{
    int input;
    Scanner scan = new Scanner(System.in);

    public PC01(){
        System.out.print("Input: ");
        input = scan.nextInt();
        triangle(input);
    }

    public void triangle(int k){
        for(int i = 0; i < k; i++){
            for(int j = k; j > i; j--)
                System.out.print(" ");
            for(int z = 0; z <= i; z++)
                System.out.print("* ");
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        new PC01();
    }

    
}