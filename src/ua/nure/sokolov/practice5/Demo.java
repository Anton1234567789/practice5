package ua.nure.sokolov.practice5;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Demo {

    public static void main(String[] args) {
	    Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
	    double d = formula.calculate(100);
        double d1 = formula.sqrt(16);
        System.out.println(d);
        System.out.println(d1);

        System.out.println();

        List<String> names = Arrays.asList("peter","anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        System.out.println("with sort a->z");
        Collections.sort(names, (o1, o2) -> o1.compareTo(o2));

        System.out.println(names + "\n"+ "~~~~~~~~~~~");
        System.out.println("with sort z->a" );
        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        System.out.println(names+ "\n"+ "~~~~~~~~~~~");

        Converter<String, Integer> converter = (from -> Integer.valueOf(from));
        Integer converted = converter.convert("123");
        System.out.println(converted);

        Converter<String, Integer> converter1 = Integer::valueOf;
        Integer converted1 = converter1.convert("123");
        System.out.println(converted1);

        Something something = new Something();
        Converter<String, String> converterString = something::startWith;
        String convertedString = converterString.convert("Java");
        System.out.println(convertedString);

        PersonFactory<Person> personPersonFactory = Person::new;
        Person person = personPersonFactory.create("Peter", "Parket");
        System.out.println(person);

        final int num = 1;
        Converter<Integer, String> stringConverter = (from -> String.valueOf(from + num));
        System.out.println(stringConverter.convert(2));

        int num1 = 1;
        Converter<Integer, String> stringConverter1 =
                (from) -> String.valueOf(from + num1);
        System.out.println(stringConverter1.convert(2));

        Predicate<String> predicate= (s -> s.length() > 0);
        predicate.test("foo");
        predicate.negate().test("foo");

        Predicate<Boolean> notNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123");

        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();

        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));

        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);
        System.out.println("sorted stream filter ");
        stringCollection.stream().sorted().filter((s) -> s.startsWith("a")).forEach(System.out::println);

        System.out.println("~~~~~~~~~~~~~~");

        stringCollection.stream().map(String::toUpperCase).sorted((a,b)->b.compareTo(a)).forEach(System.out::print);

        System.out.println();
        boolean anyStartsWithA = stringCollection.stream().anyMatch(s -> s.startsWith("a"));
        System.out.println(anyStartsWithA);

        boolean allStartsWithA = stringCollection.stream().allMatch(s -> s.startsWith("a"));
        System.out.println(allStartsWithA);

        long startsWithB = stringCollection.stream().filter(s -> s.startsWith("b")).count();
        System.out.println(startsWithB);

        Optional<String> reduced = stringCollection.stream().sorted().reduce((s1, s2)->s1+"#"+s2);
        reduced.ifPresent(System.out::println);

        System.out.println();
/*
        int max = 1_000_000;
        List<String> values = new ArrayList<>(max);
        for (int j = 0; j < max; j++){
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long t0 = System.nanoTime();
        long count = values.stream().sorted().count();
        System.out.println("elements for sorted: " + count);

        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort: %d ms", millis));
        long t2 = System.nanoTime();

        long count1 = values.parallelStream().sorted().count();

        long t3 = System.nanoTime();

        long millis1 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
        System.out.println(String.format("parallel sort: %d ms", millis1));
*/

        Map<Integer, String> map = new HashMap<>();
        for (int j = 0; j < 10; j++){
            map.putIfAbsent(j, "val " + j);
        }

        map.forEach((id, val)-> System.out.println(val));

        map.computeIfPresent(3, (num2,val)-> val+num2);
        System.out.println(map.get(3));

    }
}
@FunctionalInterface
interface Converter<F, T>{
    T convert(F from);
}

interface Formula{

    double calculate(int a);

    default double sqrt(int a){
        return Math.sqrt(a);
    }
}

class Something{
    String startWith(String s){
        return String.valueOf(s.charAt(0));
    }
}

interface PersonFactory<P extends Person>{
    P create(String firstName, String lastName);
}

class Person{
    String firstName;
    String lastName;

    Person(){

    }
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

class Lambda4{
    static int outerStaticNum;
    int outerNum;

    void testScopes(){
        Converter<Integer, String> stringConverter2 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };

        Converter<Integer, String> stringConverter3 = (from) -> {
            outerNum = 72;
            return String.valueOf(from);
        };

    }
}