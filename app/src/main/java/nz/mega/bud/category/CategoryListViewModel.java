package nz.mega.bud.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import nz.mega.core.data.category.Category;
import nz.mega.core.data.category.CategoryDao;

public class CategoryListViewModel extends ViewModel {

    private final LiveData<List<Category>> liveCategories;

    @Inject
    CategoryListViewModel(CategoryDao categoryDao) {
        this.liveCategories = categoryDao.getAllLive();
    }

    LiveData<List<Category>> getLiveCategories() {
        return liveCategories;
    }
}
