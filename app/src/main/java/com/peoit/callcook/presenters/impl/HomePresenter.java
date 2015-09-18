package com.peoit.callcook.presenters.impl;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.peoit.callcook.R;
import com.peoit.callcook.base.BasePresenter;
import com.peoit.callcook.base.IBaseView_Response;
import com.peoit.callcook.beans.HomeBean;
import com.peoit.callcook.net.OkHttpClientManager;
import com.peoit.callcook.presenters.interfaces.IHome;
import com.peoit.callcook.ui.adapter.HomeAdapter;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ling on 2015/9/7.
 * description:
 */
public class HomePresenter extends BasePresenter<HomePresenter.OnHttpResultListener> implements IHome {
    private List<HomeBean> lists=new ArrayList<>();
    private RecyclerView recyclerView;
    //private View headerView;
    private HomeAdapter homeAdapter;
    public interface OnHttpResultListener extends IBaseView_Response {
        void onHttpResultSuccess();
    }

    public HomePresenter(OnHttpResultListener view,RecyclerView recyclerView,View headerView) {
        super(view);
        this.recyclerView=recyclerView;
        //this.headerView=headerView;
        initAdapter();
    }

    private void initAdapter() {
        homeAdapter=new HomeAdapter(lists);
        recyclerView.setAdapter(homeAdapter);
    }

    public abstract class MyResultCallback<T> extends OkHttpClientManager.ResultCallback<T> {

        @Override
        public void onBefore(Request request) {
            super.onBefore(request);
            mView.showProgress(false, mView.getStringbyid(R.string.networkrequest));
        }

        @Override
        public void onAfter() {
            super.onAfter();
            mView.hideProgress();
        }
    }

    @Override
    public void getData() {
//        Map<String, String> maps = new HashMap<>();
//        maps.put("key", "山");
//        OkHttpClientManager.postAsyn("http://www.ainonggu666.com/api/product", maps,
//                new MyResultCallback<Object>() {
//                    @Override
//                    public void onError(Request request, Exception e) {
//                        mView.showToast(R.string.networkerror);
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onResponse(Object u) {
//                        //mTv.setText(u.toString());
//                        mView.onHttpResultSuccess();
//                        generateData();
//                        homeAdapter.updateData(lists);
//                    }
//                }, mView);
        generateData();
        homeAdapter.updateData(lists.size()-10,10);
    }

    private void generateData() {
        lists.add(new HomeBean(HomeBean.type1));
        for (int i = 0; i < 10; i++) {
            lists.add(new HomeBean(HomeBean.type2));
        }
    }
}
