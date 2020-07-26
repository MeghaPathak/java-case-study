package entity;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class CatalogProductRelationship {
    private Catalog catalog;
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogProductRelationship that = (CatalogProductRelationship) o;
        return Objects.equals(catalog, that.catalog) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalog, product);
    }
}
