package nz.mega.core.data;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converters {

    @TypeConverter
    public static Currency fromCode(String code) {
        return code == null ? null : Currency.valueOf(code);
    }

    @TypeConverter
    public static String toCode(Currency currency) {
        if (currency == null) {
            return null;
        } else {
            return currency.name();
        }
    }

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
