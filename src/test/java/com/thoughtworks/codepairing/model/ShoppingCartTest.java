package com.thoughtworks.codepairing.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {

    public static final int PRICE = 100;
    public static final String PRODUCT = "Product";

    Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("test");
    }

    @Test
    public void shouldCalculatePriceWithNoDiscount() {
        Map<Product, Integer> productMap = new HashMap<>(2,1);
        ShoppingCart cart = new ShoppingCart(customer, productMap);

        Product product = new Product(PRICE, "", PRODUCT);
        cart.addProduct(product, 1);
        Order order = cart.checkout();

        assertEquals(100.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsWithNoDiscount() {
        Map<Product, Integer> productMap = new HashMap<>(2,1);
        ShoppingCart cart = new ShoppingCart(customer, productMap);

        Product product = new Product(PRICE, "", PRODUCT);
        cart.addProduct(product, 1);
        Order order = cart.checkout();

        assertEquals(20, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor10PercentDiscount() {
        Map<Product, Integer> productMap = new HashMap<>(2,1);
        ShoppingCart cart = new ShoppingCart(customer, productMap);

        Product product = new Product(PRICE, "DIS_10_ABCD", PRODUCT);
        cart.addProduct(product, 1);
        Order order = cart.checkout();

        assertEquals(90.0, order.getTotalPrice(), 0.0);

    }

    @Test
    public void shouldCalculateLoyaltyPointsFor10PercentDiscount() {
        Map<Product, Integer> productMap = new HashMap<>(2,1);
        ShoppingCart cart = new ShoppingCart(customer, productMap);

        Product product = new Product(PRICE, "DIS_10_ABCD", PRODUCT);
        cart.addProduct(product, 1);
        Order order = cart.checkout();

        assertEquals(10, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor15PercentDiscount() {
        Map<Product, Integer> productMap = new HashMap<>(2,1);
        ShoppingCart cart = new ShoppingCart(customer, productMap);

        Product product = new Product(PRICE, "DIS_15_ABCD", PRODUCT);
        cart.addProduct(product, 1);
        Order order = cart.checkout();

        assertEquals(85.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyaltyPointsFor15PercentDiscount() {
        Map<Product, Integer> productMap = new HashMap<>(2,1);
        ShoppingCart cart = new ShoppingCart(customer, productMap);

        Product product = new Product(PRICE, "DIS_15_ABCD", PRODUCT);
        cart.addProduct(product, 1);
        Order order = cart.checkout();

        assertEquals(6, order.getLoyaltyPoints());
    }

    @Test
    public void shouldCalculatePriceFor20PercentDiscount(){
        Map<Product, Integer> productMap = new HashMap<>(2,1);
        ShoppingCart cart = new ShoppingCart(customer, productMap);

        Product product = new Product(PRICE, "DIS_20_ABCD", PRODUCT);
        cart.addProduct(product, 1);
        Order order = cart.checkout();

        assertEquals(80.0, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyalPointsFor20PercentDiscount(){
        Map<Product, Integer> productMap = new HashMap<>(2,1);
        ShoppingCart cart = new ShoppingCart(customer, productMap);

        Product product = new Product(PRICE, "DIS_20_ABCD", PRODUCT);
        cart.addProduct(product, 1);
        Order order = cart.checkout();

        assertEquals(5, order.getLoyaltyPoints());
    }

   @Test
   public void shouldCalculatePriceForBuy2get1freeCount3(){
       Map<Product, Integer> productMap = new HashMap<>(2,1);
       ShoppingCart cart = new ShoppingCart(customer, productMap);

       Product product = new Product(PRICE, "BUY_2_GET_1_ABCD", PRODUCT);
       cart.addProduct(product, 3);
       Order order = cart.checkout();

       assertEquals(200, order.getTotalPrice(), 0.0);
   }

    @Test
    public void shouldCalculateLoyalPointsForBuy2get1freeCount3(){
        Map<Product, Integer> productMap = new HashMap<>(2,1);
        ShoppingCart cart = new ShoppingCart(customer, productMap);

        Product product = new Product(PRICE, "BUY_2_GET_1_ABCD", PRODUCT);
        cart.addProduct(product, 2);
        Order order = cart.checkout();

        assertEquals(40, order.getLoyaltyPoints(), 0.0);
    }

    @Test
    public void shouldCalculatePriceForBuy2get1freeCount1(){
        Map<Product, Integer> productMap = new HashMap<>(2,1);
        ShoppingCart cart = new ShoppingCart(customer, productMap);

        Product product = new Product(PRICE, "BUY_2_GET_1_ABCD", PRODUCT);
        cart.addProduct(product, 1);
        Order order = cart.checkout();

        assertEquals(100, order.getTotalPrice(), 0.0);
    }

    @Test
    public void shouldCalculateLoyalPointsForBuy2get1freeCount1(){
        Map<Product, Integer> productMap = new HashMap<>(2,1);
        ShoppingCart cart = new ShoppingCart(customer, productMap);

        Product product = new Product(PRICE, "BUY_2_GET_1_ABCD", PRODUCT);
        cart.addProduct(product, 1);
        Order order = cart.checkout();

        assertEquals(20, order.getLoyaltyPoints(), 0.0);
    }

}
