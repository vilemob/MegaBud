package nz.mega.core.data;

import androidx.room.TypeConverter;

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
}
