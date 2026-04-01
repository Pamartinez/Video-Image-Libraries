package com.samsung.android.sum.core.filter;

import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.NNFWDescriptor;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.message.MessageProducer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaFilterTracer extends DecorateFilter {
    private static final String TAG = Def.tagOf((Class<?>) MediaFilterTracer.class);
    private boolean instantRun;
    private int mediaId;
    private final List<Consumer<Message>> messageHandlers;
    private final MessageProducer messageProducer;
    private int numBlocks;

    public MediaFilterTracer(MediaFilter mediaFilter, MessageProducer messageProducer2) {
        super(mediaFilter);
        this.instantRun = false;
        this.mediaId = -1;
        this.numBlocks = -1;
        this.messageHandlers = new ArrayList();
        this.messageProducer = messageProducer2;
    }

    private Map<String, Object> getShortDescription(MFDescriptor mFDescriptor) {
        String str = TAG;
        SLog.d(str, "getShortDescription: descriptor=" + mFDescriptor);
        HashMap hashMap = new HashMap();
        if (mFDescriptor instanceof NNFWDescriptor) {
            NNFWDescriptor nNFWDescriptor = (NNFWDescriptor) mFDescriptor;
            hashMap.put("type", "NNFWDescriptor");
            hashMap.put("model", nNFWDescriptor.getNNDescriptor().getModelId());
            hashMap.put("fw", nNFWDescriptor.getFw());
            hashMap.put("hw", nNFWDescriptor.getHw());
            hashMap.put("input-data-type", nNFWDescriptor.getInputFormat().getDataType());
            hashMap.put("input-color-format", nNFWDescriptor.getInputFormat().getColorFormat());
            hashMap.put("input-shape", nNFWDescriptor.getInputFormat().getShape());
            hashMap.put("output-data-type", nNFWDescriptor.getOutputFormat().getDataType());
            hashMap.put("output-color-format", nNFWDescriptor.getOutputFormat().getColorFormat());
            hashMap.put("output-shape", nNFWDescriptor.getOutputFormat().getShape());
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$makeReport$0(Message message, Message message2) {
        message2.put("media-id", message.get("media-id", Integer.valueOf(this.mediaId)));
    }

    private void makeReport(int i2) {
        makeReport(i2, (MediaBuffer) null);
    }

    public int[] getMessagesToReceive() {
        return new int[]{2};
    }

    public boolean onMessageReceived(Message message) {
        String str = TAG;
        SLog.d(str, "onMessageReceived: " + message);
        if (message.getCode() != 2) {
            return false;
        }
        this.mediaId = ((Integer) message.get("media-id", -1)).intValue();
        this.numBlocks = ((Integer) message.get(Message.KEY_WHOLE_FRAMES, -1)).intValue();
        return false;
    }

    public void prepare() {
        String str = TAG;
        SLog.d(str, "prepare: successor=" + this.successor);
        makeReport(111);
        super.prepare();
        makeReport(112);
    }

    public void release() {
        String str = TAG;
        SLog.d(str, "release: successor=" + this.successor);
        makeReport(115);
        super.release();
        makeReport(116);
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        String str = TAG;
        SLog.d(str, "run: successor=" + this.successor);
        makeReport(113, mediaBuffer);
        MutableMediaBuffer run = super.run(mediaBuffer, mutableMediaBuffer);
        makeReport(114, run);
        return run;
    }

    private void makeReport(int i2, MediaBuffer mediaBuffer) {
        String str = TAG;
        SLog.d(str, "makeReport: code=" + i2 + ", buffer=" + mediaBuffer);
        long currentTimeMillis = System.currentTimeMillis();
        Message newMessage = this.messageProducer.newMessage(i2);
        newMessage.put(Message.KEY_UNIT_ID, Integer.valueOf(this.successor.hashCode()));
        if (mediaBuffer != null) {
            Integer num = (Integer) mediaBuffer.getExtra("media-id", Integer.valueOf(this.mediaId));
            num.getClass();
            newMessage.put("media-id", num);
            Integer num2 = (Integer) mediaBuffer.getExtra(Message.KEY_BLOCK_ID, -1);
            if (num2.intValue() != -1) {
                newMessage.put(Message.KEY_BLOCK_ID, num2);
                newMessage.put(Message.KEY_NUM_BLOCKS, mediaBuffer.getExtra(Message.KEY_NUM_BLOCKS, Integer.valueOf(this.numBlocks)));
            }
            if (mediaBuffer.containsExtra(Message.KEY_IN_FILE)) {
                newMessage.put(Message.KEY_IN_FILE, mediaBuffer.getExtra(Message.KEY_IN_FILE));
            }
        }
        switch (i2) {
            case 111:
                newMessage.put(Message.KEY_START_TIME_MS, Long.valueOf(currentTimeMillis));
                Map<String, Object> shortDescription = getShortDescription(getDescriptor());
                if (!shortDescription.isEmpty()) {
                    newMessage.put(Message.KEY_UNIT_DESCRIPTION, shortDescription);
                    break;
                }
                break;
            case 112:
                newMessage.put(Message.KEY_END_TIME_MS, Long.valueOf(currentTimeMillis));
                break;
            case 113:
                newMessage.put(Message.KEY_START_TIME_MS, Long.valueOf(currentTimeMillis));
                break;
            case 114:
                newMessage.put(Message.KEY_END_TIME_MS, Long.valueOf(currentTimeMillis));
                if (this.instantRun) {
                    this.messageHandlers.add(new e(7, this, newMessage));
                    break;
                }
                break;
            case 115:
                if (this.instantRun) {
                    this.messageHandlers.forEach(new h(newMessage, 0));
                }
                newMessage.put(Message.KEY_START_TIME_MS, Long.valueOf(currentTimeMillis));
                break;
            case 116:
                if (this.instantRun) {
                    this.messageHandlers.forEach(new h(newMessage, 1));
                }
                newMessage.put(Message.KEY_END_TIME_MS, Long.valueOf(currentTimeMillis));
                break;
        }
        newMessage.post();
    }

    public MediaFilterTracer(MediaFilter mediaFilter, MessageProducer messageProducer2, MediaFilter mediaFilter2) {
        this(mediaFilter, messageProducer2);
        if (mediaFilter2 instanceof InstantFilter) {
            this.instantRun = true;
        }
    }
}
