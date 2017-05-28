package vv.aotu.business.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import vv.aotu.R;
import vv.aotu.base.BaseFragment;
import vv.aotu.business.home.adapter.HtmlAdapter;
import vv.aotu.business.home.loading.LoadingLayout;
import vv.aotu.business.home.module.HtmlModule;
import vv.aotu.util.DBManager;

/**
 * Created by luocheng on 17/5/28.
 */

public class CollectionFragment extends BaseFragment {

  private RecyclerView mRecyclerView;
  private HtmlAdapter mAdapter;
  private LoadingLayout mLoadingLayout;

  @Override
  protected int getLayoutResId() {
    return R.layout.fragment_collection;
  }

  @Override
  protected void onInflated(View container, Bundle savedInstanceState) {
    mRecyclerView = (RecyclerView) container.findViewById(R.id.ry_collection);
    mLoadingLayout = (LoadingLayout) container.findViewById(R.id.loading);
    mAdapter = new HtmlAdapter(R.layout.item_html_view);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mRecyclerView.setAdapter(mAdapter);
    loadData();
  }

  // 从数据库中加载数据过来
  private void loadData(){
    mLoadingLayout.setStatus(LoadingLayout.Loading);
    DBManager dbManager = new DBManager(getActivity());
    List<HtmlModule> list = dbManager.getHtmlModuleList();
    if (null == list || list.isEmpty() ) {
      mLoadingLayout.setStatus(LoadingLayout.Error);
    } else {
      mLoadingLayout.setStatus(LoadingLayout.Success);
      mAdapter.addData(list);
    }
  }
}
