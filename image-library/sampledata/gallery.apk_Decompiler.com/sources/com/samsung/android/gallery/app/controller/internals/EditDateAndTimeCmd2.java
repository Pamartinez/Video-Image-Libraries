package com.samsung.android.gallery.app.controller.internals;

import A.a;
import c0.C0086a;
import com.samsung.android.gallery.app.controller.internals.EditDateAndTimeCmd;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.fileio.redact.FileRedactor;
import com.samsung.android.gallery.module.fileio.redact.FileRedactorBuilder;
import com.samsung.android.gallery.module.fileio.redact.OnProgress;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Arrays;
import java.util.Calendar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditDateAndTimeCmd2 extends EditDateAndTimeCmd {
    private DateUpdater mDateUpdater;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class DateUpdater implements OnProgress {
        /* access modifiers changed from: private */
        public final String mDateTag;
        private long mElapsed;
        FileRedactor mFileRedactor;
        private final boolean mIsTimeChanged;
        private final MediaItem[] mItems;
        long mTimeDelta;
        long mTimeRef;
        /* access modifiers changed from: private */
        public String mTimeTag;

        public /* synthetic */ DateUpdater(EditDateAndTimeCmd2 editDateAndTimeCmd2, MediaItem[] mediaItemArr, Calendar calendar, boolean z, int i2) {
            this(mediaItemArr, calendar, z);
        }

        private void initDateTime() {
            this.mTimeRef = EditDateAndTimeCmd.TimeTagApi.toMillis(this.mTimeTag);
            this.mTimeDelta = 3600000;
            if (this.mIsTimeChanged) {
                Arrays.sort(this.mItems, new a(1));
                long[] delta = EditDateAndTimeCmd.TimeTagApi.getDelta(this.mTimeTag, this.mItems.length);
                this.mTimeRef = delta[0];
                this.mTimeDelta = delta[1];
                String access$100 = EditDateAndTimeCmd2.this.TAG;
                a.A(new Object[]{Integer.valueOf(this.mItems.length), Long.valueOf(this.mTimeRef), Long.valueOf(this.mTimeDelta), Long.valueOf(this.mElapsed)}, new StringBuilder("changeDate sort"), access$100);
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ int lambda$initDateTime$2(MediaItem mediaItem, MediaItem mediaItem2) {
            int i2 = ((mediaItem.getDateTaken() - mediaItem2.getDateTaken()) > 0 ? 1 : ((mediaItem.getDateTaken() - mediaItem2.getDateTaken()) == 0 ? 0 : -1));
            if (i2 == 0) {
                return 0;
            }
            if (i2 > 0) {
                return -1;
            }
            return 1;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ Boolean lambda$run$0(FileItemInterface fileItemInterface) {
            boolean z;
            if (fileItemInterface == null || fileItemInterface.getPath() == null || fileItemInterface.isCloudOnly() || fileItemInterface.isDrm() || ((MediaItem) fileItemInterface).isRawImage() || fileItemInterface.isPostProcessing()) {
                z = false;
            } else {
                z = true;
            }
            return Boolean.valueOf(z);
        }

        /* JADX WARNING: type inference failed for: r1v4, types: [java.lang.Object, java.util.function.Function] */
        /* JADX WARNING: type inference failed for: r1v5, types: [java.util.function.BiConsumer, java.lang.Object] */
        /* access modifiers changed from: private */
        public void run() {
            String access$000 = EditDateAndTimeCmd2.this.TAG;
            Boolean valueOf = Boolean.valueOf(this.mIsTimeChanged);
            StringBuilder sb2 = new StringBuilder("[");
            sb2.append(this.mDateTag);
            sb2.append(' ');
            Log.d(access$000, "changeDate", valueOf, C0086a.m(sb2, this.mTimeTag, ']'));
            this.mElapsed = System.currentTimeMillis();
            FileRedactor build = FileRedactorBuilder.edit(this.mItems).setOperation(new d(this)).setCallback(this).setCheckValidFunction(new Object()).withMediaScan(new Object()).build();
            this.mFileRedactor = build;
            build.run();
        }

        /* access modifiers changed from: private */
        public boolean updateFunction(FileItemInterface fileItemInterface) {
            if (!this.mIsTimeChanged) {
                this.mTimeTag = TimeUtil.getExifTime(fileItemInterface.getDateTaken());
            } else {
                this.mTimeTag = EditDateAndTimeCmd.TimeTagApi.toTimeString(this.mTimeRef);
                this.mTimeRef -= this.mTimeDelta;
            }
            MediaItem mediaItem = (MediaItem) fileItemInterface;
            if (EditDateAndTimeCmd2.this.isHeifMp4Editable(mediaItem)) {
                return EditDateAndTimeCmd2.this.changeHeifMp4Date(mediaItem, this.mDateTag + " " + this.mTimeTag);
            } else if (!EditDateAndTimeCmd2.this.isApplicable(mediaItem)) {
                Log.e(EditDateAndTimeCmd2.this.TAG, "updateFunction null " + fileItemInterface);
                return false;
            } else if (ExifUtils.changeDate(fileItemInterface.getPath(), this.mDateTag, this.mTimeTag, true) != null) {
                return true;
            } else {
                return false;
            }
        }

        public void cancel() {
            this.mFileRedactor.cancel();
        }

        public void onCanceled(int i2, int i7, int i8) {
            EditDateAndTimeCmd2.this.onComplete(i7);
        }

        public void onCompleted(int i2, int i7, int i8) {
            EditDateAndTimeCmd2.this.onComplete(i7);
            String access$200 = EditDateAndTimeCmd2.this.TAG;
            a.A(new Object[]{EditDateAndTimeCmd2.this.getResultCountInfo(), Long.valueOf(this.mElapsed)}, new StringBuilder("changeDate"), access$200);
            EditDateAndTimeCmd2 editDateAndTimeCmd2 = EditDateAndTimeCmd2.this;
            String access$300 = editDateAndTimeCmd2.TAG;
            editDateAndTimeCmd2.addLog(access$300, this.mDateTag + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mTimeTag);
        }

        public void onProcess(int i2, int i7) {
            EditDateAndTimeCmd2.this.updateProgressMessage();
        }

        private DateUpdater(MediaItem[] mediaItemArr, Calendar calendar, boolean z) {
            this.mItems = mediaItemArr;
            String[] split = TimeUtil.getExifDateTime(calendar.getTimeInMillis()).split(" ");
            this.mIsTimeChanged = z;
            this.mDateTag = split[0];
            this.mTimeTag = split[1];
            initDateTime();
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$run$1(Integer num, Integer num2) {
        }
    }

    public String getIndexingValue() {
        if (this.mDateUpdater == null) {
            return null;
        }
        return String.valueOf(TimeUtil.getExifTimeInMillis(this.mDateUpdater.mDateTag + ' ' + this.mDateUpdater.mTimeTag));
    }

    public void onCancel() {
        super.onCancel();
        this.mDateUpdater.cancel();
    }

    public void runDateUpdater(MediaItem[] mediaItemArr, Object[] objArr) {
        DateUpdater dateUpdater = new DateUpdater(this, mediaItemArr, objArr[0], objArr[1].booleanValue(), 0);
        this.mDateUpdater = dateUpdater;
        dateUpdater.run();
    }
}
