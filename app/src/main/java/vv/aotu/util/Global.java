package vv.aotu.util;

import android.content.Context;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 16/12/08
 *     desc  : Utils初始化相关
 * </pre>
 */
public class Global {

  private static Context context;

  private Global() {
    throw new UnsupportedOperationException("u can't instantiate me...");
  }

  /**
   * 初始化工具类
   *
   * @param context 上下文
   */
  public static void init(Context context) {
    Global.context =context;
  }

  /**
   * 获取ApplicationContext
   *
   * @return ApplicationContext
   */
  public static Context getApplicationContext() {
    if (context != null) return context;
    throw new NullPointerException("u should init first");
  }
}
