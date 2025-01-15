package khiemvuong.product_service.controller;

import khiemvuong.product_service.model.Product;
import khiemvuong.product_service.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) {
        return productService.getProductById(productId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/")
    public ResponseEntity<String> addProduct(@RequestBody Product newProduct) {
        productService.addProduct(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable("productId") Long productId, @RequestBody Product updatedProduct) {
        if (productService.updateProduct(productId, updatedProduct)) {
            return ResponseEntity.ok("Product updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId) {
        if (productService.deleteProduct(productId)) {
            return ResponseEntity.ok("Product deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }
}
