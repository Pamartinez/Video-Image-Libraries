package com.samsung.android.gallery.app.ui.dialog.creature.merge;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.creature.MergeCreatureChoiceCmd;
import com.samsung.android.gallery.app.ui.dialog.creature.merge.IMergeCreatureDialogView;
import com.samsung.android.gallery.app.ui.dialog.creature.picker.CreaturePickerDialogPresenter;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import o6.p;
import q6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MergeCreatureDialogPresenter<V extends IMergeCreatureDialogView> extends CreaturePickerDialogPresenter<V> {
    private MediaItem mHeaderItem = ((MediaItem) this.mBlackboard.pop("data:///CreatureHeaderItemForEdit"));
    private MediaItem mTargetItem;

    public MergeCreatureDialogPresenter(V v) {
        super(v);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadHeaderThumb$1(Bitmap bitmap) {
        ((IMergeCreatureDialogView) this.mView).bindHeaderImage(bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadHeaderThumb$2(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThreadUtil.postOnUiThread(new e(10, this, bitmap));
    }

    private void showChooseDialog() {
        new MergeCreatureChoiceCmd().execute(this, this.mHeaderItem);
    }

    public MediaItem getHeaderItem() {
        return this.mHeaderItem;
    }

    public String getHeaderName() {
        return CreatureData.of(this.mHeaderItem).creatureName;
    }

    public void loadHeaderThumb() {
        MediaItem cloneCreatureItem = MediaItemBuilder.cloneCreatureItem(this.mHeaderItem);
        if (cloneCreatureItem != null) {
            ThumbnailLoader.getInstance().loadThumbnail(cloneCreatureItem, ThumbKind.MEDIUM_KIND, new B8.e(cloneCreatureItem, 5), new p(12, this));
        }
    }

    public void onDismissDialog(Object... objArr) {
        MediaItem mediaItem;
        String str = objArr[0];
        if (!TextUtils.isEmpty(str)) {
            if (str.equals(this.mHeaderItem.getSubCategory())) {
                mediaItem = this.mHeaderItem;
            } else {
                mediaItem = this.mTargetItem;
            }
            String appendArgs = ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(getLocationKey(), "title", CreatureData.of(mediaItem).creatureName), "sub", str);
            Blackboard.postBroadcastEventGlobal(EventMessage.obtain(3001, 1, ArgumentsUtil.removeArgs(appendArgs).hashCode(), appendArgs));
            BlackboardUtils.forceRefreshPicturesData(this.mBlackboard, true);
        }
        dismissDialog();
    }

    public void onItemClicked(MediaItem mediaItem) {
        postAnalyticsLog(AnalyticsEventId.EVENT_SEARCH_FACE_TAG_MERGE_PEOPLE_SELECT_ITEM);
        this.mTargetItem = mediaItem;
        this.mBlackboard.publish("data:///MergeCreatureTarget", mediaItem);
        showChooseDialog();
    }
}
