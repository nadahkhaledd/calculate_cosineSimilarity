package cosineSimilarity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Run {
    public static void main(String args[]) throws IOException {
        CosineSimilarity cosineSimilarity = new CosineSimilarity();
        String[] docs = {"docs/100.txt",
                        "docs/101.txt",
                        "docs/102.txt",
                        "docs/103.txt"};
        //cosineSimilarity.findCosineSimilarity(docs);

//        ArrayList<Integer> d = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 0, 0));
//        ArrayList<Integer> d2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
//        System.out.println(cosineSimilarity.calculateMagnitude(d));

    }
}
