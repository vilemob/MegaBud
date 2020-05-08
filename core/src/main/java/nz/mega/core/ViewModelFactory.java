package nz.mega.core;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.Lazy;

public class ViewModelFactory<T extends ViewModel> implements ViewModelProvider.Factory {

    private final Lazy<T> viewModel;

    @Inject
    public ViewModelFactory(Lazy<T> viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    @SuppressWarnings("UNCHECKED_CAST")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) viewModel.get();
    }
}
