package com.accuracy.test.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TextView;

import com.accuracy.test.R;
import com.accuracy.test.fragment.DetailFragment;
import com.accuracy.test.fragment.FragmentTabHost;
import com.accuracy.test.fragment.MainFragment;
import com.accuracy.test.fragment.PersonalFragment;
import com.accuracy.test.fragment.RouteFragment;
import com.accuracy.test.fragment.TabDB;
import com.accuracy.test.until.ActivityCollector;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.MapView;

public class MainActivity extends FragmentActivity {

    private Context mContext;
    private FragmentTabHost mFragmentTabHost;
    private MainFragment mMainFragment;
    private DetailFragment mDetailFragment;
    private RouteFragment mRouteFragment;
    private PersonalFragment mPersonalFragment;
    private MapView mMapView=null;

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
        String tabs[] = TabDB.getTabText();
        for (int i = 0; i < tabs.length; i++) {
            tabSpec = mFragmentTabHost.newTabSpec(tabs[i]).setIndicator(getTabView(i));
            mFragmentTabHost.addTab(tabSpec,TabDB.getFragments()[i],null);
            mFragmentTabHost.setTag(i);
        }
        mMainFragment = (MainFragment)getSupportFragmentManager().findFragmentByTag("首页");
        mDetailFragment = (DetailFragment)getSupportFragmentManager().findFragmentByTag("详情");
        mRouteFragment = (RouteFragment)getSupportFragmentManager().findFragmentByTag("路线");
        mPersonalFragment = (PersonalFragment)getSupportFragmentManager().findFragmentByTag("个人");
    }

    private View getTabView(int index) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tabitem_havetext, null);
        TextView textView = (TextView) view.findViewById(R.id.itemTextView);
        textView.setText(TabDB.getTabText()[index]);
        Drawable drawable = getResources().getDrawable(TabDB.getTabImg()[index]);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
        return view;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment mainFragment = getSupportFragmentManager().findFragmentByTag("首页");
        if (mainFragment != null) {
            mainFragment.onActivityResult(requestCode,resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
