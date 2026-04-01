package com.samsung.scsp.media;

import android.text.TextUtils;
import com.samsung.scsp.common.PageReader;
import com.samsung.scsp.framework.core.ScspException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaList {
    private Integer count;
    private Boolean hasNext;
    private List<Media> list;
    private String nextChangePoint;
    private String nextPageToken;
    private PageReader<MediaList> pageReader;
    private Long serverTimestamp;

    public MediaList() {
        this.list = new ArrayList();
    }

    public void addAll(MediaList mediaList) {
        this.list = mediaList.getList();
        this.nextChangePoint = mediaList.getNextChangePoint();
        this.count = Integer.valueOf(mediaList.getCount());
        this.serverTimestamp = mediaList.getServerTimestamp();
        this.hasNext = Boolean.valueOf(mediaList.hasNext());
        this.nextPageToken = mediaList.getNextPageToken();
    }

    public int getCount() {
        return this.count.intValue();
    }

    public List<Media> getList() {
        return this.list;
    }

    public String getNextChangePoint() {
        return this.nextChangePoint;
    }

    public String getNextPageToken() {
        return this.nextPageToken;
    }

    public Long getServerTimestamp() {
        return this.serverTimestamp;
    }

    public boolean hasNext() {
        if (Boolean.TRUE.equals(this.hasNext) || !TextUtils.isEmpty(this.nextPageToken)) {
            return true;
        }
        return false;
    }

    public MediaList next() {
        PageReader<MediaList> pageReader2 = this.pageReader;
        if (pageReader2 != null) {
            return pageReader2.read();
        }
        throw new ScspException(ScspException.Code.BAD_IMPLEMENTATION, "next() requires PageReader.");
    }

    public MediaList(List<Media> list2, String str) {
        new ArrayList();
        this.list = list2;
        this.nextChangePoint = str;
        this.count = Integer.valueOf(list2.size());
    }

    public MediaList(PageReader<MediaList> pageReader2) {
        this.list = new ArrayList();
        this.pageReader = pageReader2;
    }

    public MediaList(List<Media> list2) {
        new ArrayList();
        this.list = list2;
    }
}
