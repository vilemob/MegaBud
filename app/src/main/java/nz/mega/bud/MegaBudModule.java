package nz.mega.bud;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface MegaBudModule {

    @ContributesAndroidInjector
    MainActivity mainActivity();
}
