package register_projekt;

import org.junit.Assert;
import org.junit.Test;

public class MoneyTest {

    @Test
    public void instantiationWithNoArgument() {
        Money money = new Money();
        Assert.assertEquals(0, money.getKronor());
        Assert.assertEquals(0, money.getOren());
        Assert.assertEquals(0.0, money.getAmount(), 0.01);
        Assert.assertEquals("0.00 kr", money.toString());
    }

    @Test
    public void instantiationWithOneArgument() {
        Money money = new Money(10.50);
        Assert.assertEquals(10, money.getKronor());
        Assert.assertEquals(50, money.getOren());
        Assert.assertEquals(10.50, money.getAmount(), 0.01);
        Assert.assertEquals("10.50 kr", money.toString());
    }

    @Test
    public void instantiationWithTwoArguments() {
        Money money = new Money(10, 50);
        Assert.assertEquals(10, money.getKronor());
        Assert.assertEquals(50, money.getOren());
        Assert.assertEquals(10.50, money.getAmount(), 0.01);
        Assert.assertEquals("10.50 kr", money.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiationWithNegativeNumberThrowsException() {
        new Money(-10.50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiationWithIncorrectDecimalsThrowsException() {
        new Money(10.101);
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiationWithNegativeFirstArgumentThrowsException() {
        new Money(-10, 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiationWithIncorrectSecondArgumentThrowsException() {
        new Money(10, 100);
    }

    @Test
    public void getKronorReturnsCorrectValue() {
        Money money = new Money(10, 50);
        Assert.assertEquals(10, money.getKronor());
    }

    @Test
    public void getOrenReturnsCorrectValue() {
        Money money = new Money(10, 50);
        Assert.assertEquals(50, money.getOren());
    }

    @Test
    public void getAmountReturnsCorrectValue() {
        Money money = new Money(10, 50);
        Assert.assertEquals(10.50, money.getAmount(), 0.01);
    }

    @Test
    public void additionIsCorrectlyCalculated() {
        Money money1 = new Money(10, 50);
        Money money2 = new Money(5, 25);
        Money sum = money1.add(money2);
        Assert.assertEquals(15, sum.getKronor());
        Assert.assertEquals(75, sum.getOren());
        Assert.assertEquals(15.75, sum.getAmount(), 0.01);
        Assert.assertEquals("15.75 kr", sum.toString());
    }

    @Test
    public void subtractionIsCorrectlyCalculated() {
        Money money1 = new Money(10, 50);
        Money money2 = new Money(5, 25);
        Money difference = money1.subtract(money2);
        Assert.assertEquals(5, difference.getKronor());
        Assert.assertEquals(25, difference.getOren());
        Assert.assertEquals(5.25, difference.getAmount(), 0.01);
        Assert.assertEquals("5.25 kr", difference.toString());
    }

    @Test
    public void multiplicationIsCorrectlyCalculated() {
        Money money = new Money(10, 50);
        Money product = money.multiply(2.5);
        Assert.assertEquals(26, product.getKronor());
        Assert.assertEquals(25, product.getOren());
        Assert.assertEquals(26.25, product.getAmount(), 0.01);
        Assert.assertEquals("26.25 kr", product.toString());
    }

    @Test
    public void roundingIsCorrectWhenUnderHalf() {
        Money money = new Money(10, 25);
        Money rounded = money.round();
        Assert.assertEquals(10, rounded.getKronor());
        Assert.assertEquals(0, rounded.getOren());
        Assert.assertEquals(10.00, rounded.getAmount(), 0.01);
        Assert.assertEquals("10.00 kr", rounded.toString());

    }

    @Test
    public void roundingIsCorrectWhenAtHalf() {
        Money money = new Money(10, 50);
        Money rounded = money.round();
        Assert.assertEquals(11, rounded.getKronor());
        Assert.assertEquals(0, rounded.getOren());
        Assert.assertEquals(11.0, rounded.getAmount(), 0.01);
        Assert.assertEquals("11.00 kr", rounded.toString());
    }

    @Test
    public void roundingIsCorrectWhenOverHalf() {
        Money money = new Money(10, 75);
        Money rounded = money.round();
        Assert.assertEquals(11, rounded.getKronor());
        Assert.assertEquals(0, rounded.getOren());
        Assert.assertEquals(11.0, rounded.getAmount(), 0.01);
        Assert.assertEquals("11.00 kr", rounded.toString());
    }
}
