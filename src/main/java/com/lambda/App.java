package com.lambda;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class App {
    public static void main(String[] args) {
        /*
         * A lambda expression is a short hand function taking in one or more parameters
         * and outputting none or a single value.
         * 
         * There are a few ways lambdas can be created.
         */

        // Implements the method a with a parameter int x (which is x below) and outputs
        // the int x * 2. The compiler can lookup the type of x using the parameter int
        // x in A.a.
        interface A {
            int a(int x);
        }

        A l1 = x -> x * 2; // Notice @FunctionalInterface is not needed, but recommended.
        System.out.println(l1.a(10)); // This calls the lambda through the interface

        // Same as above but now with 2 parameters with type int since both parameters x
        // and y in B.b are of type int.
        interface B {
            int b(int x, int y);
        }

        B l2 = (x, y) -> x * y;
        System.out.println(l2.b(10, 20));

        // This does the same as above but this times uses a block statement which
        // basically makes the lambda function like a method. All rules for writing
        // functions (so like main) apply here, which includes return.
        B l3 = (x, y) -> {
            return x * y;
        };
        System.out.println(l3.b(10, 20));

        /*
         * Lambdas are used to pass a short (or long) function into another function.
         * For example a sorting function for custom types requires a lambda as a
         * comparator. Otherwise how should it know how to compare 2 objects of a
         * custom type?
         */
        class C { // This is a class containing a simple integer attribute.
            int c;

            C(int c) {
                this.c = c;
            }

            @Override
            public String toString() { // For display purposes only
                return Integer.toString(c);
            }
        }

        List<C> list = Arrays.asList(new C(13), new C(234), new C(32), new C(3));
        Comparator<C> comp = (a, b) -> a.c - b.c; // return < 0 => a is larger;
        // return > 0 => a is smaller;
        // return == 0 => a and b are the same;
        list.sort(comp); // The sort method uses the comparator object to sort the list of C objects.
                         // Notice Comparator<C> is just an interface. So using the lambda we just
                         // created an implementation of the interface.
        System.out.println(list);

        /*
         * Since as we noticed above that the lambda just implements an interface, we
         * can also do the above without using lambdas but with inheritance. This means
         * we can create a class implementing Comparator<C> which implements our
         * comparison function.
         */
        class CompareC implements Comparator<C> {
            @Override
            public int compare(C arg0, C arg1) {
                return arg0.c - arg1.c;
            }
        }

        List<C> list2 = Arrays.asList(new C(13), new C(234), new C(32), new C(3));
        list2.sort(new CompareC()); // This outputs the same result as above, but this time using a inherited class.
                                    // This means lambdas are nothing more but a quick way of implementing a
                                    // interface. As such lambdas are great here because they allow for the creation
                                    // of single use implementations of a interface without clogging up the
                                    // namespace with dozens of names.
        System.out.println(list2);

        /*
         * So if we need functionality to be different across multiple calls
         * of the same function we can add a lambda parameter such that a task is
         * handled differently on each call (or the same with the same lambda).
         */
        interface Stringify<T> { // This allows for a lambda to turn a class T object into a string.
            String asString(T v);
        }

        class F {
            static <T> void print(T obj, Stringify<T> str) { // This is the method for printing a custom object into the
                                                             // console. Since the console accepts strings, we need to
                                                             // convert the object into a string representation. This is
                                                             // where Stringify<T> comes in handy. We will use a
                                                             // Stringify<T> object to convert the passed in object into
                                                             // its string representation.
                System.out.println(str.asString(obj));
            }
        }

        class D { // This will be printed now
            double d;

            D(double d) {
                this.d = d;
            }
        }

        D test1 = new D(99.);
        Stringify<D> s1 = x -> "D(" + Double.toString(x.d) + ")"; // Here we create a lambda which prints our d value
                                                                  // enclosed in the class D. Thus we have a
                                                                  // Stringify<D> implementation using only a quick and
                                                                  // simple lambda!
        F.print(test1, s1); // Here we can input our lambda and object to print. Of course it's also
                            // possible to inline our lambda and object.

        class E { // This will be printed now.
            D d;

            E(double d) {
                this.d = new D(d);
            }
        }

        E test2 = new E(69.);
        Stringify<E> s2 = x -> "E(" + s1.asString(x.d) + ")"; // Since we have an attribute of type D we can just
                                                              // forward our call of stringifing the object to the
                                                              // implementation of Stringify<D>. That returns us the
                                                              // string for D in which we can wrap it with E and then
                                                              // return it. But this means we can access variables
                                                              // outside of the lambda...
        F.print(test2, s2);
    }

    // ------------------------------
    // Exersices
    // ------------------------------

    // Lambda Creation -------------------------

    public static BiFunction<Integer, Integer, Integer> minimum() {
        // Return a lambda accepting 2 Integer parameters and returning the minimum of
        // the 2.
        return null;
    }

    public static <T> Consumer<T> printer() {
        // Return a lambda accepting a parameter and printing it to the console.
        return null;
    }

    public static Comparator<Integer> compare() {
        // This time return a lambda accepting 2 Integer parameters and returning a
        // Integer comparing the 2. (See the Comparator specifications).
        return null;
    }

    public static Predicate<Character> digit() {
        // Return a lambda accepting a Character parameter and returning true or false
        // if the character is a digit or not.
        return null;
    }

    public static Function<LocalDate, String> time() {
        // Return a lambda accepting a LocalDate parameter and returning a String which
        // formats the date like so: "<Year>-<Month>-<Day>".
        return null;
    }

    // Lambda usage -------------------------------------

    public static Boolean checkString(String in, Predicate<Character> isDigit) {
        // Given a String "in" check if the String is a number using the isDigit lambda.
        return null;
    }

    public static List<Byte> sort(List<Byte> list, Comparator<Byte> comp) {
        // Sort "list" from lowest to highest and output its contents.
        return null;
    }

    public static List<Float> sort2(List<Float> list, Comparator<Float> comp) {
        // Sort "list" from HIGHEST to LOWEST and output its contents.
        return null;
    }

    public static List<Short> filter(Supplier<Short> gen) {
        // Output all odd numbers as a List in the same order as what "gen" generates
        // till it generates a 0 (not included).
        return null;
    }

    public static List<Double> bubbleSort(List<Double> list, Comparator<Double> comp) { // Hard!
        // Implement a sorting algorithm sorting "list" from HIGHEST to the LOWEST.
        // Refrain from using list.sort(...). Tipp: search bubble sort (:
        return null;
    }

    // Streams usage --------------------------------

    public static Set<Long> duplicates(List<Long> list) {
        // Given a List of Integers remove all the duplicates.
        return null;
    }

    public static List<Integer> fizz(Set<Integer> list) {
        // Given a Set of Integers remove all Integers not divisible by 3.
        return null;
    }

    public static List<Double> cast(List<Integer> list) {
        // Given a List of Integers cast them to a list of Doubles.
        return null;
    }

    public static List<Integer> duplicates2(List<Integer> list) {
        // Given a List of Integers remove all the duplicates.
        return null;
    }

    public static Integer sum(List<Integer> list) {
        // Return the sum of all Integers in "list".
        return null;
    }

    public static Short max(List<Short> list) {
        // Return the largest Short from "list". If none available then throw
        // IllegalArgumentException.
        return null;
    }

    public class Rectangle {
        int w, h;

        public Rectangle(int w, int h) {
            this.w = w;
            this.h = h;
        }

        public int getWidth() {
            return w;
        }

        public int getHeight() {
            return h;
        }
    }

    public static Integer averageArea(List<Rectangle> list) {
        // Calculate the average area of all the given Rectangles in "list".
        return null;
    }

    public static Long multiply(List<Long> list) {
        // Return the multiplication of all Longs in "list".
        return null;
    }

    public static List<Long> largeMultiply(Set<Long> list) {
        // Return a List of all Long numbers in "list" but multiplied with the largest
        // Long in "list".
        return null;
    }

    public static Integer superSum(List<List<Integer>> list) {
        // Return the sum of the sum of all the lists in the list.
        return null;
    }
}
