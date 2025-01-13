package khiemvuong.product_service.controller;

import khiemvuong.product_service.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private List<Product> products = new ArrayList<>();

    public ProductController() {
        // Thêm dữ liệu mẫu vào trong controller
        products.add(new Product(1L, "Product 1", 100.0));
        products.add(new Product(2L, "Product 2", 200.0));
        products.add(new Product(3L, "Product 3", 300.0));
    }

    // Endpoint lấy thông tin sản phẩm theo ID
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) {
        Optional<Product> product = products.stream().filter(p -> p.getId().equals(productId)).findFirst();
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Endpoint lấy danh sách tất cả sản phẩm
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(products);
    }

    // Endpoint thêm sản phẩm mới
    @PostMapping("/")
    public ResponseEntity<String> addProduct(@RequestBody Product newProduct) {
        products.add(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
    }

    // Endpoint cập nhật thông tin sản phẩm
    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable("productId") Long productId, @RequestBody Product updatedProduct) {
        Optional<Product> existingProduct = products.stream().filter(p -> p.getId().equals(productId)).findFirst();
        if (existingProduct.isPresent()) {
            existingProduct.get().setName(updatedProduct.getName());
            existingProduct.get().setPrice(updatedProduct.getPrice());
            return ResponseEntity.ok("Product updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }

    // Endpoint xóa sản phẩm theo ID
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId) {
        Optional<Product> product = products.stream().filter(p -> p.getId().equals(productId)).findFirst();
        if (product.isPresent()) {
            products.remove(product.get());
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    }
}
