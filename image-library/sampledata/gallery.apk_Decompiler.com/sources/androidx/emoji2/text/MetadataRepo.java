package androidx.emoji2.text;

import android.graphics.Typeface;
import android.util.SparseArray;
import androidx.core.os.TraceCompat;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.flatbuffer.MetadataList;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MetadataRepo {
    private final char[] mEmojiCharArray;
    private final MetadataList mMetadataList;
    private final Node mRootNode = new Node(1024);
    private final Typeface mTypeface;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Node {
        private final SparseArray<Node> mChildren;
        private TypefaceEmojiRasterizer mData;

        private Node() {
            this(1);
        }

        public Node get(int i2) {
            SparseArray<Node> sparseArray = this.mChildren;
            if (sparseArray == null) {
                return null;
            }
            return sparseArray.get(i2);
        }

        public final TypefaceEmojiRasterizer getData() {
            return this.mData;
        }

        public void put(TypefaceEmojiRasterizer typefaceEmojiRasterizer, int i2, int i7) {
            Node node = get(typefaceEmojiRasterizer.getCodepointAt(i2));
            if (node == null) {
                node = new Node();
                this.mChildren.put(typefaceEmojiRasterizer.getCodepointAt(i2), node);
            }
            if (i7 > i2) {
                node.put(typefaceEmojiRasterizer, i2 + 1, i7);
            } else {
                node.mData = typefaceEmojiRasterizer;
            }
        }

        public Node(int i2) {
            this.mChildren = new SparseArray<>(i2);
        }
    }

    private MetadataRepo(Typeface typeface, MetadataList metadataList) {
        this.mTypeface = typeface;
        this.mMetadataList = metadataList;
        this.mEmojiCharArray = new char[(metadataList.listLength() * 2)];
        constructIndex(metadataList);
    }

    private void constructIndex(MetadataList metadataList) {
        int listLength = metadataList.listLength();
        for (int i2 = 0; i2 < listLength; i2++) {
            TypefaceEmojiRasterizer typefaceEmojiRasterizer = new TypefaceEmojiRasterizer(this, i2);
            Character.toChars(typefaceEmojiRasterizer.getId(), this.mEmojiCharArray, i2 * 2);
            put(typefaceEmojiRasterizer);
        }
    }

    public static MetadataRepo create(Typeface typeface, ByteBuffer byteBuffer) {
        try {
            TraceCompat.beginSection("EmojiCompat.MetadataRepo.create");
            return new MetadataRepo(typeface, MetadataListReader.read(byteBuffer));
        } finally {
            TraceCompat.endSection();
        }
    }

    public char[] getEmojiCharArray() {
        return this.mEmojiCharArray;
    }

    public MetadataList getMetadataList() {
        return this.mMetadataList;
    }

    public int getMetadataVersion() {
        return this.mMetadataList.version();
    }

    public Node getRootNode() {
        return this.mRootNode;
    }

    public Typeface getTypeface() {
        return this.mTypeface;
    }

    public void put(TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
        boolean z;
        Preconditions.checkNotNull(typefaceEmojiRasterizer, "emoji metadata cannot be null");
        if (typefaceEmojiRasterizer.getCodepointsLength() > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "invalid metadata codepoint length");
        this.mRootNode.put(typefaceEmojiRasterizer, 0, typefaceEmojiRasterizer.getCodepointsLength() - 1);
    }
}
