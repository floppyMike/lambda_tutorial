package com.lambda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Solution {
    public static BiFunction<Integer, Integer, Integer> minimum() {
        // Return a lambda accepting 2 Integer parameters and returning the minimum of
        // the 2.
        return (x, y) -> {
            if (x < y) {
                return x;
            } else {
                return y;
            }
        };

        // Or return Integer::min;

        // Or return (x, y) -> x < y ? x : y;
    }

    public static <T> Consumer<T> printer() {
        // Return a lambda accepting a parameter and printing it to the console.
        return x -> System.out.println(x);
    }

    public static Comparator<Integer> compare() {
        // This time return a lambda accepting 2 Integer parameters and returning a
        // Integer comparing the 2. (See the Comparator specifications).
        return (x, y) -> x - y;

        // Or return Integer::compare;
    }

    public static Predicate<Character> digit() {
        // Return a lambda accepting a Character parameter and returning true or false
        // if the character is a digit or not.
        return c -> {
            int x = (int) c - (int) '0';
            return 0 <= x && x <= 9;
        };

        // Or return c -> {
        // switch (c) {
        // case '0':
        // case '1':
        // case '2':
        // case '3':
        // case '4':
        // case '5':
        // case '6':
        // case '7':
        // case '8':
        // case '9':
        // return true;
        // }
        //
        // return false;
        // };

        // Or return Character::isDigit;
    }

    public static Function<LocalDate, String> time() {
        // Return a lambda accepting a LocalDate parameter and returning a String which
        // formats the date like so: "<Year>:<Month>:<Day>".
        return d -> String.format("%d:%d:%d", d.getYear(), d.getMonth(), d.getDayOfMonth());
    }
    
    public static Boolean checkString(String in, Predicate<Character> isDigit) {
        // Given a String "in" check if the String is a number using the isDigit lambda.
        for (int i = 0; i < in.length(); ++i) {
            if (!isDigit.test(in.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static List<Byte> sort(List<Byte> list, Comparator<Byte> comp) {
        // Sort "list" from lowest to highest and output its contents.
        list.sort(comp);
        return list;
    }

    public static List<Float> sort2(List<Float> list, Comparator<Float> comp) {
        // Sort "list" from HIGHEST to LOWEST and output its contents.
        list.sort((x, y) -> -comp.compare(x, y));
        // Or list.sort(comp.reversed());
        
        return list;
    }

    public static List<Short> filter(Supplier<Short> gen) {
        // Output all odd numbers as a List in the same order as what "gen" generates.
        var l = new ArrayList<Short>();

        var n = gen.get();
        while (n != 0) {
            l.add(n);
        }

        return l;
    }

    public static List<Double> bubbleSort(List<Double> list, Comparator<Double> comp) { // Hard!
        // Implement a sorting algorithm sorting "list" from HIGHEST to the LOWEST.
        // Refrain from using list.sort(...). Tipp: search bubble sort (:
        
        boolean isSorted = true;
        
        for (int i = 0; i < list.size(); ++i) {
            for (int j = 1; j < (list.size() - i); ++j) {
                if (-comp.compare(list.get(j - 1), list.get(j)) < 0) {
                    // Swap elements
                    var tmp = list.get(j);
                    list.set(j, list.get(j - 1));
                    list.set(j - 1, tmp);
                    
                    isSorted = false;
                }
            }
            
            if (isSorted) {
                break;
            }
            
            isSorted = true;
        }

        return list;
    }
}
