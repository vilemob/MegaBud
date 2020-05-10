package nz.mega.bud.category;

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

    void save(String categoryName, int color) {

        Category category = categoryLive.getValue();
        if (category != null) {
            category.setName(categoryName);
            category.setColor(color);

            disposables.add(categoryDao.update(category)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe());
        } else {
            category = new Category();
            category.setName(categoryName);
            category.setColor(color);

            // TODO Set this values from from.
            category.setBudget(0);
            category.setCurrency(Currency.NZD);

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
