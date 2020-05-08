package nz.mega.core.data.category;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM category")
    List<Category> getAll();

    @Query("SELECT * FROM category WHERE id IN (:categoryIds)")
    List<Category> loadAllByIds(int[] categoryIds);

    @Insert
    void insertAll(Category... categories);

    @Delete
    void delete(Category category);
}
