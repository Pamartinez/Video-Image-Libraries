package com.samsung.android.gallery.app.ui.list.abstraction;

import A4.O;
import A4.f0;
import B8.e;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.samsung.android.gallery.module.abstraction.GridValue;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.YearThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class YearThumbnailRequestHolder extends ThumbnailRequestHolder {
    private final ThumbnailLoadedListener mListener;
    private final int mYearItemCount;
    private final ArrayList<MediaItem> mYearItemList;
    private final String mYearName;
    private final YearThumbnailLoader mYearThumbnailLoader = YearThumbnailLoader.getInstance();

    public YearThumbnailRequestHolder(ListViewHolder listViewHolder, ArrayList<MediaItem> arrayList, ThumbnailLoadedListener thumbnailLoadedListener) {
        super(listViewHolder);
        this.mYearItemList = arrayList;
        this.mListener = thumbnailLoadedListener;
        this.mYearName = getYearName(arrayList.get(0));
        this.mYearItemCount = arrayList.size();
    }

    private synchronized void appendYearData(int i2, Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        FileChannel channel;
        File createNewFileIfAbsent = FileUtils.createNewFileIfAbsent(this.mYearThumbnailLoader.getCacheFilename(this.mYearName));
        ByteBuffer allocate = ByteBuffer.allocate(4096);
        bitmap.copyPixelsToBuffer(allocate);
        ByteBuffer allocate2 = ByteBuffer.allocate(4100);
        allocate2.clear();
        allocate2.putInt(i2);
        allocate2.put(allocate.array());
        allocate2.rewind();
        try {
            fileOutputStream = new FileOutputStream(createNewFileIfAbsent.getAbsolutePath(), true);
            channel = fileOutputStream.getChannel();
            channel.write(allocate2);
            channel.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return;
        throw th;
        throw th;
    }

    private Bitmap bytesToBitmap(byte[] bArr) {
        Bitmap createBitmap = Bitmap.createBitmap(32, 32, Bitmap.Config.ARGB_8888);
        createBitmap.copyPixelsFromBuffer(ByteBuffer.wrap(bArr));
        return createBitmap;
    }

    private boolean drawPartialYearBitmap(Bitmap bitmap, Bitmap bitmap2, int i2) {
        int i7;
        int i8;
        boolean isEnabled = Features.isEnabled(Features.IS_RTL);
        if (isEnabled) {
            i7 = bitmap.getWidth() - 32;
        } else {
            i7 = 0;
        }
        Iterator<MediaItem> it = this.mYearItemList.iterator();
        int i10 = 0;
        while (it.hasNext()) {
            if (it.next().getThumbCacheKey().hashCode() == i2) {
                new Canvas(bitmap).drawBitmap(bitmap2, (float) i7, (float) i10, new Paint());
                return true;
            } else if (isEnabled) {
                i8 = i7 - 32;
                if (i8 < 0) {
                    i8 = bitmap.getWidth() - 32;
                    i10 += 32;
                }
            } else {
                i8 = i7 + 32;
                if (i8 >= bitmap.getWidth()) {
                    i10 += 32;
                    i8 = 0;
                }
            }
        }
        return false;
    }

    private void drawPieceBitmap(Canvas canvas, Paint paint, int i2, int i7, int i8, ArrayList<MediaItem> arrayList) {
        Bitmap bitmap;
        MediaItem mediaItem = this.mYearItemList.get(i8);
        if (mediaItem != null) {
            bitmap = this.mYearThumbnailLoader.getPieceBitmap(this.mYearName, mediaItem.getThumbCacheKey().hashCode());
        } else {
            bitmap = null;
        }
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, (float) i2, (float) i7, paint);
        } else if (mediaItem != null) {
            arrayList.add(mediaItem);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007a, code lost:
        r4 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x007b, code lost:
        r5 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0085, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r5.addSuppressed(r4);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:21:0x0070, B:31:0x0081] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.HashMap<java.lang.Integer, byte[]> getDiskCacheData() {
        /*
            r12 = this;
            java.lang.String r0 = "YearThumbnailRequestHolder"
            long r1 = java.lang.System.currentTimeMillis()
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            com.samsung.android.gallery.module.thumbnail.YearThumbnailLoader r4 = r12.mYearThumbnailLoader
            java.lang.String r5 = r12.mYearName
            java.lang.String r4 = r4.getCacheFilename(r5)
            boolean r5 = com.samsung.android.gallery.support.utils.FileUtils.exists(r4)
            if (r5 != 0) goto L_0x001a
            return r3
        L_0x001a:
            r5 = 0
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0094 }
            r6.<init>(r4)     // Catch:{ Exception -> 0x0094 }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ all -> 0x008a }
            r7 = 16384(0x4000, float:2.2959E-41)
            r4.<init>(r6, r7)     // Catch:{ all -> 0x008a }
            int r7 = r6.available()     // Catch:{ all -> 0x007d }
            float r7 = (float) r7
            r8 = 1166024704(0x45802000, float:4100.0)
            float r7 = r7 / r8
            int r8 = (int) r7
            float r8 = (float) r8
            float r8 = r7 - r8
            int r5 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r5 <= 0) goto L_0x004f
            java.lang.String r5 = "getDiskCacheData cache corrupted"
            java.lang.Float r8 = java.lang.Float.valueOf(r7)     // Catch:{ all -> 0x004d }
            java.lang.Object[] r8 = new java.lang.Object[]{r8}     // Catch:{ all -> 0x004d }
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r0, (java.lang.String) r5, (java.lang.Object[]) r8)     // Catch:{ all -> 0x004d }
            com.samsung.android.gallery.module.thumbnail.YearThumbnailLoader r5 = r12.mYearThumbnailLoader     // Catch:{ all -> 0x004d }
            java.lang.String r8 = r12.mYearName     // Catch:{ all -> 0x004d }
            r5.deleteYearData(r8)     // Catch:{ all -> 0x004d }
            goto L_0x004f
        L_0x004d:
            r5 = move-exception
            goto L_0x0081
        L_0x004f:
            r5 = 4100(0x1004, float:5.745E-42)
            byte[] r8 = new byte[r5]     // Catch:{ all -> 0x004d }
        L_0x0053:
            int r9 = r4.read(r8)     // Catch:{ all -> 0x004d }
            r10 = -1
            if (r9 == r10) goto L_0x0070
            r9 = 0
            r10 = 4
            java.nio.ByteBuffer r9 = java.nio.ByteBuffer.wrap(r8, r9, r10)     // Catch:{ all -> 0x004d }
            int r9 = r9.getInt()     // Catch:{ all -> 0x004d }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x004d }
            byte[] r10 = java.util.Arrays.copyOfRange(r8, r10, r5)     // Catch:{ all -> 0x004d }
            r3.put(r9, r10)     // Catch:{ all -> 0x004d }
            goto L_0x0053
        L_0x0070:
            r4.close()     // Catch:{ all -> 0x007a }
            r6.close()     // Catch:{ Exception -> 0x0077 }
            goto L_0x0099
        L_0x0077:
            r4 = move-exception
            r5 = r7
            goto L_0x0095
        L_0x007a:
            r4 = move-exception
            r5 = r7
            goto L_0x008b
        L_0x007d:
            r7 = move-exception
            r11 = r7
            r7 = r5
            r5 = r11
        L_0x0081:
            r4.close()     // Catch:{ all -> 0x0085 }
            goto L_0x0089
        L_0x0085:
            r4 = move-exception
            r5.addSuppressed(r4)     // Catch:{ all -> 0x007a }
        L_0x0089:
            throw r5     // Catch:{ all -> 0x007a }
        L_0x008a:
            r4 = move-exception
        L_0x008b:
            r6.close()     // Catch:{ all -> 0x008f }
            goto L_0x0093
        L_0x008f:
            r6 = move-exception
            r4.addSuppressed(r6)     // Catch:{ Exception -> 0x0094 }
        L_0x0093:
            throw r4     // Catch:{ Exception -> 0x0094 }
        L_0x0094:
            r4 = move-exception
        L_0x0095:
            r4.printStackTrace()
            r7 = r5
        L_0x0099:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "getDiskCacheData"
            r4.<init>(r5)
            java.lang.String r12 = r12.mYearName
            int r5 = (int) r7
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            java.lang.Object[] r12 = new java.lang.Object[]{r12, r5, r1}
            java.lang.String r12 = com.samsung.android.gallery.support.utils.Logger.vt((java.lang.Object[]) r12)
            r4.append(r12)
            java.lang.String r12 = r4.toString()
            com.samsung.android.gallery.support.utils.Log.p(r0, r12)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.abstraction.YearThumbnailRequestHolder.getDiskCacheData():java.util.HashMap");
    }

    private int getRowCount(int i2, int i7) {
        int i8;
        int i10 = i7 / i2;
        if (i7 % i2 == 0) {
            i8 = 0;
        } else {
            i8 = 1;
        }
        return i10 + i8;
    }

    private String getYearName(MediaItem mediaItem) {
        String dateRaw = mediaItem.getDateRaw();
        if (dateRaw == null) {
            dateRaw = TimeUtil.getIsoLocalDate(mediaItem.getDateTaken());
        }
        return "year" + dateRaw.substring(0, 4);
    }

    private String getYearThumbnailKey(int i2) {
        return YearThumbnailLoader.getYearThumbnailKey(this.mYearName, this.mYearItemCount, GridValue.revert(i2));
    }

    private boolean isInvalidPieceBitmap(Bitmap bitmap) {
        if (bitmap == null || bitmap.getByteCount() != 4096) {
            return true;
        }
        return false;
    }

    private boolean isRecycled(ListViewHolder listViewHolder) {
        if (listViewHolder == null || listViewHolder.getMediaItem() == null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadYearThumbnailAsync$0(AtomicInteger atomicInteger, MediaItem mediaItem, int i2, String str, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        Bitmap bitmap2;
        atomicInteger.getAndDecrement();
        int i7 = 0;
        if (bitmap != null && bitmap.getConfig() == Bitmap.Config.RGBA_F16) {
            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, false);
        }
        if (bitmap == null) {
            bitmap2 = this.mYearThumbnailLoader.getBrokenPieceBitmap();
        } else {
            bitmap2 = bitmap;
        }
        if (bitmap != null && bitmap.getByteCount() > 4096) {
            Log.w("YearThumbnailRequestHolder", "loadYearThumbnailAsync big size bitmap");
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            BitmapOperator stretchable = new BitmapOperator(bitmap).resize(32).crop(RectUtils.getRotatedRect(RectUtils.createCenterCropRect(width, height, mediaItem.getWidth(), mediaItem.getHeight()), width, height, mediaItem.getOrientation())).stretchable(false);
            if (!mediaItem.isVideo()) {
                i7 = mediaItem.getOrientation();
            }
            bitmap2 = stretchable.rotate(i7).apply();
        }
        if (isInvalidPieceBitmap(bitmap2)) {
            Log.e("YearThumbnailRequestHolder", "loadYearThumbnailAsync too big " + Logger.toString(bitmap2) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem);
            return;
        }
        this.mYearThumbnailLoader.putPieceBitmap(this.mYearName, i2, bitmap2);
        appendYearData(i2, bitmap2);
        Bitmap partialYearBitmap = this.mYearThumbnailLoader.getPartialYearBitmap(str);
        if (partialYearBitmap != null && drawPartialYearBitmap(partialYearBitmap, bitmap2, i2)) {
            onLoadedYearBitmap(partialYearBitmap);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$loadYearThumbnailAsync$1() {
        return isRecycled(this.mViewHolder);
    }

    private Bitmap mergeMultipleAll(Bitmap bitmap, int i2, int i7, ArrayList<MediaItem> arrayList) {
        int i8;
        int i10 = i2 * 32;
        int i11 = i7 * 32;
        if (!(bitmap != null && bitmap.getWidth() == i10 && bitmap.getHeight() == i11)) {
            bitmap = Bitmap.createBitmap(i10, i11, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        boolean isEnabled = Features.isEnabled(Features.IS_RTL);
        if (isEnabled) {
            i8 = i10 - 32;
        } else {
            i8 = 0;
        }
        int i12 = i8;
        int i13 = 0;
        int i14 = 0;
        while (i14 < this.mYearItemCount) {
            YearThumbnailRequestHolder yearThumbnailRequestHolder = this;
            ArrayList<MediaItem> arrayList2 = arrayList;
            yearThumbnailRequestHolder.drawPieceBitmap(canvas, paint, i12, i13, i14, arrayList2);
            if (isEnabled) {
                i12 -= 32;
                if (i12 < 0) {
                    i13 += 32;
                    i12 = i10 - 32;
                }
            } else {
                i12 += 32;
                if (i12 >= bitmap.getWidth()) {
                    i13 += 32;
                    i12 = 0;
                }
            }
            i14++;
            this = yearThumbnailRequestHolder;
            arrayList = arrayList2;
        }
        return bitmap;
    }

    private void onLoadedYearBitmap(Bitmap bitmap) {
        if (this.mListener != null && !isRecycled(this.mViewHolder)) {
            this.mListener.onLoaded(bitmap, this, this.mViewHolder.getThumbKind());
        }
    }

    public int getChildItemCount() {
        return this.mYearItemCount;
    }

    public Bitmap getPartialYearThumbnail(int i2) {
        return this.mYearThumbnailLoader.getPartialYearBitmap(getYearThumbnailKey(i2));
    }

    public Bitmap getYearThumbnailCache(int i2) {
        return this.mYearThumbnailLoader.getYearBitmap(getYearThumbnailKey(i2));
    }

    public Bitmap getYearThumbnailCacheAny(int i2) {
        return this.mYearThumbnailLoader.getOldYearBitmap(getYearThumbnailKey(i2));
    }

    public void loadYearThumbnailAsync(int i2) {
        YearThumbnailRequestHolder yearThumbnailRequestHolder;
        if (this.mYearItemCount > 0) {
            int revert = GridValue.revert(i2);
            String yearThumbnailKey = getYearThumbnailKey(revert);
            Bitmap yearBitmap = this.mYearThumbnailLoader.getYearBitmap(yearThumbnailKey);
            if (yearBitmap != null) {
                Log.d("YearThumbnailRequestHolder", "loadYearThumbnailAsync Cache{" + yearThumbnailKey + "}");
                onLoadedYearBitmap(yearBitmap);
                return;
            }
            Bitmap partialYearBitmap = this.mYearThumbnailLoader.getPartialYearBitmap(yearThumbnailKey);
            int rowCount = getRowCount(revert, this.mYearItemCount);
            ArrayList arrayList = new ArrayList();
            Bitmap mergeMultipleAll = mergeMultipleAll(partialYearBitmap, revert, rowCount, arrayList);
            Log.d("YearThumbnailRequestHolder", "loadYearThumbnailAsync", this.mYearName, Integer.valueOf(this.mYearItemCount), Integer.valueOf(arrayList.size()));
            if (arrayList.isEmpty()) {
                this.mYearThumbnailLoader.putYearBitmap(yearThumbnailKey, mergeMultipleAll);
                onLoadedYearBitmap(mergeMultipleAll);
                return;
            }
            this.mYearThumbnailLoader.putPartialYearBitmap(yearThumbnailKey, mergeMultipleAll);
            AtomicInteger atomicInteger = new AtomicInteger(arrayList.size());
            HashMap<Integer, byte[]> diskCacheData = getDiskCacheData();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                MediaItem mediaItem = (MediaItem) it.next();
                int hashCode = mediaItem.getThumbCacheKey().hashCode();
                Bitmap pieceBitmap = this.mYearThumbnailLoader.getPieceBitmap(this.mYearName, hashCode);
                if (pieceBitmap == null && diskCacheData.containsKey(Integer.valueOf(hashCode))) {
                    pieceBitmap = this.bytesToBitmap(diskCacheData.get(Integer.valueOf(hashCode)));
                    this.mYearThumbnailLoader.putPieceBitmap(this.mYearName, hashCode, pieceBitmap);
                }
                if (pieceBitmap != null) {
                    atomicInteger.getAndDecrement();
                    this.drawPartialYearBitmap(mergeMultipleAll, pieceBitmap, hashCode);
                    yearThumbnailRequestHolder = this;
                } else {
                    yearThumbnailRequestHolder = this;
                    MediaItem mediaItem2 = mediaItem;
                    ThumbnailLoader.getInstance().loadThumbnail(mediaItem2, this.mViewHolder.getThumbKind(), new e(mediaItem, 1), new f0(yearThumbnailRequestHolder, atomicInteger, mediaItem, hashCode, yearThumbnailKey), new O(5, yearThumbnailRequestHolder));
                }
                this = yearThumbnailRequestHolder;
            }
            this.onLoadedYearBitmap(mergeMultipleAll);
        }
    }
}
