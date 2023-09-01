package ru.finance.entity;

import ru.finance.exception.ModelException;
import ru.finance.exception.TitleException;
import ru.finance.save_load.SaveData;

import java.util.Objects;

public class Currency {
    private String title;
    private String code;
    private double rate;
    private boolean isOn;
    private boolean isBase;

    public Currency() {
    }

    public Currency(String title, String code, double rate, boolean isOn, boolean isBase) throws ModelException {
        if(title.length()==0) throw new ModelException(TitleException.TITLE_EMPTY.ordinal());
        if(code.length()==0) throw new ModelException(TitleException.CODE_EMPTY.ordinal());
        if(rate <=0 ) throw new ModelException(TitleException.RATE_INCORRECT.ordinal());

        this.title = title;
        this.code = code;
        this.rate = rate;
        this.isOn = isOn;
        this.isBase = isBase;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public boolean isBase() {
        return isBase;
    }

    public void setBase(boolean base) {
        isBase = base;
    }

    public double getRateByCurrensy(Currency currency){
        return rate/currency.rate;
    }

    public void postAdd(SaveData saveData){
        clearBase(saveData);
    }

    public void postEdit(SaveData saveData){
        clearBase(saveData);
    }

    private void clearBase(SaveData saveData){
        if(isBase){
            rate = 1;
            for (Currency currency : saveData.getCurrencies()) {
                if(!this.equals(currency)){
                    currency.setBase(false);
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(code, currency.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", rate=" + rate +
                ", isOn=" + isOn +
                ", isBase=" + isBase +
                '}';
    }
}
