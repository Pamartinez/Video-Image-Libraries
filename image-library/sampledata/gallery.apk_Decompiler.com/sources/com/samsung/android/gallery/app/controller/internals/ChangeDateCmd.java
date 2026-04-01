package com.samsung.android.gallery.app.controller.internals;

import N2.j;
import O3.b;
import O3.d;
import O3.e;
import O3.f;
import c0.C0086a;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.support.utils.ExifCompat;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.util.ArrayList;
import java.util.Calendar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChangeDateCmd extends FixDateTimeCmd {
    private void changeDate(MediaItem[] mediaItemArr, int i2, int i7, int i8) {
        initResultList();
        showProgressDialog(getTotalCount(mediaItemArr));
        ThreadUtil.postOnUiThreadDelayed(new d(this, mediaItemArr, i2, i7, i8), 100);
    }

    /* access modifiers changed from: private */
    /* renamed from: changeDateAsync */
    public void lambda$changeDate$1(MediaItem[] mediaItemArr, int i2, int i7, int i8) {
        Object obj;
        Object obj2;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(i2);
        sb2.append(NumericEnum.SEP);
        if (i7 > 9) {
            obj = Integer.valueOf(i7);
        } else {
            obj = C0086a.i(i7, "0");
        }
        sb2.append(obj);
        sb2.append(NumericEnum.SEP);
        if (i8 > 9) {
            obj2 = Integer.valueOf(i8);
        } else {
            obj2 = C0086a.i(i8, "0");
        }
        sb2.append(obj2);
        SimpleThreadPool.getInstance().execute(new e(this, mediaItemArr, sb2.toString(), i2, i7, i8));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$changeDateAsync$3(String str, MediaItem mediaItem) {
        return changeHeifMp4Date(mediaItem, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$changeDateAsync$4(MediaItem[] mediaItemArr, String str, int i2, int i7, int i8) {
        int i10;
        long j2;
        MediaItem[] mediaItemArr2 = mediaItemArr;
        String str2 = str;
        long currentTimeMillis = System.currentTimeMillis();
        int length = mediaItemArr2.length;
        long j3 = 43200000;
        boolean z = false;
        int i11 = 0;
        while (i11 < length) {
            MediaItem mediaItem = mediaItemArr2[i11];
            if (isInterrupted()) {
                break;
            }
            if (isApplicable(mediaItem)) {
                String timeString = ExifCompat.DateTimeUtc.toTimeString(j3);
                j2 = 60000;
                Log.d(this.TAG, j.d("changeDate {", str2, " ", timeString, "}"));
                String[] changeDate = ExifUtils.changeDate(mediaItem.getPath(), str2, timeString, z);
                if (changeDate != null) {
                    addResult(mediaItem);
                    updateGroupItems(mediaItem, new f(changeDate, 0));
                }
                int i12 = i2;
                int i13 = i8;
                i10 = i11;
                updateProgressMessage();
                i11 = i10 + 1;
                z = false;
            } else {
                j2 = 60000;
                if (isHeifMp4Editable(mediaItem)) {
                    String B = C0212a.B(str2, " ", ExifCompat.DateTimeUtc.toTimeString(j3));
                    if (changeHeifMp4Date(mediaItem, B)) {
                        addResult(mediaItem);
                        updateGroupItems(mediaItem, new b(2, this, B));
                    }
                } else if (mediaItem.isLocal() && mediaItem.getPath() != null && !mediaItem.isHeif()) {
                    Calendar instance = Calendar.getInstance();
                    instance.setTimeInMillis(FileUtils.getDateModified(mediaItem.getPath()));
                    instance.set(1, i2);
                    instance.set(2, i7 - 1);
                    instance.set(5, i8);
                    i10 = i11;
                    FileUtils.setDateModified(mediaItem.getPath(), instance.getTimeInMillis());
                    addResult(mediaItem);
                    updateProgressMessage();
                    i11 = i10 + 1;
                    z = false;
                }
                int i122 = i2;
                int i132 = i8;
                i10 = i11;
                updateProgressMessage();
                i11 = i10 + 1;
                z = false;
            }
            j3 += j2;
            int i1222 = i2;
            int i1322 = i8;
            i10 = i11;
            updateProgressMessage();
            i11 = i10 + 1;
            z = false;
        }
        if (this.mSimilarShotCandidate.size() > 0) {
            SimilarPhotoHelper.clearSimilarPhoto(getContext(), this.mSimilarShotCandidate);
        }
        Log.i(this.TAG, "changeDate {" + getProgressTotalCount() + "} +" + (System.currentTimeMillis() - currentTimeMillis));
        scanResult();
        addLog(this.TAG, str2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(MediaItem[] mediaItemArr, EventContext eventContext, ArrayList arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            int[] iArr = (int[]) arrayList.get(0);
            if (iArr.length == 3) {
                changeDate(mediaItemArr, iArr[0], iArr[1], iArr[2]);
            }
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] selectedItems = eventContext.getSelectedItems();
        if (selectedItems == null || selectedItems.length == 0) {
            Log.e(this.TAG, "no selected data available");
            return;
        }
        String[] findDateTime = new ExifCompat(selectedItems[0].getPath()).findDateTime();
        String str = null;
        if (findDateTime != null && ExifCompat.DateTimeUtc.toMillis(findDateTime[0]) > System.currentTimeMillis()) {
            String str2 = this.TAG;
            Log.d(str2, "update wrong date {" + findDateTime[0] + "}");
            findDateTime = null;
        }
        UriBuilder uriBuilder = new UriBuilder("data://user/Date");
        if (findDateTime != null) {
            str = findDateTime[0];
        }
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(uriBuilder.appendArg("date-time", str).build()).setOnDataCompleteListener(new b(1, this, selectedItems)).start();
    }
}
