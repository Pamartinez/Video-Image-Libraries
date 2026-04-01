package com.samsung.android.gallery.app.controller.internals;

import A4.C0368c;
import Ba.h;
import C3.C0391a;
import C3.C0392b;
import C3.i;
import Fa.C0558l;
import K.a;
import K3.C0404a;
import L5.b;
import N2.j;
import N3.c;
import O3.f;
import O3.m;
import O3.n;
import O3.o;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.app.AlertDialog;
import c0.C0086a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.module.data.GroupItemLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.media.MetadataTimeHelper;
import com.samsung.android.gallery.module.service.dialog.DialogHelper;
import com.samsung.android.gallery.module.service.dialog.NormalDialogHelper;
import com.samsung.android.gallery.module.utils.MediaFileScanner;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.search.IntelligentSearchIndex;
import com.samsung.android.gallery.support.search.SearchIndexListener;
import com.samsung.android.gallery.support.utils.ExifCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.sgpl.media.MediaMetadataInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FixDateTimeCmd extends BaseCommand {
    private final SubscriberListener mCancelListener = new C0391a(14, this);
    private DialogHelper mDialogHelper;
    private final SearchIndexListener mIndexListener = new a(12);
    private boolean mIsInterrupted;
    private ProgressInfo mProgressInfo;
    private final ArrayList<Long> mResultIdList = new ArrayList<>();
    private final ArrayList<String> mResultList = new ArrayList<>();
    private MediaFileScanner mScanner;
    final ArrayList<MediaItem> mSimilarShotCandidate = new ArrayList<>();
    private int mSuccessGroupItemCount = 0;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ProgressInfo {
        /* access modifiers changed from: private */
        public int mImageSuccess = 0;
        /* access modifiers changed from: private */
        public int mProgressCount = 0;
        /* access modifiers changed from: private */
        public final int mTotal;
        /* access modifiers changed from: private */
        public int mVideoSuccess = 0;

        public ProgressInfo(int i2) {
            this.mTotal = i2;
        }

        /* access modifiers changed from: private */
        public int getPercent() {
            return (int) ((((float) this.mProgressCount) / ((float) this.mTotal)) * 100.0f);
        }

        /* access modifiers changed from: private */
        public void increaseSuccessCount(boolean z) {
            if (z) {
                this.mImageSuccess++;
            } else {
                this.mVideoSuccess++;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Updater {
        boolean update(MediaItem mediaItem);
    }

    private void fixDateTime(MediaItem[] mediaItemArr, Function<String, String[]> function, int i2) {
        updateDateTime(mediaItemArr, new c(12, this, function), String.valueOf(i2));
    }

    private void fixDateTimeOver3000(MediaItem[] mediaItemArr) {
        long millis = ExifCompat.DateTimeUtc.toMillis("3000:01:01 00:00:00") / 1000;
        String str = this.TAG;
        StringBuilder j2 = j.j(millis, "fixDateTimeOver3000 {", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        j2.append(ExifCompat.DateTimeUtc.toDateTimeString(millis));
        j2.append("}");
        Log.d(str, j2.toString());
        updateDateTime(mediaItemArr, new C0404a(this, millis, 2), (String) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getGroupItems$10(String str, MediaItem mediaItem) {
        if (mediaItem == null || str.equals(mediaItem.getPath())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initResultList$15(Integer num, Integer num2) {
        Log.d(this.TAG, "onScanFinished", num, num2);
        onComplete(num2.intValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Object obj, Bundle bundle) {
        onCancel();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$16(int i2) {
        if (i2 > 0) {
            Blackboard.postBroadcastEventGlobal(EventMessage.obtain(8006));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onComplete$13(int i2) {
        showSuccessToast(i2, this.mProgressInfo.mImageSuccess, this.mProgressInfo.mVideoSuccess);
        getBlackboard().postEvent(EventMessage.obtain(1003));
        dismissProgressDialog();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$scanMedia$11(ArrayList arrayList) {
        onComplete(arrayList.size());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateDateTimeAsync$4(Consumer consumer, MediaItem mediaItem) {
        if (!isInterrupted()) {
            updateProgressMessage();
            consumer.accept(mediaItem);
            return;
        }
        throw new IllegalStateException("");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateDateTimeAsync$5(MediaItem[] mediaItemArr, Consumer consumer, String str) {
        long prepareUpdate = prepareUpdate();
        try {
            Arrays.stream(mediaItemArr).forEach(new c(11, this, consumer));
        } catch (IllegalStateException unused) {
        }
        scanResultWithLog(prepareUpdate, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateGroupItems$14(Updater updater, MediaItem mediaItem) {
        if (updater.update(mediaItem)) {
            addResult(mediaItem, true);
        }
    }

    /* access modifiers changed from: private */
    public void onItemSelected(DialogInterface dialogInterface, int i2) {
        MediaItem[] selectedItems = getHandler().getSelectedItems();
        String str = this.TAG;
        StringBuilder o2 = C0086a.o(i2, "onItemSelected {", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        o2.append(selectedItems.length);
        o2.append("}");
        Log.d(str, o2.toString());
        if (i2 == 0) {
            fixDateTime(selectedItems, new b(29), i2);
        } else if (i2 == 1) {
            fixDateTime(selectedItems, new o(0), i2);
        } else if (i2 == 2) {
            fixDateTimeOver3000(selectedItems);
        }
    }

    private long prepareUpdate() {
        long currentTimeMillis = System.currentTimeMillis();
        initResultList();
        return currentTimeMillis;
    }

    private void scanResultWithLog(long j2, String str) {
        String str2 = this.TAG;
        Log.i(str2, "update {" + getResultCountInfo() + "} +" + (System.currentTimeMillis() - j2));
        scanResult();
        if (str != null) {
            addLog(this.TAG, str);
        }
    }

    private void updateApplicableItem(MediaItem mediaItem, Function<String, String[]> function) {
        String[] apply = function.apply(mediaItem.getPath());
        if (apply != null) {
            addResult(mediaItem);
            this.mResultList.add(mediaItem.getPath());
            this.mResultIdList.add(Long.valueOf(mediaItem.getFileId()));
            updateGroupItems(mediaItem, new f(apply, 1));
        }
    }

    private void updateDateTime(MediaItem[] mediaItemArr, Consumer<MediaItem> consumer, String str) {
        showProgressDialog(getTotalCount(mediaItemArr));
        ThreadUtil.postOnUiThreadDelayed(new m(this, mediaItemArr, consumer, str, 0), 100);
    }

    /* access modifiers changed from: private */
    /* renamed from: updateDateTimeAsync */
    public void lambda$updateDateTime$3(MediaItem[] mediaItemArr, Consumer<MediaItem> consumer, String str) {
        SimpleThreadPool.getInstance().execute(new m(this, mediaItemArr, consumer, str, 1));
    }

    private void updateNormalImage(MediaItem mediaItem) {
        String dateTimeInFile;
        String path = mediaItem.getPath();
        if (path != null && (dateTimeInFile = ExifCompat.getDateTimeInFile(path)) != null) {
            FileUtils.setDateModified(path, ExifCompat.DateTimeUtc.toMillis(dateTimeInFile) - TimeUtil.getSystemTimeZoneOffset());
            addResult(mediaItem);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updateTimeOver3000 */
    public void lambda$fixDateTimeOver3000$6(long j2, MediaItem mediaItem) {
        if (mediaItem.getDateModified() > j2) {
            long dateModified = mediaItem.getDateModified();
            FileUtils.setDateModified(mediaItem.getPath(), dateModified);
            addResult(mediaItem);
            updateGroupItems(mediaItem, new n(dateModified));
        }
    }

    public final void addLog(String str, String str2) {
        DebugLogger.getEditDateAndTimeInstance().lambda$apply$0(str, " C(" + getResultCountInfo() + "), DT(" + str2 + ")");
    }

    public final void addResult(MediaItem mediaItem) {
        addResult(mediaItem, false);
    }

    public boolean changeHeifMp4Date(MediaItem mediaItem, String str) {
        try {
            String localTimeFromMetadata = MetadataTimeHelper.getLocalTimeFromMetadata(mediaItem, TimeUtil.getExifTimeInMillis(str), MetadataTimeHelper.updateVideoTimeZoneOffset(mediaItem));
            MediaMetadataInterface mediaMetadataInterface = new MediaMetadataInterface(mediaItem.getPath());
            MediaMetadataInterface.Attribute attribute = MediaMetadataInterface.Attribute.ATTR_CREATION_TIME;
            if (!TextUtils.isEmpty(localTimeFromMetadata)) {
                str = localTimeFromMetadata;
            }
            mediaMetadataInterface.setAttribute(attribute, str);
            return mediaMetadataInterface.saveAttributes();
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
            return false;
        }
    }

    public final void dismissProgressDialog() {
        DialogHelper dialogHelper = this.mDialogHelper;
        if (dialogHelper != null) {
            dialogHelper.dismissDialog();
            getBlackboard().unsubscribe("command://CancelDialog", this.mCancelListener);
            this.mDialogHelper = null;
        }
    }

    public Long getAnalyticsValue() {
        return Long.valueOf((long) getHandler().getSelectedItems().length);
    }

    public void getGroupItems(MediaItem mediaItem, Consumer<MediaItem> consumer) {
        String path = mediaItem.getPath();
        if (path == null) {
            String str = this.TAG;
            Log.e(str, "update group skip {" + mediaItem.getGroupMediaId() + "}");
            return;
        }
        ArrayList<MediaItem> groupShotList = GroupItemLoader.getGroupShotList(mediaItem);
        String str2 = this.TAG;
        Log.d(str2, "update group {" + mediaItem.getGroupTypeEnum() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + groupShotList.size() + "}");
        groupShotList.stream().filter(new C0392b(path, 9)).forEach(consumer);
    }

    public String getIndexingValue() {
        return "";
    }

    public Map<String, String> getIndexingValueMap(String str) {
        return IntelligentSearchIndex.TagType.DATE_SUGGESTION.getValueMap(str);
    }

    public String getProgressDialogTitle() {
        return getContext().getString(R.string.processing);
    }

    public int getProgressTotalCount() {
        return this.mProgressInfo.mTotal;
    }

    public String getResultCountInfo() {
        StringBuilder sb2 = new StringBuilder();
        C0086a.A(sb2, this.mResultList, "/");
        sb2.append(this.mProgressInfo.mTotal);
        return sb2.toString();
    }

    public final int getTotalCount(MediaItem[] mediaItemArr) {
        return mediaItemArr.length;
    }

    public final void initResultList() {
        this.mResultList.clear();
        this.mResultIdList.clear();
        this.mSuccessGroupItemCount = 0;
        this.mScanner = new MediaFileScanner(getApplicationContext(), new h(7, this));
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public boolean isApplicable(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.getPath() == null || mediaItem.isCloudOnly() || mediaItem.isDrm()) {
            return false;
        }
        if (mediaItem.isJpeg() || mediaItem.isPng()) {
            return true;
        }
        return false;
    }

    public boolean isHeifMp4Editable(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.getPath() == null || mediaItem.isCloudOnly() || mediaItem.isDrm()) {
            return false;
        }
        if (mediaItem.isHeif() || mediaItem.isVideo()) {
            return true;
        }
        return false;
    }

    public boolean isInterrupted() {
        return this.mIsInterrupted;
    }

    public void onCancel() {
        this.mIsInterrupted = true;
    }

    public void onComplete(int i2) {
        long j2;
        String str = this.TAG;
        StringBuilder o2 = C0086a.o(i2, "onComplete {", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        o2.append(this.mSuccessGroupItemCount);
        o2.append("}");
        Log.i(str, o2.toString());
        if (i2 > 0) {
            requestIndexingToScs();
            ThreadUtil.postOnUiThreadDelayed(new i(9), 2000);
        }
        C0368c cVar = new C0368c(this, i2 - this.mSuccessGroupItemCount, 14);
        if (i2 > 0) {
            j2 = 2500;
        } else {
            j2 = 500;
        }
        ThreadUtil.postOnUiThreadDelayed(cVar, j2);
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        new AlertDialog.Builder(getContext()).setTitle((CharSequence) "Fix date&time").setItems(new String[]{"Fix from filename", "Fix from EXIF tags", "Fix wrong file time(3000-01-01 or later)"}, new C0558l(6, this)).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) null).create().show();
    }

    public void requestIndexingToScs() {
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH) && !TextUtils.isEmpty(getIndexingValue()) && !this.mResultIdList.isEmpty()) {
            IntelligentSearchIndex.getInstance().indexing((Collection<Long>) this.mResultIdList, getIndexingValueMap(getIndexingValue()), IntelligentSearchIndex.IndexMode.APPEND, this.mIndexListener);
        }
    }

    public void scanMedia(ArrayList<String> arrayList) {
        if (arrayList.size() > 0) {
            MediaScanner.scanFiles((Collection<String>) arrayList, (MediaScannerListener) new O3.b(4, this, arrayList));
        } else {
            onComplete(0);
        }
    }

    public final void scanResult() {
        MediaFileScanner mediaFileScanner = this.mScanner;
        if (mediaFileScanner != null) {
            mediaFileScanner.finishScan();
        }
    }

    public void showCannotChangeToast() {
        int i2;
        if (!this.mIsInterrupted) {
            if (getProgressTotalCount() > 1) {
                i2 = R.string.cannot_change_with_that_formats;
            } else {
                i2 = R.string.cannot_change_with_that_format;
            }
            showToast(i2);
        }
    }

    public final void showProgressDialog(int i2) {
        this.mProgressInfo = new ProgressInfo(i2);
        NormalDialogHelper normalDialogHelper = new NormalDialogHelper();
        this.mDialogHelper = normalDialogHelper;
        normalDialogHelper.init(getBlackboard());
        this.mDialogHelper.showDialog(getProgressDialogTitle(), 0, i2, 0);
        getBlackboard().subscribeOnUi("command://CancelDialog", this.mCancelListener);
    }

    public void showSuccessToast(int i2, int i7, int i8) {
        String str;
        if (i2 > 0) {
            str = i2 + " files updated successfully";
        } else {
            str = "No files updated\nNon jpeg file or no date in exif or filename";
        }
        showToast(str);
    }

    public void updateGroupItems(MediaItem mediaItem, Updater updater) {
        if (mediaItem.isGroupShot()) {
            getGroupItems(mediaItem, new c(10, this, updater));
        }
    }

    public void updateProgressMessage() {
        DialogHelper dialogHelper = this.mDialogHelper;
        if (dialogHelper != null) {
            ProgressInfo progressInfo = this.mProgressInfo;
            int b = progressInfo.mProgressCount + 1;
            progressInfo.mProgressCount = b;
            dialogHelper.updateDialog(b, this.mProgressInfo.mTotal, this.mProgressInfo.getPercent());
        }
    }

    private void addResult(MediaItem mediaItem, boolean z) {
        this.mResultList.add(mediaItem.getPath());
        this.mResultIdList.add(Long.valueOf(mediaItem.getFileId()));
        if (z) {
            this.mSuccessGroupItemCount++;
        } else {
            this.mProgressInfo.increaseSuccessCount(mediaItem.isImage());
        }
        if (!mediaItem.isSimilarShot()) {
            this.mSimilarShotCandidate.add(mediaItem);
        }
        MediaFileScanner mediaFileScanner = this.mScanner;
        if (mediaFileScanner != null) {
            mediaFileScanner.scan(mediaItem);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updateDateTime */
    public void lambda$fixDateTime$8(MediaItem mediaItem, Function<String, String[]> function) {
        if (isApplicable(mediaItem)) {
            updateApplicableItem(mediaItem, function);
        } else if (mediaItem.isLocal() && !mediaItem.isHeif()) {
            updateNormalImage(mediaItem);
        }
    }
}
