package com.shop.service;

import com.shop.dao.RoleDao;
import com.shop.entities.Role;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleDao rdao;
    
    @Override
    public List<Role> getRoles() {
        return rdao.findAll();
    }

    @Override
    public Role findById(Integer id) {
        return rdao.findById(id);
    }
    
}
