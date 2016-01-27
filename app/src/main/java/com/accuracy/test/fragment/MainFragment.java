package com.accuracy.test.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.service.carrier.CarrierService;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Handler;

import com.accuracy.test.R;
import com.tianditu.android.maps.GeoPoint;
import com.tianditu.android.maps.MapController;
import com.tianditu.android.maps.MapView;
import com.tianditu.android.maps.MyLocationOverlay;
import com.tianditu.android.maps.Overlay;


public class MainFragment extends Fragment implements View.OnClickListener {
    private Activity mActivity;
    private View mMap;
    private View mView;
    private MapView mMapView = null;
    protected MapController mController = null;
    private MyLocationOverlay myLocationOverlay;
    protected Context mCon = null;
    private Overlay mOverlay;
    private int mCount = 0;
    Handler hander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Location ll = new Location(LocationManager.GPS_PROVIDER);
            if (mCount % 2 == 1) {
                ll.setLongitude(ll.getLongitude());
                ll.setLatitude(ll.getLatitude());
            } else {
                ll.setLongitude(ll.getLongitude());
                ll.setLatitude(ll.getLatitude());
            }
            mCount++;
            myLocationOverlay.onLocationChanged(ll);
        }

    };


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
        mMapView = (MapView) mView.findViewById(R.id.mapsView);
        mMapView.displayZoomControls(true);
        mController = mMapView.getController();


        myLocationOverlay = new MyLocationOverlay(getActivity(), mMapView);
        myLocationOverlay.enableCompass();
        myLocationOverlay.enableMyLocation();
        mMapView.getOverlays().add(myLocationOverlay);


        Resources res = getResources();
        Drawable marker = res.getDrawable(R.drawable.icon_focus_mark);
        ImageView location = (ImageView) mView.findViewById(R.id.iv_location);

        LocationManager mLocationManager = (LocationManager) mActivity.getSystemService(Context.LOCATION_SERVICE);
        if (mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000, 0, myLocationOverlay);
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