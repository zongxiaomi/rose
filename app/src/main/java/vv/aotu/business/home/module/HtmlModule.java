package vv.aotu.business.home.module;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by mi on 2017/5/7.
 */

public class HtmlModule implements Serializable {

  String img;
  String title;
  String time;
  String href;
  String browserCount;
  String uploadTime;
  String videoSrc;
  String idInfo;
  int id;
  boolean isCollection;// 是否是收藏过的

  public void setCollection(boolean isCollection){
    this.isCollection = isCollection;
  }

  public boolean isCollection(){
    return isCollection;
  }

  public void setIdInfo(String idInfo) {
    this.idInfo = idInfo;
  }

  public void setId(int id){
    this.id = id;
  }

  // 解析得到处理每个model里面的唯一id
  public int getId() {
    if (TextUtils.isEmpty(idInfo)) {
      return id;
    }
    String string;
    try {
      string = idInfo.substring(1, 5);
      id = Integer.valueOf(string);
    } catch (Exception ignored) {}
    return id;
  }

  public void setVideoSrc(String videoSrc) {
    this.videoSrc = videoSrc;
  }

  public String getVideoSrc() {
    return videoSrc;
  }

  public void setUploadTime(String uploadTime) {
    this.uploadTime = uploadTime;
  }

  public String getUploadTime() {
    return uploadTime;
  }

  public String getBrowserCount() {
    return browserCount;
  }

  public void setBrowserCount(String browserCount) {
    this.browserCount = browserCount;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getTitle() {
    return title;
  }

  public String getTime() {
    return time;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setTime(String time) {
    this.time = time;
  }

}
