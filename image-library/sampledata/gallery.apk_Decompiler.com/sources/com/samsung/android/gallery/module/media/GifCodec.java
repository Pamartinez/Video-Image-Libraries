package com.samsung.android.gallery.module.media;

import A.a;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.media.quramsoft.AgifDecoder;
import com.samsung.android.gallery.module.media.quramsoft.AgifEncoder;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GifCodec {
    public static boolean crop(Uri uri, String str, Rect rect, int i2, int i7, long j2) {
        String str2;
        long crop;
        long j3 = j2;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            str2 = str;
            try {
                File file = new File(str2);
                long crop2 = crop(uri, file, rect, i2, i7, 1);
                if (crop2 > j3) {
                    int ceil = (int) Math.ceil((double) (((float) crop2) / ((float) j3)));
                    while (true) {
                        int i8 = ceil + 1;
                        crop = crop(uri, file, rect, i2, i7, ceil);
                        if (crop <= j3) {
                            break;
                        }
                        ceil = i8;
                    }
                    crop2 = crop;
                }
                Log.d("GifCodec", "crop" + Logger.vt(Integer.valueOf(i2), Integer.valueOf(i7), Long.valueOf(crop2), Long.valueOf(currentTimeMillis)));
                return true;
            } catch (IllegalStateException e) {
                e = e;
                Log.e((CharSequence) "GifCodec", "crop failed", (Throwable) e);
                FileUtils.delete(str2);
                return false;
            }
        } catch (IllegalStateException e7) {
            e = e7;
            str2 = str;
            Log.e((CharSequence) "GifCodec", "crop failed", (Throwable) e);
            FileUtils.delete(str2);
            return false;
        }
    }

    public static long crop(Uri uri, File file, Rect rect, int i2, int i7, int i8) {
        AgifEncoder of2;
        long currentTimeMillis = System.currentTimeMillis();
        FileUtils.delete(file);
        Bitmap bitmap = null;
        try {
            AgifDecoder of3 = AgifDecoder.of(AppResources.getAppContext(), uri);
            try {
                of2 = AgifEncoder.of(file.getPath());
                int width = of3.getWidth();
                int height = of3.getHeight();
                int numOfFrame = of3.getNumOfFrame();
                if (of2.start(i2, i7, numOfFrame)) {
                    bitmap = BitmapUtils.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                    for (int i10 = 0; i10 < numOfFrame; i10++) {
                        of3.decodeFrame(bitmap);
                        int delay = of3.getDelay();
                        if (delay <= 0) {
                            delay = 100;
                        }
                        if (i10 % i8 == 0) {
                            of2.setDelay(delay * i8);
                            Bitmap crop = BitmapUtils.crop(bitmap, rect, i2, i7);
                            boolean addFrameTP = of2.addFrameTP(crop);
                            if (bitmap != crop) {
                                BitmapUtils.putBitmap(crop);
                            }
                            if (!addFrameTP) {
                                throw new IllegalStateException("agif encoding failed to add bitmap");
                            }
                        }
                    }
                    a.q(of2);
                    a.p(of3);
                    BitmapUtils.putBitmap(bitmap);
                    Log.d("GifCodec", "crop {s=" + i8 + ",n=" + numOfFrame + GlobalPostProcInternalPPInterface.SPLIT_REGEX + file.length() + "} +" + (System.currentTimeMillis() - currentTimeMillis));
                    return file.length();
                }
                throw new IllegalStateException("agif encoding failed to start");
            } catch (Throwable th) {
                if (of3 != null) {
                    a.p(of3);
                }
                throw th;
            }
            throw th;
        } catch (Error | Exception e) {
            throw new IllegalStateException(e.getMessage());
        } catch (Throwable th2) {
            BitmapUtils.putBitmap(bitmap);
            throw th2;
        }
    }
}
