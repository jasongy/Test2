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
    private View mMap;
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
        mView = inflater.inflate(R.layout.fragment_main, container, false);

        return mView;
    }

    private void initView() {
        mMapView= (MapView) mActivity.findViewById(R.id.mapsView);
        mMapView.displayZoomControls(true);
    }
    //
    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}