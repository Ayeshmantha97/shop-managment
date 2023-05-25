package com.devstack.pos.bo;

import com.devstack.pos.dto.CustomerDto;
import com.devstack.pos.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserBo extends SuperBo {
    public boolean saveUser(UserDto userDto) throws SQLException, ClassNotFoundException;
    public boolean updateUser(UserDto userDto);
    public boolean deleteUser(String email);
    public UserDto findUser(String email) throws SQLException, ClassNotFoundException;
    public List<CustomerDto> findAllUsers();
}
