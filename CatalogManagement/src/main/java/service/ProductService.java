package service;

import entity.Product;
import exception.MyException;
import repository.ProductRepository;

import java.util.List;

public class ProductService implements CustomService {

    private ProductRepository productRepository;
    private CatalogProductService catalogProductService;

    public ProductService(CatalogProductService catalogProductService) {
        this.catalogProductService = catalogProductService;
        this.productRepository = ProductRepository.getInstance();
    }

    @Override
    public void add(Object o) {
        if (productRepository.add(o)) {
            System.out.println("Product added successfully");
            return;
        }
        System.out.println("Error in product Add");
    }

    @Override
    public void update(Object o) throws MyException {
        if (productRepository.update(o)) {
            System.out.println("Product update Success");
            return;
        }
        System.out.println("Error in product Updation");
    }

    @Override
    public void delete(Object o) throws MyException {
        if (productRepository.delete(o) && catalogProductService.deleteRelationshipByProduct((Product) o)) {
            System.out.println("Product deleted from Product and Catalog list");
            return;
        }
        System.out.println("Error in product deletion");
    }

    @Override
    public Product get(Long id) {
        Product product = productRepository.get(id);
        if (product == null)
            System.out.println("No such product exists with id :" + id);
        return product;
    }

    public void search(String searchQuery) {
        List<String> searchResults = catalogProductService.search(searchQuery);
        System.out.println("******* " + searchResults.size() + " " + "Search Results for " + "\'" + searchQuery + "\'" + " " + " : *********");
        searchResults.forEach(s -> System.out.println(s));
    }

}
