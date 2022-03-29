package cs121;

import producerConsumer.*;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class GeneratorDemo {
    public static void main(String[] args) {
        System.out.println("demo of Range as Iterable<Integer>");
        Range range = new Range(3);
        for (int i: range) {
            System.out.println(i);
        }

        System.out.println("\ndemo of Range.stream() as Stream<Integer>");
        Range range5 = new Range(5);
        Stream<Integer> stream1 = range5.stream().filter(i->i%2==0).map(x->2*x);
        stream1.forEach((i -> System.out.println(i)));

        Range range7 = new Range(7);
        List<Integer> ints = range7.stream().collect(Collectors.toList());
        System.out.printf("\nRange(7)->toList()=%s\n", ints);

        Range range10= new Range(10);
        Integer sum = range10.stream().filter(i->i%2==0).reduce(0, (x,y)->x+y);
        System.out.printf("\n0+2+4+6+8=%d\n", sum);

        // range10 already closed!
        Integer sum2 = range10.stream().filter(i->i%2==0).reduce(0, (x,y)->x+y);
        System.out.printf("\nsum2=%d\n", sum2);

        ScannerStream ss = new ScannerStream(new java.io.File("abc.txt"));
        ss.stream().sorted().forEach((str) -> System.out.println(str));

    }
}
