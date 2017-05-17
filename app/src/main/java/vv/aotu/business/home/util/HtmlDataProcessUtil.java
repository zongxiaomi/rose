package vv.aotu.business.home.util;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import vv.aotu.business.home.module.HtmlModule;


/**
 * Created by mi on 2017/5/14.
 */

public class HtmlDataProcessUtil {



  public static List<HtmlModule> parseGridList(Document document) {
    if (document == null) return null;
    List<HtmlModule> list = new ArrayList<>();
    Elements select = document.select("li[id]").select("a[href]");

    for (int i = 0; i < select.size(); i++) {
      HtmlModule module = new HtmlModule();
      Element element = select.get(i);
      String href = element.attr("abs:href");
      String img = element.select("img[src]").attr("abs:src");
      String title = element.select("div.category-title").text();
      String count = element.select("div.category-overlay").text();
      module.setBrowserCount(count);
      module.setTitle(title);
      module.setImg(img);
      module.setHref(href);
      list.add(module);

    }
    return list;

  }

  public static List<HtmlModule> parseListData(Document document) {
    List<HtmlModule> list = new ArrayList<>();

    Elements select = document.select("li[id]").select("a[href]");

    for (int i = 0; i < select.size(); i++) {
      HtmlModule module = new HtmlModule();
      Element element = select.get(i);
      String time = element.select("span.video-duration").text();
      String href = element.attr("abs:href");
      Elements img = element.select("img[src]");
      String browserCount = element.select("span.video-views").text();
      String uploadTime = element.select("span.video-added").text();
      String title = img.attr("alt");
      String imgUrl = img.attr("src");
      module.setBrowserCount(browserCount);
      module.setTitle(title);
      module.setImg(imgUrl);
      module.setHref(href);
      module.setTime(time);
      module.setUploadTime(uploadTime);
      list.add(module);

    }
    return list;

  }

  public static HtmlModule parseDetail(Document document) {

    if (document == null) return null;

    final String title = document.title();
    final String thumb = document.getElementsByAttribute("poster").attr("poster");
    Elements selects = document.select("video[id]").select("source[res]");
    final String src = selects.attr("src");
    HtmlModule module = new HtmlModule();
    module.setTitle(title);
    module.setImg(thumb);
    module.setVideoSrc(src);
    return module;

  }

}
