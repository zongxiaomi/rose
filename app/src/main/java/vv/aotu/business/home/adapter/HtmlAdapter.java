package vv.aotu.business.home.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import vv.aotu.R;
import vv.aotu.business.home.activity.HtmlDetailActivity;
import vv.aotu.business.home.module.HtmlModule;



/**
 * Created by mi on 2017/5/7.
 */

public class HtmlAdapter extends BaseQuickAdapter<HtmlModule, BaseViewHolder> {


  public HtmlAdapter(int layoutResId, List<HtmlModule> data) {
    super(layoutResId, data);
  }

  public HtmlAdapter(List<HtmlModule> data) {
    super(data);
  }

  public HtmlAdapter(int layoutResId) {
    super(layoutResId);
  }

  @Override
  protected void convert(BaseViewHolder helper, final HtmlModule item) {
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
