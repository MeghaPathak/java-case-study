package repository;

import entity.Catalog;
import entity.CatalogProductRelationship;
import entity.Product;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class CatalogProductRelationshipRepository {
    private Set<CatalogProductRelationship> catalogToProduct;
    public static CatalogProductRelationshipRepository relationshipRepository;

    public CatalogProductRelationship addRelationship(Catalog c, Product p) {
        CatalogProductRelationship relationship = CatalogProductRelationship.builder().catalog(c).product(p).build();
        catalogToProduct.add(relationship);
        return relationship;
    }

    public boolean removeRelationship(Catalog c, Product p) {
        CatalogProductRelationship relationship = CatalogProductRelationship.builder().catalog(c).product(p).build();
        return catalogToProduct.remove(relationship);
    }

    public boolean deleteRelationshipByProduct(Product p) {
        Set<CatalogProductRelationship> collect = catalogToProduct.stream().filter(r -> r.getProduct().equals(p)).collect(Collectors.toSet());
        return catalogToProduct.removeAll(collect);
    }

    public boolean deleteRelationshipByCatalog(Catalog c) {
        Set<CatalogProductRelationship> collect = catalogToProduct.stream().filter(r -> r.getCatalog().equals(c)).collect(Collectors.toSet());
        return catalogToProduct.removeAll(collect);
    }

    private CatalogProductRelationshipRepository() {
        this.catalogToProduct = new HashSet<>();
    }

    public static CatalogProductRelationshipRepository getInstance() {
        if (relationshipRepository == null)
            relationshipRepository = new CatalogProductRelationshipRepository();
        return relationshipRepository;
    }


}
