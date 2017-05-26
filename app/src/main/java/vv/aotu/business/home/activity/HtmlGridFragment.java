package vv.aotu.business.home.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.jsoup.nodes.Document;

import java.util.List;

import vv.aotu.Constants;
import vv.aotu.R;
import vv.aotu.base.BaseFragment;
import vv.aotu.business.home.adapter.HtmlGridAdapter;
import vv.aotu.business.home.loading.LoadingLayout;
import vv.aotu.business.home.module.HtmlModule;
import vv.aotu.business.home.util.HtmlDataProcessUtil;



public class HtmlGridFragment extends BaseFragment {

  private RecyclerView mRecyclerView;
  private HtmlGridAdapter mAdapter;
  private LoadingLayout mLoadingLayout;

  private void getCategories() {
    mLoadingLayout.setStatus(LoadingLayout.Loading);
    HtmlAsyncTask task2 = new HtmlAsyncTask();
    task2.setDataCallback(new HtmlAsyncTask.Callback() {
      @Override
      public void onDataCallback(Document document) {
        if (document == null) {
          mLoadingLayout.setStatus(LoadingLayout.Error);
        } else {
          mLoadingLayout.setStatus(LoadingLayout.Success);
          List<HtmlModule> htmlModules = HtmlDataProcessUtil.parseGridList(document);
          mAdapter.setNewData(htmlModules);
        }
      }
    });
    task2.execute(Constants.CATEGORIES);

  }

  private void initRecycleView() {
    mLoadingLayout = (LoadingLayout) findViewById(R.id.loading);
    mLoadingLayout.setOnReloadListener(new LoadingLayout.OnReloadListener() {
      @Override
      public void onReload(View v) {
        getCategories();
      }
    });
    mRecyclerView = (RecyclerView) findViewById(R.id.html_grid);
    mAdapter = new HtmlGridAdapter(R.layout.item_html_grid);
    mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    mRecyclerView.setAdapter(mAdapter);


  }


  @Override
  protected int getLayoutResId() {
    return R.layout.activity_html_grid;
  }

  @Override
  protected void onInflated(View container, Bundle savedInstanceState) {
    initRecycleView();
    getCategories();
  }

  public View findViewById(int id) {
    return mContainer.findViewById(id);
  }
}
