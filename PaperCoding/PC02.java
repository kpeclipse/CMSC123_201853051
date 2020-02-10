package PaperCoding;
import java.util.*;

public class PC02{
    int input;
    Scanner scan = new Scanner(System.in);

    public PC02(){
        System.out.print("Input: ");
        input = scan.nextInt();
        SawBlade(input);
    }

    public void SawBlade(int k){
        for(int i = 0; i < k; i++){
            for(int j = i; j >= 0; j--){
                for(int x = j; x >= 0; x--)
                    System.out.print("*");
                for(int y = k-1; y > i; y--)
                    System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        new PC02();
    }

    
}