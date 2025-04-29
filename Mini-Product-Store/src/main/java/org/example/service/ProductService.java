package org.example.service;

import org.example.model.Category;
import org.example.model.Product;

import java.util.Comparator;
import java.util.List;

public class ProductService {

    private static final ProductService instance = new ProductService();

    private ProductService() {
    }

    public static ProductService getInstance() {
        return instance;
    }

    public final List<Product> filterByCategory(List<Product> list, Category category, String sortType){
        return sortByPrice(list, sortType).stream()
                .filter(product -> product.getCategory().equals(category))
                .toList();
    }

    public final List<Product> filterByCategory(List<Product> list, Category category){
        return list.stream()
                .filter(prod -> prod.getCategory().equals(category))
                .toList();
    }

    public final double calculateTotalPrice(List<Product> list){
        List<Double> prices = list.stream().map(Product::getPrice).toList();
        return prices.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private List<Product> sortByPrice(List<Product> list, String sortType){
        if (sortType.equals("asc")) {
            return list.stream()
                    .sorted(Comparator.comparing(Product::getPrice))
                    .toList();
        } else {
            return list.stream()
                    .sorted(Comparator.comparing(Product::getPrice).reversed())
                    .toList();
        }
    }

}
