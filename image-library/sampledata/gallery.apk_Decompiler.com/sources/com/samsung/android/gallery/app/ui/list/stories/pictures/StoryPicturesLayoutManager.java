package com.samsung.android.gallery.app.ui.list.stories.pictures;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesLayoutManager;
import com.samsung.android.gallery.module.dataset.chapter.LayoutInfo;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryPicturesLayoutManager extends PicturesLayoutManager {
    private StoryPicturesViewAdapter mAdapter;
    private int mDefaultSpacing;
    private final StoryLayoutBuilder mLayoutBuilder;
    private int mRatioSpacing;

    public StoryPicturesLayoutManager(RecyclerView recyclerView, int i2) {
        super(recyclerView.getContext(), i2);
        initDimen(recyclerView.getContext());
        this.mLayoutBuilder = new StoryLayoutBuilder(recyclerView.getContext());
    }

    private void updateTimeViewLayout(View view, int i2, boolean z) {
        if (view != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams.width = i2;
            int i7 = 20;
            layoutParams.removeRule(20);
            layoutParams.removeRule(21);
            if (!z) {
                i7 = 21;
            }
            layoutParams.addRule(i7);
        }
    }

    public int getSpacing(int i2) {
        return this.mDefaultSpacing + this.mRatioSpacing;
    }

    public void initDimen(Context context) {
        int i2;
        if (StoryHelper.getWidthRatio(context) < 1.0f) {
            i2 = 0;
        } else {
            i2 = -context.getResources().getDimensionPixelOffset(R.dimen.story_pictures_list_padding);
        }
        this.mDefaultSpacing = i2;
    }

    public void onAdapterChangedInternal(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        super.onAdapterChangedInternal(adapter, adapter2);
        this.mAdapter = (StoryPicturesViewAdapter) adapter2;
    }

    public void updateFooterPadding(View view) {
        View findViewById = view.findViewById(R.id.related_container);
        if (findViewById != null) {
            findViewById.setPadding(-getSpacing(1), findViewById.getPaddingTop(), -getSpacing(1), findViewById.getPaddingBottom());
        }
    }

    public void updateRatioSpacing(int i2) {
        this.mRatioSpacing = i2;
    }

    public boolean updateTimelineWidth() {
        return false;
    }

    public void updateViewSize(View view, int i2, int i7) {
        super.updateViewSize(view, i2, i7);
        if (i2 == 0) {
            int viewAdapterPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewAdapterPosition();
            int verticalGapRatio = (int) (this.mLayoutBuilder.getVerticalGapRatio() * ((float) getHintWidthSpace(1)));
            if (verticalGapRatio != view.getPaddingBottom()) {
                view.setPadding(0, 0, 0, verticalGapRatio);
            }
            LayoutInfo layoutInfo = this.mAdapter.getLayoutInfo(getHintDataPosition(viewAdapterPosition, 1));
            this.mLayoutBuilder.buildImageLayout(layoutInfo, getHintWidthSpace(1), (RelativeLayout.LayoutParams) ((FrameLayout) view.findViewById(R.id.item_container)).getLayoutParams());
            TextView textView = (TextView) view.findViewById(R.id.time_view);
            if (layoutInfo.groupType != 1) {
                return;
            }
            if (layoutInfo.align.start() || layoutInfo.align.end()) {
                int measureTextViewWidth = ViewUtils.getMeasureTextViewWidth(textView);
                int timeViewSpace = this.mLayoutBuilder.getTimeViewSpace(getHintWidthSpace(1));
                if (timeViewSpace < (textView.getPaddingStart() * 2) + measureTextViewWidth) {
                    updateTimeViewLayout(textView, timeViewSpace, !layoutInfo.align.start());
                } else {
                    updateTimeViewLayout(textView, -2, !layoutInfo.align.start());
                }
            }
        } else if (ViewHolderValue.isFooter(i2)) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMarginStart(getSpacing(1));
            marginLayoutParams.setMarginEnd(getSpacing(1));
            updateFooterPadding(view);
        }
    }
}
