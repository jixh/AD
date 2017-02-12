package com.jktaihe.expanddata;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExpandActivity extends AppCompatActivity  implements ViewPager.OnPageChangeListener {

    private List<ImageView> imageViewList;
    private ViewPager mViewPager;
    private TextView mTextView;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_main);
        initView();
    }

    public void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTextView = (TextView) findViewById(R.id.tv_image_description);
        prepareData();

        ViewPagerAdapter adapter = new ViewPagerAdapter();
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(1);
        mViewPager.addOnPageChangeListener(this);
    }

    private void prepareData() {
        imageViewList = new ArrayList<ImageView>();
        int[] imageResIDs = getImageResIDs();
        if (imageResIDs.length == 1){
            addView(0);
        }else if (imageResIDs.length > 1){
            for (int i = 0; i < imageResIDs.length; i++) {
                if (i == 0) {
                    addView(imageResIDs.length - 1);
                }
                addView(i);
                if (i == (imageResIDs.length - 1)) {
                    addView(0);

                }
            }
        }
    }

    private void addView(int imageResID) {
        ImageView iv = new ImageView(this);
        iv.setBackgroundResource(getImageResIDs()[imageResID]);
        imageViewList.add(iv);
    }

    /**
     * 在此处本来是5张图片，现在在数组首尾各加了一张图
     * @return
     */
    private int[] getImageResIDs() {
        return new int[]{
                R.mipmap.a1,
                R.mipmap.a2,
                R.mipmap.a3,
        };
    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViewList.size();
        }

        /**
         * 判断出去的view是否等于进来的view 如果为true直接复用
         */
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        /**
         * 销毁预加载以外的view对象, 会把需要销毁的对象的索引位置传进来就是position
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViewList.get(position));
        }

        /**
         * 创建一个view
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViewList.get(position));
            return imageViewList.get(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        if (imageViewList.size() > 1 && arg0 == 0) {
            //多于1，才会循环跳转
            if (position < 1) { //首位之前，跳转到末尾（N）
                position = imageViewList.size() - 2;
                mViewPager.setCurrentItem(position, false); //false:不显示跳转过程的动画
            } else if (position > imageViewList.size() - 2) { //末位之后，跳转到首位（1）
                position = 1;
                mViewPager.setCurrentItem(position, false); //false:不显示跳转过程的动画
            }
            mTextView.setText("第" + position + "个引导页面");
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        this.position = position;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
