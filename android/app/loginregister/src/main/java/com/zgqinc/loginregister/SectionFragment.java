package com.zgqinc.loginregister;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SectionFragment extends Fragment {

    private ListView leftListView;
    private RecyclerView rightRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_section, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        leftListView = view.findViewById(R.id.left_list_view);
        rightRecyclerView = view.findViewById(R.id.right_recycler_view);

        // 初始化左侧列表内容
        String[] items = {"Dishes 1", "Dishes 2", "Dishes 3", "Dishes 4", "Dishes 5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, items);
        leftListView.setAdapter(adapter);

        // 初始化右侧 RecyclerView
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        updateRightContent("1");

        // 左侧列表项点击事件
        leftListView.setOnItemClickListener((parent, v, position, id) -> updateRightContent(items[position]));
    }

    private void updateRightContent(String item) {
        // 构造右侧内容
        RightContentAdapter adapter = new RightContentAdapter(item);
        rightRecyclerView.setAdapter(adapter);
    }
}
