package register_projekt;

import org.junit.Assert;
import org.junit.Test;

public class MoneyTest {
    @Test
    public void testDefaultConstructor() {
        Money money = new Money();
        Assert.assertEquals(0, money.getKronor());
        Assert.assertEquals(0, money.getOren());
        Assert.assertEquals(0.0, money.getAmount(), 0.01);
        Assert.assertEquals("0.00 kr", money.toString());
    }

    @Test
    public void testAmountConstructor() {
        Money money = new Money(10.50);
        Assert.assertEquals(10, money.getKronor());
        Assert.assertEquals(50, money.getOren());
        Assert.assertEquals(10.50, money.getAmount(), 0.01);
        Assert.assertEquals("10.50 kr", money.toString());
    }

    @Test
    public void testKronorOrenConstructor() {
        Money money = new Money(10, 50);
        Assert.assertEquals(10, money.getKronor());
        Assert.assertEquals(50, money.getOren());
        Assert.assertEquals(10.50, money.getAmount(), 0.01);
        Assert.assertEquals("10.50 kr", money.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeAmountConstructor() {
        new Money(-10.50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidOrenConstructor() {
        new Money(10, 100);
    }

    @Test
    public void testGetKronor() {
        Money money = new Money(10, 50);
        Assert.assertEquals(10, money.getKronor());
    }

    @Test
    public void testGetOren() {
        Money money = new Money(10, 50);
        Assert.assertEquals(50, money.getOren());
    }

    @Test
    public void testGetAmount() {
        Money money = new Money(10, 50);
        Assert.assertEquals(10.50, money.getAmount(), 0.01);
    }

    @Test
    public void testSum() {
        Money money1 = new Money(10, 50);
        Money money2 = new Money(5, 25);
        Money sum = money1.sum(money2);
        Assert.assertEquals(15, sum.getKronor());
        Assert.assertEquals(75, sum.getOren());
        Assert.assertEquals(15.75, sum.getAmount(), 0.01);
        Assert.assertEquals("15.75 kr", sum.toString());
    }

    @Test
    public void testSubtract() {
        Money money1 = new Money(10, 50);
        Money money2 = new Money(5, 25);
        Money difference = money1.subtract(money2);
        Assert.assertEquals(5, difference.getKronor());
        Assert.assertEquals(25, difference.getOren());
        Assert.assertEquals(5.25, difference.getAmount(), 0.01);
        Assert.assertEquals("5.25 kr", difference.toString());
    }

    @Test
    public void testMultiply() {
        Money money = new Money(10, 50);
        Money multiplied = money.multiply(2.5);
        Assert.assertEquals(26, multiplied.getKronor());
        Assert.assertEquals(25, multiplied.getOren());
        Assert.assertEquals(26.25, multiplied.getAmount(), 0.01);
        Assert.assertEquals("26.25 kr", multiplied.toString());
    }

    @Test
    public void testRound() {
        Money money1 = new Money(10, 25);
        Money rounded1 = money1.round();
        Assert.assertEquals(10, rounded1.getKronor());
        Assert.assertEquals(0, rounded1.getOren());
        Assert.assertEquals(10.00, rounded1.getAmount(), 0.01);
        Assert.assertEquals("10.00 kr", rounded1.toString());

        Money money2 = new Money(10, 75);
        Money rounded2 = money2.round();
        Assert.assertEquals(11, rounded2.getKronor());
        Assert.assertEquals(0, rounded2.getOren());
        Assert.assertEquals(11.0, rounded2.getAmount(), 0.01);
        Assert.assertEquals("11.00 kr", rounded2.toString());
    }
}
