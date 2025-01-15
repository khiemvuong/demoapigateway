package khiemvuong.product_service.service;

import khiemvuong.product_service.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product(1L, "Product 1", 100.0));
        products.add(new Product(2L, "Product 2", 200.0));
        products.add(new Product(3L, "Product 3", 300.0));
    }

    public Optional<Product> getProductById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProduct = getProductById(id);
        if (existingProduct.isPresent()) {
            existingProduct.get().setName(updatedProduct.getName());
            existingProduct.get().setPrice(updatedProduct.getPrice());
            return true;
        }
        return false;
    }

    public boolean deleteProduct(Long id) {
        return products.removeIf(p -> p.getId().equals(id));
    }
}
