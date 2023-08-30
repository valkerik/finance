package ru.finance.bean;


import ru.finance.entity.Users;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@Local
public class UserBean {

    // Будет инициализирован контейнером Glassfish
    // unitName = "datasource" - это имя persistence-unit
    // EntityManager дает возможность выполнять CRUD запросы в БД
    @PersistenceContext(unitName = "datasource")
    private EntityManager em;

    // Добавляем User-а В базу данных
    public Users add(Users user) {
        return em.merge(user);
    }

    // Получаем пользователя по id
    public Users get(long id) {
        return em.find(Users.class, id);
    }

    // обновляем пользователя
    // если User которого мыпытаемся обновить нет,
    // то запишется он как новый
    public void update(Users user) {
        add(user);
    }

    // удаляем User по id
    public void delete(long id) {
        em.remove(get(id));
    }

    // Получаем все пользователей с БД
    public List<Users> getAll() {
        TypedQuery<Users> namedQuery = em.createNamedQuery("getAllUsers", Users.class);
        List<Users> users = namedQuery.getResultList();
        System.out.println(users);
        return users;
    }
}
