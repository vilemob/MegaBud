package nz.mega.core.data.category;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM category")
    List<Category> getAll();

    @Query("SELECT * FROM category")
    LiveData<List<Category>> getAllLive();

    @Query("SELECT * FROM category WHERE id IN (:categoryIds)")
    List<Category> loadAllByIds(int[] categoryIds);

    @Insert
    Completable insertAll(Category... categories);

    @Delete
    void delete(Category category);
}
