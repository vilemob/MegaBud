package nz.mega.bud;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import nz.mega.core.CoreModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        CoreModule.class,
        MegaBudModule.class
})
public interface MegaBudComponent extends AndroidInjector<MegaBudApp> {

    @Component.Factory
    interface Factory extends AndroidInjector.Factory<MegaBudApp> {
    }
}
