package cosineSimilarity;

import java.io.IOException;
import java.util.HashMap;

public class Run {
    public static void main(String args[]) throws IOException {
        CosineSimilarity cosineSimilarity = new CosineSimilarity();
        String[] docs = {"docs/100.txt",
                        "docs/101.txt",
                        "docs/102.txt",
                        "docs/103.txt"};


        HashMap<String, Double> cosineSimilarities = cosineSimilarity.findCosineSimilarity(docs);

        System.out.println("Cosine Similarities: ");
        for(String similarity : cosineSimilarities.keySet())
        {
            System.out.println(similarity + " " + cosineSimilarities.get(similarity).toString());

        }
    }
}
