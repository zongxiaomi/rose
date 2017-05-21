package vv.aotu.business.home.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.TextView;

import vv.aotu.R;
import vv.aotu.base.BaseFragment;
import vv.aotu.base.BaseNavigationActivity;
import vv.aotu.business.home.fragment.EmptyFragment;
import vv.aotu.business.home.fragment.HomeFragment;

public class HomeActivity extends BaseNavigationActivity {

  private TextView mTextMessage;

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
      new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
          switch (item.getItemId()) {
            case R.id.navigation_category:
              mFragment = (BaseFragment) Fragment.instantiate(HomeActivity.this,
                  HtmlGridFragment.class.getName(), null);
              replaceFragment(mFragment);
              return true;
            case R.id.navigation_search:
              mFragment = (BaseFragment) Fragment.instantiate(HomeActivity.this,
                  EmptyFragment.class.getName(), null);
              replaceFragment(mFragment);
              return true;
            case R.id.navigation_fav:
              mFragment = (BaseFragment) Fragment.instantiate(HomeActivity.this,
                  EmptyFragment.class.getName(), null);
              replaceFragment(mFragment);
              return true;
            case R.id.navigation_home:
              mFragment = (BaseFragment) Fragment.instantiate(HomeActivity.this,
                  HomeFragment.class.getName(), null);
              replaceFragment(mFragment);
              return true;

          }
          return false;
        }

      };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    navigation.setSelectedItemId(R.id.navigation_category);
  }

}
