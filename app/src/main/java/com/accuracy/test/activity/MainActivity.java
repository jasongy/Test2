package com.accuracy.test.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.TabHost;

import com.accuracy.test.R;
import com.accuracy.test.fragment.FragmentTabHost;
import com.accuracy.test.until.ActivityCollector;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.MapView;

public class MainActivity extends FragmentActivity {

    private Context mContext;
    private FragmentTabHost mFragmentTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mContext =this;
        ActivityCollector.addActivity(this);
        initTab();
        
    }

    private void initTab() {
        mFragmentTabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        mFragmentTabHost.setup(mContext,getSupportFragmentManager(),R.id.contentLayout);
        mFragmentTabHost.getTabWidget().setDividerDrawable(null);
        TabHost.TabSpec tabSpec;
        String tabs[] = TabDB.get
    }

}
