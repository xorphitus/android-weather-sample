package jp.co.xorphitus.wheathersample.network;

import jp.co.xorphitus.wheathersample.models.Weather;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by 200105 on 2016/06/01.
 */
public interface LivedoorWeatherService {
  @GET("/forecast/webservice/json/v1?city=130010")
  public Call<Weather> find();
}
