package service;

import entity.Catalog;
import entity.CatalogProductRelationship;
import entity.Product;
import lombok.Getter;
import repository.CatalogProductRelationshipRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
public class CatalogProductService {
    private CatalogProductRelationshipRepository repository;
    private InvertedIndexService invertedIndexService;

    public CatalogProductService() {
        this.invertedIndexService = new InvertedIndexService();
        this.repository = CatalogProductRelationshipRepository.getInstance();
    }

    public void addRelationship(Catalog c, Product p) {
        CatalogProductRelationship relationship = repository.addRelationship(c, p);
        invertedIndexService.index(relationship);
        System.out.println("Product successfully added to Catalog");
    }

    public boolean deleteRelationshipByProduct(Product p) {
        boolean isSuccess = repository.deleteRelationshipByProduct(p);
        if (isSuccess) {
            invertedIndexService.updateKeywordMap(p);
            System.out.println("Product removed successfully from All catalog.");
        } else
            System.out.println("The product does not exists in the Catalog");
        return isSuccess;
    }

    public boolean deleteRelationshipByCatalog(Catalog c) {
        boolean isSuccess = repository.deleteRelationshipByCatalog(c);
        if (!isSuccess)
            System.out.println("The product does not exists in the Catalog");
        else
            System.out.println("Product removed successfully from All catalog.");
        return isSuccess;
    }

    public List<String> search(String searchQuery) {
        List<String> searchResults = new LinkedList<>();
        Set<CatalogProductRelationship> search = invertedIndexService.search(searchQuery.toLowerCase());
        search.stream().forEach(relationship -> {
            String result = "Product: " + relationship.getProduct().getTitle() + " " + "in Catalog :" + relationship.getCatalog().getName();
            searchResults.add(result);
        });
        return searchResults;
    }
}
