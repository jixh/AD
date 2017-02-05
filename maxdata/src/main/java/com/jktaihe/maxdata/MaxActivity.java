package com.jktaihe.maxdata;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MaxActivity extends AppCompatActivity{

    private List<ImageView> imageViewList;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_max_main);
        initView();
    }

    public void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        prepareData();
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        mViewPager.setAdapter(adapter);
        //设置ViewPager的默认项, 设置当前页位置，一开始才能往左滑动
        mViewPager.setCurrentItem(1000);
        mViewPager.setOffscreenPageLimit(1);
//        mViewPager.setPageMargin(40);
        mViewPager.setPageTransformer(true,new MyTransformation());
    }

    private void prepareData() {
        imageViewList = new ArrayList<ImageView>();
        int[] imageResIDs = getImageResIDs();
        ImageView iv;
        for (int i = 0; i < imageResIDs.length; i++){
            iv = new ImageView(this);
            iv.setBackgroundResource(imageResIDs[i]);
            imageViewList.add(iv);
        }
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

    class ViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 2000;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            ((ViewPager)container).removeView((ImageView)object); 非无限循环可以用
            container.removeView(container);//这一行也可以去掉但可能有内存泄露问题
        }
        public Object instantiateItem(ViewGroup container, int position) {
            //方案1
            View view = imageViewList.get(position % imageViewList.size());
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            container.addView(view,0);
            return view;

            //方案2
//            if (container.getChildCount() == imageViewList.size()) {
//                container.removeView(imageViewList.get(position % imageViewList.size()));
//            }
//            container.addView(imageViewList.get(position% imageViewList.size()), 0);//添加页卡
//            return imageViewList.get(position% imageViewList.size());

        }
    }


}
