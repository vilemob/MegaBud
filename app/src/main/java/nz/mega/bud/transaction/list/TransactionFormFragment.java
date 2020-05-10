package nz.mega.bud.transaction.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import nz.mega.bud.databinding.FragmentTransactionFormBinding;

public class TransactionFormFragment extends Fragment {

    private FragmentTransactionFormBinding viewBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.viewBinding = FragmentTransactionFormBinding.inflate(getLayoutInflater());
        return this.viewBinding.getRoot();
    }
}
