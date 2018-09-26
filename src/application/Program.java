package application;

import application.service.ProductService;
import entities.Product;
import mapper.ProductMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Utiliza expressão lambda para ordenar por ordem alfabética a lista de produtos (Comparator)
public class Program {
    public static void main(String[] args) {

        List<Product> list = new ArrayList<>();

        list.add(new Product("TV", 900.00));
        list.add(new Product("Notebook", 1200.00));
        list.add(new Product("Tablet", 450.00));

        list.sort(Comparator.comparing(p -> p.getName().toUpperCase()));

        for (Product p : list) {
            System.out.println(p);
        }
    }

    // Remove todos os itens da lista com preço acima de 100 (Predicate)
    @Test
    public void test(){
        List<Product> list = new ArrayList<>();

        list.add(new Product("TV", 900.00));
        list.add(new Product("Mouse", 50.00));
        list.add(new Product("Tablet", 350.00));
        list.add(new Product("HD Case", 80.90));

        Double min = 100.00;

        list.removeIf(x -> x.getPrice() >= min);

        for (Product p : list) {
            System.out.println(p);

        }
    }

    // Aumenta o preço de todos os produtos em 10% (Consumer)
    @Test
    public void test1(){
        List<Product> list = new ArrayList<>();

        list.add(new Product("TV", 900.00));
        list.add(new Product("Mouse", 50.00));
        list.add(new Product("Tablet", 350.00));
        list.add(new Product("HD Case", 80.90));

        list.forEach(x -> x.setPrice(x.getPrice() * 1.1));

        list.forEach(System.out::println);
    }

    // Deixa todos os produtos com o nome em maiúsculas utilizando a classe criada ProductMapper (Function)
    @Test
    public void test2(){
        List<Product> list = new ArrayList<>();

        list.add(new Product("TV", 900.00));
        list.add(new Product("Mouse", 50.00));
        list.add(new Product("Tablet", 350.00));
        list.add(new Product("HD Case", 80.90));

        // Com map
        list = list.stream().map(ProductMapper::mapProductNameToUpperCase).collect(Collectors.toList());
        list.forEach(System.out::println);

        //Com forEach
        list.forEach(ProductMapper::mapProductNameToUpperCase);
        list.forEach(System.out::println);
    }

    // Criando um método que soma o preço de todos os elementos de uma lista filtrando de acordo com um predicado (Predicate)
    @Test
    public void test3(){
        List<Product> list = new ArrayList<>();

        list.add(new Product("TV", 900.00));
        list.add(new Product("Mouse", 50.00));
        list.add(new Product("Tablet", 350.00));
        list.add(new Product("HD Case", 80.90));

        // Somando o preço de todos os que o nome começam com T
        double resultComMetodoEstatico = ProductService.filteredSum(list, x -> x.getName().charAt(0) == 'T');

        double resultStream = list.stream()
                .filter(x -> x.getName().charAt(0) == 'T')
                .map(x -> x.getPrice())
                .reduce(0.0, (x,y) -> x + y);

        // Somando todos os preços dos produtos onde o preço é menor ou igual a 100
        double result1ComMetodoEstatico = ProductService.filteredSum(list, x -> x.getPrice() <= 100);

        double result1Stream = list.stream()
                .filter(x -> x.getPrice() <= 100)
                .map(x -> x.getPrice())
                .reduce(0.0, (x,y) -> x + y);

        System.out.println(resultComMetodoEstatico);
        System.out.println(resultStream);
        System.out.println(result1ComMetodoEstatico);
        System.out.println(result1Stream);
    }
}
