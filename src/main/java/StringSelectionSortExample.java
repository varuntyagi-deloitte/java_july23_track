public class StringSelectionSortExample {
    public static void main(String[] args) {
        String[] strings = {"banana", "apple", "orange", "grape"};

        System.out.println("Before sorting:");
        array(strings);

        // Selection sort algorithm
        for (int i = 0; i < strings.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < strings.length; j++) {
                if (strings[j].compareTo(strings[index]) < 0) {
                    index = j;
                }
            }
            String temp = strings[index];
            strings[index] = strings[i];
            strings[i] = temp;
        }

        System.out.println("After sorting:");
        array(strings);
    }

    public static void array(String[] strings) {
        for (String string : strings) {
            System.out.print(string + " ");
        }
        System.out.println();
    }
}