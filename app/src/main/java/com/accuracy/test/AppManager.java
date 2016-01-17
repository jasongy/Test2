package com.accuracy.test;

import android.app.Activity;
import android.content.Context;
import android.util.AndroidException;
import android.util.Size;

import java.util.Stack;

/**
 * activity堆栈式管理
 * Created by qq on 2016/1/11.
 */
public class AppManager {
    private static Stack<Activity> activityStack;
    private static AppManager instance;
    private AppManager(){}
    /**
     * 单一实例
     */
    public  static AppManager getAppManager(){
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity){
        if (activityStack ==null){
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public Activity currentActivity(){
        Activity activity = activityStack.lastElement();
        return  activity;
    }

    public void finishActivity(){
        Activity activity  =activityStack.lastElement();
        finishActivity(activity);
    }

    public void finishActivity(Activity activity){
        if(activity !=null &&! activity.isFinishing()){
        activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public void finishActivity(Class<?>cls){
        for (Activity activity: activityStack){
            if (activity.getClass().equals(cls)){
                finishActivity();
                break;
            }
        }
    }

    public  void  finishAllActivity(){
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                //finishActivity方法中的activity.isFinishing()方法会导致某些activity无法销毁
                //貌似跳转的时候最后一个activity 是finishing状态，所以没有执行
                //内部实现不是很清楚，但是实测结果如此，使用下面代码则没有问题
                // find by TopJohn
                //finishActivity(activityStack.get(i));
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public static Activity getActivity(Class<?>cls){
        if(activityStack !=null)
            for (Activity activity: activityStack){
                if(activity.getClass().equals(cls)){
                    return activity;
            }
        }
        return null;
    }

    public void AppExit(Context context){
        try{
            finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }catch (Exception e) {
        }
    }
}
