package nz.mega.bud.transaction.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import nz.mega.core.data.transaction.Transaction;
import nz.mega.core.data.transaction.TransactionDao;

public class TransactionListViewModel extends ViewModel {

    final LiveData<List<Transaction>> transactionsLive;

    @Inject
    public TransactionListViewModel(TransactionDao transactionDao) {
        this.transactionsLive  = transactionDao.getAllLive();
    }
}
