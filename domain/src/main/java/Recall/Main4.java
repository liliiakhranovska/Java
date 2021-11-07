package Recall;

public class Main4 {
    public static void main(String[] args) {
        Dog dog = new Dog ("Bob", 3, 2);
        Converter<Dog, Raccoon> converter = x -> new Raccoon (x.name, x.age, x.weight);
        Raccoon raccoon = converter.convert(dog);
        System.out.println(raccoon.weight);
        System.out.println(Converter.isNotNull(dog));
    }
}
