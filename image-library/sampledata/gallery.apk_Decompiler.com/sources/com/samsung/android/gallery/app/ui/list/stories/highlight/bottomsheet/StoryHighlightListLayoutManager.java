package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet;

import D5.c;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesLayoutManager;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHighlightListLayoutManager extends PicturesLayoutManager {
    private Consumer<View> mAttachCallback;
    private final Context mContext;

    public StoryHighlightListLayoutManager(RecyclerView recyclerView, int i2) {
        super(recyclerView.getContext(), i2);
        this.mContext = recyclerView.getContext();
    }

    public void addView(View view, int i2) {
        super.addView(view, i2);
        Optional.ofNullable(this.mAttachCallback).ifPresent(new c(view, 2));
    }

    public int getSpacing(int i2) {
        Resources resources = this.mContext.getResources();
        return resources.getDimensionPixelOffset(R.dimen.story_highlight_list_item_gap) - resources.getDimensionPixelOffset(R.dimen.story_highlight_list_view_start_padding);
    }

    public void setAttachCallback(Consumer<View> consumer) {
        this.mAttachCallback = consumer;
    }
}
