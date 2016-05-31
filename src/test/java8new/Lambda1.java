package test.java8new;

/**
 * Created by Next on 24.05.2016.
 */
public class Lambda1 {
    public static void main(String[] args) {
        Converter<String, Integer> converter = from -> Integer.valueOf(from);/*new Converter<String, Integer>() {
                                                                                @Override
                                                                                public Integer convert(String from) {
                                                                                    return Integer.valueOf(from);
                                                                                }
                                                                            };*/
        Integer converted = converter.convert("123");
        System.out.println(converted);
        converter=Integer::parseInt;
        System.out.println(converter.convert("456"));
        Something something = new Something();
        Converter<String, String> converter1 = something::startsWith;
        String java = converter1.convert("Java");
        System.out.println(java);
    }
}

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

class Something {
    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
}