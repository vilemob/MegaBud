package nz.mega.bud.category.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import nz.mega.bud.R;
import nz.mega.bud.databinding.FragmentCategoryFormBinding;
import nz.mega.core.ViewModelFactory;
import nz.mega.core.data.Currency;

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
        configureColorSpinner(colors);

        final String[] currencies = getResources().getStringArray(R.array.currencies);
        configureCurrencySpinner(currencies);

        this.viewBinding.saveButton.setOnClickListener(v -> {
            //region Parse and validate inputs
            // TODO Validate inputs and display errors or proceed.
            String categoryName = this.viewBinding.categoryEditText.getText().toString();

            final int color = colors[this.viewBinding.colorSpinner.getSelectedItemPosition()];

            final String budgetInput = this.viewBinding.budgetEditText.getText().toString();
            final double budget = budgetInput.isEmpty() ? 0.0 : Double.parseDouble(budgetInput);

            String currencyInput =
                    currencies[this.viewBinding.currencySpinner.getSelectedItemPosition()];
            Currency currency = Currency.valueOf(currencyInput);
            //endregion

            viewModel.save(categoryName, color, budget, currency);

            //region Hide keyboard.
            this.viewBinding.budgetEditText.clearFocus();
            this.viewBinding.categoryEditText.clearFocus();
            //endregion

            NavDirections action = CategoryFormFragmentDirections
                    .actionCategoryFormFragmentToCategoryListFragment();
            Navigation.findNavController(v).navigate(action);
        });
    }

    private void configureCurrencySpinner(String[] currencies) {
        ArrayAdapter<CharSequence> currencyAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, currencies);

        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.viewBinding.currencySpinner.setAdapter(currencyAdapter);
    }

    private void configureColorSpinner(@NonNull int[] colors) {
        final String[] colorNames = getResources().getStringArray(R.array.palette_names);

        List<Map<String, ?>> data = new ArrayList<>();
        for (int i = 0; i < colors.length; i++) {
            Map<String, Integer> rowData = new HashMap<>();
            rowData.put(KEY_COLOR_INDEX, i);
            data.add(rowData);
        }

        SimpleAdapter colorAdapter = new SimpleAdapter(requireContext(), data,
                android.R.layout.simple_spinner_item, new String[]{KEY_COLOR_INDEX},
                new int[]{android.R.id.text1});

        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        colorAdapter.setViewBinder((view1, data1, textRepresentation) -> {
            TextView text1 = (TextView) view1;
            final Integer index = (Integer) data1;
            text1.setText(colorNames[index]);
            text1.setBackgroundColor(colors[index]);
            return true;
        });

        this.viewBinding.colorSpinner.setAdapter(colorAdapter);
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
                setColorSpinnerSelection(category.getColor());
                this.viewBinding.budgetEditText.setText(String.valueOf(category.getBudget()));
                setCurrencySpinnerSelection(category.getCurrency());
            }
        });
    }

    private void setCurrencySpinnerSelection(Currency currency) {
        final String[] currencies = getResources().getStringArray(R.array.currencies);
        for (int i = 0; i < currencies.length; i++) {
            if (Currency.valueOf(currencies[i]).equals(currency)) {
                this.viewBinding.currencySpinner.setSelection(i);
                break;
            }
        }
    }

    private void setColorSpinnerSelection(int color) {
        final int[] colors = getResources().getIntArray(R.array.palette);
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == color) {
                this.viewBinding.colorSpinner.setSelection(i);
                break;
            }
        }
    }
}
