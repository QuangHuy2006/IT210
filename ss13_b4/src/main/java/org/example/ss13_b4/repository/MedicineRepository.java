package org.example.ss13_b4.repository;

import org.example.ss13_b4.model.Medicine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class MedicineRepository {

    private final SessionFactory sessionFactory;

    public MedicineRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Medicine> findExpiredMedicines(LocalDate today) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Medicine m WHERE m.expiryDate < :today";
            Query<Medicine> query = session.createQuery(hql, Medicine.class);
            query.setParameter("today", today);
            return query.getResultList();
        }
    }
}