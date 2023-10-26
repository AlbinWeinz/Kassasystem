package register_projekt;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class MoneyTest {

    @Test
    public void instantiationWithNoArgument() {
        Money money = new Money();
        Assert.assertEquals(0, money.getInteger());
        Assert.assertEquals(0, money.getFractional());
        Assert.assertEquals(0.0, money.getAmount(), 0.01);
        Assert.assertEquals("0.00", money.toString());
    }

    @Test
    public void instantiationWithOneArgument() {
        Money money = new Money(10.50);
        Assert.assertEquals(10, money.getInteger());
        Assert.assertEquals(50, money.getFractional());
        Assert.assertEquals(10.50, money.getAmount(), 0.01);
        Assert.assertEquals("10.50", money.toString());
    }

    @Test
    public void instantiationWithTwoArguments() {
        Money money = new Money(10, 50);
        Assert.assertEquals(10, money.getInteger());
        Assert.assertEquals(50, money.getFractional());
        Assert.assertEquals(10.50, money.getAmount(), 0.01);
        Assert.assertEquals("10.50", money.toString());
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
    public void getIntegerReturnsCorrectValue() {
        Money money = new Money(10, 50);
        Assert.assertEquals(10, money.getInteger());
    }

    @Test
    public void getFractionalReturnsCorrectValue() {
        Money money = new Money(10, 50);
        Assert.assertEquals(50, money.getFractional());
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
        Assert.assertEquals(15, sum.getInteger());
        Assert.assertEquals(75, sum.getFractional());
        Assert.assertEquals(15.75, sum.getAmount(), 0.01);
        Assert.assertEquals("15.75", sum.toString());
        Assert.assertEquals(new Money(15, 75), sum);
    }

    @Test
    public void subtractionIsCorrectlyCalculated() {
        Money money1 = new Money(10, 50);
        Money money2 = new Money(5, 25);
        Money difference = money1.subtract(money2);
        Assert.assertEquals(5, difference.getInteger());
        Assert.assertEquals(25, difference.getFractional());
        Assert.assertEquals(5.25, difference.getAmount(), 0.01);
        Assert.assertEquals("5.25", difference.toString());
        Assert.assertEquals(new Money(5, 25), difference);
    }

    @Test
    public void multiplicationIsCorrectlyCalculated() {
        Money money = new Money(10, 50);
        Money product = money.multiply(2.5);
        Assert.assertEquals(26, product.getInteger());
        Assert.assertEquals(25, product.getFractional());
        Assert.assertEquals(26.25, product.getAmount(), 0.01);
        Assert.assertEquals("26.25", product.toString());
        Assert.assertEquals(new Money(26, 25), product);
    }

    @Test
    public void roundingIsCorrectWhenUnderHalf() {
        Money money = new Money(10, 25);
        Money rounded = money.round();
        Assert.assertEquals(10, rounded.getInteger());
        Assert.assertEquals(0, rounded.getFractional());
        Assert.assertEquals(10.00, rounded.getAmount(), 0.01);
        Assert.assertEquals("10.00", rounded.toString());
        Assert.assertEquals(new Money(10), rounded);

    }

    @Test
    public void roundingIsCorrectWhenAtHalf() {
        Money money = new Money(10, 50);
        Money rounded = money.round();
        Assert.assertEquals(11, rounded.getInteger());
        Assert.assertEquals(0, rounded.getFractional());
        Assert.assertEquals(11.0, rounded.getAmount(), 0.01);
        Assert.assertEquals("11.00", rounded.toString());
        Assert.assertEquals(new Money(11), rounded);
    }

    @Test
    public void roundingIsCorrectWhenOverHalf() {
        Money money = new Money(10, 75);
        Money rounded = money.round();
        Assert.assertEquals(11, rounded.getInteger());
        Assert.assertEquals(0, rounded.getFractional());
        Assert.assertEquals(11.0, rounded.getAmount(), 0.01);
        Assert.assertEquals("11.00", rounded.toString());
        Assert.assertEquals(new Money(11), rounded);
    }

    @Test
    public void equalsReturnsCorrectValue() {
        Money money1 = new Money(10, 50);
        Money money2 = new Money(10.50);
        Money money3 = new Money(1050);
        assertTrue(money1.equals(money2));
        assertFalse(money1.equals(money3));
    }

    @Test
    public void equalsReturnsTrueForSameObject() {
        Money money = new Money(10, 50);
        assertTrue(money.equals(money));
    }

    @Test
    public void equalsReturnsFalseForNonMoneyObjects() {
        Money money = new Money(10.0);
        int number = 10;
        assertFalse(money.equals(number));
    }

    @Test
    public void equalsReturnsFalseForNull() {
        Money money = new Money(10.0);
        assertFalse(money.equals(null));
    }

    @Test
    public void compareToReturnsCorrectValues() {
        Money money1 = new Money(10, 50);
        Money money2 = new Money(10.25);
        Money money3 = new Money(10.50);
        Money money4 = new Money(10.75);
        assertTrue(money1.compareTo(money2) > 0);
        assertTrue(money1.compareTo(money3) == 0);
        assertTrue(money1.compareTo(money4) < 0);
    }
}