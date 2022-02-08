import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    private final static int R = 256;
    public static String[] sort(String[] asciis) {
        if(asciis.length <= 1){
            return asciis;
        }
        // TODO: Implement LSD Sort
        int maxlen = asciis[0].length();//getmaxlen(asciis);
        for(int i = maxlen-1; i >= 0; i--){
            asciis = sortHelperLSD(asciis, i);
        }
        return asciis;
    }
    public static int getmaxlen(String[] s){
        int max = Integer.MIN_VALUE;
        for(String item : s){
           if(item.length() > max){
               max = item.length();
           }
        }
        return max;
    }
    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static String[] sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        // find max

        int[] counts = new int[R];
        for (String s : asciis) {
            counts[s.charAt(index)]++;
        }

        // however, below is a more proper, generalized implementation of
        // counting sort that uses start position calculation
        int[] starts = new int[R];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sorted2 = new String[asciis.length];
        for (int i = 0; i < asciis.length; i += 1) {
            int item = asciis[i].charAt(index);
            int place = starts[item];
            sorted2[place] = asciis[i];
            starts[item] += 1;
        }

        // return the sorted array
        return sorted2;

    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
    private static String[] s = {"923", "534", "132", "231", "975", "233", "240", "433", "671", "681"};

    public static void assertIsSorted(String[] a) {
        String previous = a[0];
        for (String x : a) {
            assertTrue(previous.compareTo(x) <= 0);
            previous = x;
        }
    }
    @Test
    public void testBetterWithSomeNegative() {
        String[] sortedS = RadixSort.sort(s);
        assertIsSorted(sortedS);
    }

}
