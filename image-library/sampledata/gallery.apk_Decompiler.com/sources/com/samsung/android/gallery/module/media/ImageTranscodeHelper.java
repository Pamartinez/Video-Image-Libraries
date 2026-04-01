package com.samsung.android.gallery.module.media;

import A.a;
import com.samsung.android.gallery.support.utils.FileType;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sum.core.types.MimeType;
import com.samsung.android.sum.solution.ImageTranscoder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageTranscodeHelper {
    private final String mInputPath;
    private final String mOutputPath;

    public ImageTranscodeHelper(String str, String str2) {
        this.mInputPath = str;
        this.mOutputPath = str2;
    }

    private MimeType getMimeType(String str) {
        String mimeType = FileType.getMimeType(str);
        mimeType.getClass();
        char c5 = 65535;
        switch (mimeType.hashCode()) {
            case -1487464693:
                if (mimeType.equals("image/heic")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1487464690:
                if (mimeType.equals("image/heif")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1487394660:
                if (mimeType.equals("image/jpeg")) {
                    c5 = 2;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 1:
                return MimeType.IMAGE_HEIC;
            case 2:
                return MimeType.IMAGE_JPEG;
            default:
                return null;
        }
    }

    public String transcode() {
        long currentTimeMillis = System.currentTimeMillis();
        MimeType mimeType = getMimeType(this.mInputPath);
        MimeType mimeType2 = getMimeType(this.mOutputPath);
        if (mimeType == null || mimeType2 == null) {
            return null;
        }
        FileUtils.createNewFileIfAbsent(this.mOutputPath);
        String transcodeInternal = transcodeInternal(mimeType, mimeType2);
        Log.d("ImageTranscodeHelper", "transcode -> " + Logger.getEncodedString(transcodeInternal) + " +" + (System.currentTimeMillis() - currentTimeMillis));
        return transcodeInternal;
    }

    public String transcodeInternal(MimeType mimeType, MimeType mimeType2) {
        ImageTranscoder build;
        try {
            build = ImageTranscoder.newBuilder().setInputMimeType(mimeType).setOutputMimeType(mimeType2).build();
            String transcode = build.transcode(this.mInputPath, this.mOutputPath);
            build.close();
            return transcode;
        } catch (Exception e) {
            a.s(e, new StringBuilder("transcode failed e="), "ImageTranscodeHelper");
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
