package jp.co.xorphitus.wheathersample.models.weather;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import jp.co.xorphitus.wheathersample.models.weather.forecast.Image;
import jp.co.xorphitus.wheathersample.models.weather.forecast.Temperature;

/**
 * Created by 200105 on 2016/06/01.
 */
@JsonObject public class Forecast {
  @JsonField public String dateLabel;

  @JsonField public String telop;

  @JsonField public String date;

  @JsonField public Temperature temperature;

  @JsonField public Image image;
}