package com.glde.base.library_base.route;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dalvik.system.DexFile;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * 作者：oyzb
 * 时间：2022/3/1 21:56
 * 邮箱：ouyangzb@yonyou.com
 * 说明：
 */
public class Aroute {
    private Context mContext;
    private static Map<String,Class<? extends Activity>> map;
    private static Aroute instance;
    public static Aroute getInstance(){
        if(instance==null){
            synchronized (Aroute.class){
                if(instance==null){
                    instance=new Aroute();
                }
                if(map==null){
                    map=new HashMap<>();
                }
            }
        }
        return instance;
    }
    public void init(Context context){
        mContext=context;
        //获取baseApk（android 打包后的apk）下对应包名为com.glde.dlr.route的所有类集合
        List<String> classNameList=getActivityFromPackage("com.glde.dlr.route");
        for(String className:classNameList){
            try {
                //将类装载进内存
                Class<?> cls= Class.forName(className);
                //判断转载进内存的类是否实现了IRoute接口
                if(IRoute.class.isAssignableFrom(cls)){
                    IRoute iRoute= (IRoute) cls.newInstance();//实例化对象向上转型为IRoute
                    iRoute.onPut();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
    public List<String>  getActivityFromPackage(String packgeName){
        List<String> classList=new ArrayList<>();
        try {
            //mContext.getPackageName()  获取  /data/app/对应包名下的base.apk的文件路径
            String path=mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(),0).sourceDir;
            DexFile dexFile=new DexFile(path);//将baskapk 解压成dex,并且扫描apk中的dex有哪些类
            Enumeration<String> enumeration=dexFile.entries();//获取dexFail中所有类名
            //遍历dex所有类名
            while (enumeration.hasMoreElements()){
                //获取类名
                String className=enumeration.nextElement();
                if(className.startsWith(packgeName)){
                    classList.add(className);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classList;
    }
    public void putActivity(String activityName,Class cls){
        if(!TextUtils.isEmpty(activityName)&&null!=cls){
            map.put(activityName,cls);
        }
    }
    public void startActivity(String activityName, Bundle bundle,Context context){
        Class<? extends Activity> activity=map.get(activityName);
        if(null==activity){
            return;
        }
        Intent intent=new Intent(context,activity);
        if(null!=bundle){
            intent.putExtras(bundle);
        }
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
