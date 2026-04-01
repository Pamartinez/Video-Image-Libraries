package com.samsung.android.gallery.app.ui.list.stories.category.categorylist;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class YearItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Consumer<YearItemViewHolder> mClickConsumer;
    private TextView mTitle;
    private View mTitleContainer;
    private int mYear;

    public YearItemViewHolder(View view) {
        super(view);
        initView(view);
    }

    private void initView(View view) {
        this.mTitle = (TextView) view.findViewById(R.id.title);
        this.mTitleContainer = view.findViewById(R.id.title_container);
    }

    public int getYear() {
        return this.mYear;
    }

    public void onClick(View view) {
        Consumer<YearItemViewHolder> consumer = this.mClickConsumer;
        if (consumer != null) {
            consumer.accept(this);
        }
    }

    public void recycle() {
        this.itemView.setOnClickListener((View.OnClickListener) null);
        this.mClickConsumer = null;
    }

    public void setOnClickListener(Consumer<YearItemViewHolder> consumer) {
        this.mClickConsumer = consumer;
        this.itemView.setOnClickListener(this);
    }

    public void setTitle(int i2) {
        String str;
        this.mYear = i2;
        if (i2 == 0) {
            str = AppResources.getString(R.string.all);
        } else {
            str = StringCompat.toReadableNumber((long) i2);
        }
        this.mTitle.setText(str);
        this.itemView.setContentDescription(str);
    }

    public void updateTitleContainer(boolean z) {
        int i2;
        int i7;
        TextView textView = this.mTitle;
        Context context = this.itemView.getContext();
        if (z) {
            i2 = R.color.story_trip_year_filter_selected_title_color;
        } else {
            i2 = R.color.story_trip_year_filter_unselected_title_color;
        }
        textView.setTextColor(context.getColor(i2));
        View view = this.mTitleContainer;
        Context context2 = this.itemView.getContext();
        if (z) {
            i7 = R.color.story_trip_year_filter_selected_background_color;
        } else {
            i7 = R.color.story_trip_year_filter_unselected_background_color;
        }
        view.setBackgroundColor(context2.getColor(i7));
    }
}
