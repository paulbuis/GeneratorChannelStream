package producerConsumer;

import java.io.Closeable;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.BlockingQueue;

import java.util.Iterator;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.Spliterators;
import java.util.Spliterator;

public class Channel<T> implements Closeable, Iterable<T> {

    private final BlockingQueue<IterationResult<T>> queue = new SynchronousQueue<>();
    boolean closed = false;

    public void send(T item) {
        if (closed) {
            System.err.println("attempting send() on closed channel");
            return;
        }
        try {
            queue.put(new IterationResult<>(item));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public IterationResult<T> recv() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new IterationResult<>(null, false);
        }
    }

    @Override
    public void close() {
        try {
            if (!closed) {
                closed = true;
                queue.put(new IterationResult<>(null, false));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Iterator<T> iterator() {
        return new ChannelIterator();
    }

    public Stream<T> stream() {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator(), Spliterator.ORDERED), false);
    }

    private class ChannelIterator implements Iterator<T> {

        IterationResult<T> nextResult = null;

        ChannelIterator() {
            if (Channel.this.closed) {
                System.err.println("ChannelIterator(): Channel already closed");
            } else {
                nextResult = Channel.this.recv();
            }
        }

        @Override
        public boolean hasNext() {
            return (nextResult != null) && nextResult.hasMore();
        }

        @Override
        public T next() {
            T result = nextResult.data();
            nextResult =  Channel.this.recv();
            return result;
        }
    }
}
