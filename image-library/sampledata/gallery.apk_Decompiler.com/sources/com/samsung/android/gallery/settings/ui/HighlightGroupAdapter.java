package com.samsung.android.gallery.settings.ui;

import Ab.b;
import D5.a;
import D5.c;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceGroupAdapter;
import androidx.preference.PreferenceViewHolder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HighlightGroupAdapter extends PreferenceGroupAdapter {
    private String mHighlightKey;
    private int mHighlightPos = -1;
    private RecyclerView mListView;
    private CustomSmoothScroller mSmoothScroller;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CustomSmoothScroller extends LinearSmoothScroller {
        private final RecyclerView mListView;

        public CustomSmoothScroller(Context context, RecyclerView recyclerView) {
            super(context);
            this.mListView = recyclerView;
        }

        public int calculateDtToFit(int i2, int i7, int i8, int i10, int i11) {
            Rect systemInsets = WindowUtils.getSystemInsets(this.mListView.getRootWindowInsets());
            int toolBarHeightWithPadding = SystemUi.getToolBarHeightWithPadding(this.mListView.getContext()) + systemInsets.top + i7;
            int i12 = systemInsets.bottom;
            if (i10 - i12 < toolBarHeightWithPadding) {
                return (-(toolBarHeightWithPadding - i10)) - i12;
            }
            return 0;
        }
    }

    public HighlightGroupAdapter(PreferenceGroup preferenceGroup) {
        super(preferenceGroup);
    }

    private void applyHighlight(RecyclerView recyclerView) {
        if (recyclerView != null) {
            int i2 = this.mHighlightPos;
            String str = this.mHighlightKey;
            if (str != null && i2 >= 0) {
                this.mHighlightPos = -1;
                this.mHighlightKey = null;
                recyclerView.postDelayed(new b((Object) this, i2, (Object) str, 14), 600);
                return;
            }
            return;
        }
        Log.e(" HighlightGroupAdapter", "applyHighlight failed. not attached");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$applyHighlight$0(int i2, String str) {
        applyHighlight(this.mListView, i2, str);
    }

    private void smoothScrollToPosition(RecyclerView recyclerView, int i2) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            if (this.mSmoothScroller == null) {
                this.mSmoothScroller = new CustomSmoothScroller(recyclerView.getContext(), recyclerView);
            }
            this.mSmoothScroller.setTargetPosition(i2);
            linearLayoutManager.startSmoothScroll(this.mSmoothScroller);
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mListView = recyclerView;
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mListView = null;
    }

    public void requestHighlight(RecyclerView recyclerView, String str) {
        int preferenceAdapterPosition = getPreferenceAdapterPosition(str);
        Log.d(" HighlightGroupAdapter", "requestHighlight", Integer.valueOf(preferenceAdapterPosition), str);
        if (preferenceAdapterPosition >= 0 && recyclerView != null) {
            this.mHighlightPos = preferenceAdapterPosition;
            this.mHighlightKey = str;
            smoothScrollToPosition(recyclerView, preferenceAdapterPosition);
            notifyItemChanged(this.mHighlightPos);
        }
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder, int i2) {
        super.onBindViewHolder(preferenceViewHolder, i2);
        if (i2 == this.mHighlightPos) {
            applyHighlight(this.mListView);
        }
    }

    private void applyHighlight(RecyclerView recyclerView, int i2, String str) {
        if (recyclerView != null) {
            Preference item = getItem(i2);
            if (item == null || !str.equals(item.getKey())) {
                Log.e((CharSequence) " HighlightGroupAdapter", "applyHighlight failed. invalid preference", item);
                return;
            }
            PreferenceViewHolder preferenceViewHolder = (PreferenceViewHolder) recyclerView.findViewHolderForAdapterPosition(i2);
            Log.d(" HighlightGroupAdapter", "applyHighlight", Integer.valueOf(i2), str, Boolean.valueOf(preferenceViewHolder != null));
            View view = preferenceViewHolder != null ? preferenceViewHolder.itemView : null;
            if (view != null) {
                Optional.ofNullable(view.getBackground()).ifPresent(new c(view, 1));
                view.setPressed(true);
                view.postDelayed(new a(view, 1), 4000);
                return;
            }
            return;
        }
        Log.e((CharSequence) " HighlightGroupAdapter", "applyHighlight failed. not attached", Integer.valueOf(i2), str);
    }
}
