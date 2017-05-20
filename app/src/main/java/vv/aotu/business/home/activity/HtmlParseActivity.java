package vv.aotu.business.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.jsoup.nodes.Document;

import java.util.List;

import vv.aotu.R;
import vv.aotu.business.home.adapter.HtmlAdapter;
import vv.aotu.business.home.loading.LoadingLayout;
import vv.aotu.business.home.module.HtmlModule;
import vv.aotu.business.home.util.HtmlDataProcessUtil;


public class HtmlParseActivity extends AppCompatActivity {



  private HtmlAsyncTask mTask;
  private RecyclerView mRv;
  private HtmlAdapter mAdapter;
  private LoadingLayout mLoadingLayout;
  private String mUrl;
  private int mPage = 2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_html_parse);
    mLoadingLayout = (LoadingLayout) findViewById(R.id.loading);
    mUrl = getIntent().getStringExtra("url");
    initView();
    getHtmlData(mUrl);


  }

  private void getHtmlData(String url) {
    mLoadingLayout.setStatus(LoadingLayout.Loading);
    HtmlAsyncTask task = new HtmlAsyncTask();
    task.setDataCallback(new HtmlAsyncTask.Callback() {
      @Override
      public void onDataCallback(Document document) {
        if (document == null) {
          mLoadingLayout.setStatus(LoadingLayout.Error);
        } else {
          mLoadingLayout.setStatus(LoadingLayout.Success);
          List<HtmlModule> list = HtmlDataProcessUtil.parseListData(document);
          mAdapter.addData(list);
          if (list.size() < 18) mAdapter.setEnableLoadMore(false);
        }
      }
    });
    task.execute(url);

  }

  private String getNextUrl(int page) {
    if (mUrl == null) return mUrl;
    String[] split = mUrl.split("/");// ["http:","","www.xxx.com","3pqj"]
    String host = split[0] + "//" + split[2] + "/";
    String path = split[3] + "/recent/" + page;
    return host + path;

  }

  private void initView() {

    mRv = (RecyclerView) findViewById(R.id.html_rv);
    mRv.setLayoutManager(new LinearLayoutManager(this));
    mAdapter = new HtmlAdapter(R.layout.item_html_view);
    mRv.setAdapter(mAdapter);
    mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);

    // mAdapter.setEmptyView(R.layout.item_html_view,mRv);
    mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
      @Override
      public void onLoadMoreRequested() {

        loadMoreTask();

      }
    }, mRv);



  }

  private void loadMoreTask() {

    if (mAdapter.getItemCount() > 18) {

      String nextUrl = getNextUrl(mPage);

      HtmlAsyncTask task2 = new HtmlAsyncTask();
      task2.setDataCallback(new HtmlAsyncTask.Callback() {
        @Override
        public void onDataCallback(Document document) {

          List<HtmlModule> list = HtmlDataProcessUtil.parseListData(document);
          mAdapter.addData(list);
          if (list.size() == 0) {
            mAdapter.loadMoreFail();
          } else if (list.size() < 18) {
            mAdapter.loadMoreEnd();
          } else {
            mAdapter.loadMoreComplete();
            mPage++;
          }

        }
      });
      task2.execute(nextUrl);
    } else {
      mAdapter.loadMoreEnd();
    }
  }

  public static void launch(Context context, String url) {
    Intent intent = new Intent(context, HtmlParseActivity.class);
    intent.putExtra("url", url);
    if (!(context instanceof Activity)) {
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
    context.startActivity(intent);
  }

}
