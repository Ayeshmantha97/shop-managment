package com.devstack.pos.dao.custom;

import com.devstack.pos.dao.CrudDao;
import com.devstack.pos.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends CrudDao<User,String> {
    public  boolean save(User user) throws SQLException, ClassNotFoundException;
    public  boolean update(User user) throws SQLException, ClassNotFoundException;
    public  boolean delete(String email) throws SQLException, ClassNotFoundException;
    public User find(String email) throws SQLException, ClassNotFoundException;
    public List<User> findAll() throws SQLException, ClassNotFoundException;
}
