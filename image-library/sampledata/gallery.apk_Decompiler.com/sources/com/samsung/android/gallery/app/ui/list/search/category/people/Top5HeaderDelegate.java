package com.samsung.android.gallery.app.ui.list.search.category.people;

import a6.C0419b;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Top5HeaderDelegate {
    private TextView mEditButton;
    private final EditModeListener mEditModeListener;
    private View mGuideTextView;
    private boolean mIsEditMode;
    private final boolean mPickerMode;
    private final ViewGroup mTop5Layout;
    private final ICreatureCategoryView mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface EditModeListener {
    }

    public Top5HeaderDelegate(ICreatureCategoryView iCreatureCategoryView, View view, boolean z, EditModeListener editModeListener) {
        this.mView = iCreatureCategoryView;
        this.mTop5Layout = (ViewGroup) view.findViewById(R.id.top5_layout);
        this.mPickerMode = z;
        this.mEditModeListener = editModeListener;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init$0(View view) {
        this.mIsEditMode = !this.mIsEditMode;
        updateEditModeVisibility();
        EditModeListener editModeListener = this.mEditModeListener;
        if (editModeListener != null) {
            ((a) editModeListener).a(this.mIsEditMode);
        }
    }

    private void updateEditModeVisibility() {
        int i2;
        ViewUtils.setVisibleOrGone(this.mGuideTextView, this.mIsEditMode);
        ViewUtils.setVisibleOrGone(this.mEditButton, !this.mIsEditMode);
        if (!this.mIsEditMode) {
            TextView textView = this.mEditButton;
            if (this.mView.hasOnlyMe()) {
                i2 = R.string.select_top5;
            } else {
                i2 = R.string.edit_top5;
            }
            ViewUtils.setText(textView, i2);
        }
    }

    public void init() {
        TextView textView = (TextView) this.mTop5Layout.findViewById(R.id.edit_top5_button);
        this.mEditButton = textView;
        SeApiCompat.setButtonShapeEnabled(textView);
        this.mEditButton.setOnClickListener(new C0419b(25, this));
        this.mGuideTextView = this.mTop5Layout.findViewById(R.id.edit_top5_guide_text);
    }

    public boolean isEditMode() {
        return this.mIsEditMode;
    }

    public boolean onBackPressed() {
        if (!this.mIsEditMode) {
            return false;
        }
        this.mIsEditMode = false;
        updateEditModeVisibility();
        EditModeListener editModeListener = this.mEditModeListener;
        if (editModeListener == null) {
            return true;
        }
        ((a) editModeListener).a(this.mIsEditMode);
        return true;
    }

    public void updateTop5Visibility(boolean z) {
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            ViewUtils.setVisibleOrGone(this.mTop5Layout, z);
        } else {
            ViewUtils.setVisibleOrInvisible(this.mTop5Layout, z);
        }
        if (this.mPickerMode || !z) {
            ViewUtils.setVisibility(this.mEditButton, 8);
        } else {
            updateEditModeVisibility();
        }
    }
}
