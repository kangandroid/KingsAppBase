package com.king.mobile.lib.pattern.prototype;

class Sample implements Cloneable {
    public String name;
    public int id;

    public Sample(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public Sample clone() throws CloneNotSupportedException {
        Sample clone = (Sample) super.clone();
        clone.id++;
        return clone;
    }
}
