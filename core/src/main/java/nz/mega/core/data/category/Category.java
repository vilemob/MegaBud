package nz.mega.core.data.category;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import nz.mega.core.data.Currency;

@Entity
public class Category {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "color")
    public int color;

    @ColumnInfo(name = "budget")
    public double budget;

    @ColumnInfo(name = "currency")
    private String currency;

    public String getCurrency() {
        return currency;
    }

    /**
     * This setter should be used by Room only, which is why it's package private and should not
     * made visible to other modules. Use the {@link #setCurrency(Currency)} method instead.
     *
     * @param currency Code for currency as a string.
     */
    void setCurrency(String currency){
        this.currency = currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency.getCode();
    }
}
