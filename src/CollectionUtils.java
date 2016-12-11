/**
 * Created by Дмитрий on 11.12.2016.
 */

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class CollectionUtils {
    private CollectionUtils() {
    }


    public static <E> List<E> filter(List<E> elements, Predicate<E> filter) {
        List<E> result = new ArrayList<E>();
        for (E element : elements) {
            if (filter.test(element)) {
                result.add(element);
            }
        }
        return result;
    }


    public static <E> boolean anyMatch(List<E> elements, Predicate<E> predicate) {
        for (E element : elements) {
            if (predicate.test(element)) {
                return true;
            }
        }
        return false;
    }


    public static <E> boolean allMatch(List<E> elements, Predicate<E> predicate) {
        for (E element : elements) {
            if (!predicate.test(element)) {
                return false;
            }
        }
        return true;
    }


    public static <E> boolean noneMatch(List<E> elements, Predicate<E> predicate) {
        for (E element : elements) {
            if (!predicate.test(element)) {
                return true;
            }
        }
        return false;
    }


    public static <T, R> List<R> map(List<T> elements, Function<T, R> mappingFunction) {
        List<R> result = new ArrayList<R>();
        for (T element : elements) {
            result.add(mappingFunction.apply(element));
        }
        return result;
    }


    public static <E> Optional<E> max(List<E> elements, Comparator<E> comparator) {
        E maxElement = elements.get(0);
        for (int i = 1; i < elements.size(); i++) {
            maxElement = comparator.compare(maxElement, elements.get(i)) > 0 ? maxElement : elements.get(i);
        }

        return Optional.of(maxElement);
    }


    public static <E> Optional<E> min(List<E> elements, Comparator<E> comparator) {
        E minElement = elements.get(0);
        for (int i = 1; i < elements.size(); i++) {
            minElement = comparator.compare(minElement, elements.get(i)) < 0 ? minElement : elements.get(i);
        }

        return Optional.of(minElement);
    }


    public static <E> List<E> distinct(List<E> elements) {
        List<E> result = new ArrayList<E>();
        for (E element : elements) {
            if (!result.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }


    public static <E> void forEach(List<E> elements, Consumer<E> consumer) {
        for (E element : elements) {
            consumer.accept(element);
        }

    }


    public static <E> Optional<E> reduce(List<E> elements, BinaryOperator<E> accumulator) {
        E result = elements.get(0);
        for (int i = 1; i < elements.size(); i++) {
            result = accumulator.apply(result, elements.get(i));
        }
        return Optional.of(result);
    }


    public static <E> E reduce(E seed, List<E> elements, BinaryOperator<E> accumulator) {
        E result = elements.get(0);
        for (int i = 1; i < elements.size(); i++) {
            result = accumulator.apply(result, elements.get(i));
        }
        return result;
    }


    public static <E> Map<Boolean, List<E>> partitionBy(List<E> elements, Predicate<E> predicate) {
        Map<Boolean, List<E>> map = new HashMap<>();
        List<E> trueList = new ArrayList<E>();
        List<E> falseList = new ArrayList<E>();
        for (E element : elements) {
            if (predicate.test(element)) {
                trueList.add(element);
            }
            else falseList.add(element);
        }
        map.put(true, trueList);
        map.put(false, falseList);
        return map;
    }


    public static <T, K> Map<K, List<T>> groupBy(List<T> elements, Function<T, K> classifier) {
        Map<K, List<T>> map = new HashMap<K, List<T>>();
        List<T>  list = new ArrayList<T>();
        for (T element : elements){
            K key = classifier.apply(element);
            if (map.containsKey(key)){
                map.get(key).add(element);
            }
            else {
                list.add(element);
                map.put(key, list);
            }
        }
        return map;
    }


    public static <T, K, U> Map<K, U> toMap(List<T> elements, Function<T, K> keyFunction, Function<T, U> valueFunction, BinaryOperator<U> mergeFunction) {
        Map<K, U> map = new HashMap<K, U>();
        K key;
        U value;
        for (T element : elements){
            key = keyFunction.apply(element);
            value = valueFunction.apply(element);
            if (map.containsKey(key)){
                map.put(key, mergeFunction.apply(map.get(key), value));
            }
            else {
                map.put(key, value);
            }
        }
        return map;
    }

}
