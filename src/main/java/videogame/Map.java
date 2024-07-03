package videogame;

import lombok.Getter;

@Getter
public class Map {

    private static char[][] map = new char[6][6];

    /**
     * Alapértelmezett konstruktor, amely inicializálja a térképet előre meghatározott értékekkel.
     * A térkép különböző karakterekkel van feltöltve, amelyek különböző objektumokat jelölnek a játékban.
     */
    public Map() {
        map = new char[][] {
                {'b','w','w','w','r','w','c'},
                {'b','w','b','w','w','b','w'},
                {'w','r','b','r','w','w','w'},
                {'b','b','b','w','b','w','w'},
                {'r','r','w','r','r','w','w'},
                {'w','w','b','r','w','w','r'},
                {'w','w','r','r','w','w','w'},
        };
    }

    /**
     * A térkép visszaadása.
     *
     * @return A térkép 6x6-os karakter mátrixa.
     */
    public char[][] getMap() {
        return map;
    }
}
