package com.peoit.callcook.ui.base;

import android.support.v4.app.FragmentManager;
import android.view.View;

import com.peoit.callcook.utils.MyLogger;


/**
 * Created by ling on 2015/8/13.
 * last:2015/8/13
 * description:
 */
public abstract class BaseFragmentActivity extends BaseActivity{
    protected FragmentManager fragmentManager;

    @Override
    protected void initView() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                } else {
                    finish();
                }
            }
        });
    }

    @Override
    protected void initData() {
        fragmentManager = getSupportFragmentManager();
    }

//    protected void addFragmentToContainer(Fragment fragment,String tag) {
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.fragment_container, fragment, tag);
//        fragmentTransaction.commit();
//    }
//    protected void addFragmentToStack(Fragment fragment,String tag) {
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.fragment_container, fragment, tag);
//        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            MyLogger.i("fragmentManager.getBackStackEntryCount(): " + fragmentManager.getBackStackEntryCount());
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}
