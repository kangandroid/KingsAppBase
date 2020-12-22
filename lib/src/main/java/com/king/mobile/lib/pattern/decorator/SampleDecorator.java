package com.king.mobile.lib.pattern.decorator;

public class SampleDecorator implements ISample {
    Sample sample;

    public SampleDecorator(Sample sample) {
        this.sample = sample;
    }

    @Override
    public void doSomething() {
        doSomethingElse();
        sample.doSomething();
    }

    private void doSomethingElse() {
        System.out.println("doSomethingElse");
    }
}
