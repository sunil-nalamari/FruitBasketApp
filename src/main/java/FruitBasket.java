import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FruitBasket {

    public static String[] csvheader = {"fruit-type", "age-in-days", "characteristic1", "characteristic2"};
    public static List<List<String>> dataset = new ArrayList<List<String>>();

    public static void TotalNumOfFruits() {
        System.out.println("Total number of fruit: " + dataset.size());
    }

    public static void TotalTypesofFruits() {
        Set<String> TypesofFruits = new HashSet<String>();
        for (int i = 0; i < dataset.size(); i++) {
            TypesofFruits.add(dataset.get(i).get(0));
        }
        System.out.println("Total types of fruit:" + TypesofFruits.size());
    }

    public static void ReadDataFromCSVfile(String[] data) {
        List<String> l1 = new ArrayList<String>();
        for (int i = 0; i < data.length; i++) {
            l1.add(data[i]);
        }
        dataset.add(l1);

    }

    public static void OldestFruitAge() {
        int max = 0;
        for (int i = 0; i < dataset.size(); i++) {
            if (max < Integer.parseInt(dataset.get(i).get(1))) {
                max = Integer.parseInt(dataset.get(i).get(1));
            }
        }
        for (int i = 0; i < dataset.size(); i++) {
            if (Integer.parseInt(dataset.get(i).get(1)) == max) {
                System.out.println("Fruit: " + dataset.get(i).get(0) + "   Age: " + dataset.get(i).get(1));
            }
        }
    }

    public static void FruitDescendingOrder() {
        Map<String, Integer> Fruittype = new HashMap<String, Integer>();
        for (int i = 0; i < dataset.size(); i++) {
            if (Fruittype.containsKey(dataset.get(i).get(0))) {
                Fruittype.put(dataset.get(i).get(0), Fruittype.get(dataset.get(i).get(0)) + 1);

            } else {
                Fruittype.put(dataset.get(i).get(0), 1);
            }
        }
        System.out.println(Fruittype);
    }

    public static void VariousCharacteristics() {
        List<List<String>> fruits = new ArrayList<List<String>>();
        List<List<String>> varChrFruits = new ArrayList<List<String>>();
        Map<List<String>, Integer> M1 = new HashMap<List<String>, Integer>();
        for (int i = 0; i < dataset.size(); i++) {
            List<String> L1 = new ArrayList<String>();
            for (int j = 0; j < dataset.get(i).size(); j++) {
                if (j != 1) {
                    L1.add(dataset.get(i).get(j));
                }
            }
            fruits.add(L1);
        }
        List<String> l1 = new ArrayList<String>();
        List<String> l2 = new ArrayList<String>();
        for (int i = 0; i < fruits.size(); i++) {
            int count = 1;
            l1 = fruits.get(i);
//            if (!varChrFruits.contains(l1)) {
//                for (int j = i + 1; j < fruits.size(); j++) {
//                    l2 = fruits.get(j);
//                    if (l1.equals(l2)) {
//                        count++;
//                        varChrFruits.add(l2);
//                    }
//                }
//            }
            if (M1.containsKey(l1)) {
                M1.put(l1, M1.get(l1) + 1);
            } else {
                M1.put(l1, 1);
            }
        }
        Iterator<Map.Entry<List<String>, Integer>> itr = M1.entrySet().iterator();
        while(itr.hasNext())
        {
            Map.Entry<List<String>, Integer> entry = itr.next();
            System.out.println( entry.getValue()+":" + entry.getKey());
        }

    }

    public static void DisplayOptions() {
        System.out.println("************************\n" +
                "Chose below option to get the data\n" +
                "1.Total Number of Fruits \n" +
                "2.Total types of Fruits\n" +
                "3.Oldest Fruit & Age\n" +
                "4.Number of Each Type of Fruit in Descending order\n" +
                "5.various characteristics (count, color, shape, etc.) of each fruit by type:\n" +
                "6.To get All above Information\n" +
                "0.To exit from the menu\n" +
                "************************\n");
    }

    public static void main(String[] args) throws IOException {

        String csvFile=args[0];
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        br = new BufferedReader(new FileReader(csvFile));
        String[] Headers = br.readLine().split(cvsSplitBy);
        for (int j = 0; j < Headers.length; j++) {
            if (!Headers[j].equals(csvheader[j])) {
                System.out.println("Error :422 invalid CSV format Header");
                System.out.println(Headers[j] + " should be " + csvheader[j]);
                return;
            }
        }
        while ((line = br.readLine()) != null) {
            // use comma as separator
            String[] DataSet = line.split(cvsSplitBy);
            ReadDataFromCSVfile(DataSet);
        }

        Scanner in = new Scanner(System.in);
        while (true) {
            DisplayOptions();
            System.out.println("Enter input");
            int input = in.nextInt();
            switch (input) {
                case 1:
                    TotalNumOfFruits();
                    System.out.println();
                    break;
                case 2:
                    TotalTypesofFruits();
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Oldest Fruit & Age");
                    OldestFruitAge();
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Number of Each Type of Fruit in Descending order");
                    FruitDescendingOrder();
                    System.out.println();
                    break;
                case 5:
                    System.out.println("various characteristics (count, color, shape, etc.) of each fruit by type");
                    VariousCharacteristics();
                    System.out.println();
                    break;
                case 6:
                    TotalNumOfFruits();
                    TotalTypesofFruits();
                    System.out.println("Oldest Fruit & Age");
                    OldestFruitAge();
                    System.out.println("Number of Each Type of Fruit in Descending order");
                    FruitDescendingOrder();
                    System.out.println("various characteristics (count, color, shape, etc.) of each fruit by type");
                    VariousCharacteristics();
                    System.out.println();
                    break;
                case 0:
                    System.out.println("Process exit code 0");
                    return;
                default:
                    System.out.println("Invalid Input:  " + input);


            }
        }
    }
}