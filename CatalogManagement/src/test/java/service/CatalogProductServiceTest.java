package service;

import entity.Catalog;
import entity.Product;
import org.junit.Before;
import org.junit.Test;
import repository.CatalogProductRelationshipRepository;

import static org.junit.Assert.assertEquals;

public class CatalogProductServiceTest {

    private CatalogProductService catalogProductService = new CatalogProductService();
    CatalogProductRelationshipRepository repository = CatalogProductRelationshipRepository.getInstance();
    private Product p = Product.builder().id(1l).title("Britinia Biscuits").description("Butter Wheat Biscuits").build();
    private Catalog c = Catalog.builder().id(1l).name("Food").build();


    @Before
    public void setup() {
        catalogProductService.addRelationship(c, p);
    }

    @Test
    public void deleteRelationshipByProduct_positiveCase() {
        catalogProductService.addRelationship(c, Product.builder().id(100l).title("Oreo Biscuits").description("Chocolate Biscuits").build());
        assertEquals(catalogProductService.deleteRelationshipByProduct(p), true);
    }

    @Test
    public void deleteRelationshipByProduct_negativeCase() {
        assertEquals(catalogProductService.deleteRelationshipByProduct(Product.builder().id(13l).title("Monaco Biscuits").description("Chocolate Biscuits").build()), false);
    }

    @Test
    public void deleteRelationshipByCatalog() {
        assertEquals(catalogProductService.deleteRelationshipByCatalog(c), true);
    }
}