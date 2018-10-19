package com.hm707.random;

class Pair {
    Object item;
    int weight;
    
    public Pair(Object item, int weight){
        this.item = item;
        this.weight = weight;
    }

    public Object getItem() {
        return item;
    }

    public int getWeight() {
        return weight;
    }
}