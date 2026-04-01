package com.samsung.android.gallery.plugins.filebrowser;

import A.a;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.exifinterface.media.ExifInterface;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.plugins.R$string;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import x6.f;
import za.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilePreviewFragment extends FileListFragment {
    ZipData mZipData;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MediaItemZip extends MediaItem {
        final FileInfo fi;

        public MediaItemZip(long j2, FileInfo fileInfo, String str) {
            String str2;
            this.fi = fileInfo;
            setFileId(j2);
            setMimeType(fileInfo.mimeType);
            setMediaType(MediaType.valueOf(fileInfo.mediaType));
            setStorageType(StorageType.Stream);
            setDisplayName(fileInfo.name);
            setTitle(fileInfo.entry.data.name + "." + fileInfo.name);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(fileInfo.entry.data.name);
            sb2.append("/");
            if (TextUtils.isEmpty(str)) {
                str2 = "";
            } else {
                str2 = C0212a.A(str, "/");
            }
            sb2.append(str2);
            sb2.append(fileInfo.name);
            setPath(sb2.toString());
            setFileSize(fileInfo.size());
            setDateModified(fileInfo.lastModified());
            setTag("zip-entry", fileInfo);
        }

        public byte[] getByteArray() {
            return this.fi.entry.read();
        }

        public String getDiskCacheKey() {
            return getFileId() + NumericEnum.SEP + this.fi.size + NumericEnum.SEP + getPath();
        }

        public InputStream getInputStream() {
            return this.fi.entry.getInputStream();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initData$1(CharSequence charSequence) {
        Context context = getContext();
        Toast.makeText(context, "Unsupported on Android system. Please re-compress the files using '" + charSequence + "'", 0).show();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadAndStartViewer$4(MediaItem[] mediaItemArr, Context context) {
        BitmapHolder bitmapHolder;
        ExifInterface exifInterface;
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem.getExifTag() == null) {
                FileInfo fileInfo = (FileInfo) mediaItem.getTag("zip-entry");
                if (fileInfo != null) {
                    bitmapHolder = BitmapLoader.of(context).getCache(fileInfo.entry);
                } else {
                    bitmapHolder = null;
                }
                if (!(bitmapHolder == null || (exifInterface = bitmapHolder.exif) == null)) {
                    mediaItem.update(exifInterface);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveZipFile$2(FileInfo fileInfo, String str) {
        InputStream inputStream;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            inputStream = fileInfo.entry.getInputStream();
            FileUtils.writeFile(str, inputStream);
            String str2 = this.TAG;
            Log.d(str2, "saveZipFile" + Logger.vt(Logger.getEncodedString(str), Long.valueOf(currentTimeMillis)));
            if (inputStream != null) {
                inputStream.close();
                return;
            }
            return;
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("save zip file failed. e="), this.TAG);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveZipFile$3(FileInfo fileInfo, String str, DialogInterface dialogInterface, int i2) {
        SimpleThreadPool.getInstance().execute(new j((Object) this, fileInfo, (Object) str, 1));
    }

    public String getSubtitle() {
        return FileUtils.splitPathAndName(this.mPath)[1];
    }

    public String getTitle() {
        return "Zip Browser";
    }

    public void initData(String str) {
        try {
            this.mZipData = new ZipData().open(str);
            loadAndUpdate("");
        } catch (IllegalArgumentException e) {
            String str2 = this.TAG;
            Log.e(str2, "initData failed. e=" + e.getMessage());
            ThreadUtil.runOnUiThread(new f(13, this, PackageMonitorCompat.getInstance().getApplicationLabel("com.sec.android.app.myfiles")));
            ThreadUtil.postOnUiThreadDelayed(new f(1, this), 800);
        }
    }

    public void loadAndStartViewer(Context context, String str, FileInfo fileInfo) {
        ExifInterface exifInterface;
        FolderInfo findFolder = this.mZipData.findFolder(this.mRelativePath);
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        int hashCode = (this.mZipData.path + "/" + this.mRelativePath).hashCode();
        Iterator<FileInfo> it = findFolder.files.iterator();
        MediaItemZip mediaItemZip = null;
        while (it.hasNext()) {
            FileInfo next = it.next();
            if (next.isMedia()) {
                MediaItemZip mediaItemZip2 = new MediaItemZip((long) (arrayList.size() + hashCode), next, this.mRelativePath);
                arrayList.add(mediaItemZip2);
                if (mediaItemZip == null && next.name.equals(fileInfo.name)) {
                    BitmapHolder cache = BitmapLoader.of(context).getCache(next.entry);
                    if (!(cache == null || (exifInterface = cache.exif) == null)) {
                        mediaItemZip2.update(exifInterface);
                    }
                    mediaItemZip = mediaItemZip2;
                }
            }
        }
        int indexOf = arrayList.indexOf(mediaItemZip);
        MediaItem[] mediaItemArr = (MediaItem[]) arrayList.toArray(new MediaItem[0]);
        Blackboard.getApplicationInstance().publish("data://fm/dir/array", mediaItemArr);
        String str2 = this.TAG;
        Log.d(str2, "loadAndStartViewer" + Logger.vt(this.mRelativePath, Integer.valueOf(findFolder.files.size()), Integer.valueOf(arrayList.size()), Integer.valueOf(indexOf), Long.valueOf(currentTimeMillis)));
        SimpleThreadPool.getInstance().execute(new f(14, mediaItemArr, context));
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setClassName("com.sec.android.gallery3d", "com.sec.android.gallery3d.app.GalleryActivity");
            intent.setType("image/*");
            intent.putExtra("quick-view-internal", true);
            intent.putExtra("quick-view-data-location", "data://fm/dir/array");
            intent.putExtra("quick-view-data-position", indexOf);
            context.startActivity(intent);
        } catch (Exception e) {
            a.s(e, new StringBuilder("loadAndStartViewer failed. e="), this.TAG);
        }
    }

    public void loadAndUpdate(String str) {
        Log.i(this.TAG, "loadAndUpdate", str);
        computePositionMap(str);
        this.mRelativePath = str;
        update(this.mZipData.findFolder(str));
        notifyBackAllowStateChange();
    }

    public void onBindView(ViewGroup viewGroup) {
        Optional.ofNullable((ImageView) viewGroup.findViewById(R$id.home)).ifPresent(new b(1));
        super.onBindView(viewGroup);
    }

    public void onClick(FileViewHolder fileViewHolder) {
        String str;
        String str2;
        String str3 = this.TAG;
        StringBuilder sb2 = new StringBuilder("onClick#");
        if (fileViewHolder.data.isDir()) {
            str = "folder";
        } else {
            str = "file";
        }
        sb2.append(str);
        sb2.append(" ");
        sb2.append(fileViewHolder);
        Log.d(str3, sb2.toString());
        if (fileViewHolder.data.isDir()) {
            if (this.mRelativePath.isEmpty()) {
                str2 = fileViewHolder.data.name;
            } else {
                str2 = this.mRelativePath + "/" + fileViewHolder.data.name;
            }
            loadAndUpdate(str2);
        } else if (fileViewHolder.data.isZip()) {
            saveZipFile(getContext(), fileViewHolder.data);
        } else if (fileViewHolder.data.isMedia()) {
            loadAndStartViewer(getContext(), this.mRelativePath, fileViewHolder.data);
        }
    }

    public void onDetach() {
        super.onDetach();
        ZipData zipData = this.mZipData;
        if (zipData != null) {
            zipData.close();
        }
        this.mZipData = null;
    }

    public void saveZipFile(Context context, FileInfo fileInfo) {
        String uniqueFilename = FileUtils.getUniqueFilename(FileUtils.getParent(this.mPath), fileInfo.name);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage((CharSequence) "Save the zip file to " + FileUtils.getNameFromPath(uniqueFilename) + "?").setPositiveButton(R$string.ok, (DialogInterface.OnClickListener) new i(this, fileInfo, uniqueFilename, 4)).setNegativeButton(R$string.cancel, (DialogInterface.OnClickListener) null).show();
    }
}
