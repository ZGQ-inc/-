package com.zgqinc.loginregister;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class SearchActivity extends AppCompatActivity {


    private static final int[] SAMPLE_IMAGES = {
            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground
    };

    private static final String[] SAMPLE_TEXTS = {
            "Nature", "Cityscape", "Mountains", "Beach", "Sunset"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // 设置瀑布流布局管理器（2列）
        recyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        // 静态数据适配器
        recyclerView.setAdapter(new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_layout, parent, false);
                return new RecyclerView.ViewHolder(view) {};
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
                return 20; // 展示 20 个项目
            }
        });

    }
}
