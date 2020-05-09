package nz.mega.bud;

import android.content.Context;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import nz.mega.bud.main.MainActivity;
import nz.mega.bud.main.MainModule;

@Module
public interface MegaBudModule {

    @Binds
    Context context(MegaBudApp app);

    @ContributesAndroidInjector(modules = MainModule.class)
    MainActivity mainActivity();
}
