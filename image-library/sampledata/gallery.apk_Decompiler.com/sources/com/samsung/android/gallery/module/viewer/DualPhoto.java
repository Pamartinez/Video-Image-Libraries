package com.samsung.android.gallery.module.viewer;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.abstraction.DualShotState;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.cache.BytesBuffer;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.File;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DualPhoto {
    private BytesBuffer mAltStream;
    private Bitmap mCoverBitmap;
    private DualShotState mFileState;
    private HashMap<String, SefData> mMap = new HashMap<>();
    private MediaItem mMediaItem;
    private DualShotState mState;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SefData {
        byte[] data;
        int type;

        public /* synthetic */ SefData(int i2) {
            this();
        }

        private SefData() {
        }
    }

    public DualPhoto(MediaItem mediaItem) {
        DualShotState dualShotState;
        this.mMediaItem = mediaItem;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String path = mediaItem.getPath();
            if (path != null) {
                File file = new File(path);
                String[] keyNameArray = SeApiCompat.getSefFileCompat().getKeyNameArray(file);
                if (keyNameArray != null) {
                    if (keyNameArray.length != 0) {
                        for (String str : keyNameArray) {
                            SefData sefData = new SefData(0);
                            sefData.data = SeApiCompat.getSefFileCompat().getData(file, str);
                            sefData.type = SeApiCompat.getSefFileCompat().getDataType(file, str);
                            this.mMap.put(str, sefData);
                        }
                        if (this.mMap.get(SefInfo.DUAL_SHOT_IMAGE_LIVE_FOCUS.keyName) != null) {
                            dualShotState = DualShotState.Wide;
                        } else {
                            dualShotState = DualShotState.CloseUp;
                        }
                        this.mState = dualShotState;
                        this.mFileState = dualShotState;
                        this.mAltStream = getAltStream(dualShotState);
                        Log.d("DualPhoto", this + " +" + (System.currentTimeMillis() - currentTimeMillis));
                        return;
                    }
                }
                Log.e("DualPhoto", "constructor#parse failed. empty sef");
            }
        } catch (Exception e) {
            Log.e((CharSequence) "DualPhoto", "constructor#parse failed", (Throwable) e);
        }
    }

    private BytesBuffer getAltStream(DualShotState dualShotState) {
        SefData sefData;
        if (dualShotState == DualShotState.Wide) {
            sefData = this.mMap.get(SefInfo.DUAL_SHOT_IMAGE_LIVE_FOCUS.keyName);
            if (sefData == null) {
                sefData = this.mMap.get(SefInfo.DUAL_SHOT_IMAGE_CLOSEUP.keyName);
            }
        } else {
            sefData = this.mMap.get(SefInfo.DUAL_SHOT_IMAGE_WIDE.keyName);
        }
        if (sefData == null) {
            return null;
        }
        byte[] bArr = sefData.data;
        return new BytesBuffer(bArr, 0, bArr.length);
    }

    public boolean isWide() {
        if (this.mState == DualShotState.Wide) {
            return true;
        }
        return false;
    }

    public void setCoverImage(Bitmap bitmap) {
        this.mCoverBitmap = bitmap;
    }

    public String toString() {
        return "DualPhoto{" + this.mFileState + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mState + "} " + this.mAltStream;
    }
}
