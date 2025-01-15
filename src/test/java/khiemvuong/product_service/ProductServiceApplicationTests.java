package khiemvuong.product_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	@BeforeEach
void setupData() throws Exception {
    String initialProduct1 = """
        {
            "id": 1,
            "name": "Product 1",
            "price": 100.0
        }
        """;

    // Tạo lại sản phẩm 1
    mockMvc.perform(post("/api/products/")
            .contentType("application/json")
            .content(initialProduct1))
        .andExpect(status().isCreated())
        .andExpect(content().string("Product added successfully"));
}

	@Test
	void contextLoads() {
	}
	 @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllProducts() throws Exception {
        mockMvc.perform(get("/api/products/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].id").exists());
    }

    @Test
    void shouldAddNewProduct() throws Exception {
        String newProduct = """
            {
                "id": 4,
                "name": "Product 4",
                "price": 400.0
            }
            """;

        mockMvc.perform(post("/api/products/")
                .contentType("application/json")
                .content(newProduct))
            .andExpect(status().isCreated())
            .andExpect(content().string("Product added successfully"));
    }

    @Test
    void shouldUpdateProduct() throws Exception {
        String updatedProduct = """
            {
                "id": 1,
                "name": "Updated Product 1",
                "price": 150.0
            }
            """;

        mockMvc.perform(put("/api/products/1")
                .contentType("application/json")
                .content(updatedProduct))
            .andExpect(status().isOk())
            .andExpect(content().string("Product updated successfully"));
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/1"))
            .andExpect(status().isOk())
            .andExpect(content().string("Product deleted successfully"));
    }	
}
