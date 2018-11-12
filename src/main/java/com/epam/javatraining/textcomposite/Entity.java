package com.epam.javatraining.textcomposite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Entity< T extends TextElementInterface<T>> {
    protected List<T> elements;

    public Entity() { elements = new ArrayList<>(); }

    public Entity(Entity<T> e) {
        elements = e.elements;
    }

    public Entity(List<T> elements) {
        this.elements = elements;
    }

    public int size() {
        return elements.size();
    }

    public T get(int index) {
        return elements.get(index);
    }

    public void set(int index, T element) {
        elements.set(index, element);
    }

    public void add(T element) {
        elements.add(element);
    }

    public void add(int index, T element) {
        elements.add(index, element);
    }

    public void remove(int index) {
        elements.remove(index);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(T element : elements) {
            result.append(element.toString());
        }

        return result.toString();
    }

    public String toMarketString() {
        StringBuilder result = new StringBuilder();
        for(T element : elements) {
            result.append(element.toMarketString());
        }

        String tag = this.getClass().getSimpleName();
        return  "<" + tag + ">" + result + "</" + tag + ">";
    }

    public Entity<T> copy() {
        List<T> list = new LinkedList<>();
        for(T element : elements) {
            list.add( element.copy() );
        }

        return new Entity<>(list);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Entity<T> other = (Entity<T>) obj;

        if(this.size() != other.size()) {
            return false;
        }

        int size = this.size();
        for(int i = 0; i < size; i++) {
            if( !this.get(i).equals( other.get(i) ) ) {
                return false;
            }
        }

        return true;
    }

    public boolean contains(T element) {
        return elements.contains(element);
    }
}


