package nz.mega.core.data.transaction;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import nz.mega.core.data.Currency;

@Entity
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "datetime")
    private Date dateTime;

    @ColumnInfo(name = "value")
    private double value;

    @ColumnInfo(name = "currency")
    private Currency currency;

    @ColumnInfo(name = "exchange", defaultValue = "1.0")
    private double exchange;

    @ColumnInfo(name = "category")
    private int categoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getExchange() {
        return exchange;
    }

    public void setExchange(double exchange) {
        this.exchange = exchange;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getUSDValue() {
        if (currency.equals(Currency.USD)) {
            return this.value;
        } else {
            return this.value / this.exchange;
        }
    }

    public double getNZDValue() {
        if (currency.equals(Currency.NZD)) {
            return this.value;
        } else {
            return this.value * this.exchange;
        }
    }
}
