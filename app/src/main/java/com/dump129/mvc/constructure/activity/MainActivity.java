package com.dump129.mvc.constructure.activity;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dump129.mvc.constructure.R;
import com.dump129.mvc.constructure.fragment.MainFragment;
import com.dump129.mvc.constructure.fragment.SecondFragment;
import com.dump129.mvc.constructure.util.ScreenUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int screenWidth = ScreenUtils.getInstance().getSrceenWidth();
        int screenHeight = ScreenUtils.getInstance().getScreenHeight();

        Toast.makeText(MainActivity.this, "Width: " + screenWidth, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "Height: " + screenHeight, Toast.LENGTH_SHORT).show();

        // First Created Fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.newInstance(123), "MainFragment")
                    .commit();

            SecondFragment secondFragment = SecondFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, secondFragment, "SecondFragment")
                    .detach(secondFragment)
                    .commit();
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (savedInstanceState == null) {
            // First method
            // mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.contentContainer);

            // Second method
            MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentByTag("MainFragment");
            fragment.setHelloText("Woo hoo");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_first_tab: {
                MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag("MainFragment");
                SecondFragment secondFragment = (SecondFragment) getSupportFragmentManager().findFragmentByTag("SecondFragment");

                getSupportFragmentManager().beginTransaction()
                        .attach(mainFragment).detach(secondFragment).commit();
                return true;
            }
            case R.id.action_second_tab: {
                MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag("MainFragment");
                SecondFragment secondFragment = (SecondFragment) getSupportFragmentManager().findFragmentByTag("SecondFragment");

                getSupportFragmentManager().beginTransaction()
                        .attach(secondFragment).detach(mainFragment).commit();

                return true;
            }
            case R.id.action_second_fragment: {
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.from_right, R.anim.to_left, R.anim.from_left, R.anim.to_right)
                        .replace(R.id.contentContainer, SecondFragment.newInstance())
                        .addToBackStack(null)
                        .commit();

                Toast.makeText(MainActivity.this, "Second Fragment", Toast.LENGTH_SHORT).show();

                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
