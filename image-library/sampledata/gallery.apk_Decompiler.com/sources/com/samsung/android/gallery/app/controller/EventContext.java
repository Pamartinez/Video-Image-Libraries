package com.samsung.android.gallery.app.controller;

import android.app.Activity;
import android.content.Context;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.InstantSubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface EventContext {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnSelectionListener {
        boolean onSelectionCompleted(EventContext eventContext, MediaItem[] mediaItemArr);
    }

    Activity getActivity();

    MediaItem[] getAllItems() {
        return new MediaItem[0];
    }

    Context getApplicationContext() {
        return getActivity().getApplicationContext();
    }

    MediaItem getBestItem() {
        return null;
    }

    Blackboard getBlackboard() {
        return Blackboard.getInstance(getActivity().toString());
    }

    Context getContext() {
        return getActivity();
    }

    MediaItem getCurrentItem() {
        return null;
    }

    int getCurrentViewDepth() {
        return 0;
    }

    Object getEventContextData(String str) {
        return null;
    }

    int getFirstItemDataPositionFromSelected() {
        return -1;
    }

    MediaItem getHeaderItem() {
        return null;
    }

    String getLocationKey() {
        return null;
    }

    int getMaxDepth() {
        return 0;
    }

    MediaData getMediaData() {
        return null;
    }

    String getScreenId() {
        return null;
    }

    String getScreenLabel() {
        return null;
    }

    int getSelectedItemCount() {
        return 0;
    }

    MediaItem[] getSelectedItems() {
        return new MediaItem[0];
    }

    Toolbar getToolbar() {
        return null;
    }

    int getTotalCount() {
        return 0;
    }

    boolean isPhotoUpperPositioned() {
        return false;
    }

    boolean isSelectAll() {
        return false;
    }

    boolean isSelectionMode() {
        return false;
    }

    void postAnalyticsLog(AnalyticsEventId analyticsEventId) {
    }

    boolean showDeleteAllWarning() {
        return false;
    }

    void postAnalyticsLog(AnalyticsEventId analyticsEventId, long j2) {
    }

    void postAnalyticsLog(AnalyticsEventId analyticsEventId, String str) {
    }

    void postAnalyticsLog(String str, AnalyticsEventId analyticsEventId) {
    }

    void postAnalyticsLog(String str, AnalyticsEventId analyticsEventId, long j2) {
    }

    void prepareExtendedShare() {
    }

    void subscribeInstant(String str, InstantSubscriberListener instantSubscriberListener) {
    }

    void subscribeInstantOnUi(String str, InstantSubscriberListener instantSubscriberListener) {
    }
}
