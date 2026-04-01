package com.samsung.android.gallery.support.library.abstraction;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface VideoTranscoderCompat {
    void encode();

    int getEstimatedSize();

    void setOnCompleteListener(Consumer<Boolean> consumer);

    void setOnProgressListener(Consumer<Integer> consumer);

    void setOptions(TranscodingOptions transcodingOptions);

    void stop();
}
