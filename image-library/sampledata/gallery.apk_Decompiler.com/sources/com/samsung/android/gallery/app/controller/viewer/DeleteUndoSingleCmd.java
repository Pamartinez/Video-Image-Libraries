package com.samsung.android.gallery.app.controller.viewer;

import Ba.g;
import D0.f;
import U5.c;
import W3.C0411a;
import android.app.Activity;
import android.database.Cursor;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.DeleteBaseCmd;
import com.samsung.android.gallery.app.controller.internals.ShowLowStorageCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.service.message.DeleteMsgMgr;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.trash.abstraction.TrashLogType;
import com.samsung.android.gallery.module.trash.abstraction.TrashRestoreItem;
import com.samsung.android.gallery.module.trash.factory.TrashProviderFactory;
import com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper;
import com.samsung.android.gallery.module.trash.helper.TrashHelperFactory;
import com.samsung.android.gallery.module.trash.helper.TrashProgressListener;
import com.samsung.android.gallery.module.trash.helper.TrashRestoreHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import z2.k;
import z2.m;
import z2.n;
import z2.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteUndoSingleCmd extends DeleteBaseCmd {
    private static r sSnackBar;
    /* access modifiers changed from: private */
    public int mCount;
    private String mLocationKey = null;
    public MediaData mMediaData;
    /* access modifiers changed from: private */
    public MediaItem mMediaItem = null;
    /* access modifiers changed from: private */
    public int mPosition;

    /* access modifiers changed from: private */
    public void deleteWithCmh() {
        if (LocationKey.isStoryPictures(this.mLocationKey)) {
            SimpleThreadPool.getInstance().execute(new C0411a(this, 0));
        }
    }

    /* access modifiers changed from: private */
    public void finishDeletion(TrashDeleteHelper trashDeleteHelper) {
        boolean isSucceed = trashDeleteHelper.isSucceed();
        if (SdkConfig.atLeast(SdkConfig.GED.Q) && isSucceed) {
            Log.d(this.TAG, "finishDeletion > force refresh viewer");
            getBlackboard().publish("viewer_force_refresh", Boolean.TRUE);
        } else if (isSucceed || trashDeleteHelper.isAbnormalRecordDeleteRequested()) {
            BlackboardUtils.forceRefreshPicturesData(getBlackboard(), false);
        }
        if (isSucceed) {
            MediaData mediaData = this.mMediaData;
            if (mediaData != null) {
                mediaData.register(new MediaData.SimpleDataChangeListener() {
                    boolean success;

                    /* access modifiers changed from: private */
                    public /* synthetic */ void lambda$onDataChanged$0() {
                        DeleteUndoSingleCmd deleteUndoSingleCmd = DeleteUndoSingleCmd.this;
                        deleteUndoSingleCmd.showSnackBar(deleteUndoSingleCmd.getHandler(), DeleteUndoSingleCmd.this.mMediaData);
                    }

                    public boolean isInstant() {
                        return this.success;
                    }

                    public void onDataChanged() {
                        boolean z;
                        if (DeleteUndoSingleCmd.this.mCount - 1 == DeleteUndoSingleCmd.this.mMediaData.getCount()) {
                            z = true;
                        } else {
                            z = false;
                        }
                        this.success = z;
                        if (!z || DeleteUndoSingleCmd.this.mCount <= 1) {
                            Log.d(DeleteUndoSingleCmd.this.TAG, "fail send showSnackBar : " + DeleteUndoSingleCmd.this.mMediaData.getCount());
                            return;
                        }
                        Log.d(DeleteUndoSingleCmd.this.TAG, "send showSnackBar : " + DeleteUndoSingleCmd.this.mMediaData.getCount());
                        ThreadUtil.postOnUiThreadDelayed(new b(this, 0), 100);
                    }
                });
            } else {
                Log.e(this.TAG, "NO MEDIA DATA");
            }
        } else {
            showDeleteFailResult(trashDeleteHelper);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$deleteWithCmh$0() {
        StoryHelper.removeFromStory(getContext(), this.mMediaItem, getHandler().getSelectedItems());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showSnackBar$1(final MediaData mediaData) {
        Cursor trashCursor = TrashProviderFactory.getInstance(getContext()).getTrashCursor(this.mMediaItem.getFileId());
        if (trashCursor != null) {
            try {
                if (trashCursor.moveToFirst()) {
                    String str = this.TAG;
                    Log.d(str, "start UNDO : " + mediaData.getCount());
                    getBlackboard().post("command:///RefreshWithoutDelay", (Object) null);
                    TrashRestoreItem trashRestoreItem = new TrashRestoreItem(trashCursor);
                    TrashRestoreHelper restoreHelper = TrashHelperFactory.getRestoreHelper(getContext(), false);
                    restoreHelper.restoreItem(trashRestoreItem);
                    restoreHelper.done();
                    restoreHelper.dump(TrashLogType.RESTORE_SINGLE, this.mLocationKey);
                    mediaData.register(new MediaData.SimpleDataChangeListener() {
                        boolean success;

                        /* access modifiers changed from: private */
                        public /* synthetic */ void lambda$onDataChanged$0() {
                            DeleteUndoSingleCmd.this.getBlackboard().post("command://ViewerMoveTo", Integer.valueOf(DeleteUndoSingleCmd.this.mPosition));
                        }

                        public boolean isInstant() {
                            return this.success;
                        }

                        public void onDataChanged() {
                            boolean z;
                            if (DeleteUndoSingleCmd.this.mCount == mediaData.getCount()) {
                                z = true;
                            } else {
                                z = false;
                            }
                            this.success = z;
                            if (z) {
                                String access$300 = DeleteUndoSingleCmd.this.TAG;
                                Log.d(access$300, "send move prev : " + mediaData.getCount());
                                ThreadUtil.postOnUiThreadDelayed(new b(this, 1), 100);
                                return;
                            }
                            String access$400 = DeleteUndoSingleCmd.this.TAG;
                            Log.d(access$400, "fail send move prev : " + mediaData.getCount());
                        }
                    });
                    mediaData.close();
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (trashCursor != null) {
            trashCursor.close();
            return;
        }
        return;
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showSnackBar$2(MediaData mediaData, View view) {
        SimpleThreadPool.getInstance().execute(new c(10, this, mediaData));
    }

    /* access modifiers changed from: private */
    public void operateDeleteInternal() {
        MediaData open = MediaDataFactory.getInstance(getBlackboard()).open(this.mLocationKey, true);
        this.mMediaData = open;
        this.mCount = open.getCount();
        String str = this.TAG;
        Log.d(str, "operate delete internal : " + this.mCount);
        getBlackboard().postEvent(EventMessage.obtain(3015));
        setDeletedToCmh();
        try {
            final TrashDeleteHelper deleteHelper = TrashHelperFactory.getDeleteHelper(getContext());
            deleteHelper.setProgressListener(1, new TrashProgressListener() {
                public void onComplete() {
                    DeleteUndoSingleCmd.this.finishDeletion(deleteHelper);
                }

                public void onProgress(int i2) {
                }
            });
            deleteHelper.deleteItemForUndo(this.mMediaItem);
            deleteHelper.done();
            deleteHelper.dump(TrashLogType.MOVE_TO_TRASH_SINGLE, this.mLocationKey);
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "unable to delete item", (Throwable) e);
            showToast((int) R.string.delete_item_failed);
            this.mMediaData.close();
            this.mMediaData = null;
        }
    }

    private void setDeletedToCmh() {
        LocationKey.isStoryPictures(this.mLocationKey);
    }

    private void showDeleteFailResult(TrashDeleteHelper trashDeleteHelper) {
        int deleteFailedToastMessage;
        if (!trashDeleteHelper.isSucceed() && !trashDeleteHelper.isAbnormalRecordDeleted() && (deleteFailedToastMessage = DeleteMsgMgr.getDeleteFailedToastMessage(trashDeleteHelper.getImageFailedCount(), trashDeleteHelper.getVideoFailedCount())) != -1) {
            showToast(deleteFailedToastMessage);
        }
    }

    /* access modifiers changed from: private */
    public void showSnackBar(EventContext eventContext, MediaData mediaData) {
        boolean H5;
        Log.d(this.TAG, "show snack bar");
        Activity activity = eventContext.getActivity();
        r rVar = sSnackBar;
        if (rVar != null) {
            f F4 = f.F();
            k kVar = rVar.f2225x;
            synchronized (F4.e) {
                H5 = F4.H(kVar);
            }
            if (H5) {
                sSnackBar.a(3);
                sSnackBar = null;
            }
        }
        if (activity != null) {
            r j2 = r.j(activity.findViewById(16908290), -1, 0, false, getContext().getString(R.string.select_items_to_move_to_the_trash));
            j2.k("UNDO", new g(15, this, mediaData));
            AnonymousClass3 r5 = new n() {
                public void onDismissed(r rVar, int i2) {
                    if (i2 != 1) {
                        DeleteUndoSingleCmd.this.deleteWithCmh();
                        TrashDeleteHelper.deleteRelatedData(DeleteUndoSingleCmd.this.mMediaItem.getFileId());
                    }
                }
            };
            if (j2.u == null) {
                j2.u = new ArrayList();
            }
            j2.u.add(r5);
            sSnackBar = j2;
            View findViewById = j2.g.findViewById(R.id.fast_option_view);
            if (findViewById != null) {
                m mVar = j2.l;
                if (mVar != null) {
                    mVar.a();
                }
                m mVar2 = new m(j2, findViewById);
                if (ViewCompat.isAttachedToWindow(findViewById)) {
                    findViewById.getViewTreeObserver().addOnGlobalLayoutListener(mVar2);
                }
                findViewById.addOnAttachStateChangeListener(mVar2);
                j2.l = mVar2;
                sSnackBar.l();
                return;
            }
            throw new IllegalArgumentException("Unable to find anchor view with id: 2131297206");
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_FS_DELETE.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mMediaItem = objArr[0];
        this.mLocationKey = objArr[1];
        this.mPosition = objArr[2].intValue();
        if (this.mMediaItem == null) {
            Log.e(this.TAG, "unable to operate. item is null.");
        } else if (isLowStorageWithTrash()) {
            Log.e(this.TAG, "not enough storage");
            new ShowLowStorageCmd().execute(eventContext, new Object[0]);
        } else {
            operateDelete();
        }
    }

    public void operateDelete() {
        Log.d(this.TAG, "operate delete");
        SimpleThreadPool.getInstance().execute(new C0411a(this, 1));
    }
}
