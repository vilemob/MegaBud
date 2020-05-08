package nz.mega.bud.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.HasAndroidInjector;
import nz.mega.bud.R;
import nz.mega.core.ViewModelFactory;

public class MainActivity extends AppCompatActivity implements HasAndroidInjector {

    @Inject
    ViewModelFactory<MainViewModel> viewModelFactory;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.viewModel = new ViewModelProvider(this, viewModelFactory)
                .get(MainViewModel.class);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return viewModel.getAndroidInjector();
    }
}
