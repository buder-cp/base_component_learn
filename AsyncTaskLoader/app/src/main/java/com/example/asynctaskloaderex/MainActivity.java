package com.example.asynctaskloaderex;

import android.app.ProgressDialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<ApplicationInfo>> {

    ProgressDialog progressDialog = null;


    private List<ApplicationInfo> mListApps;

    private ListView mAppListView;

    private AppListAdapter mApplistAdapter;

    private PackageManager mPm;

    private LruCache<String, Drawable> mIconCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppListView = (ListView) findViewById(R.id.listview);

        mApplistAdapter = new AppListAdapter();

        mAppListView.setAdapter(mApplistAdapter);

        mPm = getPackageManager();

        mIconCache = new LruCache<>(80);
    }


    @Override
    protected void onResume() {
        super.onResume();
        startLoad();
    }

    private void startLoad() {

        progressDialog = new ProgressDialog(this);
        progressDialog.show();

        getSupportLoaderManager().initLoader(1, null, this);


    }


    @Override
    public Loader<List<ApplicationInfo>> onCreateLoader(int id, Bundle args) {

        //args 是getSupportLoaderManager().initLoader传过来的数据
        Log.e("branch", "onCreateLoader");

        return new AppListLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<ApplicationInfo>> loader, List<ApplicationInfo> data) {


        Log.e("branch", "onLoadFinished-》 " + data);

        mListApps = data;

        mApplistAdapter.notifyDataSetChanged();

        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<ApplicationInfo>> loader) {

        Log.e("branch", "onLoaderReset");
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        mListApps = null;
        mApplistAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("branch", "onStart");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("branch", "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("branch", "onDestroy");

        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }


    private class AppListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mListApps == null ? 0 : mListApps.size();
        }

        @Override
        public Object getItem(int position) {
            return mListApps.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            AppHolder appHolder = null;
            if (convertView == null) {

                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.app_list_item_layout, null, false);

                appHolder = new AppHolder(convertView);

            } else {

                appHolder = (AppHolder) convertView.getTag();

            }


            ApplicationInfo applicationInfo = (ApplicationInfo) getItem(position);


            Drawable logo = mIconCache.get(applicationInfo.packageName);

            if (logo == null) {

                logo = mPm.getApplicationIcon(applicationInfo);

                mIconCache.put(applicationInfo.packageName, logo);
            }

            appHolder.mIcon.setImageDrawable(logo);

            appHolder.mTitle.setText(mPm.getApplicationLabel(applicationInfo));

            return convertView;
        }


        private class AppHolder {

            private final ImageView mIcon;

            private final TextView mTitle;

            public AppHolder(View itemView) {

                mIcon = (ImageView) itemView.findViewById(R.id.icon);

                mTitle = (TextView) itemView.findViewById(R.id.title);

                itemView.setTag(this);

            }

        }

    }

}