package com.samsung.android.gallery.widget.dragdrop.clipdata;

import A.a;
import Fa.C0571z;
import N2.j;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.net.Uri;
import android.os.PersistableBundle;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import qa.e;
import t4.C0517a;
import vb.C0712a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClipDataCreatorDexImpl implements ClipDataCreator {
    protected MediaItem[] mCoverItems;
    private ClipData mDragClipData;
    protected boolean mFromAlbum;
    protected MediaItem[] mSelectedItems;
    private final DexShortcutIntent mShortcutIntent = new DexShortcutIntent();

    public ClipDataCreatorDexImpl(MediaItem[] mediaItemArr) {
        this.mSelectedItems = mediaItemArr;
    }

    private void addIntentsForAlbums(Context context) {
        Arrays.stream(this.mCoverItems).filter(new C0571z(4)).forEach(new C0712a(this, context, 0));
    }

    private void addIntentsForItems(Context context) {
        MediaItem[] mediaItemArr = this.mSelectedItems;
        if (mediaItemArr.length <= 30) {
            Arrays.stream(mediaItemArr).forEach(new C0712a(this, context, 1));
        }
    }

    private void addShortCutIntentsToClipData(Context context) {
        if (this.mDragClipData == null) {
            return;
        }
        if (this.mFromAlbum) {
            addIntentsForAlbums(context);
        } else {
            addIntentsForItems(context);
        }
    }

    private ClipData buildClipData(MediaItem[] mediaItemArr) {
        int i2;
        ClipData clipData = null;
        if (mediaItemArr == null || mediaItemArr.length <= 0) {
            Log.e("ClipDataCreatorDexImpl", "buildClipData skip. no data");
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (MediaItem mediaItem : mediaItemArr) {
            Uri uri = ContentUri.getUri(mediaItem);
            if (uri != null) {
                arrayList.add(uri);
                arrayList2.add(mediaItem.getMimeType());
            }
        }
        if (arrayList.size() > 0) {
            clipData = new ClipData(createClipDescription(arrayList2), new ClipData.Item((Uri) arrayList.get(0)));
            for (int i7 = 1; i7 < arrayList.size(); i7++) {
                clipData.addItem(new ClipData.Item((Uri) arrayList.get(i7)));
            }
        }
        StringBuilder sb2 = new StringBuilder("buildClipData");
        String str = "in=" + mediaItemArr.length;
        String g = j.g(new StringBuilder("u="), arrayList);
        StringBuilder sb3 = new StringBuilder("c=");
        if (clipData != null) {
            i2 = clipData.getItemCount();
        } else {
            i2 = -1;
        }
        sb3.append(i2);
        a.A(new Object[]{str, g, sb3.toString(), Long.valueOf(currentTimeMillis)}, sb2, "ClipDataCreatorDexImpl");
        return clipData;
    }

    private ClipDescription createClipDescription(List<String> list) {
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putStringArray("secdndfiletype", (String[]) list.stream().distinct().toArray(new C0517a(8)));
        persistableBundle.putString("operation", "copy");
        persistableBundle.putBoolean("isFromGallery", true);
        ClipDescription clipDescription = new ClipDescription("selectedUri", (String[]) list.toArray(new String[0]));
        clipDescription.setExtras(persistableBundle);
        return clipDescription;
    }

    private static Predicate<ClipData.Item> isMatchingItem(MediaItem mediaItem) {
        return new B8.j(mediaItem, 13);
    }

    private static Predicate<ClipData.Item> isValidIntentClipData() {
        return new e(16);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addIntentsForAlbums$1(Context context, MediaItem mediaItem) {
        this.mDragClipData.addItem(new ClipData.Item(this.mShortcutIntent.getForAlbum(context, mediaItem)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addIntentsForItems$2(Context context, MediaItem mediaItem) {
        this.mDragClipData.addItem(new ClipData.Item(this.mShortcutIntent.getForItem(context, mediaItem)));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$createClipDescription$0(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$isValidIntentClipData$3(ClipData.Item item) {
        if (item.getUri() != null || item.getIntent() == null || item.getIntent().getExtras() == null) {
            return false;
        }
        return true;
    }

    public void addExtraDataToClipData(Context context, ArrayList<ClipData.Item> arrayList, MediaItem mediaItem) {
        if (arrayList.stream().filter(new e(15)).filter(isValidIntentClipData()).count() < 30) {
            arrayList.add(new ClipData.Item(this.mShortcutIntent.getForItem(context, mediaItem)));
        }
    }

    public ClipData get(Context context) {
        this.mDragClipData = buildClipData(this.mSelectedItems);
        addShortCutIntentsToClipData(context);
        return this.mDragClipData;
    }

    public void removeExtraDataFromClipData(ArrayList<ClipData.Item> arrayList, MediaItem mediaItem) {
        arrayList.stream().filter(new e(15)).filter(isValidIntentClipData()).filter(isMatchingItem(mediaItem)).findFirst().ifPresent(new f4.a(arrayList, 20));
    }

    public ClipDataCreatorDexImpl(MediaItem[] mediaItemArr, MediaItem[] mediaItemArr2) {
        this.mSelectedItems = mediaItemArr;
        this.mCoverItems = mediaItemArr2;
        this.mFromAlbum = true;
    }
}
