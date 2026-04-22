package org.example.ss13_b5.repository;

import org.example.ss13_b5.model.Prescription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PrescriptionRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Prescription> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Prescription", Prescription.class).list();
        }
    }

    public void save(Prescription prescription) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(prescription);
            tx.commit();
        }
    }

    public List<Prescription> findByPatientCode(String patientCode) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Prescription p WHERE p.patientCode = :code";
            Query<Prescription> query = session.createQuery(hql, Prescription.class);
            query.setParameter("code", patientCode);
            return query.list();
        }
    }
}

