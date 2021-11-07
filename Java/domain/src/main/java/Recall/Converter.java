package Recall;

@FunctionalInterface
public interface Converter<D, R> {
    R convert(D d);

    static<T> boolean isNotNull (T t) {
        return t != null;
    }
}
