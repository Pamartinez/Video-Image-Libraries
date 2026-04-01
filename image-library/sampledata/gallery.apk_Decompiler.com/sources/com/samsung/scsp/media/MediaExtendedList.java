package com.samsung.scsp.media;

import android.text.TextUtils;
import com.samsung.scsp.common.PageReader;
import com.samsung.scsp.framework.core.ScspException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaExtendedList {
    private Integer count;
    private List<MediaExtended> list;
    private String nextPageToken;
    private PageReader<MediaExtendedList> pageReader;
    private String photoId;
    private Long serverTimestamp;

    public MediaExtendedList() {
        this.list = new ArrayList();
    }

    public void addAll(MediaExtendedList mediaExtendedList) {
        this.list = mediaExtendedList.getList();
        this.nextPageToken = mediaExtendedList.getNextPageToken();
        this.count = Integer.valueOf(mediaExtendedList.getCount());
        this.serverTimestamp = mediaExtendedList.getServerTimestamp();
        this.photoId = mediaExtendedList.getPhotoId();
    }

    public int getCount() {
        return this.count.intValue();
    }

    public List<MediaExtended> getList() {
        return this.list;
    }

    public String getNextPageToken() {
        return this.nextPageToken;
    }

    public String getPhotoId() {
        return this.photoId;
    }

    public Long getServerTimestamp() {
        return this.serverTimestamp;
    }

    public boolean hasNextPage() {
        return !TextUtils.isEmpty(this.nextPageToken);
    }

    public MediaExtendedList next() {
        PageReader<MediaExtendedList> pageReader2 = this.pageReader;
        if (pageReader2 != null) {
            return pageReader2.read();
        }
        throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "next() requires PageReader.");
    }

    public MediaExtendedList(List<MediaExtended> list2, String str) {
        new ArrayList();
        this.list = list2;
        this.nextPageToken = str;
    }

    public MediaExtendedList(PageReader<MediaExtendedList> pageReader2) {
        this.list = new ArrayList();
        this.pageReader = pageReader2;
    }

    public MediaExtendedList(List<MediaExtended> list2) {
        new ArrayList();
        this.list = list2;
    }
}
