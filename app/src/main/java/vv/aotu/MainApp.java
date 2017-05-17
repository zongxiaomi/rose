package vv.aotu;

import android.app.Application;

import com.weavey.loading.lib.LoadingLayout;

import vv.aotu.base.util.GlobalConfig;


/**
 * Created by mi on 2017/1/22.
 */

public class MainApp extends Application {

  // private RefWatcher mRefWatcher;
  //
  // public static RefWatcher getRefWatcher(Context context) {
  // if (context != null) {
  // MainApp mainApp = (MainApp) context.getApplicationContext();
  // return mainApp.mRefWatcher;
  // }
  // return null;
  // }


  @Override
  public void onCreate() {
    super.onCreate();
    // mRefWatcher = LeakCanary.install(this);
    // Global.init(this);
    GlobalConfig.setAppContext(this);
    initLoading();
  }

  private void initLoading() {
    LoadingLayout.getConfig()
        .setErrorText("出错啦~请稍后重试！")
        .setEmptyText("抱歉，暂无数据")
        .setNoNetworkText("无网络连接，请检查您的网络···")
        // .setErrorImage(R.mipmap.dog)
        // .setEmptyImage(R.drawable.leak_canary_icon)
        // .setNoNetworkImage(R.drawable.dog)
        // .setAllTipTextColor(R.color.crazy_lottery_bg_color)
        .setAllTipTextSize(14)
        .setReloadButtonText("点我重试哦")
        .setReloadButtonTextSize(14)
        .setReloadButtonTextColor(R.color.c2)
        .setReloadButtonWidthAndHeight(150, 40)
        .setAllPageBackgroundColor(R.color.c4);
  }

  protected MainApp getContext() {
    return this;
  }

}
