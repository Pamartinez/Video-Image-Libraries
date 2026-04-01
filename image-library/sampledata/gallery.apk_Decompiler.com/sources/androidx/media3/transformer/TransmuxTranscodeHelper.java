package androidx.media3.transformer;

import F2.U;
import F2.y0;
import android.content.Context;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.transformer.Composition;
import androidx.media3.transformer.EditedMediaItem;
import androidx.media3.transformer.EditedMediaItemSequence;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class TransmuxTranscodeHelper {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ResumeMetadata {
    }

    public static Composition buildUponComposition(Composition composition, boolean z, boolean z3, ResumeMetadata resumeMetadata) {
        Composition.Builder buildUpon = composition.buildUpon();
        U u = composition.sequences;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < u.size(); i2++) {
            EditedMediaItemSequence editedMediaItemSequence = (EditedMediaItemSequence) u.get(i2);
            U u3 = editedMediaItemSequence.editedMediaItems;
            ArrayList arrayList2 = new ArrayList();
            for (int i7 = 0; i7 < u3.size(); i7++) {
                EditedMediaItem editedMediaItem = (EditedMediaItem) u3.get(i7);
                EditedMediaItem.Builder buildUpon2 = editedMediaItem.buildUpon();
                if (i7 == 0) {
                    buildUpon2.setMediaItem(editedMediaItem.mediaItem.buildUpon().setClippingConfiguration(editedMediaItem.mediaItem.clippingConfiguration.buildUpon().setStartPositionMs(Util.usToMs(0) + editedMediaItem.mediaItem.clippingConfiguration.startPositionMs).build()).build());
                }
                if (z) {
                    buildUpon2.setRemoveAudio(true);
                }
                if (z3) {
                    buildUpon2.setRemoveVideo(true);
                }
                arrayList2.add(buildUpon2.build());
            }
            arrayList.add(new EditedMediaItemSequence.Builder((List<EditedMediaItem>) arrayList2).setIsLooping(editedMediaItemSequence.isLooping).build());
        }
        buildUpon.setSequences(arrayList);
        return buildUpon.build();
    }

    public static Composition buildUponCompositionForTrimOptimization(Composition composition, long j2, long j3, long j8, boolean z, boolean z3) {
        Effects effects;
        EditedMediaItem editedMediaItem = (EditedMediaItem) ((EditedMediaItemSequence) composition.sequences.get(0)).editedMediaItems.get(0);
        MediaItem build = editedMediaItem.mediaItem.buildUpon().setClippingConfiguration(new MediaItem.ClippingConfiguration.Builder().setStartPositionUs(j2).setEndPositionUs(j3).setStartsAtKeyFrame(z).build()).build();
        if (z3) {
            effects = new Effects(editedMediaItem.effects.audioProcessors, y0.f278h);
        } else {
            effects = editedMediaItem.effects;
        }
        return composition.buildUpon().setSequences(U.B(new EditedMediaItemSequence.Builder(editedMediaItem.buildUpon().setMediaItem(build).setDurationUs(j8).setEffects(effects).build()).build())).build();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.common.util.concurrent.ListenableFuture, com.google.common.util.concurrent.D, java.lang.Object] */
    public static ListenableFuture copyFileAsync(final File file, final File file2) {
        final ? obj = new Object();
        new Thread("TransmuxTranscodeHelper:CopyFile") {
            /* JADX WARNING: Removed duplicated region for block: B:32:0x0051 A[SYNTHETIC, Splitter:B:32:0x0051] */
            /* JADX WARNING: Removed duplicated region for block: B:35:0x0056 A[Catch:{ IOException -> 0x0059 }] */
            /* JADX WARNING: Removed duplicated region for block: B:38:0x005c A[SYNTHETIC, Splitter:B:38:0x005c] */
            /* JADX WARNING: Removed duplicated region for block: B:41:0x0061 A[Catch:{ IOException -> 0x0064 }] */
            /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r7 = this;
                    com.google.common.util.concurrent.D r0 = r0
                    boolean r0 = r0.isCancelled()
                    if (r0 == 0) goto L_0x0009
                    goto L_0x0059
                L_0x0009:
                    r0 = 0
                    java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0046, all -> 0x0043 }
                    java.io.File r2 = r3     // Catch:{ Exception -> 0x0046, all -> 0x0043 }
                    r1.<init>(r2)     // Catch:{ Exception -> 0x0046, all -> 0x0043 }
                    java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x003e, all -> 0x003b }
                    java.io.File r3 = r4     // Catch:{ Exception -> 0x003e, all -> 0x003b }
                    r2.<init>(r3)     // Catch:{ Exception -> 0x003e, all -> 0x003b }
                    int r3 = H2.f.f340a     // Catch:{ Exception -> 0x0034 }
                    r3 = 8192(0x2000, float:1.14794E-41)
                    byte[] r3 = new byte[r3]     // Catch:{ Exception -> 0x0034 }
                L_0x001e:
                    int r4 = r1.read(r3)     // Catch:{ Exception -> 0x0034 }
                    r5 = -1
                    if (r4 != r5) goto L_0x0036
                    com.google.common.util.concurrent.D r3 = r0     // Catch:{ Exception -> 0x0034 }
                    r3.set(r0)     // Catch:{ Exception -> 0x0034 }
                    r1.close()     // Catch:{ IOException -> 0x0059 }
                    r2.close()     // Catch:{ IOException -> 0x0059 }
                    return
                L_0x0031:
                    r7 = move-exception
                L_0x0032:
                    r0 = r1
                    goto L_0x005a
                L_0x0034:
                    r0 = move-exception
                    goto L_0x004a
                L_0x0036:
                    r5 = 0
                    r2.write(r3, r5, r4)     // Catch:{ Exception -> 0x0034 }
                    goto L_0x001e
                L_0x003b:
                    r7 = move-exception
                    r2 = r0
                    goto L_0x0032
                L_0x003e:
                    r2 = move-exception
                    r6 = r2
                    r2 = r0
                    r0 = r6
                    goto L_0x004a
                L_0x0043:
                    r7 = move-exception
                    r2 = r0
                    goto L_0x005a
                L_0x0046:
                    r1 = move-exception
                    r2 = r0
                    r0 = r1
                    r1 = r2
                L_0x004a:
                    com.google.common.util.concurrent.D r7 = r0     // Catch:{ all -> 0x0031 }
                    r7.setException(r0)     // Catch:{ all -> 0x0031 }
                    if (r1 == 0) goto L_0x0054
                    r1.close()     // Catch:{ IOException -> 0x0059 }
                L_0x0054:
                    if (r2 == 0) goto L_0x0059
                    r2.close()     // Catch:{ IOException -> 0x0059 }
                L_0x0059:
                    return
                L_0x005a:
                    if (r0 == 0) goto L_0x005f
                    r0.close()     // Catch:{ IOException -> 0x0064 }
                L_0x005f:
                    if (r2 == 0) goto L_0x0064
                    r2.close()     // Catch:{ IOException -> 0x0064 }
                L_0x0064:
                    throw r7
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.media3.transformer.TransmuxTranscodeHelper.AnonymousClass3.run():void");
            }
        }.start();
        return obj;
    }

    public static Composition createAudioTranscodeAndVideoTransmuxComposition(Composition composition, String str) {
        Composition buildUponComposition = buildUponComposition((Composition) Assertions.checkNotNull(composition), false, true, (ResumeMetadata) null);
        Composition.Builder buildUpon = buildUponComposition.buildUpon();
        ArrayList arrayList = new ArrayList(buildUponComposition.sequences);
        arrayList.add(new EditedMediaItemSequence.Builder(new EditedMediaItem.Builder(new MediaItem.Builder().setUri(str).build()).build()).build());
        buildUpon.setSequences(arrayList);
        buildUpon.setTransmuxVideo(true);
        return buildUpon.build();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.google.common.util.concurrent.ListenableFuture, com.google.common.util.concurrent.D, java.lang.Object] */
    public static ListenableFuture getMp4Info(Context context, String str, long j2) {
        final ? obj = new Object();
        final Context context2 = context;
        final String str2 = str;
        final long j3 = j2;
        new Thread("TransmuxTranscodeHelper:Mp4Info") {
            public void run() {
                try {
                    obj.set(Mp4Info.create(context2, str2, j3));
                } catch (Exception e) {
                    obj.setException(e);
                }
            }
        }.start();
        return obj;
    }
}
