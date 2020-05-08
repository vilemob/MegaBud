package nz.mega.core;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface CurrencyLayerService {

    @Headers("Accept: application/json")
    @GET("live?access_key=332ae32450e5379f046045cef5ccf1aa&format=1&currencies=NZD")
    Call<CurrencyLayerResponse> liveUSDToNZD();
}
