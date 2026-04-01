package com.samsung.android.gallery.module.mde;

import A4.C0367b;
import android.database.Cursor;
import android.database.CursorWrapper;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.mobileservice.social.group.provider.GroupContract;
import com.samsung.android.sdk.mobileservice.social.share.provider.SpaceContract;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MdeSpaceGroupCursor extends CursorWrapper {
    private static final HashSet<Integer> sDecodeMap = new HashSet<Integer>() {
        {
            HashMap<String, Integer> hashMap = MdeSpaceGroupCursor.sMap;
            add(hashMap.get("title"));
            add(hashMap.get("thumbnail_local_path"));
            add(hashMap.get("groupName"));
            add(hashMap.get("group_thumbnail_local_path"));
        }
    };
    protected static final HashMap<String, Integer> sMap = new HashMap<String, Integer>() {
        {
            put("__absID", 0);
            put("spaceId", 1);
            put("groupId", 2);
            put("unread_count", 3);
            put(SpaceContract.Space.MEDIA_COUNT, 4);
            put("title", 5);
            put("owner", 6);
            put("is_owned_by_me", 7);
            put("thumbnail_local_path", 8);
            put("meta_data", 9);
            put("_cover_item", 10);
            put("cover_id", 11);
            put("memo", 12);
            put("groupName", 13);
            put(GroupContract.Group.MEMBERS_COUNT, 14);
            put("type", 15);
            put(GroupContract.Group.CREATED_TIME, 16);
            put("group_thumbnail_local_path", 17);
        }
    };
    private final int mColumnCount;
    private final ArrayList<String[]> mCursorList = new ArrayList<>();
    int mIndex = 0;
    private final String mTotalStr;

    public MdeSpaceGroupCursor(Cursor cursor, String str) {
        super(cursor);
        this.mTotalStr = str;
        this.mColumnCount = sMap.size();
        parseStr();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$parseStr$0(String str) {
        this.mCursorList.add(str.split("&"));
    }

    private void parseStr() {
        this.mCursorList.clear();
        Arrays.stream(this.mTotalStr.split("\u001f")).forEach(new C0367b(26, this));
    }

    public int getColumnCount() {
        return this.mColumnCount;
    }

    public int getColumnIndex(String str) {
        return sMap.getOrDefault(str, -1).intValue();
    }

    public int getCount() {
        return this.mCursorList.size();
    }

    public int getInt(int i2) {
        String[] strArr = this.mCursorList.get(this.mIndex);
        if (i2 < strArr.length) {
            return UnsafeCast.toInt(strArr[i2], 0);
        }
        return 0;
    }

    public long getLong(int i2) {
        String[] strArr = this.mCursorList.get(this.mIndex);
        if (i2 < strArr.length) {
            return UnsafeCast.toLong(strArr[i2], 0);
        }
        return 0;
    }

    public String getString(int i2) {
        String[] strArr = this.mCursorList.get(this.mIndex);
        if (i2 >= strArr.length) {
            return "";
        }
        if (sDecodeMap.contains(Integer.valueOf(i2))) {
            return ArgumentsUtil.decode(strArr[i2]);
        }
        return strArr[i2];
    }

    public boolean moveToFirst() {
        this.mIndex = 0;
        return true;
    }

    public boolean moveToLast() {
        this.mIndex = getCount() - 1;
        return true;
    }

    public boolean moveToNext() {
        if (this.mIndex >= getCount() - 1) {
            return false;
        }
        this.mIndex++;
        return true;
    }

    public boolean moveToPosition(int i2) {
        if (i2 < 0 || i2 >= getCount()) {
            return false;
        }
        this.mIndex = i2;
        return true;
    }
}
