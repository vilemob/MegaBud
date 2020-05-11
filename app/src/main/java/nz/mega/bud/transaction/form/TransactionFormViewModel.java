package nz.mega.bud.transaction.form;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import nz.mega.core.data.category.Category;
import nz.mega.core.data.category.CategoryDao;

public class TransactionFormViewModel extends ViewModel {

    final LiveData<List<Category>> categoriesLive;

    @Inject
    public TransactionFormViewModel(CategoryDao categoryDao) {
        this.categoriesLive = categoryDao.getAllLive();
    }
}
