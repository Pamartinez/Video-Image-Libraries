package com.samsung.android.gallery.module.story.transcode.transcoder.audio;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.ConcurrentLinkedQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AudioTranscodeHandler extends Handler {
    private final ConcurrentLinkedQueue<Runnable> mTransCodeQueue = new ConcurrentLinkedQueue<>();
    private boolean resumed = true;

    public AudioTranscodeHandler(Looper looper) {
        super(looper);
    }

    public void addJob(Runnable runnable) {
        this.mTransCodeQueue.offer(runnable);
        if (this.mTransCodeQueue.size() == 1) {
            sendMessage(obtainMessage(100));
        }
    }

    public void handleMessage(Message message) {
        if (message.what == 100) {
            while (this.resumed && !this.mTransCodeQueue.isEmpty()) {
                Runnable poll = this.mTransCodeQueue.poll();
                if (poll != null) {
                    poll.run();
                }
            }
        }
    }

    public void stop() {
        this.resumed = false;
        this.mTransCodeQueue.clear();
        getLooper().quitSafely();
    }
}
