package com.accuracy.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.accuracy.test.activity.MainActivity;

/**
 * Created by qq on 2016/1/11.
 */
public class AppStart extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //防止第三方跳转出现双实例
        Activity aty = AppManager.getActivity(MainActivity.class);
        if (aty !=null&&! aty.isFinishing()){
            finish();
        }
        final View view = View.inflate(this, R.layout.app_start,null);
        setContentView(view);

        //渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.5f,1.0f);
        aa.setDuration(800);
        view.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                redirectTo();
            }

            

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        int cacheVersion = PreferenceHelper.readInt(this,"first_install",
//                "first_install",-1);
////        int currentVersion = TDevice.getVersionCode();
////        if(cacheVersion <currentVersion) {
////            PreferenceHelper.write(this,"first_install","first_install"
////            ,currentVersion);
//            cleanImageCache();
//        }
//
//
//
//    private void cleanImageCache() {
//    final File folder  = FileUtils.getSaveFolder("gis/imagecahce");
//        KJAsyncTask.execute(new Runnable(){
//            @Override
//            public void run() {
//                for (File file:folder.listFiles()){
//                    file.delete();
//                }
//            }
//        });
//    }


    /*
    * 跳转中
    * */
    private void redirectTo() {
//        Intent uploadLog = new Intent(this,LogUpLoadService.class);
//        startService(uploadLog);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
