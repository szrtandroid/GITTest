package com.example.photoswalldemo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

/**
 * 照片墙主活动，使用GridView展示照片墙。
 *
 * @author guolin
 */
public class MainActivity extends AppCompatActivity {

    /**
     * 用于展示照片墙的GridView
     */
    private GridView mPhotoWall;

    /**
     * GridView的适配器
     */
    private PhotoWallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTransParentState();

        mPhotoWall = (GridView) findViewById(R.id.photo_wall);
        adapter = new PhotoWallAdapter(this, 0, Images.imageThumbUrls, mPhotoWall);
        mPhotoWall.setAdapter(adapter);
    }

    private void setTransParentState() {

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
//			getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }


//	@Override
//	public void onWindowFocusChanged(boolean hasFocus) {
//		super.onWindowFocusChanged(hasFocus);
//		if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//			View decorView = getWindow().getDecorView();
//			decorView.setSystemUiVisibility(
//					View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//							| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//							| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//							| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//							| View.SYSTEM_UI_FLAG_FULLSCREEN
//							| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//		}
//
//	}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出程序时结束所有的下载任务
        adapter.cancelAllTasks();
    }

}
