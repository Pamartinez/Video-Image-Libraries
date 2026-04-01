package com.samsung.android.gallery.app.ui.list.stories.pictures.header;

import A.a;
import A4.C0372g;
import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesHeaderView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HeaderSimpleData extends MediaData.SimpleDataChangeListener {
    AtomicBoolean mFirstLoaded = new AtomicBoolean(false);
    private final String[] mLatLongList = new String[2];
    private final ArrayList<HeaderDataListener> mListener = new ArrayList<>();
    private MediaData mMediaData;
    private final int[] mMediaTypeCount = new int[2];

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LocationBuilder {
        private int mCount;
        private final ArrayList<String> mLatLongKeys;
        private final StringJoiner mLatitudeList;
        private final StringJoiner mLongitudeList;

        public /* synthetic */ LocationBuilder(int i2) {
            this();
        }

        /* access modifiers changed from: private */
        public void append(MediaItem mediaItem) {
            if (mediaItem != null && MapUtil.isValidLocation(mediaItem.getLatitude(), mediaItem.getLongitude())) {
                String str = mediaItem.getLatitude() + "/" + mediaItem.getLongitude();
                if (!this.mLatLongKeys.contains(str)) {
                    this.mLatLongKeys.add(str);
                    this.mLatitudeList.add(String.valueOf(mediaItem.getLatitude()));
                    this.mLongitudeList.add(String.valueOf(mediaItem.getLongitude()));
                    this.mCount++;
                }
            }
        }

        /* access modifiers changed from: private */
        public String[] build() {
            return new String[]{this.mLatitudeList.toString(), this.mLongitudeList.toString()};
        }

        public String toString() {
            return "/L" + this.mCount;
        }

        private LocationBuilder() {
            this.mLatitudeList = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            this.mLongitudeList = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            this.mLatLongKeys = new ArrayList<>();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MediaCountBuilder {
        private int mImageCount;
        private int mVideoCount;

        public /* synthetic */ MediaCountBuilder(int i2) {
            this();
        }

        /* access modifiers changed from: private */
        public void append(MediaItem mediaItem) {
            if (mediaItem.isImage()) {
                this.mImageCount++;
            } else {
                this.mVideoCount++;
            }
        }

        /* access modifiers changed from: private */
        public int[] build() {
            return new int[]{this.mImageCount, this.mVideoCount};
        }

        public String toString() {
            return "/I" + this.mImageCount + "/V" + this.mVideoCount;
        }

        private MediaCountBuilder() {
            this.mImageCount = 0;
            this.mVideoCount = 0;
        }
    }

    public HeaderSimpleData(IStoryPicturesHeaderView iStoryPicturesHeaderView) {
        MediaData open = iStoryPicturesHeaderView.getMediaData((String) null).open(iStoryPicturesHeaderView.getLocationKey());
        this.mMediaData = open;
        open.register(this);
    }

    private void notifyLocationChanged() {
        Iterator<HeaderDataListener> it = this.mListener.iterator();
        while (it.hasNext()) {
            HeaderDataListener next = it.next();
            if (next.accept(HeaderType.MAP)) {
                next.notifyDataChanged();
            }
        }
    }

    private void notifyMediaTypeCountChanged() {
        Iterator<HeaderDataListener> it = this.mListener.iterator();
        while (it.hasNext()) {
            HeaderDataListener next = it.next();
            if (next.accept(HeaderType.SUMMARY)) {
                next.notifyDataChanged();
            }
        }
    }

    /* access modifiers changed from: private */
    public void reloadData() {
        long currentTimeMillis = System.currentTimeMillis();
        MediaData mediaData = this.mMediaData;
        if (mediaData != null && mediaData.isDataAvailable()) {
            try {
                LocationBuilder locationBuilder = new LocationBuilder(0);
                MediaCountBuilder mediaCountBuilder = new MediaCountBuilder(0);
                int count = mediaData.getCount();
                for (int i2 = 0; i2 < mediaData.getCount(); i2++) {
                    MediaItem readCache = mediaData.readCache(i2);
                    if (readCache != null) {
                        locationBuilder.append(readCache);
                        mediaCountBuilder.append(readCache);
                    }
                }
                ThreadUtil.postOnUiThread(new a(this, locationBuilder, mediaCountBuilder));
                Log.d("HeaderSimpleData", "reloadData {T" + count + locationBuilder.toString() + mediaCountBuilder.toString() + "} +" + (System.currentTimeMillis() - currentTimeMillis));
            } catch (Exception e) {
                a.s(e, new StringBuilder("reloadData failed="), "HeaderSimpleData");
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: swapDataAndNotifyOnUi */
    public void lambda$reloadData$0(LocationBuilder locationBuilder, MediaCountBuilder mediaCountBuilder) {
        if (swapLocations(locationBuilder)) {
            notifyLocationChanged();
        }
        if (swapMediaCount(mediaCountBuilder)) {
            notifyMediaTypeCountChanged();
        }
        this.mFirstLoaded.set(true);
    }

    private boolean swapLocations(LocationBuilder locationBuilder) {
        String[] b = locationBuilder.build();
        if (TextUtils.equals(this.mLatLongList[0], b[0]) && TextUtils.equals(this.mLatLongList[1], b[1])) {
            return false;
        }
        String[] strArr = this.mLatLongList;
        strArr[0] = b[0];
        strArr[1] = b[1];
        return true;
    }

    private boolean swapMediaCount(MediaCountBuilder mediaCountBuilder) {
        int[] b = mediaCountBuilder.build();
        int[] iArr = this.mMediaTypeCount;
        int i2 = iArr[0];
        int i7 = b[0];
        if (i2 == i7 && iArr[1] == b[1]) {
            return false;
        }
        iArr[0] = i7;
        iArr[1] = b[1];
        return true;
    }

    public void destroy() {
        this.mListener.clear();
        this.mMediaData.unregister(this);
        this.mMediaData.close();
        this.mMediaData = null;
    }

    public String[] getLatLongList() {
        return this.mLatLongList;
    }

    public int[] getMediaTypeCount() {
        return this.mMediaTypeCount;
    }

    public void onDataChanged() {
        SimpleThreadPool.getInstance().execute(new C0372g(8, this));
    }

    public void register(HeaderDataListener headerDataListener) {
        if (!this.mListener.contains(headerDataListener)) {
            this.mListener.add(headerDataListener);
            if (this.mFirstLoaded.get()) {
                headerDataListener.notifyDataChanged();
            }
        }
    }

    public void unregister(HeaderDataListener headerDataListener) {
        this.mListener.remove(headerDataListener);
    }
}
