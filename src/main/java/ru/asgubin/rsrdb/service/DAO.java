package ru.asgubin.rsrdb.service;

import java.sql.SQLException;
import java.util.Optional;

public interface DAO<T, ID> {
    Optional<T> find(ID num) throws SQLException;
//    List<T> findAll() throws SQLException;
//    boolean save(T o) throws  SQLException;
//    boolean update(T o) throws SQLException;
//    boolean delete(T o) throws SQLException;
}
