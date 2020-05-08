package nz.mega.core.data;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static nz.mega.core.data.Currency.NZD;
import static nz.mega.core.data.Currency.UNKNOWN;
import static nz.mega.core.data.Currency.USD;

@Retention(RetentionPolicy.SOURCE)
@IntDef({UNKNOWN, NZD, USD})
public @interface Currency {
    int UNKNOWN = 0;
    int NZD = 1;
    int USD = 2;
}
