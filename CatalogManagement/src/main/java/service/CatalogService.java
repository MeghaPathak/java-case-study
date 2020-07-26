package service;

import entity.Catalog;
import exception.MyException;
import repository.CatalogRepository;

public class CatalogService implements CustomService {

    private CatalogRepository catalogRepository;
    private CatalogProductService catalogProductService;

    public CatalogService() {
        this.catalogRepository = CatalogRepository.getInstance();
    }

    @Override
    public void add(Object o) {
        if (catalogRepository.add(o)) {
            System.out.println("Catalog added successfully");
        }
        System.out.println("Error in Catalog add");
    }

    @Override
    public void update(Object o) throws MyException {
        if (catalogRepository.update(o)) {
            System.out.println("Catalog update Success");
            return;
        }
        System.out.println("Error in Catalog Updation");
    }

    @Override
    public void delete(Object o) throws MyException {
        if (catalogRepository.delete(o) && catalogProductService.deleteRelationshipByCatalog((Catalog) o)) {
            System.out.println("Catalog deleted successfully");
            return;
        }
        System.out.println("Error in Catalog deletion");
    }

    @Override
    public Object get(Long id) {
        return null;
    }
}
