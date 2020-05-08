package nz.mega.core;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class CoreModule {

    @Provides
    static CurrencyLayerService provideCurrencyLayerService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.currencylayer.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        return retrofit.create(CurrencyLayerService.class);
    }
}
