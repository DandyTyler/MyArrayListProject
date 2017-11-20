package com.akos.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация изменяемого консольного меню. Хранит в себе элементы меню и выводит их.
 *
 * @see MenuEntry
 */

public class Menu {
    private static final String MENU_PATTERN = "%s - %s\n";
    private List<MenuEntry> entries = new ArrayList<>();
    private boolean isExit = false;

    /**
     * При создании экземпляра класса создается элемент для выхода из меню
     */
    public Menu() {
        entries.add(new MenuEntry("Выход") {
            @Override
            public void run() {
                isExit = true;
            }
        });
    }

    /**
     * Выводит все элементы меню до тех пор, пока пользователем не будет выбран пункт "Выход" а также проверяет
     * корректность введенных пользователем значений.
     */
    public void run() {
        while (!isExit) {
            printMenu();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String line = reader.readLine();
                int choice = Integer.parseInt(line);
                if (choice <= entries.size()) {
                    MenuEntry entry = entries.get(choice - 1);
                    entry.run();
                } else System.out.println("Нет такого пункта");
            } catch (NumberFormatException e) {
                System.out.println("Нет такого пункта");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Позволяет добавить новый элемент
     *
     * @param entry добавляемый элемент меню
     * @return ссылка на данный обьект
     */
    public Menu addEntry(MenuEntry entry) {
        int index = entries.size() - 1;
        entries.add(index, entry);
        return this;
    }

    /**
     * Выводит список элементов
     */
    private void printMenu() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\nВыберите действие:\n");
        for (int i = 0; i < entries.size(); i++) {
            MenuEntry entry = entries.get(i);
            String entryFormatted = String.format(MENU_PATTERN, (i + 1), entry.getTitle());
            buffer.append(entryFormatted);
        }
        System.out.print(buffer.toString());
    }
}
