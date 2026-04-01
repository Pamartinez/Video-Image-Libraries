package com.samsung.android.gallery.module.service.support;

import A4.C0369d;
import E7.c;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.dynamicview.PlaybackOption;
import com.samsung.android.gallery.module.pipconverter.PipConverter;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.FileType;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.sum.core.descriptor.b;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryServiceHelper {
    private Consumer<String> mCompletedListener = null;
    private int mNotificationId = -1;
    private PipConverter mPipConverter;
    private Consumer<Integer> mProgressListener = null;
    private String mResultFilePath;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum OperationType {
        SAVE_COVER,
        SAVE_CARD,
        SHARE_COVER,
        SHARE_CARD
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ResultState {
        SUCCESS,
        FAIL,
        CANCEL
    }

    private void complete(boolean z) {
        Optional.ofNullable(this.mCompletedListener).ifPresent(new c(this, z, 14));
    }

    private void deleteFile(String str) {
        if (str != null) {
            SecureFile secureFile = new SecureFile(str);
            if (secureFile.exists() && secureFile.delete()) {
                MediaScanner.scanFolder(FileUtils.getDirectoryFromPath(str, false), (MediaScannerListener) null);
                Log.d("StoryServiceHelper", "delete result file : " + Logger.getEncodedString(str));
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$complete$2(boolean z, Consumer consumer) {
        String str;
        if (z) {
            str = this.mResultFilePath;
        } else {
            str = null;
        }
        consumer.accept(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareEncode$0(Integer num) {
        boolean z;
        updateProgress(num.intValue());
        if (num.intValue() == -1 || num.intValue() == 100) {
            if (num.intValue() == 100) {
                z = true;
            } else {
                z = false;
            }
            complete(z);
        }
    }

    private boolean startBitmapToFile(Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        if (bitmap == null) {
            return false;
        }
        try {
            SecureFile secureFile = new SecureFile(this.mResultFilePath);
            if (secureFile.createNewFile()) {
                fileOutputStream = new FileOutputStream(secureFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                return true;
            }
        } catch (IOException | NullPointerException | SecurityException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return false;
        throw th;
    }

    private void updateProgress(int i2) {
        Optional.ofNullable(this.mProgressListener).ifPresent(new C0369d(i2, 20));
    }

    public void cancel() {
        deleteFile(this.mResultFilePath);
        this.mResultFilePath = null;
        this.mNotificationId = -1;
    }

    public Bitmap getContentBitmap(Blackboard blackboard, Rect rect, boolean z, boolean z3) {
        Bitmap bitmap = (Bitmap) blackboard.pop("data://user/storyContentBitmap");
        if (bitmap == null) {
            Log.w("StoryServiceHelper", "getContentBitmap(), null bitmap");
            return null;
        }
        if (!z3 && !z) {
            int round = ((int) Math.round((((double) bitmap.getWidth()) * 1.58839779d) / 100.0d)) / 4;
            Rect rect2 = new Rect(rect.left - round, rect.top - round, rect.right + round, rect.bottom + round);
            Paint paint = new Paint();
            paint.setColor(-1);
            new Canvas(bitmap).drawRect(rect2, paint);
        }
        return bitmap;
    }

    public int getNotificationId() {
        return this.mNotificationId;
    }

    public String getNotificationMessage(Context context, OperationType operationType, boolean z) {
        if (z) {
            if (operationType == OperationType.SHARE_COVER) {
                return context.getString(R$string.story_cover_sharing_success);
            }
            if (operationType == OperationType.SHARE_CARD) {
                return context.getString(R$string.story_card_sharing_success);
            }
            if (operationType == OperationType.SAVE_COVER) {
                return context.getString(R$string.story_cover_saved);
            }
            if (operationType == OperationType.SAVE_CARD) {
                return context.getString(R$string.story_contents_card_saved);
            }
            return "";
        } else if (operationType == OperationType.SHARE_COVER) {
            return context.getString(R$string.story_cover_sharing);
        } else {
            if (operationType == OperationType.SHARE_CARD) {
                return context.getString(R$string.story_card_sharing);
            }
            if (operationType == OperationType.SAVE_COVER) {
                return context.getString(R$string.story_cover_saving);
            }
            if (operationType == OperationType.SAVE_CARD) {
                return context.getString(R$string.story_contents_card_saving);
            }
            return "";
        }
    }

    public String getResultMessage(Context context, ResultState resultState, OperationType operationType) {
        int i2;
        if (resultState == ResultState.SUCCESS) {
            String replace = FileUtils.getDirectoryFromPath(this.mResultFilePath).replace(FileUtils.EXTERNAL_STORAGE_DIR, "");
            if (operationType == OperationType.SHARE_COVER) {
                return context.getString(R$string.story_cover_sharing_success);
            }
            if (operationType == OperationType.SHARE_CARD) {
                return context.getString(R$string.story_card_sharing_success);
            }
            if (operationType == OperationType.SAVE_COVER) {
                return context.getString(R$string.story_cover_saving_success_cover_toast, new Object[]{replace});
            }
            if (operationType == OperationType.SAVE_CARD) {
                return context.getString(R$string.story_contents_card_saving_success_cover_toast, new Object[]{replace});
            }
        } else if (resultState == ResultState.FAIL) {
            if (FileType.isImage(this.mResultFilePath)) {
                i2 = R$string.could_not_save_image;
            } else {
                i2 = R$string.could_not_save_video;
            }
            return context.getString(i2);
        } else if (resultState == ResultState.CANCEL) {
            if (operationType == OperationType.SHARE_CARD || operationType == OperationType.SHARE_COVER) {
                return context.getString(R$string.conversion_canceled);
            }
            return context.getString(R$string.story_saving_cancel_toast);
        }
        return "";
    }

    public boolean prepareEncode(Context context, String str, String str2, Bitmap bitmap, Rect rect, ArrayList<PlaybackOption> arrayList) {
        if (rect == null || (rect.width() == 0 && rect.height() == 0)) {
            Log.d("StoryServiceHelper", "video position and size error");
            return false;
        } else if (bitmap.getWidth() == 0 || bitmap.getHeight() == 0) {
            Log.d("StoryServiceHelper", "wrong bitmap");
            return false;
        } else {
            this.mResultFilePath = str;
            PipConverter pipConverter = new PipConverter(context, str, str2);
            this.mPipConverter = pipConverter;
            pipConverter.setVideoPosition(rect.left, rect.top);
            this.mPipConverter.setVideoSize(rect.width(), rect.height());
            this.mPipConverter.setProgressListener(new b(26, this));
            return this.mPipConverter.prepareEncode(str2, bitmap, arrayList);
        }
    }

    public boolean prepareSaveBitmap(String str, Bitmap bitmap) {
        this.mResultFilePath = str;
        if (bitmap == null || bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
            return false;
        }
        return true;
    }

    public void setCompletedListener(Consumer<String> consumer) {
        this.mCompletedListener = consumer;
    }

    public void setNotificationId(int i2) {
        this.mNotificationId = i2;
    }

    public void setProgressListener(Consumer<Integer> consumer) {
        this.mProgressListener = consumer;
    }

    public void startEncode() {
        PipConverter pipConverter = this.mPipConverter;
        if (pipConverter != null) {
            pipConverter.start();
        }
    }

    public void startSaveBitmap(Bitmap bitmap) {
        complete(startBitmapToFile(bitmap));
    }

    public void stopEncode() {
        PipConverter pipConverter = this.mPipConverter;
        if (pipConverter != null) {
            pipConverter.stop();
            this.mPipConverter = null;
        }
        this.mNotificationId = -1;
    }
}
