package vv.aotu.business.home.module;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @author mi
 * @time 2017/5/21
 */

public class HomeSectionEntry extends SectionEntity<HtmlModule> {


  public HomeSectionEntry(boolean isHeader, String header) {
    super(isHeader, header);
  }

  public HomeSectionEntry(HtmlModule htmlModule) {
    super(htmlModule);
  }
}
