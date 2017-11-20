package com.akos;


import com.akos.list.MyArrayList;
import com.akos.menu.Menu;
import com.akos.menu.MenuEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main класс предназначенный для демонстрации работы <tt>MyArrayList</tt>
 */
public class App {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        Menu menu = new Menu();
        menu.addEntry(new MenuEntry("Добавление элемента в список") {
            @Override
            public void run() {
                System.out.println("\nТекущий список: " + list + "\nВведите элемент типа int, который хотите добавить: ");
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
        menu.addEntry(new MenuEntry("Удалить элемент из списка") {
            @Override
            public void run() {
                System.out.println("\nТекущий список: " + list + "\nВведите индекс элемета, который хотите удалить: ");
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
        menu.addEntry(new MenuEntry("Удалить максимальный элемент") {
            @Override
            public void run() {
                if(list.size()<=0)
                    System.out.println("В массиве нет элементов!");
                else {list.removeMax();
                    System.out.println("Список после удаления: " +list);}
            }
        });
        menu.addEntry(new MenuEntry("Удалить минимальный элемент") {
            @Override
            public void run() {
                if(list.size()<=0)
                    System.out.println("В списке нет элементов!");
                else {list.removeMin();
                    System.out.println("Список после удаления: " +list);}
            }
        });
        menu.addEntry(new MenuEntry("Распечатать список") {
            @Override
            public void run() {
                System.out.println("\n"+list);
            }
        });
        menu.run();
    }
}
