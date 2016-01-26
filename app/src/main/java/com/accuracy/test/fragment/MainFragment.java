package com.accuracy.test.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.service.carrier.CarrierService;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.accuracy.test.R;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MyLocationOverlay;
import com.tianditu.android.maps.Overlay;

import java.util.List;


public class MainFragment extends Fragment implements View.OnClickListener{
    private Activity mActivity;
    private View mMap;
    private View mView;
    private MapView mMapView = null;
    protected MapController mController = null;
    private MyLocationOverlay myLocationOverlay;
    protected Context mCon   = null;
    private Overlay mOverlay;


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
        mMapView= (MapView) mView.findViewById(R.id.mapsView);
        mMapView.displayZoomControls(true);
        mController = mMapView.getController();


        myLocationOverlay = new MyLocationOverlay(getActivity(),mMapView);
        myLocationOverlay.enableCompass();
        myLocationOverlay.enableMyLocation();
        mMapView.getOverlays().add(myLocationOverlay);
        myLocationOverlay.getAccuracy();

        Resources res = getResources();
        Drawable marker = res.getDrawable(R.drawable.icon_focus_mark);
        ImageView location = (ImageView) mView.findViewById(R.id.iv_location);
    }
    //
    public float getAccuracy(){
        return  myLocationOverlay.getAccuracy();
    }

    class MyOverlay extends MyLocationOverlay {
        public MyOverlay(Context context, MapView mapView) {
            super(context, mapView);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onLocationChanged(Location location) {
            super.onLocationChanged(location);
            if (location != null) {
                String strLog = String.format("您当前位置: \r\n" + "纬度: %f\r\n"
                        + "经度:%f", location.getLongitude(), location.getLatitude());
                Toast.makeText(mCon, strLog, Toast.LENGTH_LONG).show();
            }
            GeoPoint point = myLocationOverlay.getMyLocation();
            if (point != null)
                mMapView.getController().animateTo(point);
        }
    }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_location:
                    GeoPoint point = myLocationOverlay.getMyLocation();
                    if (point !=null) {
                        mMapView.getController().animateTo(point);
                    }
                    break;
            }
        }
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


}