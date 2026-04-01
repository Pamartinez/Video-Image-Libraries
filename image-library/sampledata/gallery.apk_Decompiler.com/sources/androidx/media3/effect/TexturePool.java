package androidx.media3.effect;

import F2.H;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TexturePool {
    private final int capacity;
    private final Deque<GlTextureInfo> freeTextures;
    private final Deque<GlTextureInfo> inUseTextures;
    private final boolean useHighPrecisionColorComponents;

    public TexturePool(boolean z, int i2) {
        this.capacity = i2;
        this.useHighPrecisionColorComponents = z;
        this.freeTextures = new ArrayDeque(i2);
        this.inUseTextures = new ArrayDeque(i2);
    }

    private void createTextures(GlObjectsProvider glObjectsProvider, int i2, int i7) {
        Assertions.checkState(this.freeTextures.isEmpty());
        Assertions.checkState(this.inUseTextures.isEmpty());
        for (int i8 = 0; i8 < this.capacity; i8++) {
            this.freeTextures.add(glObjectsProvider.createBuffersForTexture(GlUtil.createTexture(i2, i7, this.useHighPrecisionColorComponents), i2, i7));
        }
    }

    private Iterator<GlTextureInfo> getIteratorToAllTextures() {
        Iterable[] iterableArr = {this.freeTextures, this.inUseTextures};
        for (int i2 = 0; i2 < 2; i2++) {
            iterableArr[i2].getClass();
        }
        return new H(iterableArr).iterator();
    }

    public int capacity() {
        return this.capacity;
    }

    public void deleteAllTextures() {
        Iterator<GlTextureInfo> iteratorToAllTextures = getIteratorToAllTextures();
        while (iteratorToAllTextures.hasNext()) {
            iteratorToAllTextures.next().release();
        }
        this.freeTextures.clear();
        this.inUseTextures.clear();
    }

    public void ensureConfigured(GlObjectsProvider glObjectsProvider, int i2, int i7) {
        if (!isConfigured()) {
            createTextures(glObjectsProvider, i2, i7);
            return;
        }
        GlTextureInfo next = getIteratorToAllTextures().next();
        if (next.width != i2 || next.height != i7) {
            deleteAllTextures();
            createTextures(glObjectsProvider, i2, i7);
        }
    }

    public void freeTexture(GlTextureInfo glTextureInfo) {
        Assertions.checkState(this.inUseTextures.contains(glTextureInfo));
        this.inUseTextures.remove(glTextureInfo);
        this.freeTextures.add(glTextureInfo);
    }

    public int freeTextureCount() {
        if (!isConfigured()) {
            return this.capacity;
        }
        return this.freeTextures.size();
    }

    public GlTextureInfo getMostRecentlyUsedTexture() {
        if (this.inUseTextures.isEmpty()) {
            return null;
        }
        return this.inUseTextures.getLast();
    }

    public boolean isConfigured() {
        return getIteratorToAllTextures().hasNext();
    }

    public boolean isUsingTexture(GlTextureInfo glTextureInfo) {
        return this.inUseTextures.contains(glTextureInfo);
    }

    public GlTextureInfo useTexture() {
        if (!this.freeTextures.isEmpty()) {
            GlTextureInfo remove = this.freeTextures.remove();
            this.inUseTextures.add(remove);
            return remove;
        }
        throw new IllegalStateException("Textures are all in use. Please release in-use textures before calling useTexture.");
    }

    public void freeTexture() {
        Assertions.checkState(!this.inUseTextures.isEmpty());
        this.freeTextures.add(this.inUseTextures.remove());
    }
}
