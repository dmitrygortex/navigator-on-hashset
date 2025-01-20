package Algorithms;//import java.util.AbstractSet;
//import java.util.HashSet;
//import java.util.LinkedList;
//import java.util.SortedSet;
//




import java.util.LinkedList;

public class HashSetDM<T> {

//TODO:
//      ХЭШСЕТЫ БАЗА ПЕРЕСМОТРЕТЬ ПОТОМ ЕЩЕ РАЗ!!!
//      Хеш-таблица — Самая Популярная Структура Данных
//      https://www.youtube.com/watch?v=rPp46idEvnM


//      КАК РАБОТАЮТ ХЭШ-ТАБЛИЦЫ | СТРУКТУРЫ ДАННЫХ
//      https://www.youtube.com/watch?v=cWbuK7C13HQ



    private static final int ELEMENTS_COUNT = 16;
    //TODO: доразбираться в статье если останется время
    //https://medium.com/@alejandro.itoaramendia/the-hash-table-data-structure-a-complete-guide-27fb7ebed2ff
    private static final double RESIZE_INDEX = 0.75;
    private LinkedList<T>[] buckets;
    private int currentSize;

    public HashSetDM() {
        buckets = initBuckets(ELEMENTS_COUNT);
        currentSize = 0;
    }

    public int getBucketsCount(){
        return buckets.length;
    }

    public int size() {
        return currentSize;
    }

    private LinkedList<T>[] initBuckets(int countOfBuckets) {
        LinkedList<T>[] newBuckets = new LinkedList[countOfBuckets];
        for (int i = 0; i < countOfBuckets; i++) {
            newBuckets[i] = new LinkedList<>();
        }
        return newBuckets;
    }

    private int getIndex(T val) {
//        var hash = key.hashCode();
//        if (hash == Integer.MIN_VALUE){
//            hash = Integer.MAX_VALUE;
//        }
        int hashCode = val.hashCode();
        //return Math.abs(hashCode) % buckets.length;
        // из за проблемы с переполнением просто зануляю первый бит чтобы отриц не было индексов
        return (hashCode & 0x7FFFFFFF) % buckets.length;
    }


    private void resize() {
        LinkedList<T>[] ostBucket = buckets;
        buckets = initBuckets(ostBucket.length * 2);
        currentSize = 0;

        for (LinkedList<T> bucket : ostBucket) {
            for (T element : bucket) {
                add(element);
            }
        }
    }

    public void add(T val) {
        //проверяю в навигаторе но на всякий случай мало ли еще будет где то использоваться
        if (contains(val)) {
            System.out.println(val.toString() + " уже был добавлен");
            return;
        }

//        слишком много коллизий
//        if (currentSize > buckets.length) {
//            resize();
//        }

        if ((double) currentSize / buckets.length >= RESIZE_INDEX) {
            resize();
        }

        int index = getIndex(val);
        buckets[index].add(val);
        currentSize++;
    }

    public boolean contains(T val) {
        int index = getIndex(val);
        LinkedList<T> bucket = buckets[index];
        for (T item : bucket) {
            if (item.equals(val)) {
                return true;
            }
        }
        return false;
    }

    public void remove(T val) {
        int index = getIndex(val);
        LinkedList<T> bucket = buckets[index];
        boolean removed = bucket.remove(val);
        if (removed) {
            currentSize--;
        }
    }

    public Iterable<T> iterator() {
        var valuesList = new LinkedList<T>();
        for (var bucket : buckets) {
            valuesList.addAll(bucket);
        }
        return valuesList;
    }
}