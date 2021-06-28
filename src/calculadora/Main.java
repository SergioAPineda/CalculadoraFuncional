package calculadora;

import java.util.Enumeration;
import java.util.function.BiFunction;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        BiFunction<Integer, Integer, Integer> suma = (a, b) -> a+b;
        BiFunction<Integer, Integer, Integer> resta = (a, b) -> a-b;

        BiFunction<Integer, Integer, Integer> multiplicacion = (a, b) -> IntStream.range(0, b+1)
                .reduce((acumulador, numero) -> {
                    return suma.apply(acumulador, a);
                }).getAsInt();

        BiFunction<Integer, Integer, Integer> division = (a, b) -> {
            if(a.equals(0) && b.equals(0)){
                throw new IllegalArgumentException("no es posible realizar la operación, la divicion de 0 entre 0 es indefinido");
            }
            if(b.equals(0)){
                throw new IllegalArgumentException("No es posible divider por 0");
            }

            return IntStream.range(0, a)
                    .reduce((acumulador, numero) -> multiplicacion.apply(numero, b) <= a? suma.apply(acumulador, 1) : acumulador)
                    .orElse(0);


        };

        System.out.println(suma.apply(1,5));
        System.out.println(resta.apply(5,1));
        System.out.println(multiplicacion.apply(5,3));
        System.out.println(division.apply(10,5));
    }
}
