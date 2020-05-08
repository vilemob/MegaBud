package nz.mega.bud;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import nz.mega.bud.main.MainActivity;
import nz.mega.bud.main.MainModule;

@Module
public interface MegaBudModule {

    @ContributesAndroidInjector(modules = MainModule.class)
    MainActivity mainActivity();
}
