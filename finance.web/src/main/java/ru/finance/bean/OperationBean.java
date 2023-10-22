package ru.finance.bean;

import ru.finance.entity.Operation;
import ru.finance.entity.Users;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@Local
public class OperationBean {

    // Будет инициализирован контейнером Glassfish
    // unitName = "datasource" - это имя persistence-unit
    // EntityManager дает возможность выполнять CRUD запросы в БД
    @PersistenceContext(unitName = "datasource")
    private EntityManager em;

    // Добавляем Operation В базу данных
    public Operation add(Operation operation) {
        return em.merge(operation);
    }

    // Получаем operation по id
    public Operation get(long id) {
        return em.find(Operation.class, id);
    }

    // обновляем Operation
    // если Operation которого мыпытаемся обновить нет,
    // то запишется как новый
    public void update(Operation operation) {
        add(operation);
    }


    // удаляем Operation по id
    public void delete(long id) {
        em.remove(get(id));
    }

    // Получаем все operation с БД
    public List<Operation> getAll(){
        TypedQuery<Operation> namedQuery = em.createNamedQuery("getAllOperation", Operation.class);
        List<Operation> operations = namedQuery.getResultList();
        System.out.println(operations);
        return operations;
    }
}