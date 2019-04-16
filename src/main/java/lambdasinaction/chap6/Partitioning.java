package lambdasinaction.chap6;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static lambdasinaction.chap6.Dish.menu;

public class Partitioning {

    public static void main(String ... args) {
        System.out.println("Dishes partitioned by vegetarian: " + partitionByVegeterian());
        System.out.println("Vegetarian Dishes by type: " + vegetarianDishesByType());
        System.out.println("Most caloric dishes by vegetarian: " + mostCaloricPartitionedByVegetarian());
        System.out.println("Vegetarian Dishes by calories: " + vegetarianDishesByCalories());
        System.out.println("Vegetarian Dishes by counting: " + vegetarianDishesCounting());
    }

    private static Map<Boolean, List<Dish>> partitionByVegeterian() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian));
    }

    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType() {
        return menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
    }

    private static Map<Boolean, Map<Boolean, List<Dish>>> vegetarianDishesByCalories() {
        return  menu.stream().collect(partitioningBy(Dish:: isVegetarian, partitioningBy(d -> d.getCalories() > 500)));
    }

    private static Map<Boolean, Long> vegetarianDishesCounting() {
        return  menu.stream().collect(partitioningBy(Dish:: isVegetarian, counting()));
    }


    private static Object mostCaloricPartitionedByVegetarian() {
        return menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
    }
}

