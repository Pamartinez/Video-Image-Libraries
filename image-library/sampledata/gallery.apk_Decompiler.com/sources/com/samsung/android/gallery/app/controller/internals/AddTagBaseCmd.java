package com.samsung.android.gallery.app.controller.internals;

import Ad.C0720a;
import C3.C0392b;
import L5.b;
import M4.j;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.tag.MyTagUpdater;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AddTagBaseCmd extends AddTagCmd {
    private final OnMyTagListener mListener;
    private final MediaItem mMediaItem;
    private final ArrayList<MediaItem> mPrevList;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnMyTagListener {
        void onSelectDone(ArrayList<String> arrayList);
    }

    public AddTagBaseCmd(MediaItem mediaItem, ArrayList<MediaItem> arrayList, OnMyTagListener onMyTagListener) {
        this.mMediaItem = mediaItem;
        this.mPrevList = arrayList;
        this.mListener = onMyTagListener;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$contains$0(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.getTitle() == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getPrevList$2(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.getTitle() == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onTagSelected$4(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.getTitle() == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onTagSelected$6(String str) {
        return !TextUtils.isEmpty(str);
    }

    public boolean contains(String str) {
        return this.mPrevList.stream().filter(new j(10)).anyMatch(new C0392b(str, 7));
    }

    public ArrayList<String> getPrevList() {
        return (ArrayList) this.mPrevList.stream().filter(new j(11)).map(new b(26)).collect(Collectors.toCollection(new C0720a(1)));
    }

    public int getPrevListCount() {
        return this.mPrevList.size();
    }

    public void onTagSelected(ArrayList<String> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = (ArrayList) this.mPrevList.stream().filter(new j(12)).map(new b(27)).collect(Collectors.toCollection(new C0720a(1)));
        ArrayList arrayList4 = (ArrayList) arrayList3.stream().map(new b(28)).collect(Collectors.toCollection(new C0720a(1)));
        Iterator it = ((ArrayList) arrayList.stream().filter(new j(13)).collect(Collectors.toCollection(new C0720a(1)))).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (arrayList4.contains(str.toLowerCase(Locale.getDefault()))) {
                arrayList3.remove(str);
            } else {
                arrayList2.add(str);
            }
        }
        new MyTagUpdater(getContext(), getBlackboard(), this.mMediaItem, arrayList2, arrayList3).execute(new Void[0]);
        this.mListener.onSelectDone(arrayList);
    }
}
