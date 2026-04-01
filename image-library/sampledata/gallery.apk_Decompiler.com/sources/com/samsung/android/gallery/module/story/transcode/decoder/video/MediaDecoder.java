package com.samsung.android.gallery.module.story.transcode.decoder.video;

import android.graphics.Bitmap;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.story.FilterHolder;
import com.samsung.android.gallery.module.story.FontTypefaceUtils;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.story.TitleAlign;
import com.samsung.android.gallery.module.story.transcode.config.EncodeInfo;
import com.samsung.android.gallery.module.story.transcode.config.FrameProperty;
import com.samsung.android.gallery.module.story.transcode.config.Mode;
import com.samsung.android.gallery.module.story.transcode.decoder.video.ImageDecoder;
import com.samsung.android.gallery.module.story.transcode.decoder.video.VideoDecoder;
import com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecoderFrameManager;
import com.samsung.android.gallery.module.story.transcode.util.OnErrorListener;
import com.samsung.android.gallery.module.story.transcode.util.ThumbProvider;
import com.samsung.android.gallery.module.story.transcode.util.UnsupportedCodecException;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import h4.C0464a;
import ic.l;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDecoder {
    private final DecoderFrameManager mDecodeFrameManager;
    private final Decoder[] mDecoders = new Decoder[2];
    private EncodeInfo mEncodeInfo;
    private final OnErrorListener mErrorListener;
    protected Filter mFilter;
    private final ArrayList<ThumbnailInterface> mItems;
    private final int mOutputHeight;
    private final int mOutputWidth;
    protected int mPreparedIndex;
    private final String mSubtitle;
    private final ThumbProvider mThumbProvider;
    private final String mTitle;
    protected Decoder mTitleDecoder;

    public MediaDecoder(ArrayList<ThumbnailInterface> arrayList, EncodeInfo encodeInfo, OnErrorListener onErrorListener) {
        this.mItems = arrayList;
        this.mDecodeFrameManager = new DecoderFrameManager();
        this.mEncodeInfo = encodeInfo;
        this.mErrorListener = onErrorListener;
        this.mOutputWidth = encodeInfo.outWidth;
        this.mOutputHeight = encodeInfo.outHeight;
        this.mThumbProvider = new ThumbProvider(arrayList);
        this.mTitle = encodeInfo.title;
        this.mSubtitle = encodeInfo.subTitle;
    }

    private Decoder createImageDecoder(ThumbnailInterface thumbnailInterface, int i2, boolean z) {
        String str;
        ImageDecoder.Builder id = new ImageDecoder.Builder().setId(i2);
        if (thumbnailInterface.isUriItem()) {
            str = null;
        } else {
            str = thumbnailInterface.getPath();
        }
        ImageDecoder.Builder orientation = id.setData(str, ContentUri.getUri(thumbnailInterface)).setSize(this.mOutputWidth, this.mOutputHeight).setThumbnailProvider(this.mThumbProvider).setOrientation(getOrientation(thumbnailInterface));
        boolean z3 = true;
        ImageDecoder.Builder immutable = orientation.useBlurBackground(true).setImmutable(z);
        if (!MediaItemStory.isAiEditEffect(thumbnailInterface) || MediaItemStory.getEffectItem(thumbnailInterface) == null) {
            z3 = false;
        }
        return immutable.withAiEffect(z3).build();
    }

    private Bitmap createTitleImage() {
        ThumbnailInterface thumbnailInterface = this.mItems.get(0);
        TitleAlign titleAlign = getTitleAlign(thumbnailInterface);
        return new TitleImage().createTitleImage(this.mTitle, this.mSubtitle, this.mOutputWidth, this.mOutputHeight, titleAlign.ordinal(), FontTypefaceUtils.getTextFont(MediaItemStory.getStorySaType(thumbnailInterface), thumbnailInterface.getAlbumID(), StringCompat.isKorean(this.mTitle)));
    }

    private Decoder createVideoDecoder(ThumbnailInterface thumbnailInterface, int i2) {
        try {
            VideoDecoder.Builder id = new VideoDecoder.Builder().setId(i2);
            if (thumbnailInterface.isUriItem()) {
                id.setData(ContentUri.getUri(thumbnailInterface));
            } else {
                id.setData(thumbnailInterface.getPath());
            }
            id.setStartMs(this.mEncodeInfo.getItemStartTimeMs(thumbnailInterface)).setDuration(this.mEncodeInfo.getItemDuration(thumbnailInterface)).setSize(this.mOutputWidth, this.mOutputHeight).setThumbnailProvider(this.mThumbProvider).withFilter(this.mFilter).withErrorListener(this.mErrorListener).withMotionPhoto(thumbnailInterface, isMotionPhotoMode(thumbnailInterface)).withDecoderFrameManager(this.mDecodeFrameManager).setBackgroundBitmap(this.mEncodeInfo.backgroundBitmap).setForeGroundBitmap(this.mEncodeInfo.foregroundBitmap).setDisplayPositionRect(this.mEncodeInfo.displayPositionRect).setCornerRadius(this.mEncodeInfo.cornerRadius).setScaleType(this.mEncodeInfo.scaleType);
            return id.build();
        } catch (UnsupportedCodecException e) {
            Log.e((CharSequence) "MediaDecoder", "unsupported codec", e.getMessage());
            e.printStackTrace();
            return createImageDecoder(thumbnailInterface, i2, true);
        }
    }

    private int getOrientation(ThumbnailInterface thumbnailInterface) {
        if (thumbnailInterface.isVideo() || thumbnailInterface.isBroken()) {
            return 0;
        }
        return thumbnailInterface.getOrientation();
    }

    private TitleAlign getTitleAlign(FileItemInterface fileItemInterface) {
        if (PreferenceFeatures.OneUi5x.SUPPORT_STORIES_TITLE_ALIGN) {
            return TitleAlign.getTitleAlign(fileItemInterface);
        }
        return TitleAlign.BOTTOM_MIDDLE;
    }

    private boolean isMotionPhotoMode(ThumbnailInterface thumbnailInterface) {
        if (!Mode.MOTION_PHOTO.equals(this.mEncodeInfo.mode) || !thumbnailInterface.isMotionPhoto()) {
            return false;
        }
        return true;
    }

    private Decoder prepareDecoder(int i2) {
        ThumbnailInterface thumbnailInterface = this.mItems.get(i2);
        Log.d("MediaDecoder", C0086a.i(i2, "prepareDecoder@"), toDebugString(thumbnailInterface));
        if (StoryHelper.isVideoItem(thumbnailInterface) || isMotionPhotoMode(thumbnailInterface)) {
            return createVideoDecoder(thumbnailInterface, i2);
        }
        return createImageDecoder(thumbnailInterface, i2, false);
    }

    private boolean prepareRequired(int i2, int i7) {
        if (i2 - i7 == 0) {
            return true;
        }
        return false;
    }

    private void prepareTitleDecoder() {
        try {
            this.mTitleDecoder = new ImageDecoder.Builder().setSize(this.mOutputWidth, this.mOutputHeight).setBitmap(createTitleImage()).setThumbnailProvider(this.mThumbProvider).build();
        } catch (FileNotFoundException e) {
            this.mErrorListener.onError(e);
        }
    }

    private void releaseDecoder(int i2) {
        int i7 = i2 % 2;
        Decoder decoder = this.mDecoders[i7];
        if (decoder != null) {
            decoder.release();
            this.mDecoders[i7] = null;
        }
    }

    private Decoder switchToImageDecoderOnFail(int i2, Decoder decoder) {
        Decoder createImageDecoder = createImageDecoder(this.mItems.get(i2), i2, true);
        this.mDecoders[i2 % 2] = createImageDecoder;
        decoder.release();
        return createImageDecoder;
    }

    private String toDebugString(ThumbnailInterface thumbnailInterface) {
        String str;
        String str2;
        String str3;
        StringBuilder sb2 = new StringBuilder("MediaItem@");
        sb2.append(thumbnailInterface.hashCode());
        sb2.append("(");
        sb2.append(thumbnailInterface.getWidth());
        sb2.append("x");
        sb2.append(thumbnailInterface.getHeight());
        sb2.append(") ");
        sb2.append(thumbnailInterface.getFileId());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(thumbnailInterface.getMediaId());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(thumbnailInterface.getStorageType());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (thumbnailInterface.isImage()) {
            str = "I";
        } else {
            str = "V";
        }
        sb2.append(str);
        if (StoryHelper.isVideoItem(thumbnailInterface)) {
            str2 = "v";
        } else {
            str2 = "i";
        }
        sb2.append(str2);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (thumbnailInterface.isUriItem()) {
            str3 = Logger.getEncodedString((Object) ContentUri.getUri(thumbnailInterface));
        } else {
            str3 = Logger.getEncodedString(thumbnailInterface.getPath());
        }
        sb2.append(str3);
        return sb2.toString();
    }

    private void updateDecoder(int i2) {
        if (prepareRequired(this.mPreparedIndex, i2) && this.mPreparedIndex < this.mItems.size() - 1) {
            int i7 = this.mPreparedIndex + 1;
            this.mPreparedIndex = i7;
            if (i7 < this.mItems.size()) {
                releaseDecoder(this.mPreparedIndex);
                Decoder[] decoderArr = this.mDecoders;
                int i8 = this.mPreparedIndex;
                decoderArr[i8 % 2] = prepareDecoder(i8);
            }
        }
        if (i2 > 1 && i2 > this.mItems.size() - 2) {
            releaseDecoder(i2 - 1);
        }
    }

    public void draw(ArrayList<FrameProperty> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            updateDecoder(arrayList.get(0).getIndex());
            Iterator<FrameProperty> it = arrayList.iterator();
            while (it.hasNext()) {
                FrameProperty next = it.next();
                Decoder decoder = getDecoder(next.getIndex());
                if (next.inTransition()) {
                    decoder.preRelease();
                }
                try {
                    decoder.draw(next);
                } catch (UnsupportedCodecException e) {
                    Log.e("MediaDecoder", "fail to decode video, switch to image = " + e.getMessage());
                    e.printStackTrace();
                    switchToImageDecoderOnFail(next.getIndex(), decoder).draw(next);
                }
            }
        }
    }

    public void drawTitle(float f) {
        float f5 = 1.0f;
        if (f >= 0.9f) {
            f5 = 1.0f - ((f - 0.9f) / 0.100000024f);
        }
        Decoder decoder = this.mTitleDecoder;
        if (decoder != null) {
            decoder.draw(new FrameProperty.Builder().setAlpha(f5).build());
        }
    }

    public Decoder getDecoder(int i2) {
        return this.mDecoders[i2 % 2];
    }

    public void initEffectFilter(String str) {
        Filter findCurrentSepFilter = FilterHolder.findCurrentSepFilter(str);
        this.mFilter = findCurrentSepFilter;
        this.mThumbProvider.setFilter(findCurrentSepFilter);
    }

    public void interrupt() {
        Arrays.stream(this.mDecoders).filter(new C0464a(9)).forEach(new l(19));
    }

    public void prepareDecoders() {
        for (int i2 = 0; i2 < Math.min(this.mItems.size(), 2); i2++) {
            this.mDecoders[i2] = prepareDecoder(i2);
            this.mPreparedIndex = i2;
        }
        if (!TextUtils.isEmpty(this.mTitle)) {
            prepareTitleDecoder();
        }
    }

    public void release() {
        Arrays.stream(this.mDecoders).filter(new C0464a(9)).forEach(new l(18));
        this.mDecodeFrameManager.clear();
        Decoder decoder = this.mTitleDecoder;
        if (decoder != null) {
            decoder.release();
        }
    }
}
