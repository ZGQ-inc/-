package com.zgqinc.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.zgqinc.loginregister.adapters.BannerAdapter;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Integer> bannerList; // 使用Integer存储图片资源ID
    private BannerAdapter bannerAdapter;
    private Handler handler;
    private Runnable autoScrollRunnable;
    private int currentIndex = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 绑定布局
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 初始化 RecyclerView 和本地图片资源
        recyclerView = view.findViewById(R.id.recyclerView);
        bannerList = new ArrayList<>();
        bannerList.add(R.drawable.ic_launcher_foreground); // 本地图片资源
        bannerList.add(R.drawable.ic_launcher_foreground);
        bannerList.add(R.drawable.ic_launcher_foreground);

        // 设置适配器和布局管理器
        bannerAdapter = new BannerAdapter(bannerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(bannerAdapter);

        // 自动轮播逻辑
        handler = new Handler(Looper.getMainLooper());
        autoScrollRunnable = new Runnable() {
            @Override
            public void run() {
                if (currentIndex >= bannerList.size()) {
                    currentIndex = 0;
                }
                recyclerView.smoothScrollToPosition(currentIndex++);
                handler.postDelayed(this, 3000); // 每3秒滚动一次
            }
        };
        handler.postDelayed(autoScrollRunnable, 3000);



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(autoScrollRunnable); // 停止轮播
    }
}