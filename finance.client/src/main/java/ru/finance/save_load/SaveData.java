package ru.finance.save_load;

import ru.finance.entity.*;
import ru.finance.util.Filter;
import ru.finance.xmlelement.SaveLoadXML;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SaveData {

    private static SaveData instance;

    private List<Article> articles = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private List<Currency> currencies = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private List<Transfer> transfers = new ArrayList<>();
    private final Filter filter;
 //   private Common oldCommon;

    private SaveData() {
        load();
        this.filter = new Filter();
    }

    public static SaveData getInstance(){
        if(instance == null) instance = new SaveData();
        return instance;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public Filter getFilter() {
        return filter;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public Currency getBaseCurrency(){
        for (Currency currency : currencies) {
            if(currency.isBase()) return currency;
        }
        return new Currency();
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }

    private void load() {
        SaveLoadXML.load(this);
        sort();
    }

    private void sort() {
        this.articles.sort((Article t,Article t1) -> t.getTitle().compareToIgnoreCase(t1.getTitle()));
        this.accounts.sort((Account t,Account t1) -> t.getTitle().compareToIgnoreCase(t1.getTitle()));
        this.transactions.sort((Transaction t,Transaction t1) -> (int)(t1.getDate().compareTo(t.getDate())));
        this.transfers.sort((Transfer t,Transfer t1) -> (int)(t1.getDate().compareTo(t.getDate())));
        this.currencies.sort(new Comparator<Currency>() {
            @Override
            public int compare(Currency o1, Currency o2) {
                if (o1.isBase()) return -1;
                if (o2.isBase()) return 1;
                if (o1.isOn() ^ o2.isOn()){
                    if(o2.isOn()) return 1;
                    else return -1;
                }
                return o1.getTitle().compareToIgnoreCase(o2.getTitle());
            }
        });
    }

    public void save(){
        SaveLoadXML.save(this);
    }

    public List<Transaction> getFilteredTransactions(){
        ArrayList<Transaction> list = new ArrayList<>();
        for (Transaction t : transactions) {
            if(filter.checkDate(t.getDate())) list.add(t);
        }
        return list;
    }

    public List<Transaction> getLastTransactions(int count){
        return new ArrayList<>(transactions.subList(0, Math.min(count, transactions.size())));

    }

    public List<Transfer> getFilteredTransfers(){
        ArrayList<Transfer> list = new ArrayList<>();
        for (Transfer t : transfers) {
            if(filter.checkDate(t.getDate())) list.add(t);
        }
        return list;
    }
}
