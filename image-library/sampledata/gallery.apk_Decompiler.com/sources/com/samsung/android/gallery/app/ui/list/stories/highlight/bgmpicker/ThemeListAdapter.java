package com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;
import g6.f;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThemeListAdapter extends RecyclerView.Adapter<ThemeItemViewHolder> {
    private Consumer<EffectTheme> mClickConsumer;
    private int mFocusedPosition;
    private final ArrayList<String> mItems = new ArrayList<>();

    public ThemeListAdapter() {
        for (EffectTheme titleResId : EffectTheme.values()) {
            this.mItems.add(AppResources.getString(titleResId.getTitleResId()));
        }
    }

    private int getItemResId() {
        return R.layout.story_theme_list_item_layout;
    }

    private ThemeItemViewHolder getItemView(RecyclerView recyclerView, int i2) {
        return (ThemeItemViewHolder) recyclerView.findViewHolderForAdapterPosition(i2);
    }

    /* access modifiers changed from: private */
    public void onSelect(ThemeItemViewHolder themeItemViewHolder, boolean z) {
        int absoluteAdapterPosition;
        int i2;
        if (this.mClickConsumer != null && (absoluteAdapterPosition = themeItemViewHolder.getAbsoluteAdapterPosition()) != (i2 = this.mFocusedPosition)) {
            notifyItemChanged(i2, "deselect");
            this.mFocusedPosition = absoluteAdapterPosition;
            updateItemBackground(themeItemViewHolder);
            for (EffectTheme effectTheme : EffectTheme.values()) {
                if (effectTheme.ordinal() == absoluteAdapterPosition) {
                    Log.d("ThemeListAdapter", "onSelect", Integer.valueOf(absoluteAdapterPosition), effectTheme);
                    this.mClickConsumer.accept(effectTheme);
                    return;
                }
            }
        }
    }

    private void updateItemBackground(ThemeItemViewHolder themeItemViewHolder) {
        if (themeItemViewHolder == null) {
            return;
        }
        if (themeItemViewHolder.getAbsoluteAdapterPosition() == this.mFocusedPosition) {
            themeItemViewHolder.updateTitleContainer(true);
        } else {
            themeItemViewHolder.updateTitleContainer(false);
        }
    }

    public int getFocusedPosition() {
        return this.mFocusedPosition;
    }

    public int getItemCount() {
        return this.mItems.size();
    }

    public void setFocus(RecyclerView recyclerView, EffectTheme effectTheme) {
        int i2 = this.mFocusedPosition;
        int indexOf = this.mItems.indexOf(AppResources.getString(effectTheme.getTitleResId()));
        this.mFocusedPosition = indexOf;
        recyclerView.scrollToPosition(indexOf);
        if (i2 != this.mFocusedPosition) {
            this.mClickConsumer.accept(effectTheme);
            updateItemBackground(getItemView(recyclerView, this.mFocusedPosition));
            updateItemBackground(getItemView(recyclerView, i2));
        }
    }

    public void setOnClickListener(Consumer<EffectTheme> consumer) {
        this.mClickConsumer = consumer;
    }

    public ThemeItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new ThemeItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getItemResId(), viewGroup, false));
    }

    public void onViewAttachedToWindow(ThemeItemViewHolder themeItemViewHolder) {
        super.onViewAttachedToWindow(themeItemViewHolder);
        updateItemBackground(themeItemViewHolder);
    }

    public void onViewRecycled(ThemeItemViewHolder themeItemViewHolder) {
        themeItemViewHolder.recycle();
    }

    public void onBindViewHolder(ThemeItemViewHolder themeItemViewHolder, int i2) {
        themeItemViewHolder.setTitle(this.mItems.get(i2));
        themeItemViewHolder.setOnClickListener(new f(3, this));
        themeItemViewHolder.updateTitlePadding();
    }

    public void onBindViewHolder(ThemeItemViewHolder themeItemViewHolder, int i2, List<Object> list) {
        if (list.contains("deselect")) {
            themeItemViewHolder.updateTitleContainer(false);
            return;
        }
        if (list.contains("configuration_changed")) {
            themeItemViewHolder.updateTitlePadding();
        }
        themeItemViewHolder.setOnClickListener(new f(3, this));
        super.onBindViewHolder(themeItemViewHolder, i2, list);
    }
}
