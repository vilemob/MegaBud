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
    @Currency
    public int currency;
}
