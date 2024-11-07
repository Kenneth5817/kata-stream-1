package org.iesvdm.kata1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Exercise1Test extends PetDomainForKata
{
    @Test
    @Tag("KATA")
    public void getFirstNamesOfAllPeople()
    {
        //TODO

        // Replace empty list firstNames with a stream transformation on people.
        List<String> firstNames = this.people.stream()
                    .map(p->p.getFirstName())
                    .toList();
        firstNames.forEach(System.out::println);

        var expectedFirstNames = Arrays.asList("Mary", "Bob", "Ted", "Jake", "Barry", "Terry", "Harry", "John");
        Assertions.assertIterableEquals(expectedFirstNames, firstNames);
    }

    @Test
    @Tag("KATA")
    public void getNamesOfMarySmithsPets()
    {
        Optional<Person> optionalPerson = this.getPersonNamed("Mary Smith");
        List<String> names = new ArrayList<>();
        if (optionalPerson.isPresent()) {
            List<Pet> pets = optionalPerson.get().getPets();

            //TODO
            // Replace empty list name with a stream transformation on pets.
            if (pets != null) {
                names = pets.stream()
                        .map(pet -> pet.getName())
                        .toList();
                names.forEach(System.out::println);
            }else{
                System.out.println("No hay mascotas disponibles");
            }
        }else{
            System.out.println("No se ha encontrado a Mary");
        }
        Assertions.assertEquals("Tabby", names.get(0));
    }

    @Test
    @Tag("KATA")
    @DisplayName("getPeopleWithCats 🐱")
    public void getPeopleWithCats()
    {
        //Hecho en clase
        List<String>peopleWithCats2=people.stream()
                .filter(person -> person.hasPet(PetType.CAT))
                . map(Person::getLastName).toList();

        //TODO
        // Replace empty list with a positive filtering stream on people
        List<Person> peopleWithCats = this.people.stream()
                .filter(person ->person.getPets().stream()
                        .anyMatch(pet -> pet.getType()==PetType.CAT))
                .toList();
        peopleWithCats.forEach(Person->System.out.println(Person.getLastName()));

        var expectedLastNames = Arrays.asList("Smith", "Smith");
        List<String>actualNames=peopleWithCats.stream()
                        .map(p->p.getLastName())
                                .toList();
        Assertions.assertEquals(expectedLastNames, actualNames);
        Assertions.assertEquals(peopleWithCats.size(), actualNames.size());
    }

    @Test
    @Tag("KATA")
    @DisplayName("getPeopleWithoutCats 🐱")
    public void getPeopleWithoutCats()
    {
        //TODO
        // Replace empty list with a negative filtering stream on people
        List<Person> peopleWithoutCats = this.people.stream()
                .filter(p->p.getPets().isEmpty() | p.getPets().stream().noneMatch(pet -> pet.getType()==PetType.CAT))
                .sorted(Comparator.comparing(person -> person.getFirstName()))
                .toList();

        List<String>actualFirstNames=peopleWithoutCats.stream()
                .map(Person::getFirstName)
                .collect(Collectors.toList());
        System.out.println("Actual first names: " + actualFirstNames);

        var expectedFirstNames = Arrays.asList("Barry","Harry","Jake","John", "Ted", "Terry");
        Assertions.assertIterableEquals(expectedFirstNames, actualFirstNames);


        //Hecho en clase->>>>>otra manera mas sencilla
        List<Person> peopleWithoutCats2=people.stream()
                .filter(p->!p.hasPet(PetType.CAT)).toList();

        var expectedFirstNamed=Arrays.asList("Smith", "Snake", "Bird", "Turtle", "Hamster", "Doe");
        Assertions.assertEquals(expectedFirstNamed, peopleWithoutCats2.stream()
                .map(p->p.getLastName())
                        .toList());
    }
}
