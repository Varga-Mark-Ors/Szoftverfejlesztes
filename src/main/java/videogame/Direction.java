package videogame;

import lombok.Getter;

@Getter
public enum Direction {
    /**
     * Az irányok és azok értékei
     */
    UP (0),
    RIGHT (1),
    DOWN (2),
    LEFT (3);

    /**
     * Az irány numerikus értéke
     */
    public final int value;

    /**
     * Konstruktor az irány létrehozásához egy adott értékkel.
     * Az értéknek 0 és 3 között kell lennie.
     *
     * @param i Az irány értéke.
     * @throws IllegalArgumentException Ha az érték nem 0 és 3 között van.
     */
    Direction(int i) {
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException("Nem megfelelő érték. Az érték 0-3 között kell, hogy legyen.");
        }
        else {
            this.value = i;
        }
    }

    /**
     * Egy értékhez tartozó irány visszaadása.
     *
     * @param value Az érték.
     * @return A megfelelő irány.
     * @throws IllegalArgumentException Ha az értékhez nem tartozik irány.
     */
    public static Direction fromValue(int value) {
        for (Direction dir : Direction.values()) {
            if (dir.value == value) {
                return dir;
            }
        }
        throw new IllegalArgumentException("Nem létező érték: " + value);
    }
}
