package com.akos;

import com.akos.list.MyArrayList;
import com.akos.menu.Menu;
import com.akos.menu.MenuEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        Menu menu = new Menu();
        menu.addEntry(new MenuEntry("Добавление элемента в массив") {
            @Override
            public void run() {
                System.out.println("\nТекущий массив: " + list + "\nВведите элемент типа int, который хотите добавить: ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    list.add(Integer.parseInt(reader.readLine()));
                } catch (NumberFormatException e) {
                    System.out.println("Это не int, попробуйте снова");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        menu.addEntry(new MenuEntry("Удалить элемент из массива") {
            @Override
            public void run() {
                System.out.println("\nТекущий массив: " + list + "\nВведите индекс элемета, который хотите удалить: ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    list.remove(Integer.parseInt(reader.readLine()));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Не существует элемента по такому индексу");
                } catch (NumberFormatException e) {
                    System.out.println("Неверный индекс, попробуйте снова");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        menu.addEntry(new MenuEntry("Распечатать массив") {
            @Override
            public void run() {
                System.out.println(list);
            }
        });
        menu.run();
    }
}
