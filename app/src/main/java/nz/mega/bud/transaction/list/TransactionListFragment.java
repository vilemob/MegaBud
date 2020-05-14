package nz.mega.bud.transaction.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import nz.mega.bud.databinding.FragmentTransactionListBinding;
import nz.mega.core.ViewModelFactory;

public class TransactionListFragment extends Fragment {

    @Inject
    ViewModelFactory<TransactionListViewModel> viewModelFactory;
    private TransactionListViewModel viewModel;
    private FragmentTransactionListBinding viewBinding;
    private TransactionListAdapter adapter = new TransactionListAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.viewBinding = FragmentTransactionListBinding.inflate(getLayoutInflater(), container,
                false);
        return this.viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.viewBinding.recyclerView.setAdapter(this.adapter);

        this.viewBinding.fab.setOnClickListener(v -> {
            NavDirections action = TransactionListFragmentDirections
                    .actionTransactionListFragmentToTransactionFormFragment();
            Navigation.findNavController(v).navigate(action);
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        this.viewModel = new ViewModelProvider(this, viewModelFactory)
                .get(TransactionListViewModel.class);

        this.viewModel.transactionsLive
                .observe(this, transactions -> this.adapter.submitList(transactions));
    }
}
