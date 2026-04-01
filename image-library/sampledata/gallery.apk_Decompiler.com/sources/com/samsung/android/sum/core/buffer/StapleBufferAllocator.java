package com.samsung.android.sum.core.buffer;

import android.os.ParcelFileDescriptor;
import com.samsung.android.sum.core.buffer.MediaBufferAllocator;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.message.Message;
import java.nio.ByteBuffer;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StapleBufferAllocator extends MediaBufferAllocator {
    public StapleBufferAllocator(MediaFormat mediaFormat) {
        super(mediaFormat);
    }

    private MediaBuffer allocAsByteBuffer() {
        MediaFormat mediaFormat = this.format;
        return new GenericMediaBuffer(mediaFormat, this.align, ByteBuffer.allocateDirect((int) (mediaFormat.bytePerPixel() * ((float) this.align.getDimension()))));
    }

    private MediaBuffer allocAsNumber() {
        if (!this.format.getMediaType().isScala()) {
            throw new UnsupportedOperationException("not implemented alloc yet");
        } else if (this.format.getDataType().isInt()) {
            return new GenericMediaBuffer(this.format, 0);
        } else {
            if (this.format.getDataType().isLong()) {
                return new GenericMediaBuffer(this.format, 0L);
            }
            if (this.format.getDataType().isFloat()) {
                return new GenericMediaBuffer(this.format, Float.valueOf(0.0f));
            }
            if (this.format.getDataType().isByte()) {
                return new GenericMediaBuffer(this.format, (byte) 0);
            }
            if (this.format.getDataType().isShort()) {
                return new GenericMediaBuffer(this.format, (short) 0);
            }
            throw new UnsupportedOperationException("not implemented alloc data-type yet");
        }
    }

    public MediaBuffer allocate() {
        if (this.format.getMediaType().isScala()) {
            return allocAsNumber();
        }
        return allocAsByteBuffer();
    }

    public MediaBuffer allocateShared() {
        MediaFormat mediaFormat = this.format;
        return new GenericMediaBuffer(mediaFormat, SharedBufferManager.create(mediaFormat));
    }

    public <T> MediaBuffer wrap(T t) {
        if (!(t instanceof ParcelFileDescriptor)) {
            return new GenericMediaBuffer(this.format, this.align, t);
        }
        GenericMediaBuffer genericMediaBuffer = new GenericMediaBuffer(this.format, new MediaBufferAllocator.Nothing());
        genericMediaBuffer.setExtra(Message.KEY_FILE_DESCRIPTOR, t);
        return genericMediaBuffer;
    }

    public StapleBufferAllocator(MediaFormat mediaFormat, Align align) {
        super(mediaFormat, align);
    }

    public MediaBuffer allocate(Align align) {
        if (align.getDimension() != 0) {
            this.align = align;
        } else if (align.getAlignOfWidth() != 0) {
            this.align.set(align.getAlignOfWidth(), align.getAlignOfHeight());
            this.align.adjustAlign();
        }
        return allocate();
    }
}
