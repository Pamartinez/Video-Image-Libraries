package com.samsung.android.gallery.module.clipboard;

import A4.B;
import B8.d;
import O3.o;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.dialog.PasteClipboardPrepareDialog;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.module.clipboard.ClipboardPasteHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SafeClipboard;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import i.C0212a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import o6.p;
import org.json.JSONArray;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClipboardDataPrepareTask {
    private byte mClipId;
    private int mDeviceId;
    private final ArrayList<MediaItem> mFileProviderItems = new ArrayList<>();
    private boolean mInterrupt = false;
    private final OnPrepareDoneListener mListener;
    private final ArrayList<MediaItem> mMediaItems = new ArrayList<>();
    private final WeakReference<Context> mRef;
    private String mResultString;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnPrepareDoneListener {
    }

    public ClipboardDataPrepareTask(Context context, OnPrepareDoneListener onPrepareDoneListener) {
        this.mRef = new WeakReference<>(context);
        this.mListener = onPrepareDoneListener;
    }

    private void getItemFromFileProvider(Uri uri) {
        Uri uri2;
        Cursor query;
        Throwable th;
        Context context = this.mRef.get();
        if (context == null) {
            Log.e("ClipboardDataPrepareTask", "unable to get items from file provider, null context");
            return;
        }
        try {
            uri2 = uri;
            try {
                query = context.getContentResolver().query(uri2, (String[]) null, (String) null, (String[]) null, (String) null);
                if (query != null) {
                    if (query.moveToFirst()) {
                        Optional ofNullable = Optional.ofNullable(ClipboardPasteHelper.getMediaItemFromFileProvider(uri2, query.getString(query.getColumnIndex("_display_name")), query.getLong(query.getColumnIndex(IParameterKey.SIZE))));
                        ArrayList<MediaItem> arrayList = this.mFileProviderItems;
                        Objects.requireNonNull(arrayList);
                        ofNullable.ifPresent(new d(arrayList, 23));
                    }
                }
                if (query != null) {
                    query.close();
                    return;
                }
                return;
            } catch (Exception e) {
                e = e;
                Log.e((CharSequence) "ClipboardDataPrepareTask", "unable to get items from file provider", Logger.getEncodedString((Object) uri2), e);
                return;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } catch (Exception e7) {
            e = e7;
            uri2 = uri;
            Log.e((CharSequence) "ClipboardDataPrepareTask", "unable to get items from file provider", Logger.getEncodedString((Object) uri2), e);
            return;
        }
        throw th;
    }

    private void getItemsFromMCF(Uri uri) {
        Uri uri2;
        Cursor query;
        Throwable th;
        Context context = this.mRef.get();
        if (context == null) {
            Log.e("ClipboardDataPrepareTask", "unable to get items from MCF, null context");
            return;
        }
        try {
            uri2 = uri;
            try {
                query = context.getContentResolver().query(uri2, (String[]) null, (String) null, (String[]) null, (String) null);
                if (query != null) {
                    if (query.moveToFirst()) {
                        String string = query.getString(query.getColumnIndex("_needSource"));
                        String string2 = query.getString(query.getColumnIndex("_display_name"));
                        long j2 = query.getLong(query.getColumnIndex(IParameterKey.SIZE));
                        if (SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equals(string)) {
                            getSubItemsFromMCF(uri2);
                        } else {
                            Optional ofNullable = Optional.ofNullable(ClipboardPasteHelper.getMediaItemFromFileProvider(uri2, string2, j2));
                            ArrayList<MediaItem> arrayList = this.mFileProviderItems;
                            Objects.requireNonNull(arrayList);
                            ofNullable.ifPresent(new d(arrayList, 23));
                        }
                    }
                }
                if (query != null) {
                    query.close();
                    return;
                }
                return;
            } catch (Exception e) {
                e = e;
                Log.e((CharSequence) "ClipboardDataPrepareTask", "unable to get items from MCF ", Logger.getEncodedString((Object) uri2), e);
                return;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } catch (Exception e7) {
            e = e7;
            uri2 = uri;
            Log.e((CharSequence) "ClipboardDataPrepareTask", "unable to get items from MCF ", Logger.getEncodedString((Object) uri2), e);
            return;
        }
        throw th;
    }

    private void getItemsFromMyFiles(Uri uri) {
        Uri uri2;
        Cursor query;
        Throwable th;
        Context context = this.mRef.get();
        if (context == null) {
            Log.e("ClipboardDataPrepareTask", "unable to get items from MyFiles, null context");
            return;
        }
        try {
            uri2 = uri;
            try {
                query = context.getContentResolver().query(uri2, new String[]{"_path"}, (String) null, (String[]) null, (String) null);
                if (query != null) {
                    if (query.moveToFirst()) {
                        String string = query.getString(0);
                        if (new SecureFile(string).isDirectory()) {
                            getMediaItemsFromPath(string);
                        }
                    }
                }
                if (query != null) {
                    query.close();
                    return;
                }
                return;
            } catch (Exception e) {
                e = e;
                Log.e((CharSequence) "ClipboardDataPrepareTask", "unable to get items from MyFiles ", Logger.getEncodedString((Object) uri2), e);
                return;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } catch (Exception e7) {
            e = e7;
            uri2 = uri;
            Log.e((CharSequence) "ClipboardDataPrepareTask", "unable to get items from MyFiles ", Logger.getEncodedString((Object) uri2), e);
            return;
        }
        throw th;
    }

    private void getMediaItemsFromPath(String str) {
        Cursor query;
        try {
            query = DbCompat.query(DbKey.ALL_PICTURES, new B(C0212a.A(str, "/%"), 14));
            if (query != null) {
                if (query.moveToFirst()) {
                    while (true) {
                        if (!this.mInterrupt) {
                            this.mMediaItems.add(MediaItemLoader.load(query));
                            if (!query.moveToNext()) {
                                break;
                            }
                        }
                        break;
                    }
                }
            }
            if (query == null) {
                return;
            }
            break;
            query.close();
            return;
        } catch (Exception e) {
            Log.e((CharSequence) "ClipboardDataPrepareTask", "unable to get items", Logger.getEncodedString(str), e);
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void getSubItemsFromMCF(Uri uri) {
        String str;
        try {
            Context context = this.mRef.get();
            if (context == null) {
                Log.e("ClipboardDataPrepareTask", "unable to get uri list from MCF, null context");
                return;
            }
            Bundle uriList = ClipboardPasteHelper.getUriList(context, uri);
            if (uriList == null) {
                Log.e("ClipboardDataPrepareTask", "unable to get uri list from MCF");
                return;
            }
            this.mClipId = uriList.getByte("KEY_GET_URI_LIST_CLIP_ID");
            this.mDeviceId = uriList.getInt("KEY_GET_URI_LIST_DEVICE_ID");
            String string = uriList.getString("KEY_GET_URI_LIST_URI_LIST");
            if (TextUtils.isEmpty(string)) {
                Log.e((CharSequence) "ClipboardDataPrepareTask", "empty uri list is delivered from MCF", Byte.valueOf(this.mClipId), Integer.valueOf(this.mDeviceId), this.mResultString);
                return;
            }
            JSONArray jSONArray = new JSONArray(string);
            int i2 = 0;
            while (i2 < jSONArray.length()) {
                if (!this.mInterrupt) {
                    Optional ofNullable = Optional.ofNullable(ClipboardPasteHelper.getMediaItemFromJSONObject(jSONArray.optJSONObject(i2)));
                    ArrayList<MediaItem> arrayList = this.mFileProviderItems;
                    Objects.requireNonNull(arrayList);
                    ofNullable.ifPresent(new d(arrayList, 23));
                    i2++;
                } else {
                    return;
                }
            }
            if (this.mFileProviderItems.isEmpty()) {
                ClipboardPasteHelper.PasteResult pasteResult = ClipboardPasteHelper.getPasteResult(this.mRef.get(), this.mClipId, this.mDeviceId);
                if (pasteResult != null) {
                    if (!pasteResult.isSuccess()) {
                        str = pasteResult.getResult();
                        this.mResultString = str;
                    }
                }
                str = null;
                this.mResultString = str;
            }
        } catch (Exception e) {
            Log.e((CharSequence) "ClipboardDataPrepareTask", "unable to get sub items ", uri, e);
        }
    }

    /* access modifiers changed from: private */
    public void lambda$run$0() {
        prepareData();
        OnPrepareDoneListener onPrepareDoneListener = this.mListener;
        ((PasteClipboardPrepareDialog) ((p) onPrepareDoneListener).e).onPrepared(this.mMediaItems, this.mFileProviderItems, this.mClipId, this.mDeviceId, this.mResultString);
    }

    private void prepareData() {
        Context context = this.mRef.get();
        if (context == null) {
            Log.e("ClipboardDataPrepareTask", "unable to get clipboard manager, null context");
            return;
        }
        SafeClipboard safeClipboard = new SafeClipboard(context);
        if (!safeClipboard.hasService()) {
            Log.e("ClipboardDataPrepareTask", "null clipboard");
            return;
        }
        ClipData primaryClip = safeClipboard.getPrimaryClip();
        ClipDescription primaryClipDescription = safeClipboard.getPrimaryClipDescription();
        if (primaryClip == null || primaryClipDescription == null) {
            Log.e("ClipboardDataPrepareTask", "null primary information");
            return;
        }
        String str = (String) Optional.ofNullable(primaryClipDescription.getLabel()).map(new o(10)).orElse((Object) null);
        if (str == null || !str.contains("mcf_continuity")) {
            prepareMediaData(primaryClip);
        } else {
            prepareMCFData(primaryClip);
        }
    }

    private void prepareMCFData(ClipData clipData) {
        int itemCount = clipData.getItemCount();
        for (int i2 = 0; i2 < itemCount && !this.mInterrupt; i2++) {
            Uri uri = clipData.getItemAt(i2).getUri();
            if (uri != null) {
                if ("com.samsung.android.honeyboard.provider".equals(uri.getAuthority())) {
                    getItemFromFileProvider(uri);
                } else if ("com.samsung.android.honeyboard.provider.CDCPContentProvider".equals(uri.getAuthority())) {
                    getItemsFromMCF(uri);
                }
            }
        }
    }

    private void prepareMediaData(ClipData clipData) {
        int itemCount = clipData.getItemCount();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < itemCount) {
            if (!this.mInterrupt) {
                Uri uri = clipData.getItemAt(i2).getUri();
                if (uri != null) {
                    String authority = uri.getAuthority();
                    if (MediaUri.getInstance().matches(uri.toString())) {
                        arrayList.add(uri);
                    } else if (authority != null) {
                        if ("com.sec.android.app.myfiles.FileProvider".equals(authority)) {
                            getItemsFromMyFiles(uri);
                        } else if (authority.contains("fileprovider")) {
                            getItemFromFileProvider(uri);
                        }
                    }
                }
                i2++;
            } else {
                return;
            }
        }
        Context context = this.mRef.get();
        if (context != null && !arrayList.isEmpty()) {
            UriItemLoader.loadMediaItemFromUrisOnSkipException(context, arrayList, this.mMediaItems);
        }
    }

    public void run() {
        SimpleThreadPool.getInstance().execute(new M9.o(22, this));
    }

    public void setInterrupt() {
        this.mInterrupt = true;
    }
}
