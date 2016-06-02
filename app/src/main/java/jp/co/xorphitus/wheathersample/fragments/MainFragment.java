package jp.co.xorphitus.wheathersample.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import jp.co.xorphitus.wheathersample.R;
import jp.co.xorphitus.wheathersample.models.Weather;
import jp.co.xorphitus.wheathersample.models.weather.Forecast;
import jp.co.xorphitus.wheathersample.network.LivedoorWeatherService;
import jp.co.xorphitus.wheathersample.network.LivedoorWeatherServiceFactory;
import org.w3c.dom.Text;
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

    final Activity activity = getActivity();

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

            ForecastAdapter adapter = new ForecastAdapter(activity, android.R.layout.simple_list_item_1, weather.forecasts);
            viewHolder.forecasts.setAdapter(adapter);
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

    @BindView(R.id.forecasts)
    ListView forecasts;

    public ViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }

  static class ForecastAdapter extends ArrayAdapter<Forecast> {
    public ForecastAdapter(Context context, int resource, List<Forecast> objects) {
      super(context, resource, objects);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
      ForecastViewHolder holder;
      if (convertView != null) {
        holder = (ForecastViewHolder) convertView.getTag();
      } else {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_forecast, parent, false);
        holder = new ForecastViewHolder(convertView);
        convertView.setTag(holder);
      }

      Forecast forecast = getItem(position);

      holder.telop.setText(forecast.telop);
      holder.dateLabel.setText(forecast.dateLabel + "の天気 (" + forecast.date + ")");

      Glide.with(getContext())
          .load(forecast.image.url)
          .override(forecast.image.width, forecast.image.height)
          .centerCrop()
          .crossFade()
          .into(holder.image);

      return convertView;
    }

    static class ForecastViewHolder {
      @BindView(R.id.telop) TextView telop;
      @BindView(R.id.dateLabel) TextView dateLabel;
      @BindView(R.id.image) ImageView image;

      public ForecastViewHolder(View view) {
        ButterKnife.bind(this, view);
      }
    }
  }
}
