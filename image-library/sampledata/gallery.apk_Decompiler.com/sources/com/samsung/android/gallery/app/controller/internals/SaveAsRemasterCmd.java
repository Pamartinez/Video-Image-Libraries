package com.samsung.android.gallery.app.controller.internals;

import A9.a;
import Ba.f;
import I5.e;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.module.abstraction.RemasterType;
import com.samsung.android.gallery.module.c2pa.C2paWrapper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.fileio.redact.FileRedactorBuilder;
import com.samsung.android.gallery.module.nondestruction.NondestructiveEditor;
import com.samsung.android.gallery.module.remaster.RemasterSaveController;
import com.samsung.android.gallery.module.service.message.RemasterSavingMessage;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveAsRemasterCmd extends BaseCommand {
    private Activity mActivity;
    private boolean mIsFromSuggestion;
    private int mOriginSefFileType;
    private long mResultType;
    private RemasterSaveController mSaveController;
    private String mShotMode;

    private void deleteRevitalizationData(MediaItem[] mediaItemArr) {
        ArrayList arrayList = new ArrayList();
        for (MediaItem fileId : mediaItemArr) {
            arrayList.add(Long.valueOf(fileId.getFileId()));
        }
        new FilesApi().deleteRevitalizationData(arrayList);
    }

    private String getTargetPath(String str, String str2) {
        return new FileNameBuilder(str2).setExtension(FileUtils.getExtension(str)).buildUnique();
    }

    private void handleSefData(MediaItem mediaItem, String str) {
        if (mediaItem.isBurstShot()) {
            removeSefInfo(str, SefInfo.BURST_SHOT_INFO.keyName);
            removeSefInfo(str, SefInfo.BURST_SHOT_BEST_PHOTO_INFO.keyName);
        } else if (mediaItem.isSingleTakenShot()) {
            removeSefInfo(str, SefInfo.SINGLE_TAKE_SHOT_INFO.keyName);
            removeSefInfo(str, SefInfo.SINGLE_TAKE_BEST_PHOTO_INFO.keyName);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$onExecute$0(MediaItem mediaItem, ThreadPool.JobContext jobContext) {
        executeInternal(getContext(), mediaItem);
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveAsFile$1(String str, long j2, Boolean bool) {
        Log.d(this.TAG, "c2pa applied", bool);
        FileUtils.setDateModified(str, j2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveAsFile$2(MediaItem mediaItem, String str, Uri uri) {
        if (uri != null) {
            deleteRevitalizationData(new MediaItem[]{mediaItem});
            this.mSaveController.onPostSave(getHandler().getMediaData());
            showResultText(str, uri);
        }
    }

    private void removeSefInfo(String str, String str2) {
        try {
            SeApiCompat.getSefFileCompat().deleteData(new SecureFile(str), str2);
        } catch (IOException unused) {
            Log.e(this.TAG, "removeSefInfo failed");
        }
    }

    private void saveAsFile(Context context, MediaItem mediaItem) {
        long dateModified;
        String originPath = MediaItemSuggest.getOriginPath(mediaItem);
        if (originPath == null) {
            String str = this.TAG;
            Log.e(str, "there wasn't original path in item = " + MediaItemUtil.getSimpleLog(mediaItem));
            return;
        }
        String path = mediaItem.getPath();
        String targetPath = getTargetPath(path, originPath);
        if (path == null) {
            String str2 = this.TAG;
            Log.e(str2, "failed to save for null path " + Logger.getEncodedString(targetPath));
        } else if (FileUtils.move(path, targetPath, true)) {
            if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
                dateModified = mediaItem.getDateTaken();
            } else {
                dateModified = FileUtils.getDateModified(originPath);
            }
            long j2 = dateModified;
            handleSefData(mediaItem, targetPath);
            new NondestructiveEditor().deleteEffectValue(targetPath);
            FileRedactorBuilder.create(targetPath, originPath).setUseFileDescriptor(false).setCallback(new f((Object) this, (Object) targetPath, j2, 4)).setManifest(C2paWrapper.Manifest.actionEdit).withMediaScan(new a(8, this, mediaItem)).build().run();
        }
    }

    private void showResultText(String str, Uri uri) {
        if (TextUtils.isEmpty(str)) {
            showToast(RemasterSavingMessage.getErrorTextId());
        } else if (this.mIsFromSuggestion) {
            new ShowSnackBarForViewerCmd().execute((EventContext) this.mActivity, uri, RemasterSavingMessage.getResultTextFromSuggestion(getContext(), str, this.mOriginSefFileType), Boolean.TRUE);
        } else {
            String copyResultText = RemasterSavingMessage.getCopyResultText(getContext(), this.mOriginSefFileType, this.mShotMode);
            if (!copyResultText.isEmpty()) {
                showToast(copyResultText);
            }
        }
    }

    public void executeInternal(Context context, MediaItem mediaItem) {
        saveAsFile(context, mediaItem);
    }

    public String getAnalyticsDetail() {
        return RemasterType.getRemasterTypeEventDetail(this.mResultType, getHandler().getLocationKey(), true);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_VIEWER_SAVE_AS_COPY_CLICK.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        this.mOriginSefFileType = mediaItem.getSefFileType();
        this.mShotMode = mediaItem.getShotMode();
        this.mIsFromSuggestion = LocationKey.isRemasterPictures(eventContext.getLocationKey());
        this.mSaveController = new RemasterSaveController(getActivity(), getBlackboard(), this.mIsFromSuggestion);
        this.mActivity = eventContext.getActivity();
        this.mResultType = MediaItemSuggest.getRevitalizedResultType(mediaItem);
        ThreadPool.getInstance().submit(new e(3, this, mediaItem));
    }
}
