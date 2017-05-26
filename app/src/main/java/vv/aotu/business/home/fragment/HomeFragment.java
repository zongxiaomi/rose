package vv.aotu.business.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.jsoup.nodes.Document;

import java.util.List;

import vv.aotu.Constants;
import vv.aotu.R;
import vv.aotu.base.BaseFragment;
import vv.aotu.business.home.activity.HtmlAsyncTask;
import vv.aotu.business.home.adapter.HomeSectionAdapter;
import vv.aotu.business.home.loading.LoadingLayout;
import vv.aotu.business.home.module.HomeSectionEntry;
import vv.aotu.business.home.util.HtmlDataProcessUtil;

/**
 * @author mi
 * @time 2017/5/21
 */

public class HomeFragment extends BaseFragment {

  private RecyclerView mRecyclerView;
  private HomeSectionAdapter mAdapter;
  private LoadingLayout mLoadingLayout;

  private void getHomeData() {
    mLoadingLayout.setStatus(LoadingLayout.Loading);
    HtmlAsyncTask task = new HtmlAsyncTask();
    task.setDataCallback(new HtmlAsyncTask.Callback() {
      @Override
      public void onDataCallback(Document document) {
        if (document == null) {
          mLoadingLayout.setStatus(LoadingLayout.Error);
        } else {
          mLoadingLayout.setStatus(LoadingLayout.Success);
          List<HomeSectionEntry> sectionEntryList = HtmlDataProcessUtil.parseHomeList(document);
          mAdapter.setNewData(sectionEntryList);
        }
      }
    });
    task.execute(Constants.HOME);

  }

  private void initRecycleView() {
    mLoadingLayout = (LoadingLayout) findViewById(R.id.loading);
    mLoadingLayout.setOnReloadListener(new LoadingLayout.OnReloadListener() {
      @Override
      public void onReload(View v) {
        getHomeData();
      }
    });
    mRecyclerView = (RecyclerView) findViewById(R.id.html_home);
    mAdapter = new HomeSectionAdapter(R.layout.item_html_view, R.layout.section_litem, null);

    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mRecyclerView.setAdapter(mAdapter);


  }

  @Override
  protected int getLayoutResId() {
    return R.layout.activity_html_home;
  }

  public View findViewById(int id) {
    return mContainer.findViewById(id);
  }

  @Override
  protected void onInflated(View container, Bundle savedInstanceState) {
    initRecycleView();
    getHomeData();
  }
}
