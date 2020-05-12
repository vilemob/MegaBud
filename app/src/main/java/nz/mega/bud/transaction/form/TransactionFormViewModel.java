package nz.mega.bud.transaction.form;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import nz.mega.core.data.Currency;
import nz.mega.core.data.category.Category;
import nz.mega.core.data.category.CategoryDao;
import nz.mega.core.data.transaction.Transaction;
import nz.mega.core.data.transaction.TransactionDao;

public class TransactionFormViewModel extends ViewModel {

    final LiveData<List<Category>> categoriesLive;

    private final TransactionDao transactionDao;

    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    TransactionFormViewModel(CategoryDao categoryDao, TransactionDao transactionDao) {
        this.categoriesLive = categoryDao.getAllLive();
        this.transactionDao = transactionDao;
    }

    void save(Date date, double value, Currency currency, Category category) {
        Transaction transaction = new Transaction();
        transaction.setDateTime(date);
        transaction.setValue(value);
        transaction.setCurrency(currency);
        transaction.setCategoryId(category.getId());

        disposables.add(transactionDao.insertAll(transaction)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}
