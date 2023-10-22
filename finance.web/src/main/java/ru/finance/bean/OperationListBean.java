package ru.finance.bean;

import ru.finance.entity.Operation;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local
public class OperationListBean {
    @EJB
    private OperationBean operationBean;

    public List<Operation> getAllOperations() {
        return operationBean.getAll();
    }
}
