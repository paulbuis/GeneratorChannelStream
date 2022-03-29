package producerConsumer;

import java.io.FileNotFoundException;
import java.util.function.Consumer;
import java.util.Scanner;

import java.io.File;

public class ScannerStream extends Generator<String> {

    private final Scanner scanner;

    public ScannerStream(Scanner scanner) {
        this.scanner = scanner;
    }

    public ScannerStream(File file) {
        this(file2Scanner(file));
    }

    private static Scanner file2Scanner(File file) {
        try {
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void produce(Consumer<String> consumer) {
        if (scanner == null) {
            return;
        }
        while (scanner.hasNextLine()) {
            consumer.accept(scanner.nextLine());
        }
        scanner.close();
    }
}