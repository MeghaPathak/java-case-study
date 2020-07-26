package service;

import entity.Catalog;
import exception.MyException;
import org.junit.Before;
import org.junit.Test;
import repository.CatalogRepository;

import static org.junit.Assert.assertEquals;

public class CatalogServiceTest {
    private CatalogService catalogService = new CatalogService();
    private CatalogRepository catalogRepository = CatalogRepository.getInstance();
    private Catalog c = Catalog.builder().id(1l).name("Food").build();


    @Before
    public void setup() {
        catalogService.add(c);
    }

    @Test
    public void add() {
        assertEquals(catalogRepository.getCatalogMap().size(), 1);
    }

    @Test
    public void update() throws MyException {
        c.setName("Desserts");
        catalogService.update(c);
        assertEquals(catalogRepository.getCatalogMap().get(1l).getName(), "Desserts");
    }

    @Test
    public void get() {
        assertEquals(catalogRepository.get(1l), c);
    }

}