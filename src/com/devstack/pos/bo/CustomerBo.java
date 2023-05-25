package com.devstack.pos.bo;

import com.devstack.pos.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBo extends SuperBo {
    public boolean saveCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException;
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String email) throws SQLException, ClassNotFoundException;
    public CustomerDto findCustomer(String email) throws SQLException, ClassNotFoundException;
    public List<CustomerDto> findAllCustomers() throws SQLException, ClassNotFoundException;
    public  List<CustomerDto> searchCustomer(String searchText) throws SQLException, ClassNotFoundException;
}
