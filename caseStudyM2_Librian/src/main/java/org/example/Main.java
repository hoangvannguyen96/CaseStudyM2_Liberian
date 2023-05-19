package org.example;

import view.LibraryView;

public class Main {
    private static final LibraryView libraryView;
    static{
        libraryView=new LibraryView();
    }
    public static void main(String[] args) {
        libraryView.libraryView();
    }
}