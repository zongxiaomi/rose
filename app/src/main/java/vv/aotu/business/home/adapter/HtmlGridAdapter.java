package vv.aotu.business.home.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import vv.aotu.R;
import vv.aotu.business.home.activity.HtmlParseActivity;
import vv.aotu.business.home.module.HtmlModule;




public class HtmlGridAdapter extends BaseQuickAdapter<HtmlModule, GridViewHolder> {
  public HtmlGridAdapter(int layoutResId, List<HtmlModule> data) {
    super(layoutResId, data);
  }

  public HtmlGridAdapter(List<HtmlModule> data) {
    super(data);
  }

  public HtmlGridAdapter(int layoutResId) {
    super(layoutResId);
  }

  @Override
  protected void convert(GridViewHolder helper, final HtmlModule item) {

    helper.setText(R.id.video_count, item.getBrowserCount());
    helper.setText(R.id.category, item.getTitle());

    Glide.with(mContext).load(item.getImg()).placeholder(R.drawable.icon_launcher).crossFade()
        .centerCrop().into((ImageView) helper.getView(R.id.category_img));

    helper.getConvertView().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String href = item.getHref();
        HtmlParseActivity.launch(v.getContext(), href);
      }
    });
  }
}
