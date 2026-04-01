package com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.sec.android.gallery3d.R;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThemeItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private BiConsumer<ThemeItemViewHolder, Boolean> mClickConsumer;
    private TextView mTitle;
    private View mTitleContainer;

    public ThemeItemViewHolder(View view) {
        super(view);
        initView(view);
    }

    private int getSidePadding() {
        Resources resources;
        if (this.itemView.getContext() != null) {
            resources = this.itemView.getContext().getResources();
        } else {
            resources = null;
        }
        if (resources == null) {
            return 0;
        }
        if (resources.getConfiguration().screenWidthDp > 600) {
            return resources.getDimensionPixelSize(R.dimen.stories_theme_title_horizontal_padding_land);
        }
        return resources.getDimensionPixelSize(R.dimen.stories_theme_title_horizontal_padding_port);
    }

    private void initView(View view) {
        this.mTitle = (TextView) view.findViewById(R.id.title);
        this.mTitleContainer = view.findViewById(R.id.title_container);
    }

    public void onClick(View view) {
        BiConsumer<ThemeItemViewHolder, Boolean> biConsumer = this.mClickConsumer;
        if (biConsumer != null) {
            biConsumer.accept(this, Boolean.FALSE);
        }
    }

    public void recycle() {
        this.itemView.setOnClickListener((View.OnClickListener) null);
        this.mClickConsumer = null;
    }

    public void setOnClickListener(BiConsumer<ThemeItemViewHolder, Boolean> biConsumer) {
        this.mClickConsumer = biConsumer;
        this.itemView.setOnClickListener(this);
    }

    public void setTitle(String str) {
        this.mTitle.setText(str);
        this.itemView.setContentDescription(str);
    }

    public void updateTitleContainer(boolean z) {
        int i2;
        int i7;
        int i8;
        TextView textView = this.mTitle;
        if (z) {
            i2 = R.style.OneUiRobotoSemiBold;
        } else {
            i2 = R.style.OneUiRobotoRegular;
        }
        textView.setTextAppearance(i2);
        TextView textView2 = this.mTitle;
        Context context = this.itemView.getContext();
        if (z) {
            i7 = R.color.story_highlight_theme_selected_title_color;
        } else {
            i7 = R.color.story_highlight_theme_unselected_title_color;
        }
        textView2.setTextColor(context.getColor(i7));
        View view = this.mTitleContainer;
        if (z) {
            i8 = this.itemView.getContext().getColor(R.color.story_highlight_theme_selected_background_color);
        } else {
            i8 = 0;
        }
        view.setBackgroundColor(i8);
    }

    public void updateTitlePadding() {
        int sidePadding = getSidePadding();
        this.mTitle.setPadding(sidePadding, 0, sidePadding, 0);
    }
}
