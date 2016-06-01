package jp.co.xorphitus.wheathersample.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bluelinelabs.logansquare.LoganSquare;
import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import jp.co.xorphitus.wheathersample.R;
import jp.co.xorphitus.wheathersample.models.Weather;
import jp.co.xorphitus.wheathersample.network.LivedoorWeatherService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * メインコンテンツ表示用
 */
public class MainFragment extends Fragment {
  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://weather.livedoor.com/")
        .addConverterFactory(LoganSquareConverterFactory.create())
        .build();

    LivedoorWeatherService service = retrofit.create(LivedoorWeatherService.class);
    Call<Weather> weather = service.find();

    final View view = inflater.inflate(R.layout.fragment_main, container, false);

    weather.enqueue(new Callback<Weather>() {
      @Override public void onResponse(Call<Weather> call, Response<Weather> response) {
        final String title = response.body().title;
        getActivity().runOnUiThread(new Runnable() {
          @Override public void run() {
            TextView textView = (TextView) view.findViewById(R.id.weather_text);
            textView.setText(title);
          }
        });
      }

      @Override public void onFailure(Call<Weather> call, Throwable t) {
        t.printStackTrace();
      }
    });

    return view;
  }
}
