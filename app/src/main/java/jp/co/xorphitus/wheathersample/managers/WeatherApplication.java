package jp.co.xorphitus.wheathersample.managers;

import android.app.Application;
import timber.log.Timber;

/**
 * Created by 200105 on 2016/06/02.
 */
public class WeatherApplication extends Application {
  @Override public void onCreate() {
    super.onCreate();

    Timber.plant(new Timber.DebugTree());
  }
}
