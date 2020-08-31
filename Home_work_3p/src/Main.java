import java.util.Arrays;
import java.util.HashMap;

public class Main {
public static void main( String[] args ) {
    String[] words = {"Deushev","Ivanov","Kozlov","Deushev","Ivanov","Kozlov","Deushev","Ivanov","Kozlov"};
    System.out.println(Arrays.toString(words));

    HashMap<String,Integer> hmap = new HashMap<>();
    for (String w : words) {
        hmap.put(w,hmap.getOrDefault(w,0)+1);
    }
    System.out.println(hmap);


    Phone phBook = new Phone();
    phBook.add("Deushev","8(917)033-48-50");
    phBook.add("Deushev","8(917)000-48-50");
    phBook.add("Ivanov","8(917)333-48-50");
    phBook.add("Ivanov","8(917)033-88-50");
    phBook.add("Kozlov","8(917)033-48-51");
    phBook.add("Kozlov","8(917)033-33-50");
    System.out.println("Phone " + phBook.get("Kozlov"));
}
}
