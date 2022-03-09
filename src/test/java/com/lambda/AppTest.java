package com.lambda;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void testMinimum() {
        Assert.assertEquals(App.minimum().apply(0, 0).intValue(), 0);
        Assert.assertEquals(App.minimum().apply(100, 0).intValue(), 0);
        Assert.assertEquals(App.minimum().apply(100, 39).intValue(), 39);
        Assert.assertEquals(App.minimum().apply(-320, 39).intValue(), -320);
        Assert.assertEquals(App.minimum().apply(-320, -320).intValue(), -320);
    }

    @Test
    public void testTime() {
        Assert.assertEquals(App.time().apply(LocalDate.of(2022, 3, 11)), "2022-3-11");
    }

    @Test
    public void testBubbleSort() {
        var arr = Arrays.asList(3., 5., 2., 10., 4., 6.);
        Assert.assertArrayEquals(Arrays.asList(10., 6., 5., 4., 3., 2.).toArray(),
                App.bubbleSort(arr, Double::compare).toArray());
    }

    @Test
    public void testCast() {
        Assert.assertArrayEquals(Arrays.asList(2., 3.).toArray(), App.cast(Arrays.asList(2, 3)).toArray());
    }

    @Test
    public void testCheckString() {
        Assert.assertTrue(App.checkString("345", Character::isDigit));
        Assert.assertFalse(App.checkString("3r5", Character::isDigit));
    }

    @Test
    public void testCompare() {
        Assert.assertTrue(App.compare().compare(0, 0) == 0);
        Assert.assertTrue(App.compare().compare(10, 4) > 0);
        Assert.assertTrue(App.compare().compare(6, 34) < 0);
    }

    @Test
    public void testDigit() {
        Assert.assertTrue(App.digit().test('5'));
        Assert.assertFalse(App.digit().test('r'));
    }

    @Test
    public void testDuplicates() {
        var set = new HashSet<Long>();
        var list = Arrays.asList(1l, 2l, 3l, 3l);

        set.addAll(list);

        Assert.assertEquals(set, App.duplicates(list));
    }

    @Test
    public void testDuplicates2() {
        Assert.assertArrayEquals(Arrays.asList(1, 2, 3).toArray(),
                App.duplicates2(Arrays.asList(1, 2, 2, 3, 1, 3)).toArray());
    }

    static short counter = 11;

    @Test
    public void testFilter() {
        Assert.assertArrayEquals(
                Arrays.asList((short) 11, (short) 9, (short) 7, (short) 5, (short) 3, (short) 1).toArray(),
                App.filter(() -> counter--).toArray());
    }

    @Test
    public void testFizz() {
        var set = new HashSet<Integer>();
        set.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        Assert.assertArrayEquals(Arrays.asList(3, 6, 9).toArray(), App.fizz(set).toArray());
    }

    @Test
    public void testLargeMultiply() {
        var set = new HashSet<Long>();
        set.addAll(Arrays.asList(1l, 2l, 3l, 4l, 5l));

        Assert.assertArrayEquals(Arrays.asList(5l, 10l, 15l, 20l, 25l).toArray(), App.largeMultiply(set).toArray());
    }

    @Test
    public void testMax() {

    }

    @Test
    public void testMemory2() {

    }

    @Test
    public void testMinimum2() {

    }

    @Test
    public void testMultiply() {

    }

    @Test
    public void testPrinter() {

    }

    @Test
    public void testSort() {

    }

    @Test
    public void testSort2() {

    }

    @Test
    public void testSum() {

    }

    @Test
    public void testSuperSum() {

    }

    @Test
    public void testTime2() {

    }

    @Test
    public void testTotalArea() {

    }
}
