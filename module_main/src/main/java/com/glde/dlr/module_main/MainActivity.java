package com.glde.dlr.module_main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.glde.base.library_base.route.Aroute;
import com.glde.base.library_base.route.IRoute;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aroute.getInstance().startActivity(IRoute.act_login,null,MainActivity.this);
            }
        });
    }
}