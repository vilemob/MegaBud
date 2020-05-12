package nz.mega.bud.transaction.form;

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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import nz.mega.bud.R;
import nz.mega.bud.databinding.FragmentTransactionFormBinding;
import nz.mega.core.ViewModelFactory;
import nz.mega.core.data.Currency;
import nz.mega.core.data.category.Category;

public class TransactionFormFragment extends Fragment {

    private static final String KEY_CATEGORY = "key_category";

    @Inject
    ViewModelFactory<TransactionFormViewModel> viewModelFactory;
    private TransactionFormViewModel viewModel;
    private FragmentTransactionFormBinding viewBinding;
    private SimpleAdapter categoriesAdapter;
    private List<Map<String, Category>> categoriesAdapterData;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.viewBinding = FragmentTransactionFormBinding.inflate(getLayoutInflater());
        return this.viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configureCurrencySpinner();
        configureCategorySpinner();

        this.viewBinding.saveButton.setOnClickListener(v -> {
            // TODO Parse date from input.
            final Date dateTime = new Date();

            final String valueInput = this.viewBinding.valueEditText.getText().toString();
            final double value = Double.parseDouble(valueInput);

            final String[] currencies = getResources().getStringArray(R.array.currencies);
            final String currencyInput =
                    currencies[this.viewBinding.currencySpinner.getSelectedItemPosition()];
            final Currency currency = Currency.valueOf(currencyInput);

            final Map<String, Category> categoryInput =
                    (Map<String, Category>) this.viewBinding.categorySpinner.getSelectedItem();
            final Category category = categoryInput.get(KEY_CATEGORY);

            this.viewModel.save(dateTime, value, currency, category);

            //region Hide keyboard.
            this.viewBinding.valueEditText.clearFocus();
            this.viewBinding.dateTimeEditText.clearFocus();
            //endregion

            NavDirections action = TransactionFormFragmentDirections
                    .actionTransactionFormFragmentToTransactionListFragment();
            Navigation.findNavController(view).navigate(action);
        });
    }

    private void configureCurrencySpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.currencies, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.viewBinding.currencySpinner.setAdapter(adapter);
    }

    private void configureCategorySpinner() {
        this.categoriesAdapterData = new ArrayList<>();

        this.categoriesAdapter = new SimpleAdapter(requireContext(),
                this.categoriesAdapterData, android.R.layout.simple_spinner_item,
                new String[]{KEY_CATEGORY}, new int[]{android.R.id.text1});

        this.categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.categoriesAdapter.setViewBinder((view, data, textRepresentation) -> {
            Category category = (Category) data;

            TextView text1 = (TextView) view;
            text1.setText(category.getName());
            text1.setBackgroundColor(category.getColor());
            return true;
        });

        this.viewBinding.categorySpinner.setAdapter(this.categoriesAdapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        this.viewModel = new ViewModelProvider(this, viewModelFactory)
                .get(TransactionFormViewModel.class);

        this.viewModel.categoriesLive.observe(this, categories -> {
            this.categoriesAdapterData.clear();

            for (int i = 0; i < categories.size(); i++) {
                Map<String, Category> rowData = new HashMap<String, Category>();
                rowData.put(KEY_CATEGORY, categories.get(i));
                this.categoriesAdapterData.add(rowData);
            }

            this.categoriesAdapter.notifyDataSetChanged();
        });
    }
}
