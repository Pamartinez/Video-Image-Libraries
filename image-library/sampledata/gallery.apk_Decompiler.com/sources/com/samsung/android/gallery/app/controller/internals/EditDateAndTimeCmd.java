package com.samsung.android.gallery.app.controller.internals;

import A.a;
import E3.e;
import M4.j;
import N3.c;
import O3.b;
import c0.C0086a;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.camera.GppmHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditDateAndTimeCmd extends FixDateTimeCmd {
    private DateUpdater mDateUpdater;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class DateUpdater extends Thread {
        /* access modifiers changed from: private */
        public final String[] mDateTimeTag;
        private final boolean mIsTimeChanged;
        private final MediaItem[] mItems;

        public /* synthetic */ DateUpdater(EditDateAndTimeCmd editDateAndTimeCmd, MediaItem[] mediaItemArr, Calendar calendar, boolean z, int i2) {
            this(mediaItemArr, calendar, z);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ int lambda$run$0(MediaItem mediaItem, MediaItem mediaItem2) {
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
        public /* synthetic */ boolean lambda$run$2(MediaItem mediaItem, String str, MediaItem mediaItem2) {
            if (!EditDateAndTimeCmd.this.isHeifMp4Editable(mediaItem2) || !EditDateAndTimeCmd.this.changeHeifMp4Date(mediaItem, str)) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$run$3(String[] strArr, MediaItem mediaItem) {
            if (!EditDateAndTimeCmd.this.isApplicable(mediaItem) || !ExifUtils.changeDateTime(mediaItem.getPath(), strArr)) {
                return false;
            }
            return true;
        }

        public void run() {
            long j2;
            boolean z;
            String str;
            long currentTimeMillis = System.currentTimeMillis();
            String[] strArr = this.mDateTimeTag;
            boolean z3 = false;
            String str2 = strArr[0];
            String str3 = strArr[1];
            long millis = TimeTagApi.toMillis(str3);
            if (this.mIsTimeChanged) {
                Arrays.sort(this.mItems, new a(0));
                long[] delta = TimeTagApi.getDelta(str3, this.mItems.length);
                millis = delta[0];
                j2 = delta[1];
                a.A(new Object[]{Integer.valueOf(this.mItems.length), Long.valueOf(millis), Long.valueOf(j2), Long.valueOf(currentTimeMillis)}, new StringBuilder("changeDate sort"), EditDateAndTimeCmd.this.TAG);
            } else {
                j2 = 3600000;
            }
            EditDateAndTimeCmd.this.initResultList();
            MediaItem[] mediaItemArr = this.mItems;
            int length = mediaItemArr.length;
            int i2 = 0;
            while (i2 < length) {
                MediaItem mediaItem = mediaItemArr[i2];
                if (isInterrupted()) {
                    break;
                }
                EditDateAndTimeCmd.this.updateProgressMessage();
                if (mediaItem == null || mediaItem.getPath() == null || mediaItem.isCloudOnly() || mediaItem.isDrm() || mediaItem.isRawImage() || mediaItem.isPostProcessing()) {
                    z = z3;
                    if (mediaItem == null) {
                        Log.w(EditDateAndTimeCmd.this.TAG, "changeDate skip invalid file");
                    }
                } else {
                    if (!this.mIsTimeChanged) {
                        str = TimeUtil.getExifTime(mediaItem.getDateTaken());
                    } else {
                        str = TimeTagApi.toTimeString(millis);
                        millis -= j2;
                    }
                    if (EditDateAndTimeCmd.this.isHeifMp4Editable(mediaItem)) {
                        String B = C0212a.B(str2, " ", str);
                        if (EditDateAndTimeCmd.this.changeHeifMp4Date(mediaItem, B)) {
                            EditDateAndTimeCmd.this.addResult(mediaItem);
                            z = z3;
                            EditDateAndTimeCmd.this.updateGroupItems(mediaItem, new b(this, mediaItem, B));
                        } else {
                            z = z3;
                        }
                    } else {
                        z = z3;
                        if (EditDateAndTimeCmd.this.isApplicable(mediaItem)) {
                            String[] changeDate = ExifUtils.changeDate(mediaItem.getPath(), str2, str, true);
                            if (changeDate != null) {
                                EditDateAndTimeCmd.this.addResult(mediaItem);
                                EditDateAndTimeCmd.this.updateGroupItems(mediaItem, new c(this, changeDate));
                            }
                        } else if (mediaItem.isJpeg() || mediaItem.isPng()) {
                            long exifTimeInMillis = TimeUtil.getExifTimeInMillis(str2 + ' ' + str);
                            if (exifTimeInMillis > 0) {
                                FileUtils.setDateModified(mediaItem.getPath(), exifTimeInMillis);
                                EditDateAndTimeCmd.this.addResult(mediaItem);
                            }
                        }
                    }
                }
                i2++;
                z3 = z;
            }
            char c5 = z3;
            if (EditDateAndTimeCmd.this.mSimilarShotCandidate.size() > 0) {
                SimilarPhotoHelper.clearSimilarPhoto(EditDateAndTimeCmd.this.getContext(), EditDateAndTimeCmd.this.mSimilarShotCandidate);
            }
            a.A(new Object[]{EditDateAndTimeCmd.this.getResultCountInfo(), Long.valueOf(currentTimeMillis)}, new StringBuilder("changeDate"), EditDateAndTimeCmd.this.TAG);
            EditDateAndTimeCmd.this.scanResult();
            EditDateAndTimeCmd editDateAndTimeCmd = EditDateAndTimeCmd.this;
            editDateAndTimeCmd.addLog(editDateAndTimeCmd.TAG, this.mDateTimeTag[c5] + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mDateTimeTag[1]);
        }

        private DateUpdater(MediaItem[] mediaItemArr, Calendar calendar, boolean z) {
            this.mItems = mediaItemArr;
            long timeInMillis = calendar.getTimeInMillis();
            String[] split = TimeUtil.getExifDateTime(timeInMillis).split(" ");
            this.mDateTimeTag = split;
            this.mIsTimeChanged = z;
            String access$000 = EditDateAndTimeCmd.this.TAG;
            Boolean valueOf = Boolean.valueOf(z);
            Long valueOf2 = Long.valueOf(timeInMillis);
            StringBuilder sb2 = new StringBuilder("[");
            sb2.append(split[0]);
            sb2.append(' ');
            Log.d(access$000, "changeDate", valueOf, valueOf2, C0086a.m(sb2, split[1], ']'));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TimeTagApi {
        public static long[] getDelta(String str, int i2) {
            long millis = toMillis(str);
            long j2 = (long) i2;
            long j3 = j2 * 10000;
            if (14400000 > j3) {
                if (millis < j3) {
                    millis = j3;
                }
                return new long[]{millis, 10000};
            }
            if (millis <= 14400000) {
                millis = 14400000;
            }
            return new long[]{millis, 14399000 / j2};
        }

        public static long toMillis(String str) {
            String[] split = str.split(NumericEnum.SEP);
            return (((long) Integer.parseInt(split[2])) * 1000) + (((long) Integer.parseInt(split[1])) * 60000) + (((long) Integer.parseInt(split[0])) * 3600000);
        }

        public static String toTimeString(long j2) {
            String str;
            String str2;
            String str3;
            int i2 = (int) (j2 / 1000);
            int i7 = i2 / 3600;
            int i8 = i2 - (i7 * 3600);
            int i10 = i8 / 60;
            int i11 = i8 - (i10 * 60);
            StringBuilder sb2 = new StringBuilder();
            if (i7 < 10) {
                str = C0086a.i(i7, "0");
            } else {
                str = Integer.toString(i7);
            }
            sb2.append(str);
            sb2.append(NumericEnum.SEP);
            if (i10 < 10) {
                str2 = C0086a.i(i10, "0");
            } else {
                str2 = Integer.toString(i10);
            }
            sb2.append(str2);
            sb2.append(NumericEnum.SEP);
            if (i11 < 10) {
                str3 = C0086a.i(i11, "0");
            } else {
                str3 = Integer.toString(i11);
            }
            sb2.append(str3);
            return sb2.toString();
        }
    }

    private boolean isValidDateTimeRange(long j2, String str) {
        long j3;
        long j8;
        if (TimeUtil.isValidLocalTime(j2)) {
            j3 = TimeUtil.IsoUtcDateTime.toTimeInMillis(str);
        } else {
            j3 = TimeUtil.getExifTimeInMillis(str);
        }
        if (TimeUtil.isValidLocalTime(j2)) {
            j8 = TimeUtil.IsoUtcDateTime.toTimeInMillis("2100-12-31 23:59:59");
        } else {
            j8 = TimeUtil.getExifTimeInMillis("2100:12:31 23:59:59");
        }
        if (j3 <= j8) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onExecute$2(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isVideo()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$3(MediaItem[] mediaItemArr, EventContext eventContext, ArrayList arrayList) {
        onDataCompleted(mediaItemArr, arrayList);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreExecute$0(MediaItem[] mediaItemArr, long j2, EventContext eventContext, Integer num) {
        String str = this.TAG;
        Log.d(str, "onPreExecute#PppChecker" + Logger.vt(Integer.valueOf(mediaItemArr.length), num, Long.valueOf(j2)));
        if (num.intValue() > 0) {
            mediaItemArr = replacePppItem(eventContext, mediaItemArr);
        }
        super.onPreExecute(eventContext, mediaItemArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreExecute$1(EventContext eventContext, MediaItem[] mediaItemArr) {
        if (GppmHelper.SUPPORT_DONATION_MULTIPLE) {
            EventContext eventContext2 = eventContext;
            MediaItem[] mediaItemArr2 = mediaItemArr;
            executePppChecker(eventContext2, mediaItemArr2, new e(this, mediaItemArr2, System.currentTimeMillis(), eventContext2, 4));
            return;
        }
        super.onPreExecute(eventContext, mediaItemArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$runDateUpdater$4(MediaItem[] mediaItemArr, Object[] objArr) {
        DateUpdater dateUpdater = new DateUpdater(this, mediaItemArr, objArr[0], objArr[1].booleanValue(), 0);
        this.mDateUpdater = dateUpdater;
        dateUpdater.start();
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_EDIT_DATE_AND_TIME.toString();
    }

    public String getIndexingValue() {
        if (this.mDateUpdater == null) {
            return null;
        }
        return String.valueOf(TimeUtil.getExifTimeInMillis(this.mDateUpdater.mDateTimeTag[0] + ' ' + this.mDateUpdater.mDateTimeTag[1]));
    }

    public String getProgressDialogTitle() {
        return getContext().getString(R.string.updating_date_and_time);
    }

    public boolean isAnalyticsEnabled() {
        return true;
    }

    public void onCancel() {
        super.onCancel();
        DateUpdater dateUpdater = this.mDateUpdater;
        if (dateUpdater != null) {
            dateUpdater.interrupt();
        }
    }

    public void onDataCompleted(MediaItem[] mediaItemArr, ArrayList<Object> arrayList) {
        if (arrayList != null && arrayList.size() >= 1) {
            Object[] objArr = (Object[]) arrayList.get(0);
            if (objArr.length == 2) {
                showProgressDialog(getTotalCount(mediaItemArr));
                runDateUpdater(mediaItemArr, objArr);
            }
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] mediaItemArr;
        String str;
        if (objArr.length > 0) {
            mediaItemArr = objArr[0];
        } else {
            mediaItemArr = eventContext.getSelectedItems();
        }
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.e(this.TAG, "no data available");
        } else if (mediaItemArr.length > 5000) {
            Utils.showToast(getContext(), (int) R.string.cant_select_more_than_5000_items);
        } else {
            long dateLocal = mediaItemArr[0].getDateLocal();
            if (TimeUtil.isValidLocalTime(dateLocal)) {
                str = TimeUtil.getIsoUtcDateTime(dateLocal);
            } else {
                str = TimeUtil.getExifDateTime(mediaItemArr[0].getDateTaken());
            }
            if (!isValidDateTimeRange(dateLocal, str)) {
                String str2 = this.TAG;
                Log.d(str2, "update wrong date {" + str + "}");
                str = TimeUtil.getExifDateTime(System.currentTimeMillis());
            }
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/DateTimePicker").appendArg("current_date", str.replace("-", NumericEnum.SEP)).appendArg("has_video", Arrays.stream(mediaItemArr).anyMatch(new j(14))).build()).setOnDataCompleteListener(new b(3, this, mediaItemArr)).start();
        }
    }

    public void onPreExecute(EventContext eventContext, Object... objArr) {
        if (objArr.length != 0 || !eventContext.isSelectionMode()) {
            super.onPreExecute(eventContext, objArr);
            return;
        }
        int selectedItemCount = eventContext.getSelectedItemCount();
        Log.d(this.TAG, "onPreExecute", Integer.valueOf(selectedItemCount));
        if (selectedItemCount > 5000) {
            Utils.showToast(getContext(), (int) R.string.cant_select_more_than_5000_items);
        } else {
            loadAndExecute(eventContext, new c(9, this, eventContext));
        }
    }

    public void runDateUpdater(MediaItem[] mediaItemArr, Object[] objArr) {
        ThreadUtil.postOnUiThreadDelayed(new J6.c(this, mediaItemArr, objArr, 17), 100);
    }

    public void showSuccessToast(int i2, int i7, int i8) {
        if (i2 == 0) {
            showCannotChangeToast();
        } else if (i2 == i7) {
            showToast(getContext().getResources().getQuantityString(R.plurals.date_time_info_changed_images, i2, new Object[]{Integer.valueOf(i2)}));
        } else if (i2 == i8) {
            showToast(getContext().getResources().getQuantityString(R.plurals.date_time_info_changed_videos, i2, new Object[]{Integer.valueOf(i2)}));
        } else {
            showToast(getContext().getResources().getQuantityString(R.plurals.date_time_info_changed_items, i2, new Object[]{Integer.valueOf(i2)}));
        }
    }
}
