package ru.finance.bean;

import ru.finance.entity.Operation;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@Local
public class OperationBean {
    @PersistenceContext(unitName = "datasource")
    private EntityManager em;
    public Operation add(Operation operation) {
        return em.merge(operation);
    }
    public Operation get(long id) {
        return em.find(Operation.class, id);
    }
    public void update(Operation operation) {
        add(operation);
    }
    public void delete(long id) {
        em.remove(get(id));
    }
    public List<Operation> getAll(){
        TypedQuery<Operation> namedQuery = em.createNamedQuery("getAllOperation", Operation.class);
        List<Operation> operations = namedQuery.getResultList();
        return operations;
    }
}