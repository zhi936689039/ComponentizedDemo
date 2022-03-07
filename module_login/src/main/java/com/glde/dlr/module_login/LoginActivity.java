package com.glde.dlr.module_login;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.glde.base.library_base.route.Aroute;
import com.glde.base.library_base.route.IRoute;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Aroute.getInstance().startActivity(IRoute.act_main,null,LoginActivity.this);
                Intent intent=new Intent();
                intent.setAction("com.glde.dlr.module_main.MainActivity");
                if (LoginActivity.this.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                    intent.addCategory("android.intent.category.DEFAULT");
                    startActivity(intent);
                }
            }
        });
    }
}