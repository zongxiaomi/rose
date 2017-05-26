package vv.aotu.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by luocheng on 17/5/24.
 */

public class DBHelper extends SQLiteOpenHelper {

  public static final String DB_NAME = "collect";
  public static final String TABLE_NAME = "collect_list";
  private static final int DB_VERSION = 1;

  public DBHelper(Context context) {
    this(context, DB_NAME, null, DB_VERSION);
  }

  // SQLiteOpenHelper子类必须要的一个构造函数
  public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
    // 必须通过super 调用父类的构造函数
    super(context, name, factory, version);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE IF NOT EXISTS collect_list (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "img VARCHAR,title VARCHAR,time VARCHAR, href VARCHAR, browserCount VARCHAR,uploadTime VARCHAR,videoSrc VARCHAR)");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }

  public void insert(ContentValues values) {
    // 获取SQLiteDatabase实例
    SQLiteDatabase db = getWritableDatabase();
    db.insert(TABLE_NAME, null, values);
  }

  public Cursor query() {
    SQLiteDatabase db = getReadableDatabase();
    return db.query(TABLE_NAME, null, null, null, null, null, null, null);
  }

  public void delete(int id) {
    // 获取SQLiteDatabase实例
    SQLiteDatabase db = getWritableDatabase();
    db.delete(TABLE_NAME, "_id=?", new String[] {String.valueOf(id)});
  }

}
