package org.example.enums;

enum Search {HITHER, YON}

public class UpcastEnum {
    public static void main(String[] args) {
        Search[] vals = Search.values();

        Enum e = Search.HITHER; // Upcast
//        e.values(); // No values() in Enum
        for (Enum enumConstant : e.getClass().getEnumConstants()) {
            System.out.println(enumConstant);
        }
    }
}
