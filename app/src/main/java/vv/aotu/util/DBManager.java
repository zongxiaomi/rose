package vv.aotu.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import vv.aotu.business.home.module.HtmlModule;

/**
 * Created by luocheng on 17/5/24.
 */

public class DBManager {

  private DBHelper mDBHelper;

  public DBManager(Context context) {
    mDBHelper = new DBHelper(context);
  }

  public void addData(HtmlModule module) {
    if (null == module) {
      return;
    }
    ContentValues cv = new ContentValues();
    cv.put("_id", module.getId());
    cv.put("img", module.getImg());
    cv.put("title", module.getTitle());
    cv.put("time", module.getTime());
    cv.put("href", module.getHref());
    cv.put("browserCount", module.getBrowserCount());
    cv.put("uploadTime", module.getUploadTime());
    cv.put("videoSrc", module.getVideoSrc());
    mDBHelper.insert(cv);
    mDBHelper.close();
  }

  public void deleteData(int id){
    mDBHelper.delete(id);
  }

  // 得到数据库中所有的_id所对应的值
  public List<Integer> getIdFromSQLit(){
    List<Integer> idList = new ArrayList<>();
    Cursor cursor  = mDBHelper.query();
    while (cursor.moveToNext()) {
      int id = cursor.getInt(cursor.getColumnIndex("_id"));
      idList.add(id);
    }
    mDBHelper.close();
    cursor.close();
    return idList;
  }

  // 得到数据库中的所有数据
  public List<HtmlModule> getHtmlModuleList() {
    List<HtmlModule> moduleList = new ArrayList<>();
    Cursor cursor = mDBHelper.query();
    while (cursor.moveToNext()) {
      int id = cursor.getInt(cursor.getColumnIndex("_id"));
      String img = cursor.getString(cursor.getColumnIndex("img"));
      String title = cursor.getString(cursor.getColumnIndex("title"));
      String time = cursor.getString(cursor.getColumnIndex("time"));
      String href = cursor.getString(cursor.getColumnIndex("href"));
      String browserCount = cursor.getString(cursor.getColumnIndex("browserCount"));
      String uploadTime = cursor.getString(cursor.getColumnIndex("uploadTime"));
      String videoSrc = cursor.getString(cursor.getColumnIndex("videoSrc"));
      HtmlModule module = new HtmlModule();
      module.setId(id);
      module.setImg(img);
      module.setTitle(title);
      module.setTime(time);
      module.setHref(href);
      module.setBrowserCount(browserCount);
      module.setUploadTime(uploadTime);
      module.setVideoSrc(videoSrc);
      moduleList.add(module);
    }
    mDBHelper.close();
    cursor.close();
    return moduleList;
  }

}
