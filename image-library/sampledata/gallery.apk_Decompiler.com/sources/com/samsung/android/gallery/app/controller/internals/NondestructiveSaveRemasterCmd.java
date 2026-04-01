package com.samsung.android.gallery.app.controller.internals;

import A.a;
import A4.S;
import H3.n;
import I3.c;
import O3.r;
import android.app.Activity;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.MimeType;
import com.samsung.android.gallery.module.abstraction.RemasterType;
import com.samsung.android.gallery.module.c2pa.C2paWrapper;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.fileio.redact.FileRedactorBuilder;
import com.samsung.android.gallery.module.nondestruction.NondestructiveEditor;
import com.samsung.android.gallery.module.remaster.RemasterSaveController;
import com.samsung.android.gallery.module.service.message.RemasterSavingMessage;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.utils.FileOpUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.FileType;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SefData;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NondestructiveSaveRemasterCmd extends BaseCommand {
    private Activity mActivity;
    private long mBackupDateModified;
    private long mFileId;
    private boolean mIsFromSuggestion;
    private boolean mIsGif;
    private boolean mIsGroupShot;
    private NondestructiveEditor mNondestructiveEditor;
    private int mOriginSefFileType;
    private MediaItem mOriginalItem;
    private MediaItem mRemasteredItem;
    private RemasterSaveController mSaveController;
    private String mShotMode;

    private void clearCache() {
        if (this.mOriginalItem != null) {
            ThumbnailLoader.getInstance().removeCache(this.mOriginalItem);
        } else {
            Log.e(this.TAG, "failed to clear cache: original item is null");
        }
    }

    private SefData getBackupSefData(String str) {
        if (this.mIsGroupShot) {
            return new SefData().read(str);
        }
        return null;
    }

    private MediaItem getOriginalMediaItem(MediaItem mediaItem, String str) {
        MediaItem clone = mediaItem.clone();
        clone.setPath(str);
        return clone;
    }

    private String handleFileData(String str, String str2, Uri uri) {
        String replaceExtension = FileUtils.replaceExtension(str, FileUtils.getExtension(str2));
        if (MimeType.getMimeType(str) != MimeType.getMimeType(str2)) {
            if (FileOpUtils.updateMimeType(getContext(), uri, FileUtils.getNameFromPath(replaceExtension), replaceExtension)) {
                str = replaceExtension;
            }
        }
        FileUtils.setDateModified(str, this.mBackupDateModified);
        return str;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveAndScan$2(String str, String str2, Uri uri) {
        verifyFileType(str, "scan");
        clearCache();
        preloadThumbnail(str, this.mRemasteredItem);
        postEvent(uri, str2);
        this.mSaveController.onPostSave(getHandler().getMediaData());
        showResultText(str, uri);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveRemasteredImage$1(String str, String str2, Uri uri, Boolean bool) {
        moveAndScan(bool.booleanValue(), str, str2, uri);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$onExecute$0(String str, String str2, Uri uri, ThreadPool.JobContext jobContext) {
        nondestructiveSave(str, str2, uri);
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$preloadThumbnail$3(MediaItem mediaItem, long j2, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        String str = this.TAG;
        Log.d(str, "loadThumbnail " + MediaItemUtil.getDebugLog(mediaItem) + " " + Logger.toSimpleString(bitmap) + " + " + (System.currentTimeMillis() - j2));
    }

    private void moveAndScan(boolean z, String str, String str2, Uri uri) {
        Log.d(this.TAG, "c2pa applied", Boolean.valueOf(z));
        if (FileUtils.move(str2, str, true)) {
            verifyFileType(str, "move");
            String handleFileData = handleFileData(str, str2, uri);
            verifyFileType(handleFileData, "ext");
            MediaScannerConnection.scanFile(getApplicationContext(), new String[]{handleFileData}, (String[]) null, new n(2, this, handleFileData));
            new CleanCmhMediaContentInfoCmd().execute(getHandler(), Long.valueOf(this.mFileId));
            return;
        }
        showToast(RemasterSavingMessage.getFailTextId());
    }

    private void moveRemasteredImage(String str, String str2, Uri uri) {
        long length = new File(str2).length();
        long length2 = new File(str).length();
        if (length <= 0 || length2 <= 0) {
            Log.e((CharSequence) this.TAG, "failed to NDS -> wrong size", a.f("original=", length2), a.f("remastered=", length));
            new InternalException("failed to NDS -> wrong size").post();
            return;
        }
        verifyFileType(str2, "init");
        transferSef(str, str2);
        verifyFileType(str2, "sef");
        FileRedactorBuilder.create(str2, str).setUseFileDescriptor(true).setCallback(new S(this, str, str2, uri, 3)).setManifest(C2paWrapper.Manifest.actionEdit).build().run();
    }

    private void nondestructiveSave(String str, String str2, Uri uri) {
        if (!TextUtils.isEmpty(this.mNondestructiveEditor.copyToHiddenDir(getContext(), str, str2))) {
            moveRemasteredImage(str, str2, uri);
        }
    }

    private void postEvent(Uri uri, String str) {
        getBlackboard().post("command://event/DataDirty", (Object) null);
        getBlackboard().postEvent(EventMessage.obtain(3056, this.mOriginalItem.getThumbCacheKey()));
        getBlackboard().postEvent(EventMessage.obtain(3040, str));
        getBlackboard().postEvent(EventMessage.obtain(3044, 1, str));
        if (MediaItemUtil.isRemasterAutosave(this.mRemasteredItem)) {
            MediaItemUtil.setRemasterAutosave(this.mRemasteredItem, false);
            getBlackboard().post("data://bixby_command_done", new Object[]{"no_error", null, str, uri});
        }
    }

    private void preloadThumbnail(String str, MediaItem mediaItem) {
        long currentTimeMillis = System.currentTimeMillis();
        MediaItem clone = mediaItem.clone();
        clone.setPath(str);
        clone.setMimeType(MimeType.getMimeType(str).mimeType);
        clone.setFileSize(FileUtils.length(str));
        ThumbnailLoader.getInstance().loadThumbnail(clone, ThumbKind.MEDIUM_KIND, new r(this, clone, currentTimeMillis, 0));
    }

    private void showResultText(String str, Uri uri) {
        if (TextUtils.isEmpty(str)) {
            showToast(RemasterSavingMessage.getErrorTextId());
        } else if (this.mIsFromSuggestion) {
            new ShowSnackBarForViewerCmd().execute((EventContext) this.mActivity, uri, RemasterSavingMessage.getResultTextFromSuggestion(getContext(), str, this.mOriginSefFileType), Boolean.TRUE);
        } else {
            String resultText = RemasterSavingMessage.getResultText(getContext(), this.mOriginSefFileType, this.mShotMode);
            if (!resultText.isEmpty()) {
                showToast(resultText);
            }
        }
    }

    private void transferSef(String str, String str2) {
        SefData backupSefData = getBackupSefData(str);
        if (backupSefData != null) {
            backupSefData.write(str2);
        }
    }

    private String verifyFile(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            File file = new File(str);
            StringBuilder sb2 = new StringBuilder("<");
            sb2.append(str2);
            sb2.append("> ");
            if (!file.exists()) {
                sb2.append("not exist");
            } else if (file.length() < 1) {
                sb2.append("wrong size. ");
                sb2.append(file.length());
            } else {
                byte[] readBytes = FileUtils.readBytes(str, 0, 32);
                String mimeType = FileType.getMimeType(readBytes);
                boolean equals = "image/jpeg".equals(mimeType);
                boolean equals2 = "image/png".equals(mimeType);
                boolean equals3 = "image/heic".equals(mimeType);
                if (equals || equals2 || equals3) {
                    return null;
                }
                sb2.append(mimeType);
                sb2.append(ArcCommonLog.TAG_COMMA);
                sb2.append(StringCompat.valueOf(readBytes, 32));
            }
            return sb2.toString();
        } catch (Error | Exception unused) {
            return null;
        }
    }

    private void verifyFileType(String str, String str2) {
        String verifyFile;
        if (!this.mIsGif && (verifyFile = verifyFile(str, str2)) != null) {
            Log.e(this.TAG, verifyFile);
            new InternalException(this.TAG).post();
        }
    }

    public String getAnalyticsDetail() {
        return RemasterType.getRemasterTypeEventDetail(MediaItemSuggest.getRevitalizedResultType(this.mRemasteredItem), getHandler().getLocationKey(), true);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_VIEWER_SAVE_CLICK.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mActivity = eventContext.getActivity();
        boolean z = false;
        MediaItem mediaItem = objArr[0];
        this.mRemasteredItem = mediaItem;
        String originPath = MediaItemSuggest.getOriginPath(mediaItem);
        String path = this.mRemasteredItem.getPath();
        MediaItem originalMediaItem = getOriginalMediaItem(this.mRemasteredItem, originPath);
        this.mOriginalItem = originalMediaItem;
        Uri writableUri = ContentUri.getWritableUri(originalMediaItem);
        this.mIsGif = this.mRemasteredItem.isGif();
        if (this.mRemasteredItem.getGroupType() > 0) {
            z = true;
        }
        this.mIsGroupShot = z;
        this.mFileId = this.mRemasteredItem.getFileId();
        this.mBackupDateModified = FileUtils.getDateModified(originPath);
        this.mOriginSefFileType = this.mRemasteredItem.getSefFileType();
        this.mShotMode = this.mRemasteredItem.getShotMode();
        this.mIsFromSuggestion = LocationKey.isRemasterPictures(eventContext.getLocationKey());
        this.mNondestructiveEditor = new NondestructiveEditor();
        this.mSaveController = new RemasterSaveController(getActivity(), getBlackboard(), this.mIsFromSuggestion);
        ThreadPool.getInstance().submit(new c(this, originPath, path, writableUri, 2));
    }
}
