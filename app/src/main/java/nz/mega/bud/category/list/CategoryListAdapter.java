package nz.mega.bud.category.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import nz.mega.bud.R;
import nz.mega.core.data.category.Category;

public class CategoryListAdapter extends ListAdapter<Category, CategoryViewHolder> {

    CategoryListAdapter() {
        super(new CategoryDiffItemCallback());
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private static class CategoryDiffItemCallback extends DiffUtil.ItemCallback<Category> {

        @Override
        public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem.getId() == newItem.getId();
        }
    }
}
