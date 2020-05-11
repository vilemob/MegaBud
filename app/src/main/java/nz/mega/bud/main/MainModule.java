package nz.mega.bud.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import nz.mega.bud.category.form.CategoryFormFragment;
import nz.mega.bud.category.list.CategoryListFragment;
import nz.mega.bud.transaction.form.TransactionFormFragment;
import nz.mega.bud.transaction.list.TransactionListFragment;

@Module
public interface MainModule {

    @ContributesAndroidInjector
    CategoryListFragment categoryListFragment();

    @ContributesAndroidInjector
    CategoryFormFragment categoryFormFragment();

    @ContributesAndroidInjector
    TransactionListFragment transactionListFragment();

    @ContributesAndroidInjector
    TransactionFormFragment transactionFormFragment();
}
