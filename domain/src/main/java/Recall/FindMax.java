package Recall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FindMax {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Numbers split with space enter print ok: ");

        List<Integer> numbers = new ArrayList<>();
        while (in.hasNextInt()) {
            numbers.add(in.nextInt());
        }
        System.out.println("MAX number is: " + Collections.max(numbers));
    }
}
