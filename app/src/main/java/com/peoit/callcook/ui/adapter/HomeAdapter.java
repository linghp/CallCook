package com.peoit.callcook.ui.adapter;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.peoit.callcook.R;
import com.peoit.callcook.base.AdapterItem;
import com.peoit.callcook.beans.HomeBean;
import com.peoit.callcook.ui.base.CommonRcvAdapter;
import com.peoit.callcook.ui.view.TagViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ling on 2015/9/15.
 * description:
 */
public class HomeAdapter extends CommonRcvAdapter<HomeBean>{
    public HomeAdapter(List<HomeBean> data) {
        super(data);
    }

    @NonNull
    @Override
    public AdapterItem<HomeBean> getItemView(Object type) {
        return initItem(type);
    }

    @Override
    public Object getItemViewType(HomeBean item) {
        return item.type;
    }

    private AdapterItem<HomeBean> initItem(Object type) {
        switch ((String) type) {
            case HomeBean.type1:
                return new headerItem();
            case HomeBean.type2:
                return new TextItem();
            default:
                Log.e(TAG, "No default item");
                return new TextItem();
        }
    }


    public class TextItem implements AdapterItem<HomeBean>{

        @Override
        public int getLayoutResId() {
            return R.layout.demo_item_text;
        }

        TextView textView;

        @Override
        public void findViews(View root) {
            textView = (TextView) root.findViewById(R.id.textView);
        }

        @Override
        public void setViews(HomeBean model, int position) {
            textView.setText(model.content);
        }

    }

    public class headerItem implements AdapterItem<HomeBean>,View.OnClickListener{

        @Override
        public int getLayoutResId() {
            return R.layout.home_rcv_header;
        }

        private TagViewPager tagViewPager;
        @Override
        public void findViews(View root) {
            tagViewPager = (TagViewPager)root.findViewById(R.id.tagViewPager);
        }

        @Override
        public void setViews(HomeBean model, int position) {
            //轮播
            List<Integer> imgLists = new ArrayList<>();
            imgLists.add(R.mipmap.ic_launcher);
            imgLists.add(R.mipmap.ic_launcher);
            imgLists.add(R.mipmap.ic_launcher);
            imgLists.add(R.mipmap.ic_launcher);
            tagViewPager.toUse(imgLists, this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
