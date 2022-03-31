package cs121;

import producerConsumer.*;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class GeneratorDemo {
    public static void main(String[] args) {
        System.out.println("demo of Range as Iterable<Integer>");
        Range range = new Range(3);
        // prints 3 lines: 0, 1, 2
        for (int i: range) {
            System.out.println(i);
        }

        // prints 3 lines: 0, 6, 12
        System.out.println("\ndemo of Range.stream() as Stream<Integer>");
        Range range5 = new Range(5);
        Stream<Integer> stream1 = range5.map(x->x*3).filter(i->i%2==0);
        stream1.forEach((i -> System.out.println(i)));


        // prints 1 line: [0, 1, 2, 3, 4, 5, 6]
        Range range7 = new Range(7);
        List<Integer> ints = range7.stream().collect(Collectors.toList());
        System.out.printf("\nRange(7)->toList()=%s\n", ints);


        // prints 7
        Range range8 = new Range(8);
        Integer sum0 = range8.reduce(Integer.MIN_VALUE, (a,b)->Math.max(a,b));
        System.out.println("\nsum0=" + sum0);


        // prints 1 line  0+2+4+6+8=20
        Range range10 = new Range(10);
        Integer sum1 = range10.filter(i->i%2==0).reduce(0, (x,y)->x+y);
        System.out.printf("\n0+2+4+6+8=%d\n", sum1);


        // range10 already closed!
        // prints 0
        // also prints to System.err "ChannelIterator(): Channel already closed"
        Integer sum2 = range10.filter(i->i%2==0).reduce(0, (x,y)->x+y);
        System.out.printf("\nsum2=%d\n", sum2);


        // abc.txt contains 3 lines C, A, B
        // output is A, B, C
        ScannerGenerator ss = new ScannerGenerator(new java.io.File("abc.txt"));
        ss.stream().sorted().forEach((str) -> System.out.println(str));
    }
}
