package com.samsung.android.gallery.widget.dragdrop.clipdata;

import android.content.ClipData;
import android.content.Context;
import android.net.Uri;
import android.os.PersistableBundle;
import android.widget.Toast;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$string;
import h4.C0464a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import q8.a;
import t4.C0517a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClipDataCreatorImpl implements ClipDataCreator {
    private boolean mDisableCTWForSplit;
    private final MediaItem[] mSelectedItems;

    public ClipDataCreatorImpl(MediaItem[] mediaItemArr) {
        this.mSelectedItems = mediaItemArr;
    }

    private PersistableBundle disableCTWForSplitViewSide() {
        String str;
        PersistableBundle persistableBundle = new PersistableBundle();
        if (Features.isEnabled(Features.IS_RTL)) {
            str = "com.samsung.android.content.clipdescription.extra.IGNORE_RIGHT_EDGE";
        } else {
            str = "com.samsung.android.content.clipdescription.extra.IGNORE_LEFT_EDGE";
        }
        persistableBundle.putBoolean(str, true);
        return persistableBundle;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$get$1(int i2) {
        return new String[i2];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Uri lambda$get$2(MediaItem mediaItem) {
        if (mediaItem.isUriItem()) {
            return Uri.parse(mediaItem.getPath());
        }
        return ContentUri.getUri(mediaItem);
    }

    public ClipData get(Context context) {
        if (this.mSelectedItems.length > 500) {
            Toast.makeText(context, R$string.two_handed_drag_and_drop_can_not_move_more_than_500_items, 0).show();
        }
        long min = (long) Math.min(500, this.mSelectedItems.length);
        String[] strArr = (String[]) Arrays.stream(this.mSelectedItems).limit(min).map(new a(18)).filter(new C0464a(13)).toArray(new C0517a(9));
        List<Uri> list = (List) Arrays.stream(this.mSelectedItems).limit(min).map(new a(19)).distinct().filter(new com.samsung.android.gallery.module.dynamicview.a(17)).collect(Collectors.toList());
        ClipData clipData = null;
        try {
            for (Uri uri : list) {
                if (clipData == null) {
                    ClipData clipData2 = new ClipData("galleryUri", strArr, new ClipData.Item(uri));
                    try {
                        if (this.mDisableCTWForSplit) {
                            clipData2.getDescription().setExtras(disableCTWForSplitViewSide());
                        } else {
                            clipData2.getDescription().setExtras(new PersistableBundle());
                        }
                        clipData = clipData2;
                    } catch (SecurityException e) {
                        e = e;
                        clipData = clipData2;
                        Log.e("ClipDataCreatorImpl", "addItemToClipData SecurityException occurred.");
                        e.printStackTrace();
                        return clipData;
                    }
                } else {
                    clipData.addItem(new ClipData.Item(uri));
                }
            }
            return clipData;
        } catch (SecurityException e7) {
            e = e7;
        }
    }

    public ClipDataCreatorImpl(MediaItem[] mediaItemArr, boolean z) {
        this.mSelectedItems = mediaItemArr;
        this.mDisableCTWForSplit = z;
    }

    public void removeExtraDataFromClipData(ArrayList<ClipData.Item> arrayList, MediaItem mediaItem) {
    }

    public void addExtraDataToClipData(Context context, ArrayList<ClipData.Item> arrayList, MediaItem mediaItem) {
    }
}
