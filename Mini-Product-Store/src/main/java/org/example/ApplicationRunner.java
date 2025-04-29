package org.example;

import org.example.view.ProductView;

public class ApplicationRunner {

    private static final ProductView productView = ProductView.getInstance();

    public static void main(String[] args) {
        productView.run();
    }
}
