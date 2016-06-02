package jp.co.xorphitus.wheathersample.models.weather.forecast;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by 200105 on 2016/06/02.
 */
@JsonObject public class Image {
  @JsonField public Integer width;
  @JsonField public String url;
  @JsonField public String title;
  @JsonField public Integer height;
}
