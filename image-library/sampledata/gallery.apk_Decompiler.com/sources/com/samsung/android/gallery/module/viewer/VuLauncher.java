package com.samsung.android.gallery.module.viewer;

import ba.C0583b;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sum.core.message.Message;
import java.util.ArrayList;
import java.util.function.Consumer;
import n0.C0235b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VuLauncher {
    Blackboard bb;
    boolean mPrepareThumbnail;
    boolean mPublishData;
    boolean mRequestBitmap = true;
    boolean mRequestBitmapUrgent = false;
    final ArrayList<String> mTrueArgs = new ArrayList<>();
    Consumer<UriBuilder> mUriUpdater;

    public VuLauncher(Blackboard blackboard) {
        this.bb = blackboard;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launch$0(MediaItem mediaItem, String str) {
        Log.d("VuLauncher", "+preparedThumbnail : ", ThumbnailLoader.getInstance().loadThumbnailSync(mediaItem, ThumbKind.MEDIUM_KIND));
        this.bb.post("command://MoveURL", str);
    }

    private void postUpdateUri(UriBuilder uriBuilder) {
        Consumer<UriBuilder> consumer = this.mUriUpdater;
        if (consumer != null) {
            consumer.accept(uriBuilder);
        }
        if (!this.mTrueArgs.isEmpty()) {
            this.mTrueArgs.forEach(new C0583b(uriBuilder, 1));
        }
    }

    public VuLauncher addTrueArgument(boolean z, String str) {
        if (z) {
            this.mTrueArgs.add(str);
        }
        return this;
    }

    public VuLauncher disableBitmapRequest() {
        this.mRequestBitmap = false;
        return this;
    }

    public VuLauncher disableTimeline() {
        addTrueArgument("disableTimeline");
        addTrueArgument("disableRealRatio");
        addTrueArgument("presentation_view");
        return this;
    }

    public VuLauncher forceDisableTransition() {
        addTrueArgument("forceDisableReturnTransition");
        return this;
    }

    public VuLauncher forcePlayVideoInGallery(boolean z) {
        if (z) {
            addTrueArgument("forcePlayVideoInGallery");
        }
        return this;
    }

    public void launch(String str, int i2, MediaItem... mediaItemArr) {
        launch(str, i2, true, mediaItemArr);
    }

    public void launchSingle(MediaItem mediaItem) {
        this.mPublishData = false;
        launch("location://file/" + mediaItem.getMediaId(), 0, mediaItem);
    }

    public VuLauncher prepareThumbnail() {
        this.mPrepareThumbnail = true;
        return this;
    }

    public VuLauncher publishData() {
        this.mPublishData = true;
        return this;
    }

    public VuLauncher requestBitmapUrgent() {
        this.mRequestBitmapUrgent = true;
        return this;
    }

    public VuLauncher setIsTemp() {
        addTrueArgument("is_temp");
        return this;
    }

    public VuLauncher setUriUpdater(Consumer<UriBuilder> consumer) {
        this.mUriUpdater = consumer;
        return this;
    }

    public VuLauncher addTrueArgument(String str) {
        this.mTrueArgs.add(str);
        return this;
    }

    public void launch(String str, int i2, boolean z, MediaItem... mediaItemArr) {
        if (mediaItemArr.length > 1 && i2 >= mediaItemArr.length) {
            Log.e((CharSequence) "VuLauncher", "wrong position", Logger.v(Integer.valueOf(i2), Integer.valueOf(mediaItemArr.length)));
        }
        MediaItem mediaItem = mediaItemArr.length == 0 ? null : mediaItemArr.length == 1 ? mediaItemArr[0] : mediaItemArr[i2];
        if (this.mRequestBitmap && mediaItem != null) {
            BlackboardUtils.requestViewerBitmap(this.bb, mediaItem, this.mRequestBitmapUrgent, z);
        }
        if (this.mPublishData) {
            this.bb.publish(DataKey.DATA(str), mediaItemArr);
        }
        UriBuilder appendUri = new UriBuilder(str).appendUri("viewer");
        if (mediaItem != null) {
            appendUri.appendArg("media_item", BlackboardUtils.publishMediaItem(this.bb, mediaItem));
        }
        appendUri.appendArg(Message.KEY_POSITION, i2);
        postUpdateUri(appendUri);
        String build = appendUri.build();
        Log.d("VuLauncher", "launch", (this.mRequestBitmap ? "B" : "b").concat(this.mPublishData ? "D" : "d"), build);
        if (!this.mPrepareThumbnail || mediaItem == null) {
            this.bb.post("command://MoveURL", build);
        } else {
            ThreadUtil.runOnBgThread(new C0235b(this, mediaItem, build, 29));
        }
    }
}
