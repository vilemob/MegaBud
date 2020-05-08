package nz.mega.core.data.category;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import nz.mega.core.data.Currency;
import nz.mega.core.data.MegaBudDatabase;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class CategoryDaoTest {
    private CategoryDao categoryDao;
    private MegaBudDatabase db;

    @Before
    public void setUp() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, MegaBudDatabase.class).build();
        categoryDao = db.categoryDao();
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    @Test
    public void writeCategoryAndReadInList() {
        // Arrange
        Category expected = new Category();
        expected.setName("Test");
        expected.setBudget(100.1);
        expected.setColor(0xFFFFFFFF);
        expected.setCurrency(Currency.NZD);

        // Act
        categoryDao.insertAll(expected);
        List<Category> categories = categoryDao.getAll();
        Category obtained = categories.get(0);

        // Assert
        assertEquals(expected.getName(), obtained.getName());
        assertEquals(expected.getBudget(), obtained.getBudget(), 0);
        assertEquals(expected.getColor(), obtained.getColor());
        assertEquals(expected.getCurrency(), obtained.getCurrency());
    }
}
