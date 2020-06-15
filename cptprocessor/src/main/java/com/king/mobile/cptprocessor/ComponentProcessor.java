package com.king.mobile.cptprocessor;

import com.google.auto.service.AutoService;
import com.king.mobile.component.Component;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.sun.tools.javac.code.Type.ClassType;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;



@AutoService(Processor.class) // 用来生成META-INF/services/javax.annotation.processing.Processor文件的
public class ComponentProcessor extends AbstractProcessor {
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder("ComponentCollector");
        classBuilder.addModifiers(Modifier.PUBLIC);

        // 构建方法
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("getAllComponents");
        // 修饰符
        methodBuilder.addModifiers(Modifier.PUBLIC,Modifier.STATIC);
        // 返回值类型
        ParameterizedTypeName returnType = ParameterizedTypeName.get(
                ClassName.get(Set.class),
                ClassName.get(Class.class)
        );
        methodBuilder.returns(returnType)
                .addStatement("$T<$T> set = new $T<>()", Set.class, Class.class, HashSet.class);

        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Component.class);
        for(Element element: elements){
            ElementKind kind = element.getKind();
            if(kind.isClass()){
                ClassType type = (ClassType) element.asType();
                methodBuilder.addStatement("set.add($T.class)", type);
            }
        }
        methodBuilder.addStatement("return set");
        classBuilder.addMethod(methodBuilder.build());
        TypeSpec clazz = classBuilder.build(); // 构建类
        writeClassToFile(clazz); // 写入文件
        return false;
    }

    private void writeClassToFile(TypeSpec clazz) {
        try {
            JavaFile file = JavaFile.builder("com.king.mobile.apt", clazz).build();
            file.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Set<String> getSupportedAnnotationTypes() {
        String canonicalName = Component.class.getCanonicalName();
        return Collections.singleton(canonicalName);

    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
