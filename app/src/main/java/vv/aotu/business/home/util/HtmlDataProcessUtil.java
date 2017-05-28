package vv.aotu.business.home.util;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import vv.aotu.business.home.module.HomeSectionEntry;
import vv.aotu.business.home.module.HtmlModule;


/**
 * Created by mi on 2017/5/14.
 */

public class HtmlDataProcessUtil {

    /**
     * 解析分类数据
     *
     * @param document
     * @return
     */
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

    /**
     * 解析列表数据
     *
     * @param document
     * @return
     */

    public static List<HtmlModule> parseListData(Document document) {
        if (null == document) {
            return null;
        }
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
            String idInfo = element.attr("href");
            module.setBrowserCount(browserCount);
            module.setTitle(title);
            module.setImg(imgUrl);
            module.setHref(href);
            module.setTime(time);
            module.setUploadTime(uploadTime);
            module.setIdInfo(idInfo);
            list.add(module);

        }
        return list;

    }

    /**
     * 解析详情页数据
     *
     * @param document
     * @return
     */

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

    /**
     * 解析首页数据
     *
     * @param document
     * @return
     */

    public static List<HomeSectionEntry> parseHomeList(Document document) {
        if (document == null) return null;
        Elements elements = document.select("div.col-xs").select("div.panel");
        int size = elements.size();
        List<HomeSectionEntry> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Element element = elements.get(i);

            String section = element.select("h2").text();
            if (section == "") section = element.select("h1").text();

            HomeSectionEntry head = new HomeSectionEntry(true, section);
            Elements models = element.select("ul.videos").select("a");
            list.add(head);
            List<HomeSectionEntry> part = new ArrayList<>();

            for (Element model : models) {

                String time = model.select("span.video-duration").text();
                String href = model.attr("abs:href");
                Elements img = model.select("img[src]");
                String browserCount = model.select("span.video-views").text();
                String uploadTime = model.select("span.video-added").text();
                String title = img.attr("alt");
                String imgUrl = img.attr("src");
                HtmlModule html = new HtmlModule();
                html.setBrowserCount(browserCount);
                html.setHref(href);
                html.setImg(imgUrl);
                html.setTime(time);
                html.setUploadTime(uploadTime);
                html.setTitle(title);
                HomeSectionEntry entry = new HomeSectionEntry(html);
                part.add(entry);

            }
            list.addAll(part);

        }
        return list;

    }

}
