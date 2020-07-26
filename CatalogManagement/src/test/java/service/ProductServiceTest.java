package service;

import entity.Catalog;
import entity.Product;
import exception.MyException;
import org.junit.Before;
import org.junit.Test;
import repository.ProductRepository;

import static org.junit.Assert.assertEquals;

public class ProductServiceTest {

    private ProductRepository productRepository = ProductRepository.getInstance();
    private CatalogProductService catalogProductService = new CatalogProductService();
    private ProductService productService = new ProductService(catalogProductService);
    private Product p = Product.builder().id(1l).title("Britinia Biscuits").description("Butter Wheat Biscuits").build();


    @Before
    public void setup() {
        productService.add(p);
        catalogProductService.addRelationship(Catalog.builder().name("Food").id(4l).build(), p);
    }

    @Test
    public void add() {
        assertEquals(productRepository.getProductMap().size(), 1);
    }

    @Test
    public void update() throws MyException {
        p.setTitle("ParleG");
        productService.update(p);
        assertEquals(productRepository.getProductMap().get(1l).getTitle(), "ParleG");
    }

    @Test
    public void delete() throws MyException {
        productService.delete(p);
        assertEquals(productRepository.getProductMap().size(), 0);
    }

    @Test
    public void get() {
        assertEquals(productService.get(1l), p);
    }

    @Test
    public void search() {
        Product p1 = Product.builder().id(2l).title("Monaco").description("Salty butter biscuit").build();
        catalogProductService.addRelationship(Catalog.builder().name("Food").id(444l).build(), p1);

        Product p2 = Product.builder().id(2l).title("Cocoa butter Cream").description("Cream with richness of cocoa butter").build();
        catalogProductService.addRelationship(Catalog.builder().name("Body Lotions").id(999l).build(), p2);

        productService.search("Butter");
    }
}

