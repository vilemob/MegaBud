package nz.mega.bud.category.list;

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
import nz.mega.bud.databinding.FragmentCategoryListBinding;
import nz.mega.core.ViewModelFactory;

public class CategoryListFragment extends Fragment {

    @Inject
    ViewModelFactory<CategoryListViewModel> viewModelFactory;
    private CategoryListViewModel viewModel;
    private FragmentCategoryListBinding viewBinding;
    private CategoryListAdapter adapter = new CategoryListAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.viewBinding = FragmentCategoryListBinding.inflate(getLayoutInflater(), container, false);
        return this.viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.viewBinding.recyclerView.setAdapter(adapter);

        this.viewBinding.fab.setOnClickListener(v -> {
            NavDirections action = CategoryListFragmentDirections
                    .actionCategoryListFragmentToCategoryFormFragment();
            Navigation.findNavController(v).navigate(action);
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        this.viewModel = new ViewModelProvider(this, viewModelFactory)
                .get(CategoryListViewModel.class);

        this.viewModel.getLiveCategories().observe(this, categories -> {
            this.adapter.submitList(categories);
        });
    }
}
