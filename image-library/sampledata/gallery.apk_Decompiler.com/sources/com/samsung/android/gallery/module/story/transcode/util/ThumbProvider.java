package com.samsung.android.gallery.module.story.transcode.util;

import C4.c;
import N2.j;
import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.R$color;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.effectfilter.ImageFilterApplier;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapSizeHolder;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.story.smartrect.StoryHighlightRect;
import com.samsung.android.gallery.module.thumbnail.IconResources;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThumbProvider {
    private final ArrayList<ThumbnailInterface> mBrokenItems = new ArrayList<>();
    protected Filter mFilter = Filter.NONE;
    private final ImageFilterApplier mImageFilterApplier = new ImageFilterApplier();
    private final ArrayList<ThumbnailInterface> mItems;
    protected final ArrayList<Integer> mRecyclable = new ArrayList<>();

    public ThumbProvider(ArrayList<ThumbnailInterface> arrayList) {
        this.mItems = arrayList;
    }

    private Bitmap applyFilter(Bitmap bitmap, Filter filter, boolean z) {
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.StoriesFilter) || bitmap == null) {
            return bitmap;
        }
        AtomicReference atomicReference = new AtomicReference(bitmap);
        if (!z || filter.noneFilter()) {
            Filter filter2 = filter;
            return this.mImageFilterApplier.applySync((Bitmap) atomicReference.get(), filter2, filter2.getIntensity(), z);
        }
        new LatchBuilder("").addWorker(new c(this, atomicReference, filter, z, bitmap)).setTimeout(2000).start();
        return (Bitmap) atomicReference.get();
    }

    private Bitmap createBrokenImage() {
        return IconResources.createIconBitmap(R$drawable.gallery_ic_timeview_error, R$color.cloud_only_image_bg, ThumbKind.MEDIUM_KIND.size());
    }

    private Bitmap getBlurBitmap(ThumbnailInterface thumbnailInterface, int i2) {
        int i7;
        Bitmap blurAfterResize = BitmapUtils.blurAfterResize(AppResources.getAppContext(), getThumbnail(thumbnailInterface, i2), 64);
        if (thumbnailInterface.isBroken() || thumbnailInterface.isVideo()) {
            i7 = 0;
        } else {
            i7 = thumbnailInterface.getOrientation();
        }
        if (blurAfterResize != null) {
            return BitmapUtils.rotateBitmap(blurAfterResize, i7);
        }
        return null;
    }

    private Rect[] getFitCenterRect(int i2, int i7) {
        return new Rect[]{new Rect(0, 0, i2, i7), new Rect(0, 0, i2, i7)};
    }

    private Bitmap getImageInternal(ThumbnailInterface thumbnailInterface, int i2) {
        if (i2 == 1) {
            return getBlurBitmap(thumbnailInterface, i2);
        }
        return getPreviewBitmap(thumbnailInterface, i2);
    }

    private Bitmap getPreviewBitmap(ThumbnailInterface thumbnailInterface, int i2) {
        Bitmap previewBitmap = getPreviewBitmap(thumbnailInterface);
        if (previewBitmap != null) {
            return previewBitmap;
        }
        Bitmap thumbnail = getThumbnail(thumbnailInterface, i2);
        if (thumbnail != null) {
            this.mRecyclable.add(Integer.valueOf(thumbnail.hashCode()));
        }
        return thumbnail;
    }

    private int getTargetSize(FileItemInterface fileItemInterface) {
        int longSideLength = DeviceInfo.getDefaultDisplay().getLongSideLength();
        if (Math.max(fileItemInterface.getWidth(), fileItemInterface.getHeight()) <= longSideLength) {
            return longSideLength;
        }
        return BitmapSizeHolder.size();
    }

    private Bitmap getThumbnail(ThumbnailInterface thumbnailInterface, int i2) {
        ThumbKind thumbKind;
        if (i2 == 0) {
            thumbKind = ThumbKind.LARGE_KIND;
        } else {
            thumbKind = ThumbKind.FREE_KIND;
        }
        return ThumbnailLoader.getInstance().loadThumbnailSync(thumbnailInterface, thumbKind);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$applyFilter$0(AtomicReference atomicReference, Filter filter, boolean z, Bitmap bitmap) {
        Bitmap applySync = this.mImageFilterApplier.applySync((Bitmap) atomicReference.get(), filter, filter.getIntensity(), z);
        atomicReference.set(applySync);
        if (bitmap != applySync) {
            recycleBitmap(bitmap);
        }
    }

    public Bitmap getBlurImage(int i2) {
        return getImage(i2, 1, false);
    }

    public Rect[] getDisplayRectWithSmartCrop(int i2, Rect rect, int i7, int i8) {
        if (i2 < 0 || i2 >= this.mItems.size()) {
            return getFitCenterRect(i7, i8);
        }
        try {
            FileItemInterface fileItemInterface = this.mItems.get(i2);
            if (this.mBrokenItems.contains(fileItemInterface)) {
                return getFitCenterRect(i7, i8);
            }
            StoryHighlightRect.RectInfo build = new StoryHighlightRect.RectBuilder(fileItemInterface).setScreenRect(rect).setImageSize(i7, i8).withSmartCrop(PreferenceFeatures.isEnabled(PreferenceFeatures.StoryHighlightSmartCrop)).withRotation(false).build();
            return new Rect[]{build.displayRect, build.imageCropRect};
        } catch (Exception e) {
            j.s(e, new StringBuilder("getDisplayRectWithSmartCrop error = "), "ThumbnailProvider");
            return getFitCenterRect(i7, i8);
        }
    }

    public Bitmap getEffectImage(int i2) {
        return getImage(i2, 0, true);
    }

    public Bitmap getImage(int i2) {
        return getImage(i2, 0, false);
    }

    public void recycleBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        if (this.mRecyclable.contains(Integer.valueOf(bitmap.hashCode()))) {
            ThumbnailLoader.getInstance().recycle((String) null, bitmap);
        } else {
            bitmap.recycle();
        }
    }

    public void setFilter(Filter filter) {
        this.mFilter = filter;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0069  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap getImage(int r8, int r9, boolean r10) {
        /*
            r7 = this;
            java.lang.String r0 = "getImage null bitmap = "
            java.lang.String r1 = "ThumbnailProvider"
            r2 = 0
            r3 = 0
            if (r10 == 0) goto L_0x001a
            java.util.ArrayList<com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface> r10 = r7.mItems     // Catch:{ Exception -> 0x0017 }
            java.lang.Object r8 = r10.get(r8)     // Catch:{ Exception -> 0x0017 }
            com.samsung.android.gallery.database.dbtype.FileItemInterface r8 = (com.samsung.android.gallery.database.dbtype.FileItemInterface) r8     // Catch:{ Exception -> 0x0017 }
            com.samsung.android.gallery.database.dbtype.FileItemInterface r8 = com.samsung.android.gallery.module.data.MediaItemStory.getEffectItem(r8)     // Catch:{ Exception -> 0x0017 }
        L_0x0014:
            com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface r8 = (com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface) r8     // Catch:{ Exception -> 0x0017 }
            goto L_0x0021
        L_0x0017:
            r8 = move-exception
            r9 = r3
            goto L_0x004c
        L_0x001a:
            java.util.ArrayList<com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface> r10 = r7.mItems     // Catch:{ Exception -> 0x0017 }
            java.lang.Object r8 = r10.get(r8)     // Catch:{ Exception -> 0x0017 }
            goto L_0x0014
        L_0x0021:
            android.graphics.Bitmap r10 = r7.getImageInternal(r8, r9)     // Catch:{ Exception -> 0x0033 }
            if (r10 == 0) goto L_0x0038
            com.samsung.android.gallery.module.effectfilter.Filter r0 = r7.mFilter     // Catch:{ Exception -> 0x0033 }
            r4 = 1
            if (r9 == r4) goto L_0x002d
            goto L_0x002e
        L_0x002d:
            r4 = r2
        L_0x002e:
            android.graphics.Bitmap r7 = r7.applyFilter(r10, r0, r4)     // Catch:{ Exception -> 0x0033 }
            return r7
        L_0x0033:
            r9 = move-exception
            r6 = r9
            r9 = r8
            r8 = r6
            goto L_0x004c
        L_0x0038:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0033 }
            r9.<init>(r0)     // Catch:{ Exception -> 0x0033 }
            long r4 = r8.getFileId()     // Catch:{ Exception -> 0x0033 }
            r9.append(r4)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r9 = r9.toString()     // Catch:{ Exception -> 0x0033 }
            com.samsung.android.gallery.support.utils.Log.w(r1, r9)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0057
        L_0x004c:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r0 = "getImage error = "
            r10.<init>(r0)
            A.a.s(r8, r10, r1)
            r8 = r9
        L_0x0057:
            if (r8 == 0) goto L_0x0067
            boolean r9 = r8.isUriItem()
            if (r9 != 0) goto L_0x0067
            java.lang.String r9 = r8.getPath()
            android.graphics.Bitmap r3 = android.graphics.BitmapFactory.decodeFile(r9)
        L_0x0067:
            if (r3 != 0) goto L_0x0074
            android.graphics.Bitmap r3 = r7.createBrokenImage()
            if (r8 == 0) goto L_0x0074
            java.util.ArrayList<com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface> r9 = r7.mBrokenItems
            r9.add(r8)
        L_0x0074:
            com.samsung.android.gallery.module.effectfilter.Filter r8 = r7.mFilter
            android.graphics.Bitmap r7 = r7.applyFilter(r3, r8, r2)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.story.transcode.util.ThumbProvider.getImage(int, int, boolean):android.graphics.Bitmap");
    }

    private Bitmap getPreviewBitmap(ThumbnailInterface thumbnailInterface) {
        return BitmapUtils.resizeForMaxBitmapSize(BitmapUtils.getDecodedBitmap(thumbnailInterface.getPath(), BitmapOptionsFactory.of(thumbnailInterface).adjustInSampleSize(getTargetSize(thumbnailInterface))));
    }
}
