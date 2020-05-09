package nz.mega.bud.category;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import nz.mega.bud.databinding.ItemCategoryBinding;
import nz.mega.core.data.category.Category;

class CategoryViewHolder extends RecyclerView.ViewHolder {

    private final ItemCategoryBinding viewBinding;

    CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        this.viewBinding = ItemCategoryBinding.bind(itemView);
    }

    void bind(Category category) {
        this.viewBinding.name.setText(category.getName());
        this.viewBinding.name.setBackgroundColor(category.getColor());
    }
}
