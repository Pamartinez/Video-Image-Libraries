package com.samsung.android.gallery.app.ui.viewholders;

import V8.a;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FontUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumsDividerViewHolder extends ListViewHolder implements View.OnClickListener {
    private final View.AccessibilityDelegate mAccessibilityDelegate = new View.AccessibilityDelegate() {
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setRoleDescription(AppResources.getString(R.string.speak_button));
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        }
    };
    private View mContainer;
    private View mNewDot;
    private ListViewHolder.SelectListener mSelectListener;
    private TextView mTitle;
    private TextView mViewAll;

    public MxAlbumsDividerViewHolder(View view, int i2) {
        super(view, i2);
    }

    private boolean isClickable(View view) {
        if (!ViewUtils.isVisible(view) || !view.isClickable()) {
            return false;
        }
        return true;
    }

    private void updateViewAllContentDescription() {
        int i2;
        if (ViewUtils.isVisible(this.mViewAll)) {
            Resources resources = this.mViewAll.getResources();
            if (ViewHolderValue.isFirstDivider(getViewType())) {
                i2 = R.string.view_all_albums;
            } else {
                i2 = R.string.view_all_shared_albums;
            }
            String string = resources.getString(i2);
            if (ViewUtils.isStubVisible(this.mNewDot)) {
                StringBuilder t = C0212a.t(string, ArcCommonLog.TAG_COMMA);
                t.append(this.mViewAll.getResources().getString(R.string.new_content_added));
                string = t.toString();
            }
            this.mViewAll.setContentDescription(string);
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        ViewUtils.setText(this.mTitle, mediaItem.getTitle());
        ViewUtils.setOnClickListener(this.mViewAll, this);
        ViewUtils.setVisibleOrGone(this.mViewAll, PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums));
        ViewUtils.setAccessibilityDelegate(this.mViewAll, this.mAccessibilityDelegate);
        ViewUtils.setVisibleOrGone(this.mNewDot, ViewUtils.isVisible(this.mViewAll));
    }

    public void bindView(View view) {
        this.mTitle = (TextView) view.findViewById(R.id.title);
        FontUtils.resizeUpToLarge(view.getContext(), this.mTitle, (float) view.getResources().getDimensionPixelSize(R.dimen.mx_albums_divider_title_text_size));
        this.mViewAll = (TextView) view.findViewById(R.id.view_all);
        FontUtils.resizeUpToLarge(view.getContext(), this.mViewAll, (float) view.getResources().getDimensionPixelSize(R.dimen.mx_albums_divider_view_all_text_size));
        SeApiCompat.setButtonShapeEnabled(this.mViewAll);
        this.mContainer = view.findViewById(R.id.divider_container);
        this.mNewDot = view.findViewById(R.id.newLabel);
    }

    public View getDecoView(int i2) {
        if (i2 == 1) {
            return this.mNewDot;
        }
        if (i2 == 27) {
            return this.mViewAll;
        }
        return super.getDecoView(i2);
    }

    public View getDividerView() {
        return this.mContainer;
    }

    public String getTitle() {
        return (String) Optional.ofNullable(this.mTitle).map(new a(18)).orElse((Object) null);
    }

    public void hideTitle() {
        ViewUtils.setVisibleOrGone(this.mTitle, false);
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent) {
        if (!isClickable(this.mViewAll) || !ViewUtils.isTouchedOnView(this.mViewAll, motionEvent)) {
            return true;
        }
        return false;
    }

    public void onClick(View view) {
        onItemClickInternal(0);
    }

    public void setSelectListener(ListViewHolder.SelectListener selectListener) {
        this.mSelectListener = selectListener;
    }

    public void setViewAllTextEnabled() {
        setViewAllTextEnabled(!this.mSelectListener.isListSelectionMode() && !this.mSelectListener.isListMoveMode());
    }

    public boolean updateDecoration(int i2, Object... objArr) {
        int i7;
        if (i2 != 1) {
            return super.updateDecoration(i2, objArr);
        }
        int i8 = 0;
        boolean booleanValue = objArr[0].booleanValue();
        View view = this.mNewDot;
        if (!booleanValue) {
            i8 = 8;
        }
        ViewUtils.setVisibility(view, i8);
        TextView textView = this.mViewAll;
        Resources resources = this.itemView.getResources();
        if (booleanValue) {
            i7 = R.dimen.new_badge_dot_size;
        } else {
            i7 = R.dimen.mx_albums_divider_view_all_padding;
        }
        ViewMarginUtils.setEndPadding(textView, resources.getDimensionPixelSize(i7));
        updateViewAllContentDescription();
        return true;
    }

    public boolean useSelectListener() {
        return true;
    }

    public void setViewAllTextEnabled(boolean z) {
        if (ViewUtils.isVisible(this.mViewAll)) {
            this.mViewAll.setAlpha(z ? 1.0f : 0.4f);
            this.mViewAll.setClickable(z);
            this.mViewAll.setFocusable(z);
            this.mViewAll.setEnabled(z);
        }
    }
}
