package application.service;

import entities.Product;

import java.util.List;
import java.util.function.Predicate;

public class ProductService {

    // Recebe a lista e um critério lambda do tipo predicate para fazer a filtragem e calcular a soma
    public static double filteredSum(List<Product> list, Predicate<Product> criteria) {
        double sum = 0.0;
        for (Product x : list) {
            if (criteria.test(x)) {
                sum += x.getPrice();
            }
        }
        return sum;
    }
}
