package com.samsung.android.gallery.module.story.transcode.decoder.audio;

import A.a;
import android.media.MediaExtractor;
import c0.C0086a;
import com.samsung.android.gallery.module.bgm.BgmUriService;
import com.samsung.android.gallery.module.story.transcode.config.AudioInfo;
import com.samsung.android.gallery.module.story.transcode.util.CodecsHelper;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.utils.Log;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BGMAudioDecoder extends AbsAudioDecoder {
    private final ArrayList<BGMData> mBGMData = new ArrayList<>();
    private long mBGMEndUs;
    private final BGMSequence mBGMSequence = new BGMSequence();
    private long mBGMStartUs;
    private BgmOptions mBgmOptions;
    private int mCurrentBGMIndex = 0;

    public BGMAudioDecoder(String str, int i2) {
        initBgmOptions(str, i2);
        initBGMData();
        initBGMSequence(((long) i2) * 1000);
    }

    private void changeBGM() {
        int i2 = this.mCurrentBGMIndex + 1;
        this.mCurrentBGMIndex = i2;
        if (i2 < this.mBGMSequence.size()) {
            reloadBGM(this.mCurrentBGMIndex);
            if (this.mBGMEndUs > 0) {
                this.mCodec.flush();
                this.mExtractor.seekTo(this.mBGMStartUs, 0);
                return;
            }
            Log.e(this.TAG, "bgm end us < 0");
            setAudioExtractDone(true);
            return;
        }
        Log.e(this.TAG, "extract all sequence");
        setAudioExtractDone(true);
    }

    private void initBGMData() {
        if (this.mBgmOptions == null) {
            Log.e(this.TAG, "bgm is not ready");
            return;
        }
        for (int i2 = 0; i2 < this.mBgmOptions.size(); i2++) {
            BgmOptions.BgmOption bgmOption = this.mBgmOptions.getBgmOption(i2);
            FileDescriptor fileDescriptor = bgmOption.f3141fd;
            if (fileDescriptor != null) {
                ArrayList<BGMData> arrayList = this.mBGMData;
                String path = bgmOption.uri.getPath();
                arrayList.add(new BGMData(fileDescriptor, path, i2 + " bgm", bgmOption.duration));
            }
        }
    }

    private void initBgmOptions(String str, int i2) {
        BgmUriService bgmUriService = new BgmUriService();
        this.mBgmOptions = bgmUriService.createBgmOptionsSync(str, i2);
        bgmUriService.destroy();
    }

    private void releaseBGMOptions() {
        BgmOptions bgmOptions = this.mBgmOptions;
        if (bgmOptions != null) {
            bgmOptions.release();
            this.mBgmOptions = null;
        }
    }

    private void releaseBGMSequence() {
        this.mBGMSequence.release();
    }

    private void reloadBGM(int i2) {
        boolean z;
        releaseAudioExtractor();
        this.mExtractor = createAudioExtractor(updateBgm(i2));
        if (getAudioTrackIndex() != -1) {
            z = true;
        } else {
            z = false;
        }
        setCopyAudio(z);
    }

    private int updateBgm(int i2) {
        int index = this.mBGMSequence.getIndex(i2);
        this.mBGMStartUs = 0;
        this.mBGMEndUs = this.mBGMSequence.getDuration(i2);
        String str = this.TAG;
        StringBuilder h5 = a.h(i2, index, "prepareAudioCodec BGM index : ", ", idx : ", ", mBGMEndUs : ");
        h5.append(this.mBGMEndUs);
        Log.d(str, h5.toString());
        return index;
    }

    public boolean canPassSampleDataToDecoder(MediaExtractor mediaExtractor) {
        long sampleTime = mediaExtractor.getSampleTime();
        if (sampleTime < 0 || sampleTime > this.mBGMEndUs) {
            return false;
        }
        return true;
    }

    public MediaExtractor createAudioExtractor() {
        return createAudioExtractor(0);
    }

    public /* bridge */ /* synthetic */ boolean extractAudio() {
        return super.extractAudio();
    }

    public /* bridge */ /* synthetic */ AudioInfo getAudioInfo() {
        return super.getAudioInfo();
    }

    public /* bridge */ /* synthetic */ int getBufferInfoFlags() {
        return super.getBufferInfoFlags();
    }

    public /* bridge */ /* synthetic */ short[] getContentShort() {
        return super.getContentShort();
    }

    public /* bridge */ /* synthetic */ int getPendingAudioDecoderOutputBufferIndex() {
        return super.getPendingAudioDecoderOutputBufferIndex();
    }

    public void handleFailedToPassSampleDataToDecoder() {
        changeBGM();
    }

    public void initBGMSequence(long j2) {
        if (this.mBGMData.size() > 0) {
            BGMSequence bGMSequence = this.mBGMSequence;
            ArrayList<BGMData> arrayList = this.mBGMData;
            BgmOptions bgmOptions = this.mBgmOptions;
            bGMSequence.init(arrayList, bgmOptions.mBodyRepeatCount, bgmOptions.mBodyLastIndex, j2);
            this.mBGMStartUs = 0;
            this.mBGMEndUs = ((long) this.mBGMData.get(0).getDuration()) * 1000;
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("BGM data : ");
            C0086a.A(sb2, this.mBGMData, ", mBGMSequence : ");
            sb2.append(this.mBGMSequence.size());
            sb2.append(", cycle: ");
            sb2.append(this.mBgmOptions.mBodyRepeatCount);
            sb2.append(", index : ");
            a.w(sb2, this.mBgmOptions.mBodyLastIndex, str);
        }
    }

    public /* bridge */ /* synthetic */ boolean isAudioDecoderDone() {
        return super.isAudioDecoderDone();
    }

    public /* bridge */ /* synthetic */ boolean isCopyAudio() {
        return super.isCopyAudio();
    }

    public /* bridge */ /* synthetic */ void onEncodingFinished() {
        super.onEncodingFinished();
    }

    public void prepareAudioCodec() {
        if (this.mBgmOptions == null) {
            setCopyAudio(false);
        } else {
            super.prepareAudioCodec();
        }
    }

    public void prepareAudioEncoding(long j2) {
        super.prepareAudioEncoding(j2);
        this.mCurrentBGMIndex = 0;
    }

    public void release() {
        super.release();
        releaseBGMOptions();
        releaseBGMSequence();
    }

    public /* bridge */ /* synthetic */ void releaseOutputBuffer() {
        super.releaseOutputBuffer();
    }

    private MediaExtractor createAudioExtractor(int i2) {
        if (this.mBGMData.get(i2).getFileDescriptor() != null) {
            return CodecsHelper.createExtractor(this.mBGMData.get(i2).getFileDescriptor());
        }
        throw new IOException("ParcelFileDescriptor is NULL");
    }
}
