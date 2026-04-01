package com.samsung.android.gallery.app.controller.externals;

import A9.a;
import Ba.g;
import Ba.h;
import H3.n;
import J6.c;
import N2.j;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.PointF;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GsonTool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.sec.android.gallery3d.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import z2.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DocumentScanCmd extends BaseCommand {
    private static final boolean SUPPORT_VEX_DOCUMENT_SCAN = Features.isEnabled(Features.SUPPORT_VEX_DOCUMENT_SCAN);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DocumentScanSaveTask {

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class EventContextImpl implements EventContext {
            final Activity activity;

            public EventContextImpl(Activity activity2) {
                this.activity = activity2;
            }

            public Activity getActivity() {
                return this.activity;
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$execute$0(Activity activity, String str, Uri uri) {
            showSnackBarForMyFiles(activity, str);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$execute$1(Activity activity, Uri uri, String str) {
            saveFile(activity, uri, str, new a(6, this, activity));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$execute$3(Activity activity, Uri uri, String str) {
            saveFile(activity, uri, str, new h(5, activity));
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$saveFile$5(TimeTickLog timeTickLog, BiConsumer biConsumer, String str, Uri uri) {
            timeTickLog.tick();
            A.a.A(new Object[]{uri, timeTickLog}, new StringBuilder("saveFile > media-scan"), "DocumentScanCmd#Save");
            if (biConsumer != null) {
                ThreadUtil.postOnUiThread(new c(biConsumer, str, uri, 14));
            }
        }

        private void saveFile(Activity activity, Uri uri, String str, BiConsumer<String, Uri> biConsumer) {
            FileOutputStream fileOutputStream;
            TimeTickLog timeTickLog = new TimeTickLog();
            if (!FileUtils.makeParentIfAbsent(str)) {
                Log.e("DocumentScanCmd#Save", "save failed. mkdir failed");
                return;
            }
            File file = new File(str);
            boolean z = false;
            try {
                InputStream openInputStream = activity.getContentResolver().openInputStream(uri);
                try {
                    fileOutputStream = new FileOutputStream(file);
                    byte[] bArr = new byte[65536];
                    while (true) {
                        int read = openInputStream.read(bArr);
                        if (read > 0) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            fileOutputStream.flush();
                            timeTickLog.tick();
                            MediaScannerConnection.scanFile(activity.getApplicationContext(), new String[]{str}, (String[]) null, new n(1, timeTickLog, biConsumer));
                            Log.d("DocumentScanCmd#Save", "saveFile" + Logger.vt(timeTickLog));
                            fileOutputStream.close();
                            openInputStream.close();
                            return;
                        }
                    }
                } catch (Throwable th) {
                    if (openInputStream != null) {
                        openInputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e) {
                if (!file.exists() || file.delete()) {
                    z = true;
                }
                Log.e("DocumentScanCmd#Save", "saveFile failed" + Logger.v(Boolean.valueOf(z)) + " e=" + e.getMessage());
                return;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }

        private void showSnackBarForMyFiles(Activity activity, String str) {
            View findViewById = activity.findViewById(16908290);
            if (findViewById != null) {
                r j2 = r.j(findViewById, 0, 0, false, activity.getString(R.string.scan_saved_to, new Object[]{FileUtils.getDirectoryFromPath(BucketUtils.translatePath(str, false), false)}));
                j2.k(j2.f2220h.getText(R.string.view), new g(5, activity, str));
                j2.l();
            }
        }

        public void execute(Activity activity, int i2, Intent intent) {
            boolean z;
            Bundle bundle;
            boolean z3 = true;
            if (i2 != -1 || intent == null || activity == null) {
                Activity activity2 = activity;
                Integer valueOf = Integer.valueOf(i2);
                if (intent != null) {
                    z = true;
                } else {
                    z = false;
                }
                Boolean valueOf2 = Boolean.valueOf(z);
                if (activity2 == null) {
                    z3 = false;
                }
                Log.w((CharSequence) "DocumentScanCmd#Save", "save skip", valueOf, valueOf2, Boolean.valueOf(z3));
                return;
            }
            Uri uri = (Uri) intent.getParcelableExtra("pdfUri", Uri.class);
            if (uri != null) {
                String str = StorageInfo.getDefault().getDocumentsDir("Document scans") + File.separator + "Scan_" + TimeUtil.getFileTimestamp() + ".pdf";
                StringBuilder sb2 = new StringBuilder("save start");
                sb2.append(Logger.v("application/pdf", Boolean.FALSE));
                sb2.append(" ");
                sb2.append(Logger.getEncodedString(uri + GlobalPostProcInternalPPInterface.SPLIT_REGEX + str));
                Log.d("DocumentScanCmd#Save", sb2.toString());
                ThreadUtil.runOnBgThread(new N3.a(this, activity, uri, str, 0));
                return;
            }
            Activity activity3 = activity;
            ArrayList d = intent.getParcelableArrayListExtra("scannedDocumentBundles", Bundle.class);
            if (d == null || d.isEmpty()) {
                bundle = null;
            } else {
                bundle = (Bundle) d.get(0);
            }
            if (bundle == null) {
                Log.e("DocumentScanCmd#Save", "save failed. no bundle available");
                return;
            }
            Uri uri2 = (Uri) bundle.getParcelable("rectifiedImageUri", Uri.class);
            if (uri2 == null) {
                Log.e("DocumentScanCmd#Save", "save failed. no input-uri");
                return;
            }
            String string = bundle.getString("MIME_TYPE");
            String string2 = bundle.getString("TARGET_PATH");
            if (TextUtils.isEmpty(string2)) {
                string2 = StorageInfo.getDefault().documentScans + File.separator + "Scan_" + TimeUtil.getFileTimestamp() + ".jpg";
            }
            if (!TextUtils.isEmpty(string)) {
                string2 = FileUtils.replaceExtension(string2, getFileExtension(string));
            }
            if (FileUtils.exists(string2)) {
                string2 = FileUtils.makeUniqueFilename(string2);
            } else {
                z3 = false;
            }
            String str2 = string2;
            StringBuilder sb3 = new StringBuilder("save start");
            sb3.append(Logger.v(string, Boolean.valueOf(z3)));
            sb3.append(" ");
            sb3.append(Logger.getEncodedString(uri2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + str2));
            Log.d("DocumentScanCmd#Save", sb3.toString());
            ThreadUtil.runOnBgThread(new N3.a(this, activity3, uri2, str2, 1));
        }

        public String getFileExtension(String str) {
            if (str.endsWith("/jpeg") || str.endsWith("/jpg")) {
                return "jpg";
            }
            if (str.endsWith("/heic") || str.endsWith("/heif")) {
                return IFormat.FORMAT_HEIC;
            }
            if (str.contains("/x-")) {
                String[] split = str.split("-");
                if (split.length > 1) {
                    return split[split.length - 1];
                }
                return "raw";
            }
            String[] split2 = str.split("/");
            if (split2.length > 1) {
                return split2[split2.length - 1];
            }
            return "jpg";
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OriginalData {
        int height;
        long id;
        String mimeType;
        int orientation;
        int orientationTag;
        String path;
        int width;

        public OriginalData(MediaItem mediaItem) {
            this.id = mediaItem.getFileId();
            this.path = mediaItem.getPath();
            this.mimeType = mediaItem.getMimeType();
            this.width = mediaItem.getWidth();
            this.height = mediaItem.getHeight();
            this.orientation = mediaItem.getOrientation();
            this.orientationTag = mediaItem.getOrientationTag();
        }

        public String toJsonString() {
            return GsonTool.toJsonString(this, false);
        }
    }

    private float[] convertVertexToFloatArray(ArrayList<PointF> arrayList) {
        float[] fArr = new float[(arrayList.size() * 2)];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            int i7 = i2 * 2;
            fArr[i7] = arrayList.get(i2).x;
            fArr[i7 + 1] = arrayList.get(i2).y;
        }
        return fArr;
    }

    private String getVexTargetPath(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            str2 = TimeUtil.getFileTimestamp() + ".jpg";
        } else {
            str2 = FileUtils.getNameFromPath(str);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(StorageInfo.getDefault().documentScans);
        return j.f(sb2, File.separator, "Scan_", str2);
    }

    private ArrayList<PointF> updateRotateVertex(ArrayList<PointF> arrayList, int i2) {
        ArrayList<PointF> arrayList2 = new ArrayList<>();
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            float f = arrayList.get(i7).x;
            float f5 = arrayList.get(i7).y;
            if (i2 == 90) {
                arrayList2.add(new PointF(f5, -f));
            } else if (i2 == 180) {
                arrayList2.add(new PointF(-f, -f5));
            } else if (i2 == 270) {
                arrayList2.add(new PointF(-f5, f));
            }
        }
        return arrayList2;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_DOCUMENT_SCAN.toString();
    }

    public boolean isFromLockScreenAndScreenLocked() {
        LaunchIntent launchIntent = (LaunchIntent) getBlackboard().read("data://launch_intent");
        if (launchIntent == null || !launchIntent.isFromLockScreen() || !SeApiCompat.isScreenLocked(getActivity())) {
            return false;
        }
        return true;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        boolean z;
        MediaItem mediaItem = objArr[0];
        ArrayList arrayList = objArr[1];
        if (mediaItem == null || (!(z = SUPPORT_VEX_DOCUMENT_SCAN) && arrayList == null)) {
            Log.e(this.TAG, "document scan failed: item or cropVertex is null");
            return;
        }
        Uri uri = ContentUri.getUri(mediaItem);
        if (PocFeatures.DUAL_PHOTO_PREVIEW && mediaItem.isStream()) {
            uri = ContentUri.getStreamUri(getContext(), mediaItem);
        }
        if (uri == null) {
            Log.e(this.TAG, "document scan failed: item uri is null");
        } else if (z) {
            startVexScan(mediaItem, uri, arrayList);
        } else {
            startScan(mediaItem, uri, arrayList);
        }
    }

    public void startScan(MediaItem mediaItem, Uri uri, ArrayList<PointF> arrayList) {
        if (mediaItem.getOrientation() != 0) {
            arrayList = updateRotateVertex(arrayList, mediaItem.getOrientation());
        }
        try {
            getContext().grantUriPermission("com.sec.android.app.camera", uri, 3);
            Intent intent = new Intent("com.sec.android.app.camera.action.DOCUMENT_SCAN");
            intent.setClassName("com.sec.android.app.camera", "com.sec.android.app.camera.cropper.DocumentScanActivity");
            intent.putExtra("cropMode", 1);
            intent.putExtra("imagePath", mediaItem.getPath());
            intent.putExtra("savingDir", FileUtils.getParent(mediaItem.getPath()));
            intent.putExtra("cropCoordinate", arrayList);
            intent.putExtra(OCRServiceConstant.KEY_PARAM_URI, uri);
            intent.putExtra("nonDestruction", true);
            intent.putExtra("isSecure", isFromLockScreenAndScreenLocked());
            intent.putExtra("heifEnabled", mediaItem.isHeif());
            intent.addFlags(3);
            getActivity().startActivityForResult(intent, 795);
        } catch (ActivityNotFoundException e) {
            Log.e((CharSequence) this.TAG, "document scan activity not found", (Throwable) e);
        }
    }

    public void startVexScan(MediaItem mediaItem, Uri uri, ArrayList<PointF> arrayList) {
        float[] fArr;
        try {
            getContext().grantUriPermission("com.samsung.android.app.vex.scanner", uri, 3);
            Intent intent = new Intent("com.samsung.android.app.vex.scanner.action.EDIT_DOCUMENT");
            intent.setClassName("com.samsung.android.app.vex.scanner", "com.samsung.android.app.vex.scanner.SingleEditActivity");
            intent.putExtra("clientPackageName", "com.sec.android.gallery3d");
            if (arrayList != null) {
                fArr = convertVertexToFloatArray(arrayList);
            } else {
                fArr = null;
            }
            intent.putExtra("EDIT_DOC_RECT", fArr);
            intent.putExtra("EDIT_INPUT_FILE", uri.toString());
            intent.putExtra("EDIT_INPUT_FILE_PATH", mediaItem.getPath());
            intent.putExtra("EXTERNAL_STORAGE_IMPORT", true);
            intent.putExtra("IS_HEIF", mediaItem.isHeif());
            intent.putExtra("IS_SECURE", isFromLockScreenAndScreenLocked());
            intent.putExtra("NON_DESTRUCTION", true);
            intent.putExtra("OBJECT_REMOVER", false);
            intent.putExtra("TARGET_PATH", getVexTargetPath(mediaItem.getPath()));
            intent.putExtra("ORIGINAL_DATA", new OriginalData(mediaItem).toJsonString());
            intent.putExtra("PDF_OUTPUT", true);
            intent.addFlags(3);
            getActivity().startActivityForResult(intent, 802);
        } catch (ActivityNotFoundException e) {
            Log.e((CharSequence) this.TAG, "document scan activity not found", (Throwable) e);
        }
    }
}
