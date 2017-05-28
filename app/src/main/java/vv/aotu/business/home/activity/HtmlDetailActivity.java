package vv.aotu.business.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import org.jsoup.nodes.Document;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import vv.aotu.R;
import vv.aotu.business.home.adapter.HtmlAdapter;
import vv.aotu.business.home.module.HtmlModule;
import vv.aotu.business.home.util.HtmlDataProcessUtil;
import vv.aotu.util.DBManager;
import vv.aotu.util.MainThreadPostUtils;
import vv.aotu.util.ViewUtils;


public class HtmlDetailActivity extends AppCompatActivity {

  private String mUrl;
  private JCVideoPlayerStandard mVideoView;
  private RecyclerView mDetailRv;
  private CheckBox mCollection;
  private DBManager mDBManager;
  private HtmlModule mHtmlModule;
  private int mId;
  private boolean mIsCollection;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_html_detail);
    mDBManager = new DBManager(this);
    initParamData();
    initCollection();
    initView();
    initListener();
    getHtmlDetailData();
  }

  // 初始化该内容是否是已经收藏过的内容
  public void initCollection() {
    List<Integer> idList = mDBManager.getIdFromSQLit();
    int size = idList.size();
    if (size <= 0) {
      mIsCollection =  false;
    }
    for (int i = 0; i < size; i++) {
      if (idList.get(i) == mId){
        mIsCollection =  true;
      }
    }
  }

  // 收藏按钮的监听事件
  private void initListener() {
    mCollection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
          mDBManager.addData(mHtmlModule);// add 到数据库中
        }else {
          mDBManager.deleteData(mId);// 从数据库中删除
        }
      }
    });
  }

  private void getHtmlDetailData() {

    HtmlAsyncTask task2 = new HtmlAsyncTask();
    task2.setDataCallback(new HtmlAsyncTask.Callback() {
      @Override
      public void onDataCallback(Document document) {

        if (document == null) {
          MainThreadPostUtils.toast("获取数据失败，请返回重试");
          return;
        }
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
    mVideoView.startVideo();

  }

  private void initParamData() {
    mHtmlModule = (HtmlModule) getIntent().getSerializableExtra("module");
    if (null == mHtmlModule) {
      return ;
    }
    mUrl = mHtmlModule.getHref();
    mId = mHtmlModule.getId();
  }

  private void initView() {
    mVideoView = (JCVideoPlayerStandard) findViewById(R.id.videoView);
    mDetailRv = (RecyclerView) findViewById(R.id.detail_recycle_view);
    mCollection = (CheckBox) findViewById(R.id.cb_collection);
    mCollection.setChecked(mIsCollection);
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

  public static void launch(Context context, HtmlModule module) {
    Intent intent = new Intent(context, HtmlDetailActivity.class);
    intent.putExtra("module", module);
    if (!(context instanceof Activity)) {
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
    context.startActivity(intent);
  }
}
