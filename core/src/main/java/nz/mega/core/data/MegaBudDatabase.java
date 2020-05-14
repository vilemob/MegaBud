package nz.mega.core.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import nz.mega.core.data.category.Category;
import nz.mega.core.data.category.CategoryDao;
import nz.mega.core.data.transaction.Transaction;
import nz.mega.core.data.transaction.TransactionDao;

@Database(entities = {Category.class, Transaction.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class MegaBudDatabase extends RoomDatabase {
    public abstract CategoryDao categoryDao();

    public abstract TransactionDao transactionDao();
}
