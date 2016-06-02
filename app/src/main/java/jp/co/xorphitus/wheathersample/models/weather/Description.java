package jp.co.xorphitus.wheathersample.models.weather;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by 200105 on 2016/06/02.
 */
@JsonObject public class Description {
  @JsonField public String text;
  @JsonField public String publicTime;
}
