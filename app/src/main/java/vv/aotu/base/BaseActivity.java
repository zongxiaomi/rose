package vv.aotu.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import vv.aotu.R;

/**
 * @author mi
 * @time 2017/5/20
 */

public class BaseActivity extends AppCompatActivity {
  protected BaseFragment mFragment;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutId());



  }

  protected void replaceFragment(Fragment fragment) {
    replaceFragment(fragment, null, false);
  }

  protected void replaceFragment(Fragment f, Bundle arguments, boolean isAddToStack) {

    if (isFinishing() || f == null) {
      return;
    }
    if (arguments != null) {
      f.setArguments(arguments);
    }
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.content, f);
    if (isAddToStack) {
      transaction.addToBackStack(null);
    }
    transaction.commitAllowingStateLoss();

  }

  protected int getLayoutId() {
    return R.layout.base_container;
  }
}
