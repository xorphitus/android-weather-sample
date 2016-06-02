package jp.co.xorphitus.wheathersample.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import java.util.List;
import jp.co.xorphitus.wheathersample.models.weather.Description;
import jp.co.xorphitus.wheathersample.models.weather.Forecast;

/**
 * Created by 200105 on 2016/06/01.
 */
@JsonObject public class Weather {
  @JsonField public List<Forecast> forecasts;
  @JsonField public String title;
  @JsonField public Description description;
}
