package jp.co.xorphitus.wheathersample.network;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by 200105 on 2016/06/01.
 */
public class LivedoorWeatherServiceFactory {
  public static LivedoorWeatherService create() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://weather.livedoor.com/")
        .addConverterFactory(LoganSquareConverterFactory.create())
        .build();

    return retrofit.create(LivedoorWeatherService.class);
  }
}
