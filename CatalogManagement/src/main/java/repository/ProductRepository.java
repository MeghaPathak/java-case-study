package repository;

import entity.Product;
import exception.ErrorCode;
import exception.MyException;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class ProductRepository implements CustomRepository {

    private HashMap<Long, Product> productMap;
    private static ProductRepository productRepository;

    @Override
    public boolean add(Object o) {
        if (!(o instanceof Product)) return false;
        Product p = (Product) o;
        productMap.put(p.getId(), p);
        return true;
    }


    @Override
    public boolean delete(Object o) throws MyException {
        if (!(o instanceof Product)) return false;
        try {
            Product p = (Product) o;
            if (productMap.get(p.getId()) == null) return false;
            productMap.remove(p.getId());
            return true;
        } catch (Exception e) {
            throw new MyException(e, ErrorCode.REPOSITOTY_ERROR);
        }
    }

    @Override
    public boolean update(Object o) throws MyException {
        try {
            if (!(o instanceof Product)) return false;
            Product p = (Product) o;
            if (productMap.get(p.getId()) == null) return false;
            productMap.put(p.getId(), p);
            return true;
        } catch (Exception e) {
            throw new MyException(e, ErrorCode.REPOSITOTY_ERROR);
        }
    }

    @Override
    public Product get(Object o) {
        if (o == null) return null;
        return productMap.getOrDefault((long)o, null);
    }

    public static ProductRepository getInstance() {
        if (productRepository == null)
            productRepository = new ProductRepository();
        return productRepository;
    }

    private ProductRepository() {
        this.productMap = new HashMap<>();
    }
}
