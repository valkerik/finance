package ru.finance.bean;

import ru.finance.entity.Operation;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

@Stateless
@Local
public class OperationListBean {
    @EJB
    private OperationBean operationBean;

    private Operation newOperation = new Operation();

    public List<Operation> getAllOperations() {
        return operationBean.getAll();
    }

    public Operation getNewOperation() {
        return newOperation;
    }

    public void setNewOperation(Operation newOperation) {
        this.newOperation = newOperation;
    }

    public void addOperation() {
        newOperation.setDate(new Date());
        operationBean.add(newOperation);
        newOperation = new Operation(); // Очищаем новую операцию после добавления
    }

    public void editOperation(long id) {
        Operation operation = operationBean.get(id);
        // Реализуйте логику редактирования операции, если необходимо

    }

    public void deleteOperation(long id) {
        operationBean.delete(id);
    }

}
