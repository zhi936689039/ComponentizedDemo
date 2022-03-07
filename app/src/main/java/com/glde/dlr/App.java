package com.glde.dlr;

import android.app.Application;

import com.glde.base.library_base.BaseApplication;
import com.glde.base.library_base.route.Aroute;

/**
 * 作者：oyzb
 * 时间：2022/2/24 18:59
 * 邮箱：ouyangzb@yonyou.com
 * 说明：
 */
public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Aroute.getInstance().init(this);
    }
}
