package com.dump129.mvc.constructure.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dump129.mvc.constructure.R;
import com.dump129.mvc.constructure.util.ScreenUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int screenWidth = ScreenUtils.getInstance().getSrceenWidth();
        int screenHeight = ScreenUtils.getInstance().getScreenHeight();

        Toast.makeText(MainActivity.this, "Width: "+screenWidth, Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "Height: "+screenHeight, Toast.LENGTH_SHORT).show();
    }
}
