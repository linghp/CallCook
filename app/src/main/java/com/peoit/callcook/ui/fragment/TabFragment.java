package com.peoit.callcook.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peoit.callcook.R;
import com.peoit.callcook.presenters.impl.HomePresenter;
import com.peoit.callcook.ui.base.BaseFragment;

public class TabFragment extends BaseFragment implements HomePresenter.OnHttpResultListener,View.OnClickListener {
    //    private String mTitle = "Default";
//
//    public static final String TITLE = "title";
    private RecyclerView recyclerView;
    private HomePresenter homePresenter;
    //private TagViewPager tagViewPager;
    private View view;
    private View recyclerViewHeader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if (getArguments() != null) {
//            mTitle = getArguments().getString(TITLE);
//        }
        view=inflater.inflate(R.layout.common_recyclerview_fab,null);
        recyclerViewHeader=inflater.inflate(R.layout.common_recyclerview_fab,null);
        return super.onCreateView(inflater,container, savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        addToBaseView(view);
//        tagViewPager = (TagViewPager)recyclerViewHeader.findViewById(R.id.tagViewPager);
//        //轮播
//        List<Integer> imgLists = new ArrayList<>();
//        imgLists.add(R.mipmap.ic_launcher);
//        imgLists.add(R.mipmap.ic_launcher);
//        imgLists.add(R.mipmap.ic_launcher);
//        imgLists.add(R.mipmap.ic_launcher);
//        tagViewPager.toUse(imgLists, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        // 如果布局大小一致有利于优化
        recyclerView.setHasFixedSize(true);
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void initData() {
        homePresenter=new HomePresenter(this,recyclerView,recyclerViewHeader);
        homePresenter.getData();
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void onHttpResultSuccess() {

    }

    @Override
    public void onClick(View v) {

    }
}
