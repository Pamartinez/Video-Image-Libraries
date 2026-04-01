package androidx.preference;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PreferenceViewHolder extends RecyclerView.ViewHolder {
    private final Drawable mBackground;
    private final SparseArray<View> mCachedViews;
    private boolean mDividerAllowedAbove;
    private boolean mDividerAllowedBelow;
    private int mDividerStartOffset = 0;
    private boolean mDrawBackground = false;
    private int mDrawCorners;
    private boolean mSubheaderRound = false;
    private ColorStateList mTitleTextColors;

    public PreferenceViewHolder(View view) {
        super(view);
        SparseArray<View> sparseArray = new SparseArray<>(4);
        this.mCachedViews = sparseArray;
        TextView textView = (TextView) view.findViewById(16908310);
        sparseArray.put(16908310, textView);
        sparseArray.put(16908304, view.findViewById(16908304));
        sparseArray.put(16908294, view.findViewById(16908294));
        int i2 = R$id.icon_frame;
        sparseArray.put(i2, view.findViewById(i2));
        sparseArray.put(16908350, view.findViewById(16908350));
        this.mBackground = view.getBackground();
        if (textView != null) {
            this.mTitleTextColors = textView.getTextColors();
        }
    }

    public View findViewById(int i2) {
        View view = this.mCachedViews.get(i2);
        if (view != null) {
            return view;
        }
        View findViewById = this.itemView.findViewById(i2);
        if (findViewById != null) {
            this.mCachedViews.put(i2, findViewById);
        }
        return findViewById;
    }

    public int getDrawCorners() {
        return this.mDrawCorners;
    }

    public boolean isBackgroundDrawn() {
        return this.mDrawBackground;
    }

    public boolean isDividerAllowedAbove() {
        return this.mDividerAllowedAbove;
    }

    public boolean isDividerAllowedBelow() {
        return this.mDividerAllowedBelow;
    }

    public boolean isDrawSubheaderRound() {
        return this.mSubheaderRound;
    }

    public void resetState() {
        Drawable background = this.itemView.getBackground();
        Drawable drawable = this.mBackground;
        if (background != drawable) {
            ViewCompat.setBackground(this.itemView, drawable);
        }
        TextView textView = (TextView) findViewById(16908310);
        if (textView != null && this.mTitleTextColors != null && !textView.getTextColors().equals(this.mTitleTextColors)) {
            textView.setTextColor(this.mTitleTextColors);
        }
    }

    public int seslGetDividerLeftOffset() {
        return this.mDividerStartOffset;
    }

    public void seslSetDividerStartOffset(int i2) {
        this.mDividerStartOffset = i2;
    }

    public void setDividerAllowedAbove(boolean z) {
        this.mDividerAllowedAbove = z;
    }

    public void setDividerAllowedBelow(boolean z) {
        this.mDividerAllowedBelow = z;
    }

    public void setPreferenceBackgroundType(boolean z, int i2, boolean z3) {
        this.mDrawBackground = z;
        this.mDrawCorners = i2;
        this.mSubheaderRound = z3;
    }
}
