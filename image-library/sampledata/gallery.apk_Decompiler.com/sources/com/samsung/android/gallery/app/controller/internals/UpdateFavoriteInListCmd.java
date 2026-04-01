package com.samsung.android.gallery.app.controller.internals;

import O3.o;
import O3.z;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import com.samsung.android.gallery.app.controller.BaseSelectedCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.tag.TagEditor;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.InstantSubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.dialog.ProgressCircleBuilder;
import com.sec.android.gallery3d.R;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UpdateFavoriteInListCmd extends BaseSelectedCommand {
    private InstantSubscriberListener mDestroySubscriberListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class UpdateFavoriteTask implements Runnable {
        private int mAddedImageCount = 0;
        private int mAddedVideoCount = 0;
        private final Consumer<Boolean> mCallback;
        private final WeakReference<Context> mContext;
        private boolean mInterrupted = false;
        private final MediaItem[] mItems;
        private Dialog mProgressDialog;
        private boolean mResult = false;
        private final boolean mSetFavorite;
        private int mTotalImageCount = 0;
        private int mTotalVideoCount = 0;

        public UpdateFavoriteTask(Context context, boolean z, MediaItem[] mediaItemArr, Consumer<Boolean> consumer) {
            this.mContext = new WeakReference<>(context);
            this.mSetFavorite = z;
            this.mItems = mediaItemArr;
            this.mCallback = consumer;
            this.mProgressDialog = new ProgressCircleBuilder(context).setProgressMessage(context.getResources().getString(R.string.processing)).create();
        }

        private void dismissDialog() {
            ThreadUtil.postOnUiThreadDelayed(new k(this, 0), 500);
        }

        /* access modifiers changed from: private */
        public boolean filter(MediaItem mediaItem) {
            if (mediaItem == null) {
                return false;
            }
            if (this.mSetFavorite) {
                if (mediaItem.isVideo()) {
                    if (!mediaItem.isFavourite()) {
                        this.mAddedVideoCount++;
                    }
                    this.mTotalVideoCount++;
                } else {
                    if (!mediaItem.isFavourite()) {
                        this.mAddedImageCount++;
                    }
                    this.mTotalImageCount++;
                }
            }
            if (!this.mSetFavorite || !mediaItem.isFavourite()) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$dismissDialog$0() {
            try {
                Dialog dialog = this.mProgressDialog;
                if (dialog != null && dialog.isShowing()) {
                    this.mProgressDialog.dismiss();
                }
                this.mProgressDialog = null;
                showResultToast();
            } catch (Exception unused) {
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$runInternal$1(Context context, MediaItem mediaItem) {
            if (!this.mInterrupted) {
                SamsungCloudCompat.setFavorite(context, mediaItem.getCloudServerId(), mediaItem.getCloudServerPath(), this.mSetFavorite);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$runInternal$2(Boolean bool, List list) {
            if (bool.booleanValue()) {
                list.forEach(new l(this, AppResources.getAppContext()));
            } else if (list.size() > 1000) {
                for (int i2 = 0; i2 <= list.size() / 1000 && !this.mInterrupted; i2++) {
                    int i7 = i2 * 1000;
                    setFavorite(list.subList(i7, Math.min(i7 + 1000, list.size())));
                }
            } else {
                setFavorite(list);
            }
            this.mResult = true;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$showDialog$3() {
            this.mProgressDialog.show();
        }

        private void runInternal() {
            ((Map) Arrays.stream(this.mItems).filter(new i(this)).collect(Collectors.groupingBy(new o(3)))).forEach(new j(this));
        }

        private void setFavorite(List<MediaItem> list) {
            if (this.mSetFavorite) {
                new TagEditor().addFavoriteInBulk(list);
            } else {
                new TagEditor().removeFavoriteInBulk(list);
            }
        }

        private void showDialog() {
            ThreadUtil.postOnUiThread(new k(this, 1));
        }

        private void showResultToast() {
            String str;
            String str2;
            int i2;
            String str3;
            int i7;
            Context context = this.mContext.get();
            if (context != null && this.mSetFavorite) {
                int i8 = this.mTotalVideoCount;
                if (i8 == 0) {
                    if (this.mResult) {
                        Resources resources = context.getResources();
                        int i10 = this.mAddedImageCount;
                        str3 = resources.getQuantityString(R.plurals.n_images_added_to_favorites, i10, new Object[]{Integer.valueOf(i10)});
                    } else {
                        if (this.mTotalImageCount == 1) {
                            i7 = R.string.the_selected_image_is_already_in_favorites;
                        } else {
                            i7 = R.string.all_the_selected_images_are_already_in_favorites;
                        }
                        str3 = context.getString(i7);
                    }
                    Utils.showToast(context, str3);
                } else if (this.mTotalImageCount == 0) {
                    if (this.mResult) {
                        Resources resources2 = context.getResources();
                        int i11 = this.mAddedVideoCount;
                        str2 = resources2.getQuantityString(R.plurals.n_videos_added_to_favorites, i11, new Object[]{Integer.valueOf(i11)});
                    } else {
                        if (i8 == 1) {
                            i2 = R.string.the_selected_video_is_already_in_favorites;
                        } else {
                            i2 = R.string.all_the_selected_videos_are_already_in_favorites;
                        }
                        str2 = context.getString(i2);
                    }
                    Utils.showToast(context, str2);
                } else {
                    if (this.mResult) {
                        Resources resources3 = context.getResources();
                        int i12 = this.mAddedImageCount;
                        int i13 = this.mAddedVideoCount;
                        str = resources3.getQuantityString(R.plurals.n_items_added_to_favorites, i12 + i13, new Object[]{Integer.valueOf(i12 + i13)});
                    } else {
                        str = context.getString(R.string.all_the_selected_items_are_already_in_favorites);
                    }
                    Utils.showToast(context, str);
                }
            }
        }

        public void run() {
            showDialog();
            runInternal();
            dismissDialog();
            this.mCallback.accept(Boolean.valueOf(this.mResult));
        }

        public void setInterrupted() {
            this.mInterrupted = true;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(InstantSubscriberListener instantSubscriberListener) {
        getBlackboard().unsubscribe("lifecycle://on_activity_destroy", instantSubscriberListener);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$1(Boolean bool) {
        if (bool.booleanValue()) {
            publishNotifyChanged(getBlackboard());
        }
        getBlackboard().postEvent(EventMessage.obtain(1003));
        Optional.ofNullable(this.mDestroySubscriberListener).ifPresent(new z(this, 1));
    }

    private void publishNotifyChanged(Blackboard blackboard) {
        BlackboardUtils.removeDataChangeObservingDelay(blackboard);
        BlackboardUtils.publishDataChangedToOtherActivities(blackboard, true);
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] selectedItems = eventContext.getSelectedItems();
        if (selectedItems == null || selectedItems.length == 0) {
            Log.e(this.TAG, "invalid items");
            return;
        }
        UpdateFavoriteTask updateFavoriteTask = new UpdateFavoriteTask(getContext(), objArr[0].booleanValue(), selectedItems, new z(this, 0));
        this.mDestroySubscriberListener = new h(updateFavoriteTask);
        getBlackboard().subscribe("lifecycle://on_activity_destroy", this.mDestroySubscriberListener);
        SimpleThreadPool.getInstance().execute(updateFavoriteTask);
    }
}
