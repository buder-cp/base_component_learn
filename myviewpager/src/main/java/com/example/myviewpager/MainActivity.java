package com.example.myviewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private ViewPager mViewPager;
    private int viewPagerItemSize = 0;
    private final int INTERVAL = 1000 * 3;
    private ArrayList<Integer> mArrayList;
    private final static int SET_VIEWPAGER_ITEM = 9527;
    private LauncherViewPagerAdapter mViewPagerAdapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SET_VIEWPAGER_ITEM:
                    if (mViewPager != null && mViewPagerAdapter != null) {
                        int currentItemIndex = mViewPager.getCurrentItem();
                        int itemsCount = mViewPagerAdapter.getCount();
                        if ((currentItemIndex + 1) < itemsCount) {
                            mViewPager.setCurrentItem(currentItemIndex + 1, true);
                        } else {
                            mViewPager.setCurrentItem(0, false);
                        }
                    }
                    break;
            }
        }
    };

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            Message message = mHandler.obtainMessage();
            message.what = SET_VIEWPAGER_ITEM;
            mHandler.sendMessage(message);
            mHandler.removeCallbacks(mRunnable);
            mHandler.postDelayed(this, INTERVAL);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉状态栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        init();
    }

    //初始化，此处需要确保先赋值，后加载数据，否则为空
    private void init() {
        initData();
        if (viewPagerItemSize > 0) {
            initViewPager();
            setAutoChangeViewPager();
        }
    }

    //准备ViewPager将显示的数据
    private void initData() {
        mContext = this;
        mArrayList = new ArrayList<Integer>();
        mArrayList.add(R.drawable.a);
        mArrayList.add(R.drawable.b);
        mArrayList.add(R.drawable.c);
        mArrayList.add(R.drawable.d);
        viewPagerItemSize = mArrayList.size();
    }


    //初始化ViewPager
    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.guide_viewpager);
        mViewPagerAdapter = new LauncherViewPagerAdapter(mContext);
        mViewPager.setPageTransformer(false, new CustPagerTransformer(this));
        mViewPagerAdapter.setAdapterData(mArrayList);
        mViewPager.setAdapter(mViewPagerAdapter);
        int currentItem = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % viewPagerItemSize;
        mViewPager.setCurrentItem(currentItem);
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                mViewPager.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    cancelAutoScroll();
                } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
                    cancelAutoScroll();
                } else if (state == ViewPager.SCROLL_STATE_IDLE) {
                    startAutoScroll();
                }
            }
        });

    }

    private void startAutoScroll() {
        cancelAutoScroll();
        mHandler.postDelayed(mRunnable, 1000 * 3);
    }

    public void cancelAutoScroll() {
        mHandler.removeCallbacksAndMessages(null);
    }

    //开启ViewPager的自动轮播
    @SuppressWarnings("deprecation")
    private void setAutoChangeViewPager() {
        mHandler.postDelayed(mRunnable, INTERVAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mViewPager) {
            mViewPager.removeAllViews();
            mViewPager = null;
        }
    }

    class LauncherViewPagerAdapter extends PagerAdapter {
        private Context mContext;
        private ArrayList<Integer> pagesArrayList;
        private View itemView;

        public LauncherViewPagerAdapter(Context context) {
            this.mContext = context;
        }

        public void setAdapterData(ArrayList<Integer> arrayList) {
            pagesArrayList = arrayList;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (pagesArrayList.size() > 0) {
                itemView = LayoutInflater.from(mContext).inflate(R.layout.guide_pager_adapter, null);
                itemView.setFocusable(true);
                ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);

                imageView.setBackgroundResource(pagesArrayList.get(position % pagesArrayList.size()));
                container.addView(itemView);
                itemView.setClickable(true); //这里itemView需要设置，否则up事件无法响应
                itemView.setEnabled(true);
                itemView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                mHandler.removeCallbacksAndMessages(null);
                                break;
                            case MotionEvent.ACTION_MOVE:
                            case MotionEvent.ACTION_UP:
                                mHandler.removeCallbacksAndMessages(null);
                                setAutoChangeViewPager();
                                break;
                        }
                        return false;
                    }
                });
                return itemView;
            }
            return null;

        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


}
