package jp.co.xorphitus.wheathersample;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import jp.co.xorphitus.wheathersample.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
      FragmentManager fragmentManager = getFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

      fragmentTransaction.add(R.id.container, new MainFragment());

      fragmentTransaction.commit();
    }
  }
}
