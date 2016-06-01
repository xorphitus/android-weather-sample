package jp.co.xorphitus.wheathersample.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.io.IOException;
import jp.co.xorphitus.wheathersample.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * メインコンテンツ表示用
 */
public class MainFragment extends Fragment {
  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    OkHttpClient client = new OkHttpClient();
    Request req = new Request.Builder().url(
        "http://weather.livedoor.com/forecast/webservice/json/v1?city=130010").build();

    View view = inflater.inflate(R.layout.fragment_main, container, false);
    final TextView textView = (TextView) view.findViewById(R.id.weather_text);
    client.newCall(req).enqueue(new Callback() {
      @Override public void onFailure(Call call, IOException e) {
        e.printStackTrace();
      }

      @Override public void onResponse(Call call, Response response) throws IOException {
        final String text = response.body().string();

        getActivity().runOnUiThread(new Runnable() {
          @Override public void run() {
            textView.setText(text);
          }
        });
      }
    });

    return view;
  }
}
