package com.lin.FragmentPagerAdapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;


import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private Fragment tab01;
    private Fragment tab02;
    private Fragment tab03;
    private Fragment tab04;

    private FragmentPagerAdapter mAdapter;  //适配器
    private List<Fragment> list_fragment;   //复制容器

    private LinearLayout mTabweixin;
    private LinearLayout mTabFriend;
    private LinearLayout mTabAddress;
    private LinearLayout mTabSetting;

    private ImageButton mImgWeixin;
    private ImageButton mImgFriend;
    private ImageButton mImgAddress;
    private ImageButton mImgSetting;



    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
        setSelect(0);
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initEvent() {
        mTabweixin.setOnClickListener(this);
        mTabFriend.setOnClickListener(this);
        mTabAddress.setOnClickListener(this);
        mTabSetting.setOnClickListener(this);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mTabweixin= (LinearLayout) findViewById(R.id.tab_weixin);
        mTabFriend= (LinearLayout) findViewById(R.id.tab_friend);
        mTabAddress= (LinearLayout) findViewById(R.id.tab_address);
        mTabSetting= (LinearLayout) findViewById(R.id.tab_setting);

        mImgWeixin= (ImageButton) findViewById(R.id.tab_weixin_img);
        mImgFriend= (ImageButton) findViewById(R.id.tab_friend_img);
        mImgAddress= (ImageButton) findViewById(R.id.tab_address_img);
        mImgSetting= (ImageButton) findViewById(R.id.tab_setting_img);

        //加载资源
        list_fragment=new ArrayList<Fragment>();
        Fragment tab01=new Weixin_Fragment();
        Fragment tab02=new Friend_Fragment();
        Fragment tab03=new Address_Fragment();
        Fragment tab04=new Setting_Fragment();
        list_fragment.add(tab01);
        list_fragment.add(tab02);
        list_fragment.add(tab03);
        list_fragment.add(tab04);

        mAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {  //适配器直接new出来
            @Override
            public Fragment getItem(int position) {
                return list_fragment.get(position);//直接返回
            }

            @Override
            public int getCount() {
                return list_fragment.size(); //放回tab数量
            }
        };


        mViewPager.setAdapter(mAdapter);  //加载适配器
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {  //监听界面拖动
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                int currentItem=mViewPager.getCurrentItem(); //获取当前界面
                resetImg();  //将所有图标变暗
                tab(currentItem); //切换图标亮度
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    } //initView

    private void tab(int i){  //用于屏幕脱拖动时切换底下图标，只在监听屏幕拖动中调用
        switch (i){
            case 0:{
                mImgWeixin.setImageResource(R.drawable.weixin_lang);
                break;
            }
            case 1:
            {
                mImgFriend.setImageResource(R.drawable.shezhi_liang);
                break;
            }
            case 2:
            {
                mImgAddress.setImageResource(R.drawable.zhibo_liang);
                break;
            }
            case 3:
            {
                mImgSetting.setImageResource(R.drawable.shezhi_liang);
                break;
            }
        }
        }//tab()


    //自定义一个方法
    private void setSelect(int i){

        mViewPager.setCurrentItem(i);//切换界面

    }  //setSelect()


/*
    //隐藏事务方法
    private void hideFragment(FragmentTransaction transaction) {  //对不为空的Fragment隐藏
        if(tab01!=null){
            transaction.hide(tab01);
        }
        if(tab02!=null){
            transaction.hide(tab02);
        }
        if(tab03!=null){
            transaction.hide(tab03);
        }
        if(tab04!=null){
            transaction.hide(tab04);
        }

    }*/

    @Override
    public void onClick(View view) {  //设置点击的为；亮色
        resetImg();
        switch (view.getId()){
            case R.id.tab_weixin:{
                setSelect(0);
                mImgWeixin.setImageResource(R.drawable.weixin_lang);
                break;
            }
            case R.id.tab_friend:
            {
                setSelect(1);
                mImgFriend.setImageResource(R.drawable.shezhi_liang);
                break;
            }
            case R.id.tab_address:
            {
                setSelect(2);
                mImgAddress.setImageResource(R.drawable.zhibo_liang);
                break;
            }
            case R.id.tab_setting:
            {
                setSelect(3);
                mImgSetting.setImageResource(R.drawable.shezhi_liang);
                break;
            }

        }


    }


    //设置暗色
    private void resetImg() {
        mImgWeixin.setImageResource(R.drawable.weixin_anse);
        mImgFriend.setImageResource(R.drawable.friend_anse);
        mImgAddress.setImageResource(R.drawable.zhibo_anse);
        mImgSetting.setImageResource(R.drawable.shezhi_anse);
    }

}
