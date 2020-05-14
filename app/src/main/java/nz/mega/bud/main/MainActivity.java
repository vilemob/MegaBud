package nz.mega.bud.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.HasAndroidInjector;
import nz.mega.bud.R;
import nz.mega.bud.databinding.ActivityMainBinding;
import nz.mega.core.ViewModelFactory;

public class MainActivity extends AppCompatActivity implements HasAndroidInjector {

    @Inject
    ViewModelFactory<MainViewModel> viewModelFactory;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        this.viewModel = new ViewModelProvider(this, viewModelFactory)
                .get(MainViewModel.class);

        ActivityMainBinding viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        NavigationUI.setupWithNavController(viewBinding.navigation,
                Navigation.findNavController(this, R.id.nav_host_fragment));
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return viewModel.getAndroidInjector();
    }
}
