package com.osman.cloudfirestoretask.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.osman.cloudfirestoretask.R;
import com.osman.cloudfirestoretask.databinding.ItemCarBinding;
import com.osman.cloudfirestoretask.model.Car;
import com.osman.cloudfirestoretask.ui.adapter.viewHolder.CarViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarViewHolder> {

    private Context context;
    private List<Car> dataList;
    private LayoutInflater inflater;

    private CarClickListener listener;

    public CarsAdapter(Context context) {
        this.context = context;
        this.dataList = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
    }

    public void setData(List<Car> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void appendData(List<Car> dataList) {
        int rangeStart = this.dataList.size();
        this.dataList.addAll(dataList);
        int rangeEnd = this.dataList.size();
        notifyItemRangeInserted(rangeStart, rangeEnd);
    }

    public void clearList() {
        this.dataList.clear();
        notifyDataSetChanged();
    }

    public void setOnItemClickLister(CarClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCarBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_car, parent, false);
        return new CarViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        if (dataList.get(position) != null) {
            holder.binding.setCar(dataList.get(position));
            holder.itemView.setOnClickListener(v -> {
                if (listener != null)
                    listener.onClick(dataList.get(position));
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

}
