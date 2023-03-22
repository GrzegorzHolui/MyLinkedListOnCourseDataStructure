import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 10; i < 100000 ; i=i*i) {
            countTimes(list, 1, 1000, 100*i, WhichFunction.ADD);
        }
    }

    private static <T> void countTimes(MyLinkedList<T> list, int times, int howManyElementsUseInRandom, int howManyElements, WhichFunction whichFunction) {
        List<List<Integer>> allScores = new ArrayList<>();
        for (int i = 0; i < times; i++) {
            List<T> listFromAttempti = new ArrayList<>();
            if (whichFunction.equals(WhichFunction.ADD)) {
                listFromAttempti = measureTheTimeOfAddingItemsInDifferentPlace(list, howManyElementsUseInRandom, howManyElements);
            } else if (whichFunction.equals(WhichFunction.REMOVE)) {
                listFromAttempti = measureTheTimeOfRemovingItemsInDifferentPlace(list, howManyElementsUseInRandom, howManyElements);
            } else if (whichFunction.equals(WhichFunction.FIND)) {
                listFromAttempti = measureTheTimeOfFindingItemsInDifferentPlace(list, howManyElementsUseInRandom, howManyElements);
            }

            list = new MyLinkedList<>();
            allScores.add((List<Integer>) listFromAttempti);
            System.out.println("W próbie " + i + " wynik są następujące");
            System.out.println("Maksymalny czas wykonania: " + listFromAttempti.get(2) + "ms");
            System.out.println("Minimalny czas wykonania: " + listFromAttempti.get(0) + "ms");
            System.out.println("Sredni czas wykonania: " + listFromAttempti.get(1) + "ms");
            System.out.println();

        }

        var max = allScores.stream().flatMap((elementList) -> {
            List<Integer> maxLoop = new ArrayList<>();
            maxLoop.add(elementList.get(2));
            return maxLoop.stream();
        }).max(Comparator.naturalOrder());

        var min = allScores.stream().flatMap((elementList) -> {
            List<Integer> minLoop = new ArrayList<>();
            minLoop.add(elementList.get(0));
            return minLoop.stream();
        }).min(Comparator.naturalOrder());

        var avg = allScores.stream().flatMap((elementList) -> {
            List<Integer> avgLoop = new ArrayList<>();
            avgLoop.add(elementList.get(1));
            return avgLoop.stream();
        }).mapToInt(e -> e.intValue()).average();

        System.out.println("W ilości prób " + times);

        System.out.println("Maksymalny czas wykonania w " + times + " próbach " + max.get() + "ms");
        System.out.println("Minimalny czas wykonania w " + times + " próbach " + min.get() + "ms");
        System.out.println("Sredni czas wykonania w " + times + " próbach " + avg.getAsDouble() + "ms");
    }


    private static <T> List<T> measureTheTimeOfAddingItemsInDifferentPlace(MyLinkedList<T> list, int howManyElementsAddInRandom, int howManyElementsAddToList) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        long sum = 0;
        int avg = 0;
        addItems(list, howManyElementsAddToList);
        Random random = new Random();
        for (Integer i = 0; i < howManyElementsAddInRandom; i++) {
            int index = random.nextInt(howManyElementsAddToList);
//            System.out.println(index);
            long startTime = System.currentTimeMillis();
            list.add(index, (T) i);
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
//            System.out.println(i + " Czas wykonania dodawania elementu " + executionTime + "ms");
            if (executionTime > max) {
                max = (int) executionTime;
            }
            if (executionTime < min) {
                min = (int) executionTime;
            }
            sum += executionTime;
        }
        avg = (int) (sum / howManyElementsAddInRandom);
//        System.out.println("Maksymalny czas wykonania: " + max + "ms");
//        System.out.println("Minimalny czas wykonania: " + min + "ms");
//        System.out.println("Sredni czas wykonania: " + avg + "ms");
        return (List<T>) Arrays.asList(min, avg, max);
    }


    private static <T> List<T> measureTheTimeOfRemovingItemsInDifferentPlace(MyLinkedList<T> list, int howManyElementsRemoveInRandom, int howManyElementsAddToList) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        long sum = 0;
        int avg = 0;
        addItems(list, howManyElementsAddToList);
        Random random = new Random();
        for (Integer i = 0; i < howManyElementsRemoveInRandom; i++) {
            int index = random.nextInt(howManyElementsAddToList);
//            System.out.println(index);
            long startTime = System.currentTimeMillis();
            list.remove(index);
            long endTime = System.currentTimeMillis();
            howManyElementsAddToList--;
            long executionTime = endTime - startTime;
//            System.out.println(i + " Czas wykonania usuwania elementu " + executionTime + "ms");
            if (executionTime > max) {
                max = (int) executionTime;
            }
            if (executionTime < min) {
                min = (int) executionTime;
            }
            sum += executionTime;
        }
        avg = (int) (sum / howManyElementsRemoveInRandom);
//        System.out.println("Maksymalny czas wykonania: " + max + "ms");
//        System.out.println("Minimalny czas wykonania: " + min + "ms");
//        System.out.println("Sredni czas wykonania: " + avg + "ms");
        return (List<T>) Arrays.asList(min, avg, max);
    }

    private static <T> List<T> measureTheTimeOfFindingItemsInDifferentPlace(MyLinkedList<T> list, int howManyElementsRemoveInRandom, int howManyElementsAddToList) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        long sum = 0;
        int avg = 0;
        addItems(list, howManyElementsAddToList);
        Random random = new Random();
        for (Integer i = 0; i < howManyElementsRemoveInRandom; i++) {
            int index = random.nextInt(howManyElementsAddToList);
//            System.out.println(index);
            long startTime = System.currentTimeMillis();
            list.get(index);
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
//            System.out.println(i + " Czas wykonania usuwania elementu " + executionTime + "ms");
            if (executionTime > max) {
                max = (int) executionTime;
            }
            if (executionTime < min) {
                min = (int) executionTime;
            }
            sum += executionTime;
        }
        avg = (int) (sum / howManyElementsRemoveInRandom);
//        System.out.println("Maksymalny czas wykonania: " + max + "ms");
//        System.out.println("Minimalny czas wykonania: " + min + "ms");
//        System.out.println("Sredni czas wykonania: " + avg + "ms");
        return (List<T>) Arrays.asList(min, avg, max);
    }


    private static <T> void addItems(MyLinkedList<T> list, int n) {
        for (Integer i = 0; i < n; i++) {
            list.add((T) i);
        }
    }

    private static <T> void measureTheTimeOfAddingItems(MyLinkedList<T> list, int n) {
        long startTime = System.currentTimeMillis();
        for (Integer i = 0; i < n; i++) {
            list.add((T) i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Czas wykonania dodawania elementów na koniec listy: " + (endTime - startTime) + "ms");
    }

    private static <T> void measureTheTimeOfAddingItemsByUsingIndex(MyLinkedList<T> list, int n) {
        long startTime = System.currentTimeMillis();
        for (Integer i = 0; i < n; i++) {
            list.add(i, (T) i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Czas wykonania dodawania elementów na dany index wynosi: " + (endTime - startTime) + "ms");
    }


    private static <T> void measureTheTimeOfRemovingAllItems(MyLinkedList<T> list, int n) {
        for (Integer i = 0; i < n; i++) {
            list.add((T) i);
        }
        long startTime = System.currentTimeMillis();
        for (Integer i = 0; i < n; i++) {
            list.remove(0);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Czas wykonania usuwania elementów na koniec listy: " + (endTime - startTime) + "ms");
    }

    private static <T> void measureTheTimeOfRemovingOneItems(MyLinkedList<T> list, int n, int elementOfIndexToRemove) {
        for (Integer i = 0; i < n; i++) {
            list.add((T) i);
        }
        long startTime = System.currentTimeMillis();
        list.remove(elementOfIndexToRemove);
        long endTime = System.currentTimeMillis();
        System.out.println("Czas wykonania usuwania elementu o indeksie " + elementOfIndexToRemove + " " + (endTime - startTime) + "ms");
    }


    private static <T> void measureTheTimeOfFindItem(MyLinkedList<T> list, int n, int elementOfIndexToFind) {
        for (Integer i = 0; i < n; i++) {
            list.add((T) i);
        }
        long startTime = System.currentTimeMillis();
        list.get(elementOfIndexToFind);
        long endTime = System.currentTimeMillis();
        System.out.println("Czas wykonania usuwania elementu o indeksie " + elementOfIndexToFind + " " + (endTime - startTime) + "ms");
    }
}