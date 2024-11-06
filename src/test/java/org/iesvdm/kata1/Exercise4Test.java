

package org.iesvdm.kata1;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.abs;


public class  Exercise4Test extends PetDomainForKata
{

    @Test
    @Tag("KATA")
    public void getAgeStatisticsOfPets()
    {
        var petAges = this.people.stream()
                .flatMap(person -> person.getPets().stream())
                .map(Pet::getAge)
                .collect(Collectors.toList());

        var uniqueAges = petAges.stream()
                .collect(Collectors.toSet());
        var expectedSet = Set.of(1, 2, 3, 4);
        Assertions.assertEquals(expectedSet, uniqueAges);

        var stats = petAges.stream()
                .mapToInt(Integer::intValue)
                .summaryStatistics();

            Assertions.assertEquals(1, stats.getMin());
            Assertions.assertEquals(4, stats.getMax());
            Assertions.assertEquals(17, stats.getSum());
            Assertions.assertEquals(1.8888888888888888, stats.getAverage(), 0.0);
            Assertions.assertEquals(9, stats.getCount());

            boolean allAgesGreaterThanZero = petAges.stream().allMatch(age -> age > 0);
            Assertions.assertTrue(allAgesGreaterThanZero);

            boolean anyAgeZero = petAges.stream().anyMatch(age -> age == 0);
            Assertions.assertFalse(anyAgeZero);

            boolean anyAgeLessThanZero = petAges.stream().anyMatch(age -> age < 0);
            Assertions.assertFalse(anyAgeLessThanZero);
        }


    @Test
    @Tag("KATA")
    @DisplayName("bobSmithsPetNamesAsString - 🐱 🐶")
    public void bobSmithsPetNamesAsString()
    {
        //TODO
        // find Bob Smith
        Person personBob = this.people.stream()
            .filter(person -> person.getFullName().equals("Bob Smith"))
            .findFirst()
            .orElseThrow(() -> new AssertionError("Bob Smith not found"));;

            System.out.println(personBob.getFullName());
        //TODO
        // get Bob Smith's pets' names
        String names = personBob.getPets().stream()
                .map(Pet::getName)  // Obtener el nombre de cada mascota
                .collect(Collectors.joining(" & "));
    }

    @Test
    @Tag("KATA")
    public void immutablePetCountsByEmoji()
    {
        //TODO
        // Unmodificable map of counts
        Map<String, Long> countsByEmoji = this.people.stream()
                .flatMap(person -> person.getPets().stream())
                .map(pet -> pet.getType().toString())
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(emoji -> emoji, Collectors.counting()),
                        Map::copyOf
                ));;

        Assertions.assertEquals(
                Map.of("🐱", 2L, "🐶", 2L, "🐹", 2L, "🐍", 1L, "🐢", 1L, "🐦", 1L),
                countsByEmoji
        );
    }

    /**
     * The purpose of this test is to determine the top 3 pet types.
     */
    @Test
    @Tag("KATA")
    @DisplayName("topThreePets - 🐱 🐶 🐹")
    public void topThreePets()
    {

        //TODO
        // Obtain three top pets
        var favorites = this.people.stream()
                .flatMap(person -> person.getPets().stream())
                .map(Pet::getType)
                .collect(Collectors.groupingBy(petType -> petType, Collectors.counting()))
                .entrySet().stream()
                .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
                .limit(3)
                .collect(Collectors.toList());;

        Assertions.assertEquals(3, favorites.size());

        Assertions.assertTrue(favorites.contains(new AbstractMap.SimpleEntry<>(PetType.CAT, Long.valueOf(2))));
        Assertions.assertTrue(favorites.contains(new AbstractMap.SimpleEntry<>(PetType.DOG, Long.valueOf(2))));
        Assertions.assertTrue(favorites.contains(new AbstractMap.SimpleEntry<>(PetType.HAMSTER, Long.valueOf(2))));

    }

    @Test
    @Tag("KATA")
    public void getMedianOfPetAges()
    {

        //TODO
        // Obtain pet ages
        var petAges = this.people.stream()
                .flatMap(person -> person.getPets().stream())
                .map(Pet::getAge)
                .sorted()
                .collect(Collectors.toList());

        //TODO
        // sort pet ages
        var sortedPetAges = new ArrayList<Integer>();

        double median;
        if (0 == sortedPetAges.size() % 2)
        {
            //TODO
            //
            // The median of a list of even numbers is the average of the two middle items
            median = 0.0;
        }
        else
        {
            // The median of a list of odd numbers is the middle item
            median = sortedPetAges.get(abs(sortedPetAges.size() / 2)).doubleValue();
        }
        Assertions.assertEquals(0.0, median, 0.0);
    }
}
