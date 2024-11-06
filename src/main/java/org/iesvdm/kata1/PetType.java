
package org.iesvdm.kata1;

public enum PetType
{
    CAT("🐱"),
    DOG("🐶"),
    HAMSTER("🐹"),
    TURTLE("🐢"),
    BIRD("🐦"),
    SNAKE("🐍");

    private final String emoji;

    PetType(String emoji)
    {
        this.emoji = emoji;
    }

    @Override
    public String toString()
    {
        return this.emoji;
    }
    public String getEmoji() {
        return this.emoji;
    }

    public static PetType fromEmoji(String searchEmoji)
    {
        // Find the correct PetType based on the String emoji
        return null;
    }
}
