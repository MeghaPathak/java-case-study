package repository;

import exception.MyException;

public interface CustomRepository<T> {
    boolean add(Object o);
    boolean delete(Object o) throws MyException;
    boolean update(Object o) throws MyException;
    T get(Object o);
}
