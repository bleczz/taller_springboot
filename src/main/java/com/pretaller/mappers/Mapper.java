package com.pretaller.mappers;

public interface Mapper<ClassA, ClassB>{
    ClassB mapTo(ClassA classA);
    ClassA mapFrom(ClassB classB);
}