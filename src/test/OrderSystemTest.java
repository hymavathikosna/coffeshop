package test;
import static org.junit.Assert.assertEquals;

import com.ordering.charlene.*;
import com.ordering.charlene.factories.CoffeeFactory;
import com.ordering.charlene.factories.OrderFactory;
import com.ordering.charlene.model.Beverage;
import com.ordering.charlene.model.Snack;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class OrderSystemTest {
    private OrderFactory orderFactory;
    private OrderSystem orderSystem;

    @Before
    public void setUp() {
        orderFactory = new OrderFactory();
        orderSystem = OrderSystem.getInstance();
        orderSystem.clearOrders(); // Make sure to clear orders before each test
    }

    @Test
    public void testAddBeverage() {
        Beverage coffee = orderFactory.createBeverage("Coffee", "Large");
        orderSystem.addBeverage(coffee);
        List<Beverage> beverages = orderSystem.getBeverages();
        assertEquals(1, beverages.size());
        assertEquals("Large Coffee (Large)", beverages.get(0).getDescription());
    }

    @Test
    public void testAddSnack() {
        Snack sandwich = orderFactory.createSnack("Sandwich");
        orderSystem.addSnack(sandwich);
        List<Snack> snacks = orderSystem.getSnacks();
        assertEquals(1, snacks.size());
        assertEquals("Sandwich", snacks.get(0).getDescription());
    }

    @Test
    public void testFreeBeverageForFifthDrink() {
        orderSystem.addBeverage(orderFactory.createBeverage("Coffee", "Large"));
        orderSystem.addBeverage(orderFactory.createBeverage("Juice", "Orange"));
        orderSystem.addBeverage(orderFactory.createBeverage("Coffee", "Medium"));
        orderSystem.addBeverage(orderFactory.createBeverage("Juice", "Apple"));
        orderSystem.addBeverage(orderFactory.createBeverage("Coffee", "Small"));

        orderSystem.applyDiscounts();

        List<Beverage> beverages = orderSystem.getBeverages();
        assertEquals("Small Coffee (Free) (Small)", beverages.get(4).getDescription());
    }

    @Test
    public void testFreeExtraWithBeverageAndSnack() {
        Beverage beverage = orderFactory.createBeverage("Coffee", "Large");
        beverage = CoffeeFactory.addExtraMilk(beverage);

        orderSystem.addBeverage(beverage);
        orderSystem.addSnack(orderFactory.createSnack("Sandwich"));

        orderSystem.applyDiscounts();

        List<Beverage> beverages = orderSystem.getBeverages();
        assertEquals("Large Coffee (Large), Extra Milk Topping (Free)", beverages.get(0).description);
    }

    @Test
    public void testTotalCostCalculation() {
        orderSystem.addBeverage(orderFactory.createBeverage("Coffee", "Large"));
        orderSystem.addBeverage(orderFactory.createBeverage("Juice", "Orange"));
        orderSystem.addBeverage(orderFactory.createBeverage("Coffee", "Medium"));
        orderSystem.addBeverage(orderFactory.createBeverage("Juice", "Orange"));
        orderSystem.addBeverage(orderFactory.createBeverage("Coffee", "Small"));
        orderSystem.addSnack(orderFactory.createSnack("Bacon"));

        orderSystem.applyDiscounts();

        double expectedTotalCost = 3.5 + 3.95 + 3.0 + 3.95 + 0.0 + 4.5; // Free drink and extra cheese on sandwich
        assertEquals(expectedTotalCost, orderSystem.calculateTotalCost(), 0.0);
    }

}

