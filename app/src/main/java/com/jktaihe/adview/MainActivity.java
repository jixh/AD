package com.jktaihe.adview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.jktaihe.expanddata.ExpandActivity;
import com.jktaihe.maxdata.MaxActivity;
import com.jktaihe.recyclerviewpager.RecycelerActivity;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_expand).setOnClickListener(this);
        findViewById(R.id.tv_max).setOnClickListener(this);
        findViewById(R.id.tv_recyceler).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_expand:
                startActivity(new Intent(this, ExpandActivity.class));
                break;
            case R.id.tv_max:
                startActivity(new Intent(this, MaxActivity.class));
                break;
            case R.id.tv_recyceler:
                startActivity(new Intent(this, RecycelerActivity.class));
                break;
        }
    }
}
