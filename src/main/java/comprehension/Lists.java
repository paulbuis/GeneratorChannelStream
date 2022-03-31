package comprehension;

import java.util.ArrayList;
import java.util.List;

import java.util.function.Function;
import java.util.function.Predicate;

public class Lists {

    public static <T,R> List<R> list(Iterable<T> iterable, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T item: iterable) {
            result.add(function.apply(item));
        }
        return result;
    }

    public static <T,R> List<R> list(Iterable<T> iterable, Function<T, R> function, Predicate<T> predicate) {
        ArrayList<R> result = new ArrayList<>();
        for (T item: iterable) {
            if (predicate.test(item)) {
                result.add(function.apply(item));
            }
        }
        return result;
    }

    public static <T> List<T> listFilter(Iterable<T> iterable, Predicate<T> predicate) {
        ArrayList<T> result = new ArrayList<>();
        for (T item: iterable) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
}
