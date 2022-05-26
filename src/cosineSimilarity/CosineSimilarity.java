package cosineSimilarity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CosineSimilarity {
    private final Map<Integer, String> documentsName;
    private final ArrayList<ArrayList<String>> documents;

    CosineSimilarity() {
        documentsName = new HashMap<>();
        documents = new ArrayList<>();
    }

    private void buildFiles(String[] docs) {
        int i = 0;
        for (String fname : docs) {
            try {
                documentsName.put(i, fname.substring(5, 8));
                String file;
                ArrayList<String> document = null;
                if ((file = Files.readString(Paths.get(fname))) != null) {
                    document = new ArrayList<>(Arrays.asList(file.split("\\W+")));
                }
                documents.add(document);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
    }

    private ArrayList<String> createTable()
    {
        HashSet<String> tableTemp = new HashSet<>();
        for(ArrayList<String> doc : documents)
            tableTemp.addAll(doc);

        ArrayList<String> table = new ArrayList<String>(tableTemp);
        Collections.sort(table);
        return table;
    }

    private HashMap<String, ArrayList<Integer>> createTFVector(ArrayList<String> table)
    {
        HashMap<String, ArrayList<Integer>> TFVector = new HashMap<>();

        for(int i=0; i<documents.size(); i++)
        {

            ArrayList<Integer> frequencies = new ArrayList<>();
            for(String word : table)
            {
                int count = Collections.frequency(documents.get(i), word);
                frequencies.add(count);
            }
            TFVector.put(documentsName.get(i), frequencies);
        }

        return TFVector;
    }

    private double calculateDotProduct(ArrayList<Integer> doc1, ArrayList<Integer> doc2)
    {
        double dotProduct = 0.0;
        for(int i=0; i<doc1.size(); i++)
            dotProduct += (doc1.get(i) * doc2.get(i));

        return dotProduct;
    }

    private double calculateMagnitude(ArrayList<Integer> frequencies)
    {
        double magnitude = 0.0;

        for(int num : frequencies)
            magnitude += Math.pow(num, 2);

        magnitude = Math.sqrt(magnitude);

        return magnitude;
    }

    private HashMap<String, Double> getDotProductWithRespectToOthers(String docName, HashMap<String, ArrayList<Integer>> TFVector)
    {
        ArrayList<Integer> docFrequencies = TFVector.get(docName);
        HashMap<String, Double> dotProducts = new HashMap<>();
        for(String doc : TFVector.keySet())
        {
            if(!doc.equalsIgnoreCase(docName))
            {
                double dotProduct = calculateDotProduct(docFrequencies, TFVector.get(doc));
                dotProducts.put((docName + " & " + doc + " -> "), dotProduct);
            }
        }
        return dotProducts;
    }

    void findCosineSimilarity(String[] docs)
    {
        buildFiles(docs);
        ArrayList<String> table = createTable();

        HashMap<String, ArrayList<Integer>> TFVector = createTFVector(table);
        HashMap<String, Double> dotProducts = new HashMap<>();
        HashMap<String, Double> magnitudes = new HashMap<>();
        for(String name : TFVector.keySet())
        {
            dotProducts.putAll(getDotProductWithRespectToOthers(name, TFVector));
            magnitudes.put(name, calculateMagnitude(TFVector.get(name)));
        }

    }
}