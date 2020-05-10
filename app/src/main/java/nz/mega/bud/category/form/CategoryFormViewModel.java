package nz.mega.bud.category.form;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import nz.mega.core.data.Currency;
import nz.mega.core.data.category.Category;
import nz.mega.core.data.category.CategoryDao;

public class CategoryFormViewModel extends ViewModel {

    private CategoryDao categoryDao;

    LiveData<Category> categoryLive;

    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    CategoryFormViewModel(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    void save(String categoryName, int color, double budget, Currency currency) {

        Category category = categoryLive.getValue();
        if (category != null) {
            category.setName(categoryName);
            category.setColor(color);
            category.setBudget(budget);
            category.setCurrency(currency);

            disposables.add(categoryDao.update(category)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
        } else {
            category = new Category();
            category.setName(categoryName);
            category.setColor(color);
            category.setBudget(budget);
            category.setCurrency(currency);

            disposables.add(categoryDao.insertAll(category)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }

    void loadCategoryById(int categoryId) {
        this.categoryLive = categoryDao.getCategoryByIdLive(categoryId);
    }
}
