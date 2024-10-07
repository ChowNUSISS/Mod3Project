package sg.edu.ntu.simple_crm;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    private ArrayList<Product> products = new ArrayList<Product>();

    // helper method
    private int getProductIndex(String id) {
        for(Product product: products) {
            if(product.getId().equals(id)){
                return products.indexOf(product);
            }
        }
        throw new ProductNotFoundException(id);
    }

    // http://localhost:8080/products/ and http://localhost:8080/products
    @GetMapping({"/",""})
    public ResponseEntity<ArrayList<Product>> getAllProducts() {
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // http://localhost:8080/products/
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        try {
            int index = getProductIndex(id);
            return new ResponseEntity<>(products.get(index), HttpStatus.OK);
        } catch(ProductNotFoundException rtex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        products.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        try {
            int index = getProductIndex(id);            
            products.set(index, product);

            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (ProductNotFoundException rtex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
        try {
            int index = getProductIndex(id);
            return new ResponseEntity<>(products.remove(index), HttpStatus.NO_CONTENT);
        } catch (ProductNotFoundException rtex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } 
}
