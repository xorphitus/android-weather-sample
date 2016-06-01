package jp.co.xorphitus.wheathersample.models.weather.forecast;

/**
 * Created by 200105 on 2016/06/01.
 */

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@JsonObject public class Temperature {
  @JsonField public Integer min;

  @JsonField public Integer max;
}