package ru.itis;

public class ArrayList {
    int array[];
    int count = 0;
    void addToEnd(int element){
        if (count <array.length) {
            array[count] = element;
            count++;
        } else {
            System.err.println("Нет места");
        }
    }
    void showList(){
        for (int i = 0; i < count; i++) {
            System.out.println(array[i] + " ");
        }
    }
}
