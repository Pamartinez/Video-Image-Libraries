package com.samsung.android.gallery.app.ui.viewer2.details.items;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Gainmap;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.adobe.internal.xmp.XMPConst;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.analyticsquery.AnalyticsQuery;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaCacheLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.details.DetailsDataRefiner;
import com.samsung.android.gallery.module.details.DetailsLoadResult;
import com.samsung.android.gallery.module.details.DetailsUpdateKey;
import com.samsung.android.gallery.module.graphics.BitmapCache;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.graphics.ImageDecoder;
import com.samsung.android.gallery.module.media.DualPhotoManipulator;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.module.nondestruction.NonDestructionManager;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.cache.BytesBuffer;
import com.samsung.android.gallery.support.cache.CacheManager;
import com.samsung.android.gallery.support.utils.ExifTag;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.JpegParser;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.sum.core.types.NumericEnum;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DetailsItemDebugExif extends DetailsItem {
    static final boolean DEBUG = PocFeatures.isEnabled(PocFeatures.MoreInfoDebug);
    static final boolean DEBUG_ANALYZED_SOLUTIONS = PackageMonitorCompat.getInstance().isPackageInstalled("com.salab.issuetracker");
    View mDebugView;
    MediaItem mMediaItem;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DebugExifAdapter extends RecyclerView.Adapter<DebugExifViewHolder> {
        final ArrayList<String[]> list;

        public DebugExifAdapter(MediaItem mediaItem) {
            ExifTag exifTag;
            DebugExifAdapter debugExifAdapter;
            String str = null;
            if (mediaItem.getExifTag() != null) {
                exifTag = mediaItem.getExifTag();
            } else {
                exifTag = new ExifTag((ExifInterface) null);
            }
            exifTag = mediaItem.isVideo() ? DetailsDataRefiner.createExifFromMetadata(mediaItem) : exifTag;
            ArrayList<String[]> arrayList = new ArrayList<>();
            arrayList.add(new String[]{"Color space", exifTag.colorSpace});
            arrayList.add(new String[]{"Orientation", (String) Optional.ofNullable(exifTag.orientation).map(new j(mediaItem)).orElse("N/A")});
            arrayList.add(new String[]{"Original time", DetailsDataRefiner.getDebugDateTime(exifTag.originalTime, exifTag.originalTimeSubSec, exifTag.originalTimeOffset)});
            arrayList.add(new String[]{"Digitized time", DetailsDataRefiner.getDebugDateTime(exifTag.digitizedTime, exifTag.digitizedTimeSubSec, exifTag.digitizedTimeOffset)});
            arrayList.add(new String[]{"Time", DetailsDataRefiner.getDebugDateTime(exifTag.takenTime, exifTag.takenTimeSubSec, exifTag.takenTimeOffset)});
            arrayList.add(new String[]{"GPS time", exifTag.gpsTime});
            arrayList.add(new String[]{"File modified", TimeUtil.getIsoLocalDateTime(mediaItem.getDateModified() * 1000).replace("-", NumericEnum.SEP)});
            arrayList.add(new String[]{"UTC time", exifTag.utcTime});
            arrayList.add(new String[]{"Software", exifTag.software});
            arrayList.add(new String[]{"Owner", (String) Optional.ofNullable(mediaItem.getOwnerPackage()).orElse("N/A")});
            arrayList.add(new String[]{"Brand", mediaItem.camModel});
            if (DetailsItemDebugExif.DEBUG) {
                arrayList.add(new String[]{"SEF", (String) Optional.ofNullable(mediaItem.getSefFileTypes()).map(new i(3)).filter(new k(0)).map(new i(4)).orElse((Object) null)});
                String readableString = MediaCacheLoader.getInstance().toReadableString(mediaItem);
                arrayList.add(new String[]{"Cache", readableString.length() > 2 ? readableString : str});
            }
            arrayList.removeIf(new k(1));
            if (DetailsItemDebugExif.DEBUG_ANALYZED_SOLUTIONS) {
                DetailsData of2 = DetailsData.of(mediaItem);
                String str2 = of2.analyzedSolutions;
                String str3 = XMPConst.ARRAY_ITEM_NAME;
                String[] strArr = {"Analyzed", str2 == null ? str3 : str2};
                arrayList.add(strArr);
                int size = arrayList.size() - 1;
                String str4 = of2.sceneTags;
                String[] strArr2 = {"Scenes", str4 != null ? str4 : str3};
                if (of2.analyzedSolutions == null) {
                    debugExifAdapter = this;
                    SimpleThreadPool.getInstance().execute(new l(debugExifAdapter, of2, mediaItem, strArr, strArr2, size));
                    debugExifAdapter.list = arrayList;
                }
            }
            debugExifAdapter = this;
            debugExifAdapter.list = arrayList;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ String lambda$new$0(MediaItem mediaItem, String str) {
            StringBuilder t = C0212a.t(str, " [");
            t.append(mediaItem.getOrientation());
            t.append("]");
            return t.toString();
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$new$2(String str) {
            return !str.isEmpty();
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$new$4(String[] strArr) {
            if (strArr[1] == null) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$new$5(int i2) {
            notifyItemRangeChanged(i2, 1);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$new$6(DetailsData detailsData, MediaItem mediaItem, String[] strArr, String[] strArr2, int i2) {
            String loadAnalyzedSolutions = AnalyticsQuery.loadAnalyzedSolutions(mediaItem.getFileId());
            detailsData.analyzedSolutions = loadAnalyzedSolutions;
            strArr[1] = loadAnalyzedSolutions;
            ThreadUtil.postOnUiThread(new m(this, i2));
        }

        public int getItemCount() {
            return this.list.size();
        }

        public DebugExifViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            return new DebugExifViewHolder(C0086a.d(viewGroup, R.layout.details_item_debug_recycler_item_layout, viewGroup, false));
        }

        public void onBindViewHolder(DebugExifViewHolder debugExifViewHolder, int i2) {
            String[] strArr = this.list.get(i2);
            debugExifViewHolder.bind(strArr[0], strArr[1]);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DebugExifThumbnailAdapter extends RecyclerView.Adapter<DebugExifThumbnailViewHolder> {
        final MediaItem item;
        final ArrayList<String> list;

        public DebugExifThumbnailAdapter(MediaItem mediaItem) {
            ArrayList<String> arrayList = new ArrayList<>();
            this.list = arrayList;
            this.item = mediaItem;
            arrayList.add("EXIF");
            if (SuperHdrConfig.SUPPORT && mediaItem.isImage()) {
                arrayList.add("HDR");
            }
            arrayList.addAll(List.of("M", "S"));
        }

        public int getItemCount() {
            return this.list.size();
        }

        public DebugExifThumbnailViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            return new DebugExifThumbnailViewHolder(C0086a.d(viewGroup, R.layout.details_item_exif_thumbnail, viewGroup, false));
        }

        public void onBindViewHolder(DebugExifThumbnailViewHolder debugExifThumbnailViewHolder, int i2) {
            debugExifThumbnailViewHolder.bind(this.item, this.list.get(i2));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DebugExifThumbnailViewHolder extends RecyclerView.ViewHolder {
        AtomicReference<Bitmap> bitmap;
        final ImageView image;
        MediaItem item;
        String key;
        final TextView title;

        public DebugExifThumbnailViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.title);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            this.image = imageView;
            imageView.setOnClickListener(new g(3, this));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$bind$1() {
            bindBitmap(this.bitmap.get());
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$bind$2(String str, MediaItem mediaItem) {
            Bitmap bitmap2;
            if ("S".equals(str)) {
                bitmap2 = BitmapCache.getDiskCache(5, CacheManager.generateKey(mediaItem.getDiskCacheKey() + "/" + mediaItem.getCropRectRatio(), mediaItem.getDateModified()));
            } else if ("M".equals(str)) {
                bitmap2 = BitmapCache.getDiskCache(0, CacheManager.generateKey(mediaItem.getDiskCacheKey(), mediaItem.getDateModified()));
            } else {
                if ("EXIF".equals(str)) {
                    if (this.item.isImage()) {
                        bitmap2 = decodeExifThumbnail(mediaItem);
                    } else {
                        byte[] embeddedPictureInVideo = BitmapUtils.getEmbeddedPictureInVideo(mediaItem.getPath());
                        if (embeddedPictureInVideo != null && embeddedPictureInVideo.length > 0) {
                            bitmap2 = ImageDecoder.decodeByteArray(embeddedPictureInVideo, 0, embeddedPictureInVideo.length, BitmapOptionsFactory.parse(embeddedPictureInVideo, 0, embeddedPictureInVideo.length));
                        }
                    }
                } else if ("HDR".equals(str)) {
                    bitmap2 = decodeHdrGainMap(mediaItem);
                } else if ("ORIG".equals(str)) {
                    String path = NonDestructionManager.getInstance().getPath(mediaItem.getOriginalFileHash());
                    if (FileUtils.exists(path)) {
                        MediaItem clone = mediaItem.clone();
                        clone.setFileId(0);
                        clone.setPath(path);
                        bitmap2 = ThumbnailLoader.getInstance().loadThumbnailSync(clone, ThumbKind.MEDIUM_KIND);
                    }
                }
                bitmap2 = null;
            }
            this.bitmap = new AtomicReference<>(bitmap2);
            this.itemView.post(new o(this, 1));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0(View view) {
            SimpleThreadPool.getInstance().execute(new o(this, 0));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$saveBitmap$3(String str) {
            Context context = this.itemView.getContext();
            Toast.makeText(context, "saved to file : " + str, 0).show();
        }

        public void bind(MediaItem mediaItem, String str) {
            this.item = mediaItem;
            this.key = str;
            this.title.setText(str);
            AtomicReference<Bitmap> atomicReference = this.bitmap;
            if (atomicReference != null) {
                bindBitmap(atomicReference.get());
            } else {
                SimpleThreadPool.getInstance().execute(new n(this, str, mediaItem));
            }
        }

        public void bindBitmap(Bitmap bitmap2) {
            String str;
            String str2;
            if (bitmap2 != null) {
                String str3 = this.key;
                if ("ORIG".equals(str3)) {
                    String path = NonDestructionManager.getInstance().getPath(this.item.getOriginalFileHash());
                    StringBuilder s = C0212a.s(str3);
                    if (path == null || !FileUtils.exists(path)) {
                        str2 = "";
                    } else {
                        str2 = " " + StringCompat.toReadableSize((double) FileUtils.length(path));
                    }
                    s.append(str2);
                    str = s.toString();
                } else {
                    StringBuilder t = C0212a.t(str3, " ");
                    t.append(bitmap2.getWidth());
                    t.append("x");
                    t.append(bitmap2.getHeight());
                    str = t.toString();
                }
                this.title.setText(str);
                this.image.setImageBitmap(bitmap2);
                return;
            }
            this.title.setText(this.key + " (N/A)");
        }

        public Bitmap decodeExifThumbnail(MediaItem mediaItem) {
            if (mediaItem.isDrm()) {
                return null;
            }
            if (mediaItem.isDng()) {
                return ImageDecoder.decodeDngThumbnail(mediaItem.getPath(), mediaItem.getDngVersion());
            }
            if (mediaItem.isHeif()) {
                return ImageDecoder.decodeHeifThumbnail(mediaItem.getPath());
            }
            if (mediaItem.isJpeg()) {
                return ImageDecoder.decodeJpegThumbnail(mediaItem.getPath());
            }
            return null;
        }

        public Bitmap decodeHdrGainMap(MediaItem mediaItem) {
            Gainmap gainmap;
            if (Build.VERSION.SDK_INT >= 34) {
                Bitmap bitmap2 = (Bitmap) Blackboard.getInstance(this.itemView.getContext().toString()).read(MediaItemUtil.getViewerBitmapKey(mediaItem));
                if (bitmap2 == null) {
                    bitmap2 = BitmapUtils.getDecodedBitmap(mediaItem.getPath(), BitmapOptionsFactory.of(mediaItem).adjustInSampleSize(400).withHdr(true, false));
                }
                if (bitmap2 != null) {
                    gainmap = bitmap2.getGainmap();
                } else {
                    gainmap = null;
                }
                if (gainmap != null) {
                    return gainmap.getGainmapContents();
                }
            }
            return null;
        }

        public byte[] getDiskCacheData(MediaItem mediaItem, String str) {
            int i2;
            String str2;
            if ("M".equals(str)) {
                i2 = 0;
            } else {
                i2 = 5;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(mediaItem.getDiskCacheKey());
            if ("S".equals(str)) {
                str2 = "/" + mediaItem.getCropRectRatio();
            } else {
                str2 = "";
            }
            sb2.append(str2);
            String sb3 = sb2.toString();
            BytesBuffer bytesBuffer = new BytesBuffer();
            if (CacheManager.getInstance().get(i2, CacheManager.generateKey(sb3, mediaItem.getDateModified()), bytesBuffer)) {
                return bytesBuffer.data;
            }
            return null;
        }

        /* JADX WARNING: Removed duplicated region for block: B:56:0x015c A[ADDED_TO_REGION] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void saveBitmap() {
            /*
                r7 = this;
                com.samsung.android.gallery.module.data.MediaItem r0 = r7.item
                if (r0 == 0) goto L_0x0185
                java.lang.String r0 = r7.key
                if (r0 == 0) goto L_0x0185
                java.util.concurrent.atomic.AtomicReference<android.graphics.Bitmap> r0 = r7.bitmap
                if (r0 == 0) goto L_0x0185
                java.lang.Object r0 = r0.get()
                if (r0 == 0) goto L_0x0185
                com.samsung.android.gallery.module.data.MediaItem r0 = r7.item
                java.lang.String r0 = r0.getPath()
                if (r0 != 0) goto L_0x001c
                goto L_0x0185
            L_0x001c:
                com.samsung.android.gallery.module.data.MediaItem r0 = r7.item
                java.lang.String r0 = r0.getPath()
                java.lang.String r1 = com.samsung.android.gallery.support.utils.FileUtils.getDirectoryFromPath(r0)
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = com.samsung.android.gallery.support.utils.FileUtils.getBaseName(r0)
                r2.append(r3)
                java.lang.String r3 = ".jpg"
                r2.append(r3)
                java.lang.String r2 = r2.toString()
                java.lang.String r3 = "EXIF"
                java.lang.String r4 = r7.key
                boolean r3 = r3.equals(r4)
                r4 = 0
                if (r3 == 0) goto L_0x0090
                com.samsung.android.gallery.module.data.MediaItem r3 = r7.item
                boolean r3 = r3.isImage()
                if (r3 == 0) goto L_0x006c
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                r3.append(r1)
                java.lang.String r1 = "exif_"
                r3.append(r1)
                r3.append(r2)
                java.lang.String r1 = r3.toString()
                java.lang.String r1 = com.samsung.android.gallery.support.utils.FileUtils.makeUniqueFilename(r1)
                byte[] r0 = com.samsung.android.gallery.support.utils.ExifUtils.getThumbnailBytes(r0)
                goto L_0x015a
            L_0x006c:
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                r3.append(r1)
                java.lang.String r1 = "embedded_"
                r3.append(r1)
                r3.append(r2)
                java.lang.String r1 = r3.toString()
                java.lang.String r1 = com.samsung.android.gallery.support.utils.FileUtils.makeUniqueFilename(r1)
                byte[] r0 = com.samsung.android.gallery.module.graphics.BitmapUtils.getEmbeddedPictureInVideo(r0)
                if (r0 == 0) goto L_0x008d
                com.samsung.android.gallery.support.utils.FileUtils.writeFile((java.lang.String) r1, (byte[]) r0)
            L_0x008d:
                r0 = r4
                goto L_0x015a
            L_0x0090:
                java.lang.String r3 = r7.key
                java.lang.String r5 = "M"
                boolean r3 = r5.equals(r3)
                if (r3 != 0) goto L_0x0133
                java.lang.String r3 = "S"
                java.lang.String r6 = r7.key
                boolean r3 = r3.equals(r6)
                if (r3 == 0) goto L_0x00a6
                goto L_0x0133
            L_0x00a6:
                java.lang.String r3 = "HDR"
                java.lang.String r5 = r7.key
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x00f3
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                r0.append(r1)
                java.lang.String r1 = "gainmap_"
                r0.append(r1)
                r0.append(r2)
                java.lang.String r0 = r0.toString()
                java.lang.String r1 = com.samsung.android.gallery.support.utils.FileUtils.makeUniqueFilename(r0)
                java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x008d }
                r2 = 100000(0x186a0, float:1.4013E-40)
                r0.<init>(r2)     // Catch:{ IOException -> 0x008d }
                java.util.concurrent.atomic.AtomicReference<android.graphics.Bitmap> r2 = r7.bitmap     // Catch:{ all -> 0x00e9 }
                java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x00e9 }
                android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2     // Catch:{ all -> 0x00e9 }
                android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ all -> 0x00e9 }
                r5 = 100
                r2.compress(r3, r5, r0)     // Catch:{ all -> 0x00e9 }
                byte[] r2 = r0.toByteArray()     // Catch:{ all -> 0x00e9 }
                r0.close()     // Catch:{ IOException -> 0x00e6 }
            L_0x00e6:
                r0 = r2
                goto L_0x015a
            L_0x00e9:
                r2 = move-exception
                r0.close()     // Catch:{ all -> 0x00ee }
                goto L_0x00f2
            L_0x00ee:
                r0 = move-exception
                r2.addSuppressed(r0)     // Catch:{ IOException -> 0x008d }
            L_0x00f2:
                throw r2     // Catch:{ IOException -> 0x008d }
            L_0x00f3:
                java.lang.String r2 = "ORIG"
                java.lang.String r3 = r7.key
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x0130
                java.lang.String r2 = "original_"
                java.lang.StringBuilder r1 = i.C0212a.t(r1, r2)
                java.lang.String r0 = com.samsung.android.gallery.support.utils.FileUtils.getNameFromPath(r0)
                r1.append(r0)
                java.lang.String r0 = r1.toString()
                java.lang.String r1 = com.samsung.android.gallery.support.utils.FileUtils.makeUniqueFilename(r0)
                com.samsung.android.gallery.module.nondestruction.NonDestructionManager r0 = com.samsung.android.gallery.module.nondestruction.NonDestructionManager.getInstance()
                com.samsung.android.gallery.module.data.MediaItem r2 = r7.item
                java.lang.String r2 = r2.getOriginalFileHash()
                java.lang.String r0 = r0.getPath(r2)
                if (r0 == 0) goto L_0x008d
                boolean r2 = com.samsung.android.gallery.support.utils.FileUtils.exists(r0)
                if (r2 == 0) goto L_0x008d
                com.samsung.android.gallery.support.utils.FileUtils.delete((java.lang.String) r1)
                com.samsung.android.gallery.support.utils.FileUtils.copy((java.lang.String) r0, (java.lang.String) r1)
                goto L_0x008d
            L_0x0130:
                r0 = r4
                r1 = r0
                goto L_0x015a
            L_0x0133:
                java.lang.StringBuilder r0 = i.C0212a.s(r1)
                java.lang.String r1 = r7.key
                boolean r1 = r5.equals(r1)
                if (r1 == 0) goto L_0x0142
                java.lang.String r1 = "medium_"
                goto L_0x0144
            L_0x0142:
                java.lang.String r1 = "small_"
            L_0x0144:
                r0.append(r1)
                r0.append(r2)
                java.lang.String r0 = r0.toString()
                java.lang.String r1 = com.samsung.android.gallery.support.utils.FileUtils.makeUniqueFilename(r0)
                com.samsung.android.gallery.module.data.MediaItem r0 = r7.item
                java.lang.String r2 = r7.key
                byte[] r0 = r7.getDiskCacheData(r0, r2)
            L_0x015a:
                if (r1 == 0) goto L_0x017a
                if (r0 == 0) goto L_0x0177
                int r2 = r0.length
                if (r2 <= 0) goto L_0x0177
                com.samsung.android.gallery.support.utils.FileUtils.writeFile((java.lang.String) r1, (byte[]) r0)
                com.samsung.android.gallery.module.data.MediaItem r0 = r7.item
                boolean r0 = r0.isVideo()
                if (r0 == 0) goto L_0x016e
                r0 = 0
                goto L_0x0174
            L_0x016e:
                com.samsung.android.gallery.module.data.MediaItem r0 = r7.item
                int r0 = r0.getOrientation()
            L_0x0174:
                com.samsung.android.gallery.support.utils.ExifUtils.changeOrientation(r1, r0)
            L_0x0177:
                com.samsung.android.gallery.support.utils.MediaScanner.scanFile(r1, r4)
            L_0x017a:
                android.view.View r0 = r7.itemView
                com.samsung.android.gallery.app.ui.viewer2.details.items.a r2 = new com.samsung.android.gallery.app.ui.viewer2.details.items.a
                r3 = 3
                r2.<init>(r3, r7, r1)
                r0.post(r2)
            L_0x0185:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.viewer2.details.items.DetailsItemDebugExif.DebugExifThumbnailViewHolder.saveBitmap():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DebugExifViewHolder extends RecyclerView.ViewHolder {
        final TextView descriptionView;
        final TextView titleView;

        public DebugExifViewHolder(View view) {
            super(view);
            this.titleView = (TextView) view.findViewById(R.id.title);
            this.descriptionView = (TextView) view.findViewById(R.id.description);
        }

        public void bind(String str, String str2) {
            this.titleView.setText(str);
            this.descriptionView.setText(str2);
        }
    }

    public DetailsItemDebugExif(DetailsView detailsView, EventContext eventContext) {
        super(detailsView, eventContext);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadSefData$8(MediaItem mediaItem, View view) {
        String str;
        ArrayList<String> arrayList;
        if (mediaItem.isShotMode("live_focus") || mediaItem.isShotMode("Dual capture")) {
            DualPhotoManipulator dualPhotoManipulator = new DualPhotoManipulator();
            dualPhotoManipulator.init(mediaItem.getPath());
            str = dualPhotoManipulator.toString();
        } else {
            str = DetailsData.computeSefParserIfAbsent(mediaItem).toReadableString(new i(2));
        }
        if (TextUtils.isEmpty(str)) {
            str = "SefData#0 {null}";
        }
        if (mediaItem.isJpeg()) {
            arrayList = JpegParser.loadXmp(mediaItem.getPath(), true);
        } else {
            arrayList = null;
        }
        if (arrayList != null && !arrayList.isEmpty()) {
            StringBuilder t = C0212a.t(str, "\n\nXmp#");
            t.append(arrayList.size());
            t.append("\n");
            t.append(String.join("\n", arrayList));
            str = t.toString();
        }
        mediaItem.setTag("debug:sefData", str);
        view.setOnClickListener((View.OnClickListener) null);
        view.post(new C0447a(2, view, str));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$showDetails$4(ExifTag exifTag) {
        return "\n\n" + exifTag;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$showDetails$5(MediaHelper.VideoInfo videoInfo) {
        return "\n\n" + videoInfo.toDebugString();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateFileData$3(View view) {
        if (this.mDebugView == null) {
            View findViewById = this.mItemView.findViewById(R.id.moreinfo_debug);
            if (findViewById instanceof ViewStub) {
                this.mDebugView = ((ViewStub) findViewById).inflate();
            }
        }
        showDetails((ViewGroup) this.mDebugView, this.mMediaItem);
    }

    private void showDetails(ViewGroup viewGroup, MediaItem mediaItem) {
        MediaHelper.VideoInfo videoInfo;
        View findViewById = this.mItemView.findViewById(R.id.item_arrow);
        if (viewGroup != null && findViewById != null && findViewById.getVisibility() != 8) {
            viewGroup.setVisibility(0);
            findViewById.setVisibility(8);
            ((RecyclerView) viewGroup.findViewById(R.id.thumbnail_list)).setAdapter(new DebugExifThumbnailAdapter(mediaItem));
            ((RecyclerView) viewGroup.findViewById(R.id.information_list)).setAdapter(new DebugExifAdapter(mediaItem));
            if (DEBUG) {
                TextView textView = (TextView) getView(R.id.exif_debug);
                textView.setVisibility(0);
                StringBuilder sb2 = new StringBuilder();
                sb2.append(mediaItem.getPath());
                sb2.append("\n\n");
                sb2.append(mediaItem.getReadableLog());
                sb2.append("\nMediaCache=");
                sb2.append(MediaCacheLoader.getInstance().get(mediaItem));
                sb2.append("\nDiskCacheKey=");
                sb2.append(BitmapCache.getCacheKey(mediaItem.getDiskCacheKey(), mediaItem.getDateModified()));
                sb2.append((String) Optional.ofNullable(mediaItem.getExifTag()).map(new i(0)).orElse(""));
                if (mediaItem.isVideo()) {
                    videoInfo = MetadataManager.getInstance().get(mediaItem);
                } else {
                    videoInfo = null;
                }
                sb2.append((String) Optional.ofNullable(videoInfo).map(new i(1)).orElse(""));
                textView.setText(sb2.toString());
                TextView textView2 = (TextView) getView(R.id.sef_data);
                textView2.setVisibility(0);
                String str = (String) mediaItem.getTag("debug:SefData");
                if (str == null) {
                    loadSefData(textView2, mediaItem);
                } else {
                    textView2.setText(str);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateFileData(MediaItem mediaItem, DetailsLoadResult detailsLoadResult) {
        this.mMediaItem = mediaItem;
        View view = this.mItemView;
        if (view instanceof ViewStub) {
            View inflate = ((ViewStub) view).inflate();
            this.mItemView = inflate;
            inflate.findViewById(R.id.details_item_title).setOnClickListener(new g(2, this));
        }
    }

    public int getLayoutId() {
        return R.id.moreinfo_debug_stub;
    }

    public void hide() {
        super.hide();
        View view = this.mDebugView;
        if (view != null) {
            Optional.ofNullable((RecyclerView) view.findViewById(R.id.thumbnail_list)).ifPresent(new e(3));
            Optional.ofNullable((RecyclerView) this.mDebugView.findViewById(R.id.information_list)).ifPresent(new e(4));
            this.mDebugView.setVisibility(8);
            Optional.ofNullable(this.mItemView.findViewById(R.id.item_arrow)).ifPresent(new e(5));
        }
    }

    public void loadSefData(View view, MediaItem mediaItem) {
        ThreadUtil.postOnBgThread(new C0447a(1, mediaItem, view));
    }

    public void registerViewUpdater() {
        this.mViewUpdaterMap.put(DetailsUpdateKey.FILE_DATA, new y(this, 2));
    }

    public boolean supportItem(MediaItem mediaItem) {
        return true;
    }
}
