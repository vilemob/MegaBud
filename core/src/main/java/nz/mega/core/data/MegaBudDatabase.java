package nz.mega.core.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import nz.mega.core.data.category.Category;
import nz.mega.core.data.category.CategoryDao;

@Database(entities = {Category.class}, version = 1)
public abstract class MegaBudDatabase extends RoomDatabase {
    public abstract CategoryDao categoryDao();
}