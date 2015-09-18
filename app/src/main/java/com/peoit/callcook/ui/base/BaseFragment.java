package com.peoit.callcook.ui.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.peoit.callcook.R;
import com.peoit.callcook.base.IBaseView_Response;
import com.peoit.callcook.ui.view.LoadingLayout;
import com.peoit.callcook.utils.LocalUserInfo;

/**
 * Created by ling on 2015/7/31.
 * last:2015/7/31
 * description:
 */
public abstract class BaseFragment extends Fragment implements IBaseView_Response {
    protected View mParent;
    protected Activity mActivity;
    // protected TitleView titleView;
    protected LoadingLayout layout_body;
    protected LocalUserInfo localUserInfo;
    private ProgressDialog pd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_base, container, false);
        layout_body = (LoadingLayout) view.findViewById(R.id.layout_body);


        return view;
    }

    public void addToBaseView(View view) {
        layout_body.addView(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("BaseFragment", getClass().getSimpleName());
        mParent = getView();
        mActivity = getActivity();
        // titleView= (TitleView) getView().findViewById(R.id.title_view);
        localUserInfo = LocalUserInfo.getInstance(getActivity());
        initView(mParent);
        initData();
        updateView();
    }

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract void updateView();

    protected void myToast(String content) {
        Toast.makeText(this.getActivity(), content, Toast.LENGTH_SHORT).show();
    }

    private boolean getStatus() {
        return (isAdded() && !isRemoving());
    }

    @Override
    public void showProgress(boolean flag, String message) {
        if (pd == null) {
            pd = new ProgressDialog(getActivity());
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setCancelable(flag);
            pd.setCanceledOnTouchOutside(false);
            pd.setMessage(message);
            pd.show();
        } else {
            pd.show();
        }
    }

    @Override
    public void hideProgress() {
        if (pd == null)
            return;

        if (pd.isShowing()) {
            pd.dismiss();
        }
    }

    @Override
    public void showToast(int resId) {
        if (getStatus()) {
            showToast(getString(resId));
        }
    }

    @Override
    public void showToast(String msg) {
        if (getStatus()) {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public String getStringbyid(int resId) {
        return getString(resId);
    }
}
