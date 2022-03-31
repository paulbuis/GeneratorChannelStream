package comprehension;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;


public class Sets {
    public static <T,R> Set<R> set(Iterable<T> iterable, Function<T, R> function) {
        Set<R> result = new HashSet<>();
        for (T item: iterable) {
            result.add(function.apply(item));
        }
        return result;
    }

    public static <T,R> Set<R> set(Iterable<T> iterable, Function<T, R> function, Predicate<T> predicate) {
        HashSet<R> result = new HashSet<>();
        for (T item: iterable) {
            if (predicate.test(item)) {
                result.add(function.apply(item));
            }
        }
        return result;
    }

    public static <T> Set<T> setFilter(Iterable<T> iterable, Predicate<T> predicate) {
        HashSet<T> result = new HashSet<>();
        for (T item: iterable) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
}
