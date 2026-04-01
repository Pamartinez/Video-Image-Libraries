package com.samsung.android.gallery.app.ui.viewer2.selection;

import V3.b;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemParams;
import com.samsung.android.gallery.widget.fastoption2.FastOptionView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SelectionFastOptionHandler {
    private FastOptionView mFastOptionView;

    public SelectionFastOptionHandler(FastOptionView fastOptionView, String str) {
        setFastOptionView(fastOptionView);
        initializeMenuItems(str);
        ViewUtils.setWidth(this.mFastOptionView, -2);
        FastOptionView fastOptionView2 = this.mFastOptionView;
        fastOptionView2.setElevation((float) fastOptionView2.getResources().getDimensionPixelSize(R.dimen.viewer_floating_fast_option_elevation));
        FastOptionView fastOptionView3 = this.mFastOptionView;
        ViewMarginUtils.setTopMargin(fastOptionView3, fastOptionView3.getResources().getDimensionPixelOffset(R.dimen.viewer_floating_fast_option_top_margin));
        FastOptionView fastOptionView4 = this.mFastOptionView;
        ViewMarginUtils.setBottomMargin(fastOptionView4, fastOptionView4.getResources().getDimensionPixelOffset(R.dimen.viewer_floating_fast_option_bottom_margin));
        FastOptionView fastOptionView5 = this.mFastOptionView;
        Objects.requireNonNull(fastOptionView5);
        ViewUtils.post(fastOptionView5, new b(2, fastOptionView5));
    }

    private void initializeMenuItems(String str) {
        if (LocationKey.isPrivateAlbum(str)) {
            this.mFastOptionView.addItem(FastOptionItemParams.builder().setTitleRes(R.string.share_short).setMenuId(1).setFastOptionMenu(true), R.drawable.gallery_ic_detail_share);
            return;
        }
        this.mFastOptionView.addItem(FastOptionItemParams.builder().setTitleRes(R.string.set_as_best_shot).setMenuId(0).setFastOptionMenu(true), R.drawable.gallery_ic_detail_bestshot);
        this.mFastOptionView.addItem(FastOptionItemParams.builder().setTitleRes(R.string.share_short).setMenuId(1).setFastOptionMenu(true), R.drawable.gallery_ic_detail_share);
        this.mFastOptionView.addItem(FastOptionItemParams.builder().setTitleRes(R.string.save).setMenuId(2).setFastOptionMenu(true), R.drawable.gallery_ic_detail_save);
        this.mFastOptionView.addItem(FastOptionItemParams.builder().setTitleRes(R.string.delete).setMenuId(3).setFastOptionMenu(true), R.drawable.gallery_ic_detail_delete);
    }

    private void setFastOptionView(FastOptionView fastOptionView) {
        this.mFastOptionView = fastOptionView;
    }

    public void enableBestItem(boolean z) {
        this.mFastOptionView.enableView(0, z);
    }

    public void enableSave(boolean z) {
        this.mFastOptionView.enableView(2, z);
    }

    public void updateDim(boolean z, int i2) {
        boolean z3;
        boolean z7;
        boolean z9;
        FastOptionView fastOptionView = this.mFastOptionView;
        boolean z10 = false;
        if (i2 != 1 || z) {
            z3 = true;
        } else {
            z3 = false;
        }
        fastOptionView.updateFastOptionItemDisabledDim(0, z3);
        FastOptionView fastOptionView2 = this.mFastOptionView;
        if (i2 == 0) {
            z7 = true;
        } else {
            z7 = false;
        }
        fastOptionView2.updateFastOptionItemDisabledDim(1, z7);
        FastOptionView fastOptionView3 = this.mFastOptionView;
        if (i2 == 0) {
            z9 = true;
        } else {
            z9 = false;
        }
        fastOptionView3.updateFastOptionItemDisabledDim(2, z9);
        FastOptionView fastOptionView4 = this.mFastOptionView;
        if (i2 == 0) {
            z10 = true;
        }
        fastOptionView4.updateFastOptionItemDisabledDim(3, z10);
    }
}
