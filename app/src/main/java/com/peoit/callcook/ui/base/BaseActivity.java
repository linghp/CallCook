package com.peoit.callcook.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.peoit.callcook.R;
import com.peoit.callcook.base.IBaseView_Response;
import com.peoit.callcook.ui.view.LoadingLayout;
import com.peoit.callcook.utils.LocalUserInfo;

/**
 * Created by ling on 2015/9/14.
 * description:
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView_Response{
    private ProgressDialog pd;
    protected LoadingLayout layout_body ;
    protected Toolbar toolbar;
    protected View layout_current;
    protected LocalUserInfo localUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BaseActivity", getClass().getSimpleName());
        localUserInfo = LocalUserInfo.getInstance(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.layout_base);
        initContentView(layoutResID);
        initView();
        initData();
        updateView();
    }

    protected void initContentView(int layoutResID) {
        toolbar = toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        layout_body = (LoadingLayout) super.findViewById(R.id.layout_body);

        layout_current = getLayoutInflater().inflate(layoutResID, null);

        layout_body.addView(layout_current);
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void updateView();

    protected void myToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    protected  <T extends View> T findViewByID_My(int id){
        return (T) super.findViewById(id);
    }


    @Override
    public void showProgress(boolean flag, String message) {
        if (pd == null) {
            pd = new ProgressDialog(this);
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
        showToast(getString(resId));
    }

    @Override
    public void showToast(String msg) {
        if (!isFinishing()) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public String getStringbyid(int resId) {
       return getString(resId);
    }

}
