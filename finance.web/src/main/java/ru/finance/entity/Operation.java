package ru.finance.entity;

import ru.finance.util.OperationType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity(name = "operations")
@NamedQueries({
        @NamedQuery(name="getAllOperation", query = "select o from operations o")
})
public class Operation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operation_id")
    private long id;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type", nullable = false)
    private OperationType operationType;
    @Column(name = "title")
    private String title;

    @Column(name = "amount")
    private double amount;

    @Column(name = "comment")
    private String comment;

    public Operation() {
    }

    public Operation(Date date, OperationType operationType, String title, double amount, String comment) {
        this.date = date;
        this.operationType = operationType;
        this.title = title;
        this.amount = amount;
        this.comment = comment;
    }

    public Operation(Date date, OperationType operationType, String title, double amount) {
        this.date = date;
        this.operationType = operationType;
        this.title = title;
        this.amount = amount;
        this.comment = "";
    }
}
