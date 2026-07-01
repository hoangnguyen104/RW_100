package com.vti.repository.impl;

import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.entity.enums.PositionName;
import com.vti.repository.IPositionRepository;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.vti.utils.HibernateUtils.sessionFactory;

public class PositionRepositoryImpl implements IPositionRepository {
    private final SessionFactory sessionFactory = HibernateUtils.sessionFactory;

    @Override
    public List<Position> findAll() {
        List<Position> positions = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try{
            String hql = "From Position";
            Query<Position> query = session.createQuery(hql, Position.class);
            positions = query.list();// lay ds
        } finally {
            session.close();
        }
        return positions;
    }

    @Override
    public Position findById(Integer id) {
        Position position = new Position();
        Session session = sessionFactory.openSession();
        try {
            String hql = "From Position where id = :idParam";
            Query<Position> query = session.createQuery(hql, Position.class);
            query.setParameter("idParam", 1);
            position = query.uniqueResult();
        } finally {
            session.close();
        }
        return position;
    }



}
