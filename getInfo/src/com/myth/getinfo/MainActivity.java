package com.myth.getinfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.example.getinfo.R;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new LoadHtmlTask(MainActivity.this).execute("http://www.zhihu.com/explore");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is
        // present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
