package RestLearning;

import java.util.*;

public class Training {

    public static void main(String[] args) {
        Iterator i;
        List<Integer> myList = new ArrayList<>();
        Collections.addAll(myList, 343,34,54,35,34,54,3,243,45,45,2,5423,5);
        i = myList.iterator();

        while (i.hasNext())
        {System.out.println(i.next());}



    }
}
