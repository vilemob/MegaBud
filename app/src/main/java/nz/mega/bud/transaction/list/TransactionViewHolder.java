package nz.mega.bud.transaction.list;

import android.content.res.Resources;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import nz.mega.bud.R;
import nz.mega.bud.databinding.ItemTransactionBinding;
import nz.mega.core.data.transaction.Transaction;

class TransactionViewHolder extends RecyclerView.ViewHolder {

    private final ItemTransactionBinding viewBinding;

    TransactionViewHolder(@NonNull View itemView) {
        super(itemView);
        this.viewBinding = ItemTransactionBinding.bind(itemView);
    }

    void bind(Transaction transaction) {
        Resources resources = this.viewBinding.getRoot().getResources();
        this.viewBinding.transactionTextView
                .setText(resources.getString(R.string.transaction_date, transaction.getDateTime()));
        this.viewBinding.transactionTextView
                .append(resources.getString(R.string.transaction_value, transaction.getValue(),
                        transaction.getCurrency()));

        // TODO Bind category and colour.
    }
}
