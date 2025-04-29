package org.example.view;

import org.example.model.Category;
import org.example.model.Product;
import org.example.service.ProductService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProductView {

    private static final List<Product> products = List.of(
            new Product("Bread", 2.49, Category.FOOD),
            new Product("Cheese", 5.99, Category.FOOD),
            new Product("Chocolate", 3.25, Category.FOOD),
            new Product("Laptop", 799.99, Category.ELECTRONICS),
            new Product("Smartphone", 599.99, Category.ELECTRONICS),
            new Product("Headphones", 149.99, Category.ELECTRONICS),
            new Product("Camera", 450.00, Category.ELECTRONICS),
            new Product("T-shirt", 19.99, Category.CLOTHES),
            new Product("Jacket", 89.99, Category.CLOTHES),
            new Product("Sneakers", 69.50, Category.CLOTHES),
            new Product("Jeans", 49.90, Category.CLOTHES),
            new Product("Milk", 1.50, Category.FOOD)
    );

    private static final Scanner sc = new Scanner(System.in);

    private static final ProductView instance = new ProductView();

    private ProductView() {}

    public static ProductView getInstance() {
        return instance;
    }

    ProductService productService = ProductService.getInstance();

    public void run(){
        printAllProducts(products);
        Category category = null;
        while(category == null){
            category = getCategory();
        }
        printProductByCategory(products, category);
        printTotalPrice(products, category);
    }

    private void printAllProducts(List<Product> list) {
        System.out.println("Список товаров:");
        list.forEach(System.out::println);
    }

    private void printProductByCategory(List<Product> list, Category category) {
        System.out.printf("Фильтрация товаров по категории: %s\n", category);
        System.out.println("""
                Введите способ сортировки:
                1. По возрастанию
                2. По убыванию
                """);
        try{
            int sortChoice = sc.nextInt();
            switch (sortChoice) {
                case 1 -> productService.filterByCategory(list, category, "asc").forEach(System.out::println);
                case 2 -> productService.filterByCategory(list, category, "desc").forEach(System.out::println);
                default -> System.out.println("Неверный ввод.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Неверный ввод, необходимо выбрать способ");
        }

    }

    private void printTotalPrice(List<Product> list, Category category) {
        System.out.printf("\nЦена всех товаров: %d $\n", Math.round(productService.calculateTotalPrice(list)));
        System.out.printf("Цена товаров в категории %s: %d $", category, Math.round(productService.calculateTotalPrice(productService.filterByCategory(list, category))));
    }

    private Category getCategory() {
        System.out.println("""
                Введите категорию для фильтрации:
                1. Еда
                2. Электроника
                3. Одежда
                """);
        try{
            int categoryChoice = sc.nextInt();
            switch(categoryChoice) {
                case 1: return Category.FOOD;
                case 2: return Category.ELECTRONICS;
                case 3: return Category.CLOTHES;
                default:
                    System.err.println("Неверный ввод");
            }
        } catch (InputMismatchException e) {
            System.err.println("Неверный ввод, необходимо выбрать способ");
        }
        return null;
    }

}
