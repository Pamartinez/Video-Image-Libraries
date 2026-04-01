package com.samsung.android.gallery.app.ui.dialog.tag;

import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.dialog.abstraction.MvpDialogPresenter;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AddTagDialogPresenter extends MvpDialogPresenter<IAddTagDialogView> {
    public AddTagDialogPresenter(IAddTagDialogView iAddTagDialogView) {
        super(iAddTagDialogView);
    }

    private void handleAddTag(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mBlackboard.post("data://user/dialog/AddTag", str);
            dismissDialog();
        }
    }

    public void onItemClicked(String str) {
        ((IAddTagDialogView) this.mView).updateTagName(str);
    }

    public void onNegativeClicked() {
        super.onNegativeClicked();
        postAnalyticsLog(AnalyticsEventId.EVENT_ADD_TAG_CANCEL_CLICKED);
    }

    public void onPositiveClicked() {
        handleAddTag(((IAddTagDialogView) this.mView).getInputText());
        postAnalyticsLog(AnalyticsEventId.EVENT_ADD_TAG_DONE_CLICKED);
    }
}
