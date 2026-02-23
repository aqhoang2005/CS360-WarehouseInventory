package com.example.cs360warehouseinventory;


import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.VH> {

    public interface ItemAction {
        void onAction(InventoryItem item);
    }

    private final ItemAction onEdit;
    private final ItemAction onDelete;
    private final List<InventoryItem> items = new ArrayList<>();

    public InventoryAdapter(ItemAction onEdit, ItemAction onDelete) {
        this.onEdit = onEdit;
        this.onDelete = onDelete;
    }

    public void submitList(List<InventoryItem> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_inventory, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        InventoryItem item = items.get(position);

        holder.itemName.setText(item.name + " (Qty: " + item.qty + ")");
        holder.itemDesc.setText(item.desc != null ? item.desc : "");
        holder.itemTags.setText(item.tags != null ? item.tags : "");

        if (item.imageUri != null && !item.imageUri.trim().isEmpty()) {
            try {
                holder.itemImage.setImageURI(Uri.parse(item.imageUri));
            } catch (Exception e) {
                holder.itemImage.setImageResource(android.R.drawable.ic_menu_gallery);
            }
        } else {
            holder.itemImage.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        holder.editBtn.setOnClickListener(v -> onEdit.onAction(item));
        holder.deleteBtn.setOnClickListener(v -> onDelete.onAction(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName, itemDesc, itemTags;
        View editBtn, deleteBtn;

        VH(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            itemDesc = itemView.findViewById(R.id.itemDesc);
            itemTags = itemView.findViewById(R.id.itemTags);
            editBtn = itemView.findViewById(R.id.editItemBtn);
            deleteBtn = itemView.findViewById(R.id.deleteItemBtn);
        }
    }
}