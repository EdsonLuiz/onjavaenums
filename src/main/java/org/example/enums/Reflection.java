package org.example.enums;

import org.example.onjava.OSExecute;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

enum Explore {HERE, THERE}

public class Reflection {
    public static Set<String> analyze(Class<?> enumClass) {
        System.out.print("_____ Analyzing " + enumClass + "____");
        System.out.println("Interfaces");

        for (Type t : enumClass.getGenericInterfaces()) {
            System.out.println(t);
        }

        System.out.println("Base: " + enumClass.getSuperclass());
        System.out.println("Methods: ");
        Set<String> methods = new TreeSet<>();

        for (Method m : enumClass.getMethods()) {
            methods.add(m.getName());
        }

        System.out.println(methods);
        return methods;
    }

    public static void main(String[] args) {
        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        System.out.println("Explore.containesAll(Enum)? " + exploreMethods.containsAll(enumMethods));
        System.out.println("Explore.removeAll(Enum): " + exploreMethods.removeAll(enumMethods));
        System.out.println(exploreMethods);

        // Decompile the code for the enum:
        OSExecute.command("javap -cp build/classes/java/main .Reflection/Explore");
    }
}
