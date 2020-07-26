package repository;

import entity.Catalog;
import entity.Product;
import exception.ErrorCode;
import exception.MyException;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class CatalogRepository implements CustomRepository {
    private HashMap<Long, Catalog> catalogMap;
    private static CatalogRepository catalogRepository;

    @Override
    public boolean add(Object o) {
        if (!(o instanceof Catalog)) return false;
        Catalog c = (Catalog) o;
        catalogMap.put(c.getId(), c);
        return true;
    }


    @Override
    public boolean delete(Object o) throws MyException {
        if (!(o instanceof Catalog)) return false;
        try {
            Catalog p = (Catalog) o;
            if (catalogMap.get(p.getId()) == null) return false;
            catalogMap.remove(p.getId());
            return true;
        } catch (Exception e) {
            throw new MyException(e, ErrorCode.REPOSITOTY_ERROR);
        }
    }

    @Override
    public boolean update(Object o) throws MyException {
        try {
            if (!(o instanceof Product)) return false;
            Catalog c = (Catalog) o;
            if (catalogMap.get(c.getId()) == null) return false;
            catalogMap.put(c.getId(), c);
            return true;
        } catch (Exception e) {
            throw new MyException(e, ErrorCode.REPOSITOTY_ERROR);
        }
    }

    @Override
    public Catalog get(Object o) {
        if (o == null) return null;

        return catalogMap.getOrDefault(o, null);
    }

    public static CatalogRepository getInstance() {
        if (catalogRepository == null)
            catalogRepository = new CatalogRepository();
        return catalogRepository;
    }

    private CatalogRepository() {
        this.catalogMap = new HashMap<>();
    }
}
