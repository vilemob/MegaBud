package nz.mega.bud.transaction.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import nz.mega.bud.databinding.FragmentTransactionListBinding;

public class TransactionListFragment extends Fragment {

    private FragmentTransactionListBinding viewBinding;

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
        this.viewBinding.fab.setOnClickListener(v -> {
            NavDirections action = TransactionListFragmentDirections
                    .actionTransactionListFragmentToTransactionFormFragment();
            Navigation.findNavController(v).navigate(action);
        });
    }
}
