package ru.finance.bean;

import ru.finance.entity.Operation;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Stateless
@Local
public class OperationListBean implements Serializable {

    private static final String RUB = " рублей.";
    @EJB
    private OperationBean operationBean;

    private Operation newOperation = new Operation();
    private Operation editOperation;
    private long operationId;

    public long getOperationId() {
        return operationId;
    }

    public void setOperationId(long operationId) {
        this.operationId = operationId;
        this.editOperation = operationBean.get(operationId);
    }

    public Operation getEditOperation() {
        return editOperation;
    }

    public void setEditOperation(Operation editOperation) {
        this.editOperation = editOperation;
    }

    public List<Operation> getAllOperations() {
        List<Operation> operations = operationBean.getAll();
        operations.sort(new Comparator<Operation>() {
            @Override
            public int compare(Operation o1, Operation o2) {
                return Long.compare(o1.getId(), o2.getId());
            }
        });
        return operations;
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

    public void deleteOperation(long id) {
        operationBean.delete(id);
    }

    public void editOperation(long id) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("operationId", id);
        FacesContext.getCurrentInstance().getExternalContext().redirect("edit.xhtml?id=" + id);
    }

    public void updateOperation()throws IOException {
        operationBean.update(editOperation); // Вызываем метод обновления в OperationBean
        editOperation = null;
        FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");
    }

    public String calculateTotal() {
       double total = 0.0;
        List<Operation> operations = getAllOperations();
        for (Operation operation : operations) {
            if (operation.getOperationTypeString().equals("INCOME")) {
                total += operation.getAmount();
            } else if (operation.getOperationTypeString().equals("EXPENSE")) {
                total -= operation.getAmount();
            }
        }
        return String.valueOf(total) + RUB;
    }

    //доходы
    public String getTotalIncome() {
        double totalIncome = 0.0;
        List<Operation> operations = getAllOperations();
        for (Operation operation : operations) {
            if (operation.getOperationTypeString().equals("INCOME")) {
                totalIncome += operation.getAmount();
            }
        }
        return String.valueOf(totalIncome) + RUB ;
    }


    //расходы
    public String getTotalExpense() {
        double totalExpense = 0.0;
        List<Operation> operations = getAllOperations();
        for (Operation operation : operations) {
            if (operation.getOperationTypeString().equals("EXPENSE")) {
                totalExpense += operation.getAmount();
            }
        }
        return String.valueOf(totalExpense) + RUB;
    }

}
