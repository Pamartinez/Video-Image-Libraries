package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;
import r6.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RelatedLayoutManager extends GridLayoutManager {
    protected final Context mContext;
    private final LayoutInfoSupplier mLayoutInfoSupplier;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface LayoutInfoSupplier {
    }

    public RelatedLayoutManager(Context context, int i2, LayoutInfoSupplier layoutInfoSupplier) {
        super(context, i2);
        this.mContext = context;
        this.mLayoutInfoSupplier = layoutInfoSupplier;
    }

    public void addView(View view) {
        super.addView(view);
        updateViewSize(view);
    }

    public void updateViewSize(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (getItemCount() > 1) {
            layoutParams.height = ((h) this.mLayoutInfoSupplier).d.lambda$createLayoutManager$0(R.dimen.story_related_page_item_height_ratio_2x);
            if (layoutParams.getViewLayoutPosition() == 0) {
                ViewMarginUtils.setBottomMargin(view, ((h) this.mLayoutInfoSupplier).d.lambda$createLayoutManager$0(R.dimen.story_related_page_item_bottom_padding_ratio));
            } else {
                ViewMarginUtils.setBottomMargin(view, 0);
            }
        } else {
            layoutParams.height = ((h) this.mLayoutInfoSupplier).d.lambda$createLayoutManager$0(R.dimen.story_related_page_item_height_ratio_1x);
            ViewMarginUtils.setBottomMargin(view, 0);
        }
        view.setLayoutParams(layoutParams);
        ViewMarginUtils.setBottomMargin(view.findViewById(R.id.title_container), ((h) this.mLayoutInfoSupplier).d.lambda$createLayoutManager$0(R.dimen.story_related_page_item_title_bottom_margin_ratio));
    }
}
