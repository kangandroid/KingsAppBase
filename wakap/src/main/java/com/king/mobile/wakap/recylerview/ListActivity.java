package com.king.mobile.wakap.recylerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.king.mobile.wakap.R;

class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = new RecyclerView(this);
        setContentView(recyclerView);
        RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(pool);
        recyclerView.setItemViewCacheSize(5);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        AdapterImp adapter = new AdapterImp();
        recyclerView.setAdapter(adapter);
    }

    class CustomLayoutManger extends RecyclerView.LayoutManager {

        @Override
        public RecyclerView.LayoutParams generateDefaultLayoutParams() {
            return null;
        }
    }

    class AdapterImp extends RecyclerView.Adapter<CustomViewHolder> {

        public AdapterImp() {
        }

        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    class CustomAdapter<T> extends ListAdapter{

        public CustomAdapter(@NonNull DiffUtil.ItemCallback diffCallback) {
            super(diffCallback);
        }

        protected CustomAdapter(@NonNull AsyncDifferConfig config) {
            super(config);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            View view = LayoutInflater.from(itemView.getContext()).inflate(R.layout.item_task, null);
        }


    }

}
