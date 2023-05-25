package com.devstack.pos.dao.custom;

import com.devstack.pos.dao.CrudDao;
import com.devstack.pos.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao extends CrudDao<Customer,String> {
    public  boolean save(Customer customer) throws SQLException, ClassNotFoundException;
    public  boolean update(Customer customer) throws SQLException, ClassNotFoundException;
    public  boolean delete(String email) throws SQLException, ClassNotFoundException;
    public Customer find(String email) throws SQLException, ClassNotFoundException;
    public List<Customer> findAll() throws SQLException, ClassNotFoundException;
    public  List<Customer> searchCustomer(String searchText) throws SQLException, ClassNotFoundException;

}
