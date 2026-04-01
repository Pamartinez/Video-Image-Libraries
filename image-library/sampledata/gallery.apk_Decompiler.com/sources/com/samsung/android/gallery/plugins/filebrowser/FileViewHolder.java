package com.samsung.android.gallery.plugins.filebrowser;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.plugins.R$drawable;
import com.samsung.android.gallery.plugins.R$id;
import com.samsung.android.gallery.plugins.R$layout;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FileViewHolder extends RecyclerView.ViewHolder {
    protected final String HASH_TAG;
    protected final String TAG;
    public BitmapHolder bitmapHolder;
    private final BitmapLoader bitmapLoader;
    protected Consumer<FileViewHolder> clickListener;
    protected FileInfo data;
    final ImageView icon;
    final ImageView image;
    final TextView info;
    private Future<?> job;
    protected Consumer<FileViewHolder> longClickListener;
    final TextView subtitle;
    final TextView title;

    public FileViewHolder(View view) {
        super(view);
        String simpleName = getClass().getSimpleName();
        this.TAG = simpleName;
        this.HASH_TAG = simpleName + '@' + Integer.toHexString(hashCode());
        this.image = (ImageView) view.findViewById(R$id.image);
        this.icon = (ImageView) view.findViewById(R$id.icon);
        this.title = (TextView) view.findViewById(R$id.title);
        this.subtitle = (TextView) view.findViewById(R$id.subtitle);
        this.info = (TextView) view.findViewById(R$id.info);
        this.itemView.setOnClickListener(new d(1, this));
        this.itemView.setOnLongClickListener(new o(this, 0));
        this.bitmapLoader = BitmapLoader.of(view.getContext());
    }

    public static FileViewHolder create(ViewGroup viewGroup) {
        return new FileViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.recycler_item_file_layout, viewGroup, false));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadBitmap$2(FileInfo fileInfo, BitmapHolder bitmapHolder2) {
        this.job = null;
        lambda$bindBitmap$5(fileInfo, bitmapHolder2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadBitmap$3(FileInfo fileInfo, BitmapHolder bitmapHolder2) {
        this.job = null;
        fileInfo.setMediaInfo(MediaInfo.copyOf(bitmapHolder2));
        lambda$bindBitmap$5(fileInfo, bitmapHolder2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadBitmap$4(FileInfo fileInfo, BitmapHolder bitmapHolder2) {
        this.job = null;
        fileInfo.setMediaInfo(MediaInfo.copyOf(bitmapHolder2));
        lambda$bindBitmap$5(fileInfo, bitmapHolder2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view) {
        Consumer<FileViewHolder> consumer = this.clickListener;
        if (consumer != null) {
            consumer.accept(this);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$1(View view) {
        Consumer<FileViewHolder> consumer = this.longClickListener;
        if (consumer == null) {
            return false;
        }
        consumer.accept(this);
        return true;
    }

    /* renamed from: bindBitmap */
    public void lambda$bindBitmap$5(FileInfo fileInfo, BitmapHolder bitmapHolder2) {
        if (bitmapHolder2 == null || bitmapHolder2.bitmap == null || fileInfo == null || !fileInfo.equals(this.data)) {
            String str = this.TAG;
            Log.w(str, "bindBitmap skip unmatched " + getBindingAdapterPosition());
            return;
        }
        bitmapHolder2.bitmap.prepareToDraw();
        if (this.image.getWidth() <= 0 || !ThreadUtil.isMainThread()) {
            this.image.post(new j((Object) this, fileInfo, (Object) bitmapHolder2, 2));
        } else {
            bindBitmap(bitmapHolder2);
        }
    }

    public void loadBitmap(FileInfo fileInfo) {
        if (fileInfo.isFile() && this.bitmapHolder == null) {
            if (!fileInfo.isCompressed()) {
                this.job = this.bitmapLoader.decodeThumbnail(fileInfo.name, (Consumer<BitmapHolder>) new n(this, fileInfo, 0));
            } else if (fileInfo.getMimeType().startsWith("image") || fileInfo.getMimeType().startsWith("video")) {
                ZipEntryHolder zipEntryHolder = fileInfo.entry;
                if (zipEntryHolder != null) {
                    this.job = this.bitmapLoader.decodeThumbnail(zipEntryHolder, (Consumer<BitmapHolder>) new n(this, fileInfo, 1));
                    return;
                }
                String str = fileInfo.name;
                if (str != null) {
                    this.job = this.bitmapLoader.decodeThumbnail(str, (Consumer<BitmapHolder>) new n(this, fileInfo, 2));
                }
            }
        }
    }

    public void onBind(FileInfo fileInfo) {
        this.data = fileInfo;
        Drawable drawable = null;
        if (fileInfo.entry != null) {
            this.title.setText(fileInfo.name);
            this.subtitle.setText(TimeUtil.getLocalizedDateTime(fileInfo.lastModified()));
            this.info.setText(StringCompat.toReadableSize((double) fileInfo.entry.getSize()));
        } else {
            this.title.setText(FileUtils.splitPathAndName(fileInfo.getDisplayName())[1]);
            this.subtitle.setText(TimeUtil.getLocalizedDateTime(fileInfo.lastModified()));
            if (!fileInfo.isDir()) {
                this.info.setText(StringCompat.toReadableSize((double) fileInfo.size()));
            } else if (fileInfo.isVirtual()) {
                this.info.setText((CharSequence) null);
            } else {
                this.info.setText(StringCompat.toReadableCount(fileInfo.count()));
            }
        }
        if (fileInfo.isDir()) {
            drawable = this.icon.getContext().getDrawable(R$drawable.ic_file_type_folder);
        } else if (fileInfo.getMimeType().startsWith("image") || fileInfo.getMimeType().startsWith("video")) {
            loadBitmap(fileInfo);
        } else if ("application/zip".equals(fileInfo.getMimeType())) {
            drawable = this.icon.getContext().getDrawable(R$drawable.ic_file_type_zip);
        } else {
            drawable = this.icon.getContext().getDrawable(R$drawable.ic_file_type_etc);
        }
        if (drawable != null) {
            this.icon.setImageDrawable(drawable);
            this.icon.setVisibility(0);
            this.image.setVisibility(8);
            return;
        }
        this.icon.setVisibility(8);
        this.image.setVisibility(0);
    }

    public void onRecycle() {
        Future<?> future = this.job;
        if (future != null) {
            future.cancel();
            this.job = null;
        }
        this.data = null;
        this.bitmapHolder = null;
        this.image.setImageDrawable((Drawable) null);
        this.image.setImageMatrix((Matrix) null);
        this.icon.setImageDrawable((Drawable) null);
        this.title.setText((CharSequence) null);
        this.subtitle.setText((CharSequence) null);
        this.info.setText((CharSequence) null);
    }

    public FileViewHolder setOnItemClickListener(Consumer<FileViewHolder> consumer) {
        this.clickListener = consumer;
        return this;
    }

    public FileViewHolder setOnItemLongClickListener(Consumer<FileViewHolder> consumer) {
        this.longClickListener = consumer;
        return this;
    }

    public String toString() {
        return this.HASH_TAG + '{' + getBindingAdapterPosition() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.data.name.replaceFirst(".*/com.sec.android.gallery3d/", "") + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.bitmapHolder + '}';
    }

    public void bindBitmap(BitmapHolder bitmapHolder2) {
        this.bitmapHolder = bitmapHolder2;
        this.image.setScaleType(ImageView.ScaleType.MATRIX);
        this.image.setImageBitmap(this.bitmapHolder.bitmap);
        this.image.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(this.image, true).withOrientation(bitmapHolder2.orientation).withOrientationTag(bitmapHolder2.orientationTag)));
    }
}
