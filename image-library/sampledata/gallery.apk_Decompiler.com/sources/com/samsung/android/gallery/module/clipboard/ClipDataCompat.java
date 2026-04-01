package com.samsung.android.gallery.module.clipboard;

import A4.C0387w;
import O3.o;
import P8.a;
import android.content.ClipData;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.scs.base.utils.Log;
import i.C0212a;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ClipDataCompat {
    public static boolean isShareable(MediaItem mediaItem, boolean z) {
        if (mediaItem == null || mediaItem.isPostProcessing() || mediaItem.isDrm() || mediaItem.isBroken()) {
            return false;
        }
        if (z) {
            return true;
        }
        if (mediaItem.isCloudOnly() || ContentUri.getUri(mediaItem) == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String[] lambda$of$2(int i2) {
        return new String[i2];
    }

    public static ClipData of(MediaItem[] mediaItemArr, CharSequence charSequence) {
        return of(mediaItemArr, charSequence, false);
    }

    public static Uri requireUserInfo(Uri uri, String str) {
        if (str == null || uri == null || !TextUtils.isEmpty(uri.getUserInfo())) {
            return uri;
        }
        Uri.Builder buildUpon = uri.buildUpon();
        StringBuilder t = C0212a.t(str, Log.TAG_SEPARATOR);
        t.append(uri.getEncodedAuthority());
        return buildUpon.encodedAuthority(t.toString()).build();
    }

    public static ClipData of(MediaItem[] mediaItemArr, CharSequence charSequence, boolean z) {
        return of(List.of(mediaItemArr), charSequence, z, false);
    }

    public static ClipData of(List<MediaItem> list, CharSequence charSequence, boolean z, boolean z3) {
        long currentTimeMillis = System.currentTimeMillis();
        List list2 = (List) list.stream().filter(new a(z3, 0)).collect(Collectors.toList());
        String str = null;
        if (!list2.isEmpty()) {
            if (z && SeApiCompat.isMyUserIdAsUserCurrent()) {
                str = "" + SeApiCompat.getMyUserId();
            }
            ClipData clipData = new ClipData(charSequence, (String[]) list2.stream().map(new o(8)).toArray(new C0387w(27)), new ClipData.Item(requireUserInfo(ContentUri.getUri((FileItemInterface) list2.get(0)), str)));
            for (int i2 = 1; i2 < list2.size(); i2++) {
                clipData.addItem(new ClipData.Item(requireUserInfo(ContentUri.getUri((FileItemInterface) list2.get(i2)), str)));
            }
            com.samsung.android.gallery.support.utils.Log.d("ClipDataCompat", "build" + Logger.vt(Integer.valueOf(list.size()), Integer.valueOf(clipData.getItemCount()), str, Long.valueOf(currentTimeMillis)) + "");
            return clipData;
        }
        com.samsung.android.gallery.support.utils.Log.e("ClipDataCompat", "build failed. no data available" + Logger.vt(Integer.valueOf(list.size()), Long.valueOf(currentTimeMillis)));
        return null;
    }
}
