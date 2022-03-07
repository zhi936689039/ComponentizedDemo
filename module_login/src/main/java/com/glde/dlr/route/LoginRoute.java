package com.glde.dlr.route;

import com.glde.base.library_base.route.Aroute;
import com.glde.base.library_base.route.IRoute;
import com.glde.dlr.module_login.LoginActivity;

/**
 * 作者：oyzb
 * 时间：2022/3/3 1:11
 * 邮箱：ouyangzb@yonyou.com
 * 说明：
 */
public class LoginRoute implements IRoute {
    @Override
    public void onPut() {
        Aroute.getInstance().putActivity(IRoute.act_login, LoginActivity.class);
    }
}
