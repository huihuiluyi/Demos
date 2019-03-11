package com.demo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

import fr.castorflex.android.verticalviewpager.sample.R;

public class MainActivity extends AppCompatActivity {

    private static final float MIN_SCALE = 0.75f;
    private static final float MIN_ALPHA = 0.75f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final VerticalViewPager verticalViewPager = (VerticalViewPager) findViewById(R.id.verticalviewpager);
        final SwipeRefreshLayout refreshLayout = findViewById(R.id.container);
        verticalViewPager.bind(refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        final List<PlaceholderFragment> list = Arrays.asList(
                PlaceholderFragment.newInstance(3),
                PlaceholderFragment.newInstance(1),
                PlaceholderFragment.newInstance(2),
                PlaceholderFragment.newInstance(3),
                PlaceholderFragment.newInstance(1)
        );

        verticalViewPager.setOffscreenPageLimit(4);

        DummyAdapter dummyAdapter = new DummyAdapter(getSupportFragmentManager(),list);

        verticalViewPager.setAdapter(dummyAdapter);

        verticalViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 这里主要是解决在onPageScrolled出现的闪屏问题
                // (positionOffset为0的时候，并不一定是切换完成，所以动画还在执行，强制再次切换，就会闪屏)
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:// 空闲状态，没有任何滚动正在进行（表明完成滚动）
                        int currentItem = verticalViewPager.getCurrentItem();
                        if (currentItem == 0) {
                            verticalViewPager.setCurrentItem(list.size() - 2, false);
                        } else if (currentItem == list.size() - 1) {
                            verticalViewPager.setCurrentItem(1, false);
                        }
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:// 正在拖动page状态
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:// 手指已离开屏幕，自动完成剩余的动画效果
                        break;
                }
            }
        });

    }


}
