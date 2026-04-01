package com.samsung.android.gallery.image360.engine.texture;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ITexture {
    void draw();

    void reset(Object... objArr);

    void setRendererRequester(RenderRequestListener renderRequestListener);

    void setScreenSize(int i2, int i7);

    void setScroll(float f, float f5);

    void setSensorScroll(float f, float f5);

    void setStatusHandler(StatusHandler statusHandler);

    void setTextureManager(TextureManager textureManager);

    void setZoom(float f);

    void updateGlAttributes();
}
