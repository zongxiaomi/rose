package vv.aotu.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

public abstract class BaseFragment extends Fragment {

  protected View mContainer;
  protected boolean mIsInflated;

  protected abstract int getLayoutResId();

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mContainer = inflater.inflate(getLayoutResId(), container, false);
    return mContainer;
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (mContainer != null) {
      onInflated(mContainer, savedInstanceState);
      mIsInflated = true;
    }
  }

  protected abstract void onInflated(View container, Bundle savedInstanceState);

  @Override
  public void onDetach() {
    super.onDetach();
    try { // 解决fragment嵌套子fragment状态问题
      Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
      childFragmentManager.setAccessible(true);
      childFragmentManager.set(this, null);
    } catch (Exception e) {
      // do nothing
    }
  }

  public boolean handleBack() {
    return false;
  }
}
