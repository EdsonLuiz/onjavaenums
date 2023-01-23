package org.example.enums;

public class NonEnum {
    public static void main(String[] args) {
        Class<Integer> intClass = Integer.class;

        try {
            for (Integer enumConstant : intClass.getEnumConstants()) {
                System.out.println(enumConstant);
            }
        } catch (Exception error) {
            System.out.println("Expected: " + error);
        }
    }
}
