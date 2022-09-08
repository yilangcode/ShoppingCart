package com.thoughtworks.codepairing.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCart {
    private Map<Product, Integer> products;
    private Customer customer;

    public ShoppingCart(Customer customer, Map<Product, Integer> products) {
        this.customer = customer;
        this.products = products;
    }

    public void addProduct(Product product, int count) {
        products.put(product, products.getOrDefault(product, 0) + count);
    }

    public Order checkout() {
        double totalPrice = 0;

        int loyaltyPointsEarned = 0;



        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int count = entry.getValue();
            double realSpending = 0;

            //重构是否应该把价格给提取出来
            if(product.getProductCode().startsWith("BUY_2_GET_1")){
                realSpending =  product.getPrice() * (count - count / 3);
                totalPrice +=  realSpending;
                loyaltyPointsEarned += realSpending / 5;

            }else if(product.getProductCode().startsWith("DIS")){
                int discount = Integer.parseInt((product.getProductCode().split("_")[1]));
                realSpending = product.getPrice() * count * (100 - discount) / 100;
                totalPrice += realSpending;
                loyaltyPointsEarned += product.getPrice() * count /discount;

            }else{
                realSpending = product.getPrice() * count;
                totalPrice += realSpending;
                loyaltyPointsEarned += realSpending / 5;
            }
        }

        return new Order(totalPrice, loyaltyPointsEarned);
    }

    @Override
    public String toString() {
        return "Customer: " + customer.getName() + "\n" + "Bought:  \n" + products.entrySet().stream().map(p -> "- " + p.getKey().getName()+ ", "+p.getKey().getPrice() + ", " + p.getValue()).collect(Collectors.joining("\n"));
    }
}
