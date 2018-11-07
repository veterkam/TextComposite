package com.epam.javatraining.textcomposite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Entity< T extends TextElementInterface<T> & Comparable<T> > {
    private List<T> elements;
    private boolean isSeparator = false;

    public Entity() { elements = new ArrayList<>(); }

    public Entity(List<T> elements) {
        this.elements = elements;
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

    public void sort() {
        Collections.sort(elements);
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

    public Entity<T> copy() {
        List<T> list = new LinkedList<>();
        for(T element : elements) {
            list.add( element.copy() );
        }

        return new Entity<>(list);
    }

    public int compareTo(Entity<T> other) {

        int size = Math.min(this.size(), other.size());
        for(int i = 0; i < size; i++) {
            int compare = this.get(i).compareTo( other.get(i) );
            if( compare != 0 ) {
                return compare;
            }
        }

        return (this.size() == other.size()) ? 0 : (this.size() < other.size()) ? -1 : 1;
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
        //return (this.compareTo((Entity<T>) obj) == 0);
    }

    public boolean contains(T element) {
        return elements.contains(element);
    }

    public boolean isSeparator() {
        return isSeparator;
    }

    public void setSeparator(boolean separator) {
        isSeparator = separator;
    }

    public int countNotSeparator() {
        int count = 0;
        for(T e : elements) {
            if(!e.isSeparator()) {
                count++;
            }
        }
        return count;
    }
}


