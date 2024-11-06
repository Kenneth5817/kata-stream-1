package org.iesvdm.kata1;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Exercise5Test extends PetDomainForKata {
    @Test
    @Tag("KATA")
    public void partitionPetAndNonPetPeople() {
        //TODO
        // Obtain a partition over people with or without pets
        Map<Boolean, List<Person>> partitioned = this.people.stream()
                .collect(Collectors.partitioningBy(person -> !person.getPets().isEmpty()));
        List<Person> partitionListPetPeople = partitioned.get(true);
        List<Person> partitionListNotPetPeople = partitioned.get(false);

        Assertions.assertEquals(7, partitionListPetPeople.size());
        Assertions.assertEquals(1, partitionListNotPetPeople.size());
    }

    @Test
    @Tag("KATA")
    @DisplayName("getOldestPet - 🐶")
    public void getOldestPet() {
        //TODO
        // obtain the oldest pet
        Pet oldestPet = this.people.stream()
                .flatMap(person -> person.getPets().stream())
                .max(Comparator.comparingInt(Pet::getAge))
                .orElseThrow(() -> new RuntimeException("No pets found"));
        Assertions.assertEquals(PetType.DOG, oldestPet.getType());
        Assertions.assertEquals(4, oldestPet.getAge());
    }

    @Test
    @Tag("KATA")
    public void getAveragePetAge() {
        //TODO
        // obtain the average age of the pets
        double averagePetAge = this.people.stream()
                .flatMap(person -> person.getPets().stream())
                .collect(Collectors.averagingInt(pet -> pet.getAge()));
        Assertions.assertEquals(1.88888, averagePetAge, 0.00001);
    }

    @Test
    @Tag("KATA")
    public void addPetAgesToExistingSet() {
        //TODO
        // obtain the set of pet ages
        Set<Integer> petAges = this.people.stream()
                .flatMap(person -> person.getPets().stream())
                .map(Pet::getAge)
                .collect(Collectors.toSet());
        var expectedSet = Set.of(1, 2, 3, 4);
        Assertions.assertEquals(expectedSet, petAges);
    }

    @Test
    @Tag("KATA")
    @DisplayName("findOwnerWithMoreThanOnePetOfTheSameType - 🐹 🐹")
    public void findOwnerWithMoreThanOnePetOfTheSameType() {
        //TODO
        // obtain owner with more than one pet of the same type
        // Obtener al dueño con más de una mascota del mismo tipo
        Person petOwner = this.people.stream()
                .filter(person -> person.getPets().stream()
                        .collect(Collectors.groupingBy(Pet::getType, Collectors.counting()))
                        .values().stream()
                        .anyMatch(count -> count > 1))
                .findFirst()
                .orElseThrow(() -> new AssertionError("No person with more than one pet of the same type"));


        //TODO
        // obtain the concatenation of the pet emojis of the owner
        String concatenatedEmojis = petOwner.getPets().stream()
                .map(pet -> pet.getType().getEmoji())
                .collect(Collectors.joining(" "));
        Assertions.assertEquals("🐹 🐹", concatenatedEmojis);
    }
}