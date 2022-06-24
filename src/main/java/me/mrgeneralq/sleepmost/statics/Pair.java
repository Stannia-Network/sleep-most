package me.mrgeneralq.sleepmost.statics;

public class Pair<X, Y> {

    private final X first;
    private final Y second;

    public Pair(X first, Y second) {
        this.first = first;
        this.second = second;
    }

    public X first(){
        return this.first;
    }

    public Y second(){
        return this.second;
    }
}
