package vv.aotu.business.home.fragment;

import android.os.Bundle;
import android.view.View;

import vv.aotu.R;
import vv.aotu.base.BaseFragment;

/**
 * @author mi
 * @time 2017/5/20
 */

public class EmptyFragment extends BaseFragment {
  @Override
  protected int getLayoutResId() {
    return R.layout.empty_layout;
  }

  @Override
  protected void onInflated(View container, Bundle savedInstanceState) {

  }
}
