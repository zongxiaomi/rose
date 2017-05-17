package vv.aotu.business.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.jsoup.nodes.Document;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import vv.aotu.R;
import vv.aotu.base.util.ViewUtils;
import vv.aotu.business.home.adapter.HtmlAdapter;
import vv.aotu.business.home.module.HtmlModule;
import vv.aotu.business.home.util.HtmlDataProcessUtil;


public class HtmlDetailActivity extends AppCompatActivity {

  private String mUrl;
  private JCVideoPlayerStandard mVideoView;
  private RecyclerView mDetailRv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_html_detail);
    initParamData();
    initView();

    getHtmlDetailData();



  }

  private void getHtmlDetailData() {


    HtmlAsyncTask task2 = new HtmlAsyncTask();
    task2.setDataCallback(new HtmlAsyncTask.Callback() {
      @Override
      public void onDataCallback(Document document) {


        bindToVideo(document);
        bindToRecycleView(document);
      }
    });

    task2.execute(mUrl);

  }

  private void bindToRecycleView(Document document) {

    List<HtmlModule> list = HtmlDataProcessUtil.parseListData(document);

    mDetailRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    HtmlAdapter adapter = new HtmlAdapter(R.layout.item_html_view);
    adapter.addHeaderView(ViewUtils.newInstance(this, R.layout.html_header_view));
    adapter.setNewData(list);

    mDetailRv.setAdapter(adapter);



  }

  private void bindToVideo(Document document) {
    HtmlModule module = HtmlDataProcessUtil.parseDetail(document);
    mVideoView.setUp(module.getVideoSrc(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,
        module.getTitle());

  }

  private void initParamData() {
    mUrl = getIntent().getStringExtra("url");
  }

  private void initView() {
    mVideoView = (JCVideoPlayerStandard) findViewById(R.id.videoView);
    mDetailRv = (RecyclerView) findViewById(R.id.detail_recycle_view);


  }

  @Override
  public void onBackPressed() {
    if (JCVideoPlayer.backPress()) {
      return;
    }
    super.onBackPressed();
  }

  @Override
  protected void onPause() {
    super.onPause();
    JCVideoPlayer.releaseAllVideos();
  }

  public static void launch(Context context, String url) {
    Intent intent = new Intent(context, HtmlDetailActivity.class);
    intent.putExtra("url", url);
    if (!(context instanceof Activity)) {
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
    context.startActivity(intent);
  }
}
