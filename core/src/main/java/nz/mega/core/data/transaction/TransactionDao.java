package nz.mega.core.data.transaction;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;

@Dao
public interface TransactionDao {

    @Query("SELECT * FROM `transaction`")
    LiveData<List<Transaction>> getAllLive();

    @Query("SELECT * FROM `transaction` WHERE id = :transactionId")
    LiveData<Transaction> getByIdLive(int transactionId);

    @Insert
    Completable insertAll(Transaction... transactions);

    @Update
    Completable update(Transaction transaction);

    @Delete
    Completable delete(Transaction transaction);
}
