package nz.mega.bud.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import nz.mega.bud.R;
import nz.mega.core.ViewModelFactory;

public class CategoryFormFragment extends Fragment {

    @Inject
    ViewModelFactory<CategoryFormViewModel> viewModelFactory;
    private CategoryFormViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_form, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        this.viewModel = new ViewModelProvider(this, viewModelFactory)
                .get(CategoryFormViewModel.class);
    }
}
