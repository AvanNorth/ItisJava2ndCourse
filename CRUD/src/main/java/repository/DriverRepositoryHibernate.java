package repository;

import model.Driver;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class DriverRepositoryHibernate implements CrudRepository<Driver, Long> {
    private final EntityManager entityManager;

    public DriverRepositoryHibernate(EntityManager manager){
        this.entityManager = manager;
    }

    @Override
    public Optional<Driver> findById(Long id) {
        return Optional.of(entityManager.find(Driver.class,id));
    }

    @Override
    public List<Driver> findAll() {
        TypedQuery<Driver> query = entityManager.createQuery("select driver from Driver driver", Driver.class);
        return query.getResultList();
    }

    @Override
    public Driver save(Driver item) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(item);
        transaction.commit();
        return item;
    }

    @Override
    public void delete(Long id) {
        Driver deleteDr = entityManager.find(Driver.class, id);
        entityManager.remove(deleteDr);
        flushAndClear();
    }

    void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }
}
