package com.akos.menu;

/**
 * Элемент меню, который имеет название и метод <tt>run()</tt>содержаший действия, выполняемые при выборе данного элемента
 *
 * @see Menu
 */

public abstract class MenuEntry {
    private String title;

    public MenuEntry(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public abstract void run();
}