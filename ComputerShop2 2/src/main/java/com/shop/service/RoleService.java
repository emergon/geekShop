package com.shop.service;

import com.shop.entities.Role;
import java.util.List;

public interface RoleService {

    public List<Role> getRoles();

    public Role findById(Integer id);
    
}
