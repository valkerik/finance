package ru.finance.entity;

import ru.finance.exception.ModelException;
import ru.finance.exception.TitleException;

import java.util.List;
import java.util.Objects;

public class Account {
    private String title;
    private Currency currency;
    private double startAmount;
    private double amount;

    public Account() {
    }

    public Account(String title, Currency currency, double startAmount) throws ModelException {
        if(title.length()==0) throw new ModelException(TitleException.TITLE_EMPTY.ordinal());
        if(currency == null) throw new ModelException(TitleException.CURRENCY_EMPTY.ordinal());

        this.title = title;
        this.currency = currency;
        this.startAmount = startAmount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(double startAmount) {
        this.startAmount = startAmount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setAmountFromTransactionsAndTransfers(List<Transaction> transactions, List<Transfer> transfers){
        this.amount = startAmount;
        for (Transaction transaction : transactions) {
            if(transaction.getAccount().equals(this)){
                this.amount+=transaction.getAmount();
            }
        }

        for (Transfer transfer : transfers) {
            if (transfer.getFromAccount().equals(this)){
                this.amount-= transfer.getFromAmount();
            }
            if (transfer.getToAccount().equals(this)){
                this.amount+= transfer.getToAmount();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(title, account.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
