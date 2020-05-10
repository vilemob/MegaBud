package nz.mega.bud.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import nz.mega.bud.category.form.CategoryFormFragment;
import nz.mega.bud.category.list.CategoryListFragment;

@Module
public interface MainModule {

    @ContributesAndroidInjector
    CategoryListFragment categoryListFragment();

    @ContributesAndroidInjector
    CategoryFormFragment categoryFormFragment();
}
