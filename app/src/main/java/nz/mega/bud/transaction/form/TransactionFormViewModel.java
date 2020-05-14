package nz.mega.bud.transaction.form;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import nz.mega.core.CurrencyLayerResponse;
import nz.mega.core.CurrencyLayerService;
import nz.mega.core.data.Currency;
import nz.mega.core.data.category.Category;
import nz.mega.core.data.category.CategoryDao;
import nz.mega.core.data.transaction.Transaction;
import nz.mega.core.data.transaction.TransactionDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionFormViewModel extends ViewModel {

    final MutableLiveData<TransactionFormState> liveState
            = new MutableLiveData<>(TransactionFormState.EDITING);

    final LiveData<List<Category>> categoriesLive;

    private final TransactionDao transactionDao;

    private CurrencyLayerService currencyLayerService;

    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    TransactionFormViewModel(CategoryDao categoryDao, TransactionDao transactionDao,
                             CurrencyLayerService currencyLayerService) {
        this.categoriesLive = categoryDao.getAllLive();
        this.transactionDao = transactionDao;
        this.currencyLayerService = currencyLayerService;
    }

    void save(Date date, double value, Currency currency, Category category) {
        Transaction transaction = new Transaction();
        transaction.setDateTime(date);
        transaction.setValue(value);
        transaction.setCurrency(currency);
        transaction.setCategoryId(category.getId());

        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd", Locale.US);
        currencyLayerService.historicalUSDToNZD(formatter.format(transaction.getDateTime()))
                .enqueue(new Callback<CurrencyLayerResponse>() {

                    @Override
                    public void onResponse(@NonNull Call<CurrencyLayerResponse> call,
                                           @NonNull Response<CurrencyLayerResponse> response) {
                        transaction.setExchange(response.body().getQuotes().get("USDNZD"));
                        disposables.add(transactionDao.insertAll(transaction)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(() -> {
                                    liveState.postValue(TransactionFormState.DONE);
                                }));
                    }

                    @Override
                    public void onFailure(Call<CurrencyLayerResponse> call, Throwable t) {
                        Log.e(TransactionFormViewModel.class.getSimpleName(),
                                t.getLocalizedMessage(), t);
                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}
