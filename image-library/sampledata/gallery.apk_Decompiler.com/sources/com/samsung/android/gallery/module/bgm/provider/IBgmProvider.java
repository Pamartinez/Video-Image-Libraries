package com.samsung.android.gallery.module.bgm.provider;

import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IBgmProvider {
    void bind();

    boolean downloadAllBgm();

    boolean downloadBgm(String str);

    List<String> getBgmUri(String str);

    List<String> getDownloadedBgm();

    void setCallback(IProviderCallback iProviderCallback);

    void unbind();
}
