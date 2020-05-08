package nz.mega.bud.main;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;

public class MainViewModel extends ViewModel {

    private final DispatchingAndroidInjector<Object> androidInjector;

    @Inject
    MainViewModel(DispatchingAndroidInjector<Object> androidInjector) {
        this.androidInjector = androidInjector;
    }

    DispatchingAndroidInjector<Object> getAndroidInjector() {
        return androidInjector;
    }
}
