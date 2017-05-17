package vv.aotu.business.home.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.weavey.loading.lib.LoadingLayout;

import org.jsoup.nodes.Document;

import java.util.List;

import vv.aotu.Constants;
import vv.aotu.R;
import vv.aotu.business.home.adapter.HtmlGridAdapter;
import vv.aotu.business.home.module.HtmlModule;
import vv.aotu.business.home.util.HtmlDataProcessUtil;


public class HtmlGridActivity extends AppCompatActivity {

  private RecyclerView mRecyclerView;
  private HtmlGridAdapter mAdapter;
  private LoadingLayout mLoadingLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_html_grid);
    initRecycleView();
    getCategories();
  }

  private void getCategories() {
    mLoadingLayout.setStatus(LoadingLayout.Loading);
    HtmlAsyncTask task2 = new HtmlAsyncTask();
    task2.setDataCallback(new HtmlAsyncTask.Callback() {
      @Override
      public void onDataCallback(Document document) {
        mLoadingLayout.setStatus(LoadingLayout.Success);
        List<HtmlModule> htmlModules = HtmlDataProcessUtil.parseGridList(document);

        mAdapter.setNewData(htmlModules);

      }
    });
    task2.execute(Constants.CATEGORIES);

  }

  private void initRecycleView() {
    mLoadingLayout = (LoadingLayout) findViewById(R.id.loading);
    mRecyclerView = (RecyclerView) findViewById(R.id.html_grid);
    mAdapter = new HtmlGridAdapter(R.layout.item_html_grid);
    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    mRecyclerView
        .setLayoutManager(new GridLayoutManager(this, 2));
    mRecyclerView.setAdapter(mAdapter);


  }


}
