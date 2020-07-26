package service;

import exception.MyException;

public interface CustomService {
    void add(Object o);

    void update(Object o) throws MyException;

    void delete(Object o) throws MyException;

    Object get(Long id);
}
