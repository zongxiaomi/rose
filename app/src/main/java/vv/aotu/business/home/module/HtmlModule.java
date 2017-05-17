package vv.aotu.business.home.module;

/**
 * Created by mi on 2017/5/7.
 */

public class HtmlModule {

  String img;
  String title;
  String time;
  String href;
  String browserCount;
  String uploadTime;
  String videoSrc;

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
