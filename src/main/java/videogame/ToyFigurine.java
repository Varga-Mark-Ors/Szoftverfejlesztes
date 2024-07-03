package videogame;

import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

/**
 * A ToyFigurine osztály egy videojáték figuráját reprezentálja,
 * amelyet a pozíciója (sor és oszlop) és az iránya határoz meg.
 * A getter és setter metódusokhoz Lombok annotációkat használ.
 */
@Getter
@Setter
public class ToyFigurine {

    private int row;

    private int col;

    private Direction direction;

    /**
     * Alapértelmezett konstruktor, amely a figurát a 6. sorba, 0. oszlopba helyezi, felfelé nézve.
     */
    public ToyFigurine() {
        row = 6;
        col = 0;
        direction = Direction.UP;
    }

    /**
     * Paraméterezett konstruktor, amely a figurát egy meghatározott pozícióba és irányba inicializálja.
     */
 public ToyFigurine(int row, int col, Direction direction) {
        this.row = row;
        this.col = col;
        this.direction = direction;
    }

    /**
     * Felülírja az equals metódust a ToyFigurine objektumok összehasonlításához.
     *
     * @param o Az objektum, amivel összehasonlítjuk.
     * @return Igaz, ha az objektumok egyenlők, különben hamis.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToyFigurine that = (ToyFigurine) o;
        return row == that.row && col == that.col && direction == that.direction;
    }

    /**
     * Felülírja a hashCode metódust a ToyFigurine hash kódjának generálásához.
     *
     * @return A ToyFigurine hash kódja.
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, col, direction);
    }
}
