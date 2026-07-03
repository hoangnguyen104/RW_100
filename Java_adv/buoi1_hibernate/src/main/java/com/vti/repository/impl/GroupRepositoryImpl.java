package com.vti.repository.impl;

import com.vti.entity.Group;
import com.vti.repository.IGroupRepository;
import com.vti.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class GroupRepositoryImpl implements IGroupRepository {

    private final SessionFactory sessionFactory = HibernateUtils.sessionFactory;

    @Override
    public List<Group> findAll() {
        Session session = sessionFactory.openSession();
        String hql = "FROM Group";
        Query<Group> query = session.createQuery(hql, Group.class);
        List<Group> groups = query.list();
        session.close();
        return groups;
    }

    @Override
    public Group findById(Integer id) {
        Session session = sessionFactory.openSession();
        Group group = session.find(Group.class, id);
        session.close();
        return group;
    }

    @Override
    public void create(Group group) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.persist(group);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Integer id, String newGroupName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            Group group = session.find(Group.class, id);
            group.setGroupName(newGroupName);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            Group group = session.find(Group.class, id);
            session.remove(group);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {

        GroupRepositoryImpl repository = new GroupRepositoryImpl();

        // Hiển thị danh sách Group
        List<Group> groups = repository.findAll();
        for (Group group : groups) {
            System.out.println(group);
        }

        // Tìm Group theo id
//        System.out.println(repository.findById(1));

        // Thêm Group
//        Group group = new Group();
//        group.setGroupName("Hibernate");
//        repository.create(group);

        // Cập nhật tên Group
//        repository.update(1, "Java Backend");

        // Xóa Group
//        repository.delete(1);
    }
}