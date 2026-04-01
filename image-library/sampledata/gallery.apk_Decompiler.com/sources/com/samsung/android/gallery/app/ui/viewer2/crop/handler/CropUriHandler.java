package com.samsung.android.gallery.app.ui.viewer2.crop.handler;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Size;
import com.samsung.android.gallery.database.dbtype.MimeType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PermissionHelper;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CropUriHandler extends CropHandler {
    protected MimeType mOutputMimeType;
    protected Uri mOutputUri;

    public CropUriHandler(Intent intent, MediaItem mediaItem) {
        super(intent, mediaItem);
    }

    private Size getCropSize(Bundle bundle, int i2, int i7) {
        int i8;
        int i10;
        if (bundle != null) {
            i10 = bundle.getInt("outputX", i2);
            i8 = bundle.getInt("outputY", i7);
            if ((!bundle.getBoolean("set-as-contactphoto", false) && !isContactUri((Uri) bundle.getParcelable("output"))) || i2 > 320 || i7 > 320) {
                i2 = i10;
                i7 = i8;
            }
        } else {
            i10 = -1;
            i8 = -1;
        }
        int i11 = i2 * i7;
        if (i11 > 5000000) {
            float sqrt = (float) Math.sqrt(5000000.0d / ((double) i11));
            i2 = Math.round(((float) i2) * sqrt);
            i7 = Math.round(((float) i7) * sqrt);
            Log.w((CharSequence) this.TAG, "getCropSize(scaled)", Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(i10), Integer.valueOf(i8), Float.valueOf(sqrt));
        } else {
            Log.d(this.TAG, "getCropSize", Integer.valueOf(i2), Integer.valueOf(i7), Integer.valueOf(i10), Integer.valueOf(i8));
        }
        if (i2 > 0 && i7 > 0) {
            return new Size(i2, i7);
        }
        Log.e((CharSequence) this.TAG, "getCropSize failed. wrong size", Integer.valueOf(i2), Integer.valueOf(i7));
        new InternalException("crop fail").post();
        return null;
    }

    private void handleHorizontalMirror(Canvas canvas, Rect rect, int i2, int i7, int i8) {
        if (isRotated(i8)) {
            canvas.scale(1.0f, -1.0f, ((float) i7) / 2.0f, ((float) i2) / 2.0f);
            int i10 = rect.top;
            int i11 = this.mBitmapHeight;
            rect.top = ((i11 / 2) + (i11 / 2)) - rect.bottom;
            rect.bottom = i11 - i10;
            return;
        }
        canvas.scale(-1.0f, 1.0f, ((float) i2) / 2.0f, ((float) i7) / 2.0f);
        int i12 = rect.left;
        int i13 = this.mBitmapWidth;
        rect.left = ((i13 / 2) + (i13 / 2)) - rect.right;
        rect.right = i13 - i12;
    }

    private boolean isContactUri(Uri uri) {
        if (uri == null) {
            return false;
        }
        String floatingFeatureString = SeApiCompat.getFloatingFeatureString("SEC_FLOATING_FEATURE_CONTACTS_CONFIG_PACKAGE_NAME");
        if (TextUtils.isEmpty(floatingFeatureString) || !uri.toString().contains(floatingFeatureString)) {
            return false;
        }
        return true;
    }

    private boolean isRotated(int i2) {
        if (i2 == 90 || i2 == 270) {
            return true;
        }
        return false;
    }

    private void rotateRect(Rect rect, int i2, int i7, int i8) {
        if (i8 != 0 && i8 != 360) {
            int width = rect.width();
            int height = rect.height();
            if (i8 == 90) {
                int i10 = rect.left;
                rect.top = i10;
                int i11 = i2 - rect.bottom;
                rect.left = i11;
                rect.right = i11 + height;
                rect.bottom = i10 + width;
            } else if (i8 == 180) {
                int i12 = i2 - rect.right;
                rect.left = i12;
                int i13 = i7 - rect.bottom;
                rect.top = i13;
                rect.right = i12 + width;
                rect.bottom = i13 + height;
            } else if (i8 == 270) {
                int i14 = rect.top;
                rect.left = i14;
                int i15 = i7 - rect.right;
                rect.top = i15;
                rect.right = i14 + height;
                rect.bottom = i15 + width;
            }
        }
    }

    public Intent buildIntent() {
        if (this.mOutputUri == null) {
            return null;
        }
        Intent intent = new Intent();
        if (this.mMediaItem.isLocal()) {
            intent.putExtra("takenTime", this.mMediaItem.getDateTaken());
            intent.putExtra("latitude", this.mMediaItem.getLatitude());
            intent.putExtra("longitude", this.mMediaItem.getLongitude());
        }
        intent.putExtra("output", this.mOutputUri);
        intent.setData(this.mOutputUri);
        return intent;
    }

    public Bitmap getCroppedImage(Rect rect, Bundle bundle) {
        int i2;
        int i7;
        Size cropSize = getCropSize(bundle, rect.width(), rect.height());
        if (cropSize == null) {
            return null;
        }
        int width = cropSize.getWidth();
        int height = cropSize.getHeight();
        Rect rect2 = new Rect(rect);
        Rect rect3 = new Rect(0, 0, width, height);
        int orientation = this.mMediaItem.getOrientation();
        int orientationTag = this.mMediaItem.getOrientationTag();
        int i8 = 360 - orientation;
        rotateRect(rect2, this.mBitmapWidth, this.mBitmapHeight, i8);
        if (isRotated(orientation)) {
            i2 = height;
        } else {
            i2 = width;
        }
        if (isRotated(orientation)) {
            i7 = width;
        } else {
            i7 = height;
        }
        rotateRect(rect3, i2, i7, i8);
        Bitmap createBitmap = BitmapUtils.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.translate(((float) width) / 2.0f, ((float) height) / 2.0f);
        canvas.rotate((float) orientation);
        if (isRotated(orientation)) {
            canvas.translate(((float) (-height)) / 2.0f, ((float) (-width)) / 2.0f);
        } else {
            canvas.translate(((float) (-width)) / 2.0f, ((float) (-height)) / 2.0f);
        }
        if (ExifUtils.isHorizontalMirror(orientationTag)) {
            handleHorizontalMirror(canvas, rect2, width, height, orientation);
        }
        canvas.drawBitmap(this.mBitmap, rect2, rect3, new Paint(2));
        return createBitmap;
    }

    public Bitmap.CompressFormat getOutputCompressFormat() {
        if (getOutputMimeType() == MimeType.PNG) {
            return Bitmap.CompressFormat.PNG;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    public String getOutputExtension() {
        if (getOutputMimeType() == MimeType.PNG) {
            return "png";
        }
        return "jpg";
    }

    public MimeType getOutputMimeType() {
        String str;
        MimeType mimeType;
        if (this.mOutputMimeType == null) {
            String stringExtra = this.mIntent.getStringExtra("outputFormat");
            if (stringExtra != null) {
                str = stringExtra.toLowerCase(Locale.US);
            } else if (this.mMediaItem.isPng() || this.mMediaItem.isGif()) {
                str = "png";
            } else {
                str = "jpg";
            }
            if (str.equals("png") || str.equals("gif")) {
                mimeType = MimeType.PNG;
            } else {
                mimeType = MimeType.JPG;
            }
            this.mOutputMimeType = mimeType;
        }
        return this.mOutputMimeType;
    }

    public boolean process(Rect rect) {
        Uri uri;
        Bundle extras = this.mIntent.getExtras();
        if (extras != null) {
            uri = (Uri) extras.getParcelable("output");
        } else {
            uri = null;
        }
        if (uri == null) {
            Log.e(this.TAG, "process failed. no extra");
            return false;
        }
        Bitmap croppedImage = getCroppedImage(rect, extras);
        if (croppedImage == null || !saveBitmapToUri(croppedImage, uri)) {
            return false;
        }
        this.mOutputUri = uri;
        return true;
    }

    public boolean saveBitmapToOutputStream(Bitmap bitmap, Bitmap.CompressFormat compressFormat, OutputStream outputStream) {
        return bitmap.compress(compressFormat, 100, outputStream);
    }

    public boolean saveBitmapToUri(Bitmap bitmap, Uri uri) {
        OutputStream openOutputStream;
        try {
            openOutputStream = AppResources.getAppContext().getContentResolver().openOutputStream(uri);
            if (openOutputStream != null) {
                boolean saveBitmapToOutputStream = saveBitmapToOutputStream(bitmap, getOutputCompressFormat(), openOutputStream);
                openOutputStream.close();
                return saveBitmapToOutputStream;
            }
            Log.w(this.TAG, "saveBitmapToUri failed, out stream is null");
            if (openOutputStream == null) {
                return false;
            }
            openOutputStream.close();
            return false;
        } catch (IOException | SecurityException e) {
            String str = this.TAG;
            Log.e((CharSequence) str, "saveBitmapToUri failed. writable=" + PermissionHelper.isUriWritable(uri), e);
            Utils.showThemeToast(R.string.access_denied);
            return false;
        } catch (IllegalArgumentException e7) {
            String str2 = this.TAG;
            Log.e(str2, "saveBitmapToUri failed uri=" + uri + ". e=" + e7.getMessage());
            new InternalException("fail saveBitmapToUri crop").post();
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
