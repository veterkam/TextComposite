package com.epam.javatraining.textcomposite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Entity< T extends TextElementInterface<T> & Comparable<T> > {
    private List<T> elements;
    private boolean isSeparator;

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

    //@Override
    public Entity<T> copy() {
        List<T> list = new LinkedList<>();
        for(T element : elements) {
            list.add( element.copy() );
        }

        return new Entity<>(list);
    }

    //@Override
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

    public boolean equal(Entity<T>  other) {
        return (this.compareTo(other) == 0);
    }

    public boolean isSeparator() {
        return isSeparator;
    }

    public void setSeparator(boolean separator) {
        isSeparator = separator;
    }
}


