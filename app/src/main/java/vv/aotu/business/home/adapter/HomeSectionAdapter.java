package vv.aotu.business.home.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import vv.aotu.R;
import vv.aotu.business.home.activity.HtmlDetailActivity;
import vv.aotu.business.home.module.HomeSectionEntry;
import vv.aotu.business.home.module.HtmlModule;

/**
 * @author mi
 * @time 2017/5/21
 */

public class HomeSectionAdapter extends BaseSectionQuickAdapter<HomeSectionEntry, BaseViewHolder> {

  /**
   * Same as QuickAdapter#QuickAdapter(Context,int) but with
   * some initialization data.
   *
   * @param layoutResId The layout resource id of each item.
   * @param sectionHeadResId The section head layout id for each item
   * @param data A new list is created out of this one to avoid mutable list
   */
  public HomeSectionAdapter(int layoutResId, int sectionHeadResId, List<HomeSectionEntry> data) {
    super(layoutResId, sectionHeadResId, data);
  }

  @Override
  protected void convertHead(BaseViewHolder helper, HomeSectionEntry item) {
    helper.setText(R.id.home_section_header, item.header);

  }

  @Override
  protected void convert(BaseViewHolder helper, final HomeSectionEntry entry) {
    final HtmlModule item = entry.t;
    if (item == null) return;
    helper.setText(R.id.tv_news_detail_title, item.getTitle());
    helper.setText(R.id.browser_count, "浏览：" + item.getBrowserCount());
    helper.setText(R.id.tv_news_detail_date, "时长" + item.getTime());
    helper.setText(R.id.upload_time, item.getUploadTime());
    Glide.with(mContext).load(item.getImg()).placeholder(R.mipmap.icon_launcher).crossFade()
        .centerCrop().into((ImageView) helper.getView(R.id.iv_news_detail_pic));

    helper.getConvertView().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String href = item.getHref();
        HtmlDetailActivity.launch(v.getContext(), href);
      }
    });

  }
}
