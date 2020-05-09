package nz.mega.bud.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import nz.mega.bud.databinding.FragmentCategoryFormBinding;
import nz.mega.core.ViewModelFactory;

public class CategoryFormFragment extends Fragment {

    @Inject
    ViewModelFactory<CategoryFormViewModel> viewModelFactory;
    private CategoryFormViewModel viewModel;
    private FragmentCategoryFormBinding viewBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.viewBinding = FragmentCategoryFormBinding.inflate(getLayoutInflater(), container, false);
        return this.viewBinding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        this.viewModel = new ViewModelProvider(this, viewModelFactory)
                .get(CategoryFormViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewBinding.saveButton.setOnClickListener(v -> {
            viewModel.save(this.viewBinding.categoryEditText.getText().toString());
        });
    }
}
