package com.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

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
        // x in A::a.
        interface A {
            int a(int x);
        }
        A l1 = x -> x * 2; // Notice @FunctionalInterface is not needed, but recommended.
        System.out.println(l1.a(10)); // This calls the lambda through the interface

        // Same as above but now with 2 parameters with type int since both parameters x
        // and y in B::b are of type int.
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
         * comparator. Otherwise how should it know how to compare 2 objects of the
         * custom type?
         */
        class C {
            int c;

            C(int c) {
                this.c = c;
            }

            @Override
            public String toString() {
                return Integer.toString(c);
            }
        }
        var list = Arrays.asList(new C(13), new C(234), new C(32), new C(3));
        Comparator<C> comp = (a, b) -> a.c - b.c; // return < 0 => a is larger;
                                                  // return > 0 => a is smaller;
                                                  // return == 0 => a and b are the same;
        list.sort(comp);
        System.out.println(list);

        /*
         * Notice a lambda basically implements an interface. So below functions the
         * same as above.
         */
        class CompareC implements Comparator<C> {
            @Override
            public int compare(C arg0, C arg1) {
                return arg0.c - arg1.c;
            }
        }
        var list2 = Arrays.asList(new C(13), new C(234), new C(32), new C(3));
        list2.sort(new CompareC());
        System.out.println(list2);

        /*
         * So basically if we need functionality to be different across multiple calls
         * of the same function we can add a lambda parameter such that a task is
         * handled differently on each call (or the same with the same lambda).
         */
        interface Stringify<T> {
            String asString(T v);
        }
        class F {
            static <T> void print(T obj, Stringify<T> str) {
                System.out.println(str.asString(obj));
            }
        }
        
        C test = new C(10000);
        Stringify<C> s = x -> Integer.toString(x.c);
        F.print(test, s);
        
        class D {
            double d;
            
            D(double d) {
                this.d = d;
            }
        } 
        
        D test1 = new D(99.);
        Stringify<D> s1 = x -> Double.toString(x.d);
        F.print(test1, s1);

        class E {
            D d;
            
            E(double d) {
                this.d = new D(d);
            }
        } 
        
        E test2 = new E(69.);
        Stringify<E> s2 = x -> s1.asString(x.d);
        F.print(test2, s2);
    }
}
