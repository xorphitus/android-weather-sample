package jp.co.xorphitus.wheathersample.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.co.xorphitus.wheathersample.R;
import jp.co.xorphitus.wheathersample.models.Weather;
import jp.co.xorphitus.wheathersample.network.LivedoorWeatherService;
import jp.co.xorphitus.wheathersample.network.LivedoorWeatherServiceFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * メインコンテンツ表示用
 */
public class MainFragment extends Fragment {
  private Subscription weatherSubscription;

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    LivedoorWeatherService service = LivedoorWeatherServiceFactory.create();
    Observable<Weather> weather = service.find();

    View view = inflater.inflate(R.layout.fragment_main, container, false);
    final ViewHolder viewHolder = new ViewHolder(view);

    weatherSubscription = weather.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.newThread())
        .subscribe(new Observer<Weather>() {
          @Override public void onCompleted() {

          }

          @Override public void onError(Throwable e) {
            Timber.d(e, "error on weather api call");
          }

          @Override public void onNext(Weather weather) {
            viewHolder.weatherText.setText(weather.title);
          }
        });

    return view;
  }

  @Override public void onDestroy() {
    weatherSubscription.unsubscribe();
  }

  static class ViewHolder {
    @BindView(R.id.weather_text)
    TextView weatherText;

    public ViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }
}
