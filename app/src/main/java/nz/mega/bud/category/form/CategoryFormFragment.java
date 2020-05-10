package nz.mega.bud.category.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import nz.mega.bud.R;
import nz.mega.bud.databinding.FragmentCategoryFormBinding;
import nz.mega.core.ViewModelFactory;

public class CategoryFormFragment extends Fragment {

    private static final String KEY_COLOR_INDEX = "key_color_index";
    @Inject
    ViewModelFactory<CategoryFormViewModel> viewModelFactory;
    private CategoryFormViewModel viewModel;
    private FragmentCategoryFormBinding viewBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.viewBinding = FragmentCategoryFormBinding
                .inflate(getLayoutInflater(), container, false);
        return this.viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final int[] colors = getResources().getIntArray(R.array.palette);

        //region Configure Spinner.
        final String[] colorNames = getResources().getStringArray(R.array.palette_names);

        List<Map<String, ?>> data = new ArrayList<>();
        for (int i = 0; i < colors.length; i++) {
            Map<String, Integer> rowData = new HashMap<>();
            rowData.put(KEY_COLOR_INDEX, i);
            data.add(rowData);
        }

        SimpleAdapter adapter = new SimpleAdapter(requireContext(), data,
                android.R.layout.simple_spinner_item, new String[]{KEY_COLOR_INDEX},
                new int[]{android.R.id.text1});

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter.setViewBinder((view1, data1, textRepresentation) -> {
            TextView text1 = (TextView) view1;
            final Integer index = (Integer) data1;
            text1.setText(colorNames[index]);
            text1.setBackgroundColor(colors[index]);
            return true;
        });

        this.viewBinding.colorSpinner.setAdapter(adapter);
        //endregion

        this.viewBinding.saveButton.setOnClickListener(v -> {
            viewModel.save(this.viewBinding.categoryEditText.getText().toString(),
                    colors[this.viewBinding.colorSpinner.getSelectedItemPosition()]);
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        this.viewModel = new ViewModelProvider(this, viewModelFactory)
                .get(CategoryFormViewModel.class);

        final int categoryId = CategoryFormFragmentArgs.fromBundle(requireArguments())
                .getCategoryId();

        viewModel.loadCategoryById(categoryId);

        this.viewModel.categoryLive.observe(this, category -> {
            if (category != null) {
                this.viewBinding.categoryEditText.setText(category.getName());

                //region Populate Spinner.
                final int[] colors = getResources().getIntArray(R.array.palette);
                int position = 0;
                for (int i = 0; i < colors.length; i++) {
                    if (colors[i] == category.getColor()) {
                        position = i;
                    }
                }

                this.viewBinding.colorSpinner.setSelection(position);
                //endregion
            }
        });
    }
}
