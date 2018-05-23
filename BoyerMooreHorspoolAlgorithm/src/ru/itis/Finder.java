package ru.itis;

public class Finder {

    int[] d; //массив сдвигов
    char[] chars; //массив символов, использованных в слове word

    //метод, расчитывающий массив сдвигов
    void shiftArrayCreating(String word) {
        d = new int[word.length()]; //объявляется массив длиной равной длине слова word
        chars = new char[word.length()];  //объявляется массив всех использованных символов длиной равной длине word
        for (int i = word.length() - 2; i > -1; i--) { //начинаем отчет с предпоследнего символа
            if (findRepeatingLetter(word.charAt(i)) != 0) //проверяем, была ли уже данная буква в слове word
                d[i] = findRepeatingLetter(word.charAt(i)); //если была, записываем ее удаление от конца
            else { //если не была,
                d[i] = word.length() - i - 1; //то расчитываем ее удаление от конца
                chars[i] = word.charAt(i); //помечаем этот символ как использованный
            } //скобка :)
        }
        if (findRepeatingLetter(word.charAt(word.length() - 1)) == 0) { //проверяем, встречался ли последний символ в слове word
            d[word.length() - 1] = word.length(); // если не встречался, присваиваем значение равное длине слова
            chars[word.length() - 1] = word.charAt(word.length() - 1); //помечаем символ как использованный
        } else
            d[word.length() - 1] = findRepeatingLetter(word.charAt(word.length() - 1)); //если использовался, то ищем самый
        //менее удаленный символ и присваиваем его значение последнему символу
    }
    // метод, отвечающий за поиск слова в строке
    //не работает, если изначально не вызвать метод shiftArrayCreating
    int find(String text, String word) { // text - строка, word - подстрока(образ, слово и т.д.)
        int pos = word.length() - 1; // pos - переменная, отвечающая за сдвиг слова, то есть если у нас
        //word длины - 6, pos = 6 - 1 = 5, то в начале алгоритма мы сравниваем символ в text под индексом pos,
        //далее мы уже к pos прибавляем необходимый нам сдвиг
        while (pos <= text.length()) { //цикл, проходящийся по всему слову
            int posTemp = pos; //локальная переменная, проверяющая эквивалентность в заданном участке строки text
            boolean equal = true; //флаг, указывающий полной совпадение подстроки word
            boolean halfEqual = false; //флаг, указывающий частичное совпадение подстроки word
            for (int i = word.length() - 1; i > -1 & equal; i--) //цикл, проверющий посимвольно подстроку и определенный участок строки
                if (word.charAt(i) != text.charAt(posTemp--)) //если последний символ word не совпал с послелним символом определенного участка строки,
                    //курсор posTemp, указывающий на последний символ, смещается влево
                    equal = false; //флаг false-ится
            if(word.charAt(word.length()-1) == text.charAt(pos)) //чтобы убедиться в частичной совпадении строки, можно просто сравнить последний символ
                halfEqual = true;
            if (equal) //проверяем, если слово найдено
                break; //выходим из цикла
            if (halfEqual) //проверяем, были ли частичные совпадения
                pos += d[d.length -1]; //если были, сдвигаем на значение последнего символа
            else pos += findLetter(text.charAt(pos)); //если не было, сдвигаем на значение текущего символа
        }
        return pos - word.length(); // выводим удаление word от начала text (количество символов)
    }
    // находит повторяющийся символ, если нашел - возвращает удаление самого ближайшего символа от конца, если
    //такого символа не было - возвращает 0
    int findRepeatingLetter(char c) {
        for (int j = chars.length - 2; j > -1; j--) //идем с предпоследенго символа
            if (chars[j] == c) // находим символ
                return chars.length - j - 1; //возвращаем ее удаление от конца
        return 0; //если не нашли - возварщаем 0
    }
    // поиск символа в слове
    int findLetter(char c) {
        for (int j = chars.length - 1; j > -1; j--) //идем с конца слова
            if (chars[j] == c) // проверяем совпадени нашего символа
                if (j == chars.length - 1)  // проверяем, если символ находится в конце слова
                    return chars.length; // тогда возвращаем длину слова
                else return chars.length - j - 1; // если не находится в конце слова - возвращаем ее удаление от конца
        return chars.length; // елси не нашли данный символ - возвращаем длину слова
    }
}

