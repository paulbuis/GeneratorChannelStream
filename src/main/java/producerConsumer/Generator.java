package producerConsumer;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.function.Consumer;

import java.io.Closeable;

public abstract class Generator<T> implements Iterable<T>, Closeable {
    private final Channel<T> channel = new Channel<>();
    private final Thread thread = new Thread(new Runner());

    public Generator() {
        thread.start();
    }

    @Override
    public final Iterator<T> iterator() {
        return channel.iterator();
    }

    public final Stream<T> stream() {
        return channel.stream();
    }

    public final Stream<T> filter(java.util.function.Predicate<T> predicate) {
        return stream().filter(predicate);
    }

    public final <R> Stream<R> map(java.util.function.Function<T,R> transform) {
        return stream().map(transform);
    }

    @Override
    public final void close() {
        if (!channel.closed) {
            channel.close();
        }
    }

    public abstract void produce(Consumer<T> consumer);

    private final class Runner implements Runnable {
        @Override
        public void run() {
            if (Generator.this.channel.closed) {
                System.err.println("Generator channel already closed");
                return;
            }
            Generator.this.produce(new ChannelConsumer());
            close();
        }
    }

    private final class ChannelConsumer implements Consumer<T> {

        @Override
        public void accept(T item) {
            Generator.this.channel.send(item);
        }
    }
}
