

public class Main {
    public static void main(String[] args) {
        HashSetDM<String> set = new HashSetDM<>();

        set.add("New York");
        set.add("Los Angeles");
        set.add("Los Angeles");
        set.add("Los Angeles");
        set.add("Los Angeles");
        set.add("Los 33333");
        set.add("Los 2");
        set.add("Los 33233333");
        set.add("Los 23");
        set.add("Los 332332333");
        set.add("Los 21");
        set.add("Los 332433333");
        set.add("Chicago");

        set.add("Chicago");

        set.add("Chicago");

        System.out.println("Contains 'Chicago': " + set.contains("Chicago")); // true
        System.out.println("Set size: " + set.size()); // 3

        set.remove("Chicago");
        System.out.println("Contains 'Chicago': " + set.contains("Chicago")); // false
        System.out.println("Set size: " + set.size()); // 2

        for (String city : set.iterator()) {
            System.out.println(city);
        }
        System.out.println(set.getBucketsCount());


        System.out.println("__");
        System.out.println((-2147483648 & 0x7FFFFFFF));
        System.out.println((-2147483647 & 0x7FFFFFFF));
        System.out.println((-2147483646 & 0x7FFFFFFF));
        System.out.println((-214 & 0x7FFFFFFF % 4));
    }
}
