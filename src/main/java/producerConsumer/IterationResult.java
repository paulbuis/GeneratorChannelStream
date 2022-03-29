package producerConsumer;

public record IterationResult<T>(T data, boolean hasMore) {
    public IterationResult(T data) {
        this(data, true);
    }
}
