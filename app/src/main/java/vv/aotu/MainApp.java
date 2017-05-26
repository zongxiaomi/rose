package vv.aotu;

import android.app.Application;

import vv.aotu.util.GlobalConfig;
import vv.aotu.business.home.loading.LoadingLayout;


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
        .setErrorImage(R.drawable.empty_ic_timeout)
        .setEmptyImage(R.drawable.marketing_no_data)
        .setNoNetworkImage(R.drawable.empty_ic_timeout)
        .setAllTipTextColor(R.color.c3)
        .setAllTipTextSize(14)
        .setReloadButtonText("点击重试")
        .setReloadButtonTextSize(14)
        .setReloadButtonTextColor(R.color.c2)
        .setReloadButtonWidthAndHeight(120, 40)
        .setAllPageBackgroundColor(R.color.c1);
  }

  protected MainApp getContext() {
    return this;
  }

}
