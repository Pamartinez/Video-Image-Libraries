package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import android.os.Bundle;
import com.samsung.android.gallery.module.dataset.MediaDataEntire;
import com.samsung.android.gallery.module.dataset.tables.ClusterTable;
import com.samsung.android.gallery.module.dataset.tables.DataTable;
import com.samsung.android.gallery.module.dataset.tables.DefaultTable;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.Utils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataSearchPictures extends MediaDataTimeline {
    private HashSet<Long> mFeedbackSet = new HashSet<>();
    protected Cursor mScsCursor;

    public MediaDataSearchPictures(Blackboard blackboard, String str) {
        super(blackboard, str);
    }

    private int findPositionOnScsCursor(long j2) {
        Cursor cursor = this.mScsCursor;
        if (cursor == null || cursor.isClosed()) {
            return -1;
        }
        this.mScsCursor.moveToFirst();
        int i2 = 0;
        do {
            Cursor cursor2 = this.mScsCursor;
            if (j2 == cursor2.getLong(cursor2.getColumnIndex("_id"))) {
                return i2;
            }
            i2++;
        } while (this.mScsCursor.moveToNext());
        return i2;
    }

    private boolean hasScsCursor(Cursor[] cursorArr) {
        if (!Features.isEnabled(Features.SUPPORT_SCS_SEARCH_FEEDBACK) || cursorArr.length <= 6 || cursorArr[6] == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void onSuggesterFeedback(Object obj, Bundle bundle) {
        try {
            Cursor cursor = this.mScsCursor;
            if (cursor != null && !cursor.isClosed()) {
                this.mScsCursor.respond((Bundle) ((Object[]) obj)[0]);
            }
        } catch (Exception e) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "onSuggesterFeedback(failed) : " + e);
        }
    }

    private void setExtraScsCursor(Cursor[] cursorArr) {
        this.mScsCursor = cursorArr[6];
    }

    public boolean checkDataMismatching(Cursor[] cursorArr, DataTable dataTable, ClusterTable[] clusterTableArr, MediaDataEntire.Candidates candidates) {
        if (isTimelineDisabled() || !super.checkDataMismatching(cursorArr, dataTable, clusterTableArr, candidates)) {
            return false;
        }
        return true;
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_FEEDBACK)) {
            arrayList.add(new SubscriberInfo("command://event/FeedbackSearchedItem", new n0(this, 0)));
            arrayList.add(new SubscriberInfo("command://event/FeedbackSuggesterItem", new n0(this, 1)));
        }
    }

    public void initExtraTable(Cursor[] cursorArr, CountDownLatch countDownLatch, MediaDataEntire.Candidates candidates) {
        super.initExtraTable(cursorArr, countDownLatch, candidates);
        if (hasScsCursor(cursorArr)) {
            setExtraScsCursor(cursorArr);
            countDownLatch.countDown();
            CountDownLatch countDownLatch2 = this.mFullLoadLatch;
            if (countDownLatch2 != null) {
                countDownLatch2.countDown();
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_FEEDBACK)) {
            Utils.closeSilently(this.mScsCursor);
        }
        this.mFeedbackSet.clear();
    }

    public void onFeedback(Object obj, Bundle bundle) {
        try {
            Cursor cursor = this.mScsCursor;
            if (cursor != null && !cursor.isClosed()) {
                Object[] objArr = (Object[]) obj;
                boolean z = false;
                int intValue = ((Integer) objArr[0]).intValue();
                Long l = (Long) objArr[1];
                long longValue = l.longValue();
                if (intValue == -1) {
                    if (this.mFeedbackSet.contains(l)) {
                        Log.w((CharSequence) this.TAG, "onFeedback() << already send feedback ", l);
                        return;
                    } else {
                        intValue = findPositionOnScsCursor(longValue);
                        z = true;
                    }
                }
                if (intValue >= 0) {
                    if (this.mScsCursor.moveToPosition(intValue)) {
                        Cursor cursor2 = this.mScsCursor;
                        long j2 = cursor2.getLong(cursor2.getColumnIndex("_id"));
                        if (longValue == j2) {
                            if (z) {
                                this.mFeedbackSet.add(l);
                            }
                            Bundle bundle2 = new Bundle();
                            bundle2.putInt("feedback_selected", intValue);
                            bundle2.putBoolean("enable_debug", PocFeatures.isEnabled(PocFeatures.SearchDebugInfo));
                            this.mScsCursor.respond(bundle2);
                            return;
                        }
                        Log.w(this.TAG, "onFeedback(failed) << wrong expect media id : pos[" + intValue + "], select[" + longValue + "], expect[" + j2 + "]");
                        return;
                    }
                }
                Log.w(this.TAG, "onFeedback(failed) << moveToPosition false : pos[" + intValue + "], select[" + longValue + "]");
            }
        } catch (Exception e) {
            Log.e(this.TAG, "onFeedback(failed) : " + e);
        }
    }

    public void swapClusterTables(Cursor[] cursorArr, ClusterTable[] clusterTableArr, DefaultTable[] defaultTableArr, int i2, CountDownLatch countDownLatch) {
        super.swapClusterTables(cursorArr, clusterTableArr, defaultTableArr, i2, countDownLatch);
        if (hasScsCursor(cursorArr)) {
            Utils.closeSilently(this.mScsCursor);
            setExtraScsCursor(cursorArr);
            countDownLatch.countDown();
        }
    }
}
