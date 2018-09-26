package mapper;

import entities.Product;

import java.util.Optional;

public class ProductMapper {

    public static Product mapProductNameToUpperCase (Product product){
        return Optional.ofNullable(product).map(x -> new Product(x.getName().toUpperCase(), x.getPrice()))
                .orElse(null);
    }
}
