package jp.co.xorphitus.wheathersample.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by 200105 on 2016/06/01.
 */
public class LivedoorWeatherServiceFactory {
  public static LivedoorWeatherService create() {
    OkHttpClient client =
        new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).build();

    Retrofit retrofit = new Retrofit.Builder().client(client)
        .baseUrl("http://weather.livedoor.com/")
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(LoganSquareConverterFactory.create())
        .build();

    return retrofit.create(LivedoorWeatherService.class);
  }
}
