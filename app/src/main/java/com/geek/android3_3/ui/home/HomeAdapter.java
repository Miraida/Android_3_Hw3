package com.geek.android3_3.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geek.android3_3.data.model.Publish;
import com.geek.android3_3.databinding.ItemHomeRvBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<Publish> list = new ArrayList<>();
    private ClickListener listener;
    private OnLongClickListener longClickListener;

    public void updateList(List<Publish> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ClickListener listener) {
        this.listener = listener;
    }

    public void setOnLongItemClickListener(OnLongClickListener listener) {
        this.longClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemHomeRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemHomeRvBinding ui;

        public ViewHolder(@NonNull ItemHomeRvBinding itemView) {
            super(itemView.getRoot());
            ui = itemView;
            ui.getRoot().setOnClickListener(v -> listener.onItemClick(list.get(getAdapterPosition()).getId()));
            ui.getRoot().setOnLongClickListener(v -> {
                longClickListener.onLongItemClick(list.get(getAdapterPosition()).getId());
                return true;
            });
        }

        public void onBind(Publish publish) {
            ui.title.setText(publish.getTitle());
        }
    }

    public interface ClickListener {
        void onItemClick(Integer id);
    }

    public interface OnLongClickListener {
        void onLongItemClick(Integer id);
    }
}
