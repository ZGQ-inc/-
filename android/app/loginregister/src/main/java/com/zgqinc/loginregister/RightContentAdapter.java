package com.zgqinc.loginregister;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class RightContentAdapter extends RecyclerView.Adapter<RightContentAdapter.ViewHolder> {

    private final List<String> contentList;

    public RightContentAdapter(String item) {
        contentList = new ArrayList<>();
        // 示例数据：根据选择的 item 动态填充内容
        for (int i = 1; i <= 5; i++) {
            contentList.add("Dish " + item + " - Description " + i);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_right_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(contentList.get(position));
        holder.imageView.setImageResource(R.drawable.ic_launcher_foreground); // 使用 ic_launcher_foreground
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.right_content_text);
            imageView = itemView.findViewById(R.id.right_content_image);
        }
    }
}
