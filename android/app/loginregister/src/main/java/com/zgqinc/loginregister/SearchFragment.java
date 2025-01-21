package com.zgqinc.loginregister;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class SearchFragment extends Fragment {

    private static final int[] SAMPLE_IMAGES = {
            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground
    };

    private static final String[] SAMPLE_TEXTS = {
            "Nature", "Cityscape", "Mountains", "Beach", "Sunset"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 绑定布局
        View view = inflater.inflate(R.layout.activity_search, container, false);

        // 初始化 RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        // 设置瀑布流布局管理器（2列）
        recyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        // 设置适配器
        recyclerView.setAdapter(new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_layout, parent, false);
                return new RecyclerView.ViewHolder(itemView) {};
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ImageView imageView = holder.itemView.findViewById(R.id.itemImage);
                TextView textView = holder.itemView.findViewById(R.id.itemText);

                // 设置图片和文本
                imageView.setImageResource(SAMPLE_IMAGES[position % SAMPLE_IMAGES.length]);
                textView.setText(SAMPLE_TEXTS[position % SAMPLE_TEXTS.length]);
            }

            @Override
            public int getItemCount() {
                return 20; // 静态展示 20 个项目
            }
        });

        return view;
    }
}
