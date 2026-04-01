package com.samsung.android.sum.core.service;

import com.samsung.android.sum.core.message.Request;
import com.samsung.android.sum.core.message.ResponseHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ServiceController {
    int createMediaFilterController();

    void releaseMediaFilterController(int i2);

    ResponseHolder request(int i2, Request request);
}
