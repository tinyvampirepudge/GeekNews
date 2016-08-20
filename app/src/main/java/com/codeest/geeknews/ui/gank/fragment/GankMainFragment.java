package com.codeest.geeknews.ui.gank.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.codeest.geeknews.R;
import com.codeest.geeknews.base.SimpleFragment;
import com.codeest.geeknews.presenter.TechPresenter;
import com.codeest.geeknews.ui.gank.adapter.GankMainAdapter;
import com.codeest.geeknews.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by codeest on 16/8/19.
 */

public class GankMainFragment extends SimpleFragment{

    @BindView(R.id.tab_gank_main)
    TabLayout mTabLayout;
    @BindView(R.id.vp_gank_main)
    ViewPager mViewPager;

    String[] tabTitle = new String[]{"Android","iOS","Web","福利"};
    List<Fragment> fragments = new ArrayList<>();

    GankMainAdapter mAdapter;
    TechFragment androidFragment;
    TechFragment iOSFragment;
    TechFragment webFragment;
    GirlFragment girlFragment;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank_main;
    }

    @Override
    protected void initEventAndData() {
        androidFragment = new TechFragment();
        iOSFragment = new TechFragment();
        webFragment = new TechFragment();
        girlFragment = new GirlFragment();
        Bundle androidBundle = new Bundle();
        androidBundle.putString("tech", TechPresenter.TECH_ANDROID);
        androidFragment.setArguments(androidBundle);
        Bundle iosBundle = new Bundle();
        iosBundle.putString("tech", TechPresenter.TECH_IOS);
        iOSFragment.setArguments(iosBundle);
        Bundle webBundle = new Bundle();
        webBundle.putString("tech", TechPresenter.TECH_WEB);
        webFragment.setArguments(webBundle);

        fragments.add(androidFragment);
        fragments.add(iOSFragment);
        fragments.add(webFragment);
        fragments.add(girlFragment);
        mAdapter = new GankMainAdapter(getChildFragmentManager(),fragments);
        mViewPager.setAdapter(mAdapter);

        //TabLayout配合ViewPager有时会出现不显示Tab文字的Bug,需要按如下顺序
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[3]));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(tabTitle[0]);
        mTabLayout.getTabAt(1).setText(tabTitle[1]);
        mTabLayout.getTabAt(2).setText(tabTitle[2]);
        mTabLayout.getTabAt(3).setText(tabTitle[3]);
    }
}
