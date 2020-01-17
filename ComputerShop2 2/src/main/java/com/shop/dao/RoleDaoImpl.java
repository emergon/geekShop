package com.shop.dao;

import com.shop.entities.Role;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Role> findAll() {
        Query q = getSession().createQuery("SELECT r FROM Role r");
        List<Role> list = q.getResultList();
        return list;
    }

    @Override
    public Role findById(Integer id) {
        return getSession().byId(Role.class).load(id);
    }

}
