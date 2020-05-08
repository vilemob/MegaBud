package nz.mega.core;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nz.mega.core.data.MegaBudDatabase;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class CoreModule {

    @Singleton
    @Provides
    static CurrencyLayerService provideCurrencyLayerService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.currencylayer.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        return retrofit.create(CurrencyLayerService.class);
    }

    @Singleton
    @Provides
    static MegaBudDatabase provideMegaBudDatabase(Context context) {
        return Room.databaseBuilder(context, MegaBudDatabase.class, "mega-bud-db").build();
    }
}
