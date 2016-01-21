package com.accuracy.test.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.accuracy.test.R;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.MapView;

public class MainFragment extends Fragment {
    private Activity mActivity;
    private View mToolBar;
    private View mView;
    private MapView mMapView = null;
    protected MapController mController = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = getActivity();
        mView = inflater.inflate(R.layout.fragment_main, container, false);
        mMapView = (MapView) mActivity.findViewById(R.id.mapsView);
        MapController mMapController = mMapView.getController();
//用给定的经纬度构造一个GeoPoint，单位是微度 (度 * 1E6)
        GeoPoint point = new GeoPoint((int) (39.915 * 1E6), (int) (116.404 * 1E6));
//设置地图中心点
        mMapController.setCenter(point);
//设置地图zoom级别
        mMapController.setZoom(12);
        return mView;

    }


   
//
    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
    }
//
//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (hidden) {
//            hideToolBar();
//        } else {
//            showToolBar();
//        }
//    }
//
//    private void showToolBar() {
//        mToolBar.setVisibility(View.VISIBLE);
//    }
//
//    private void hideToolBar() {
//        mToolBar.setVisibility(View.GONE);
//    }
}
