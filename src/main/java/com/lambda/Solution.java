package com.lambda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.lambda.App.Rectangle;

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
        return d -> String.format("%d-%d-%d", d.getYear(), d.getMonthValue(), d.getDayOfMonth());
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
            if (n % 2 == 1) {
                l.add(n);
            }
            
            n = gen.get();
        }

        return l;
    }

    public static List<Double> bubbleSort(List<Double> list, Comparator<Double> comp) { // Hard!
        // Implement a sorting algorithm sorting "list" from HIGHEST to the LOWEST.
        // Refrain from using list.sort(...). Tipp: search bubble sort (:

        boolean isSorted = true;

        for (int i = 0; i < list.size(); ++i) {
            for (int j = 1; j < (list.size() - i); ++j) {
                if (-comp.compare(list.get(j - 1), list.get(j)) > 0) {
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

    public static Set<Long> duplicates(List<Long> list) {
        // Given a List of Integers remove all the duplicates.
        return list.stream().collect(Collectors.toSet());
    }

    public static List<Integer> fizz(Set<Integer> list) {
        // Given a Set of Integers remove all Integers not divisible by 3.
        return list.stream().filter(x -> x % 3 == 0).collect(Collectors.toList());
    }

    public static List<Double> cast(List<Integer> list) {
        // Given a List of Integers cast them to a list of Doubles.
        return list.stream().map(x -> (double) x.intValue()).collect(Collectors.toList());
    }

    public static List<Integer> duplicates2(List<Integer> list) {
        // Given a List of Integers remove all the duplicates.
        return list.stream().distinct().collect(Collectors.toList());
    }

    public static Integer sum(List<Integer> list) {
        // Return the sum of all Integers in "list".
        return list.stream().mapToInt(x -> x).sum();
    }

    public static Short max(List<Short> list) {
        // Return the largest Short from "list". If none available then throw
        // IllegalArgumentException.
        var n = list.stream().mapToInt(x -> x).max();

        if (n.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return (short) n.getAsInt();
    }

    public static Integer averageArea(List<Rectangle> list) {
        // Calculate the average area of all the given Rectangles in "list".
        return list.stream().mapToInt(x -> x.getWidth() * x.getHeight()).sum() / list.size();
    }

    public static Long multiply(List<Long> list) {
        // Return the multiplication of all Longs in "list".
        return list.stream().reduce((x, y) -> x * y).get();
    }

    public static List<Long> largeMultiply(Set<Long> list) {
        // Return a List of all Long numbers in "list" but multiplied with the largest
        // Long in "list".

        if (list.isEmpty()) {
            return new ArrayList<Long>();
        }

        var max = list.stream().mapToLong(x -> x).max().getAsLong();
        return list.stream().map(x -> x * max).collect(Collectors.toList());
    }

    public static Integer superSum(List<List<Integer>> list) {
        // Return the sum of the sum of all the lists in the list.
        return list.stream().mapToInt(x -> x.stream().mapToInt(y -> y).sum()).sum();
    }
}
