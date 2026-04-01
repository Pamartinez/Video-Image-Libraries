package com.samsung.android.gallery.app.controller.externals;

import N2.j;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import c0.C0086a;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.local.LocalProviderHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConversionAsyncTask extends AsyncTask<EventContext, Void, Object[]> {
    private ConvertType mConvertType;
    private final Dialog mDialog;
    protected EventContext mHandler;
    protected MediaItem mMediaItem;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ConvertType {
        HeifToJpeg
    }

    public ConversionAsyncTask(EventContext eventContext, MediaItem mediaItem, ConvertType convertType) {
        this.mMediaItem = mediaItem;
        this.mConvertType = convertType;
        this.mHandler = eventContext;
        Dialog spinnerDialog = getSpinnerDialog(eventContext.getContext());
        this.mDialog = spinnerDialog;
        if (spinnerDialog.getWindow() != null) {
            spinnerDialog.getWindow().setGravity(17);
        }
    }

    private Object[] convert(EventContext eventContext) {
        MediaItem mediaItem;
        Context context = eventContext.getContext();
        if (context == null) {
            Log.e("ConversionAsyncTask", "convert failed null Context");
            return null;
        }
        File externalFilesDir = context.getExternalFilesDir(".share");
        if (externalFilesDir == null || !FileUtils.makeDirectoryIfAbsent(externalFilesDir)) {
            return null;
        }
        String filePath = getFilePath(externalFilesDir.getAbsolutePath());
        if (FileUtils.exists(filePath)) {
            mediaItem = UriItemLoader.createUriItem(context, new File(filePath));
        } else {
            mediaItem = convertNewFile(context, filePath);
        }
        return new Object[]{eventContext, mediaItem};
    }

    private void convertHeifToJpeg(String str, String str2) {
        if (!this.mMediaItem.isCloudOnly()) {
            SeApiCompat.getMediaTranscodeCompat().convertHeif2Jpeg(str, str2);
        } else if (hasJpgThumbnail(str.toLowerCase())) {
            saveThumbnail(str, str2);
        } else {
            Log.e("ConversionAsyncTask", "convertHeifToJpeg failed. no cloud cache");
        }
    }

    private MediaItem convertNewFile(Context context, String str) {
        convertByType(this.mMediaItem.getPath(), str);
        File file = new File(str);
        if (file.exists()) {
            updateLocalDb(context, str);
            return UriItemLoader.createUriItem(context, file);
        }
        Log.d("ConversionAsyncTask", "convertNewFile failed. " + this.mConvertType);
        return null;
    }

    private void executeShareViaCmd(EventContext eventContext, MediaItem mediaItem) {
        if (mediaItem == null) {
            showErrorToast();
        }
        ShareViaCmd shareViaCmd = new ShareViaCmd();
        if (mediaItem == null) {
            mediaItem = this.mMediaItem;
        }
        shareViaCmd.execute(eventContext, new MediaItem[]{mediaItem}, null);
    }

    private String getExtension() {
        if (this.mConvertType == ConvertType.HeifToJpeg) {
            return ".jpg";
        }
        return null;
    }

    private String getFilePath(String str) {
        StringBuilder s = C0212a.s(str);
        s.append(File.separator);
        s.append(FileUtils.getBaseName(getOriginalPath()));
        s.append(getExtension());
        return s.toString();
    }

    private String getOriginalPath() {
        boolean isCloudOnly = this.mMediaItem.isCloudOnly();
        MediaItem mediaItem = this.mMediaItem;
        if (isCloudOnly) {
            return mediaItem.getCloudData2();
        }
        return mediaItem.getPath();
    }

    private Dialog getSpinnerDialog(Context context) {
        return new AlertDialog.Builder(context, R.style.TransparentDialogStyle).setView(LayoutInflater.from(context).inflate(R.layout.spinner_dialog, (ViewGroup) null, false)).create();
    }

    private Bitmap getSrcBitmap(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1;
        return BitmapFactory.decodeFile(str, options);
    }

    private boolean hasJpgThumbnail(String str) {
        if (str.endsWith("jpg") || str.endsWith(IFormat.FORMAT_JPEG)) {
            return true;
        }
        return false;
    }

    private void saveThumbnail(String str, String str2) {
        FileOutputStream fileOutputStream;
        if (str != null) {
            try {
                fileOutputStream = new FileOutputStream(str2);
                BitmapUtils.rotateBitmap(getSrcBitmap(str), this.mMediaItem.getOrientation()).compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.close();
                return;
            } catch (IOException e) {
                Log.w((CharSequence) "ConversionAsyncTask", "saveThumbnail failed", (Throwable) e);
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            return;
        }
        throw th;
    }

    private void showErrorToast() {
        Toast.makeText(this.mHandler.getContext(), R.string.can_not_convert_image_sending_original, 0).show();
    }

    private void updateLocalDb(Context context, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues c5 = C0086a.c("__absPath", str);
        c5.put("date_added", Long.valueOf(currentTimeMillis));
        new LocalProviderHelper(context.getContentResolver()).insertConvertedFile(c5);
        j.m(currentTimeMillis, "]", "ConversionAsyncTask", new StringBuilder("insert to local db ["));
    }

    public void convertByType(String str, String str2) {
        if (this.mConvertType == ConvertType.HeifToJpeg) {
            convertHeifToJpeg(str, str2);
            return;
        }
        Log.e("ConversionAsyncTask", "convertByType not supported {" + this.mConvertType + "}");
    }

    public void onPreExecute() {
        super.onPreExecute();
        if (SeApiCompat.isActivityResumed(this.mHandler.getActivity())) {
            this.mDialog.show();
        } else {
            cancel(true);
        }
    }

    public Object[] doInBackground(EventContext... eventContextArr) {
        return convert(eventContextArr[0]);
    }

    public void onPostExecute(Object[] objArr) {
        super.onPostExecute(objArr);
        try {
            this.mDialog.dismiss();
            executeShareViaCmd(objArr[0], objArr[1]);
        } catch (IllegalArgumentException | NullPointerException e) {
            j.u(e, new StringBuilder("onPostExecute failed e="), "ConversionAsyncTask");
        }
    }
}
