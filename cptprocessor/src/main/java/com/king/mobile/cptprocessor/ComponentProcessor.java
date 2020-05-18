package com.king.mobile.cptprocessor;

import com.google.auto.service.AutoService;
import com.king.mobile.component.Component;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;



@AutoService(Processor.class) // 用来生成META-INF/services/javax.annotation.processing.Processor文件的
public class ComponentProcessor extends AbstractProcessor {

    private Types typeUtils;
    private Elements elementUtils;
    private Filer filer;
    private Messager messager;
    private Map<String, ComponentAnnotatedClass> componentClasses = new LinkedHashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Component.class);
        MethodSpec methodSpec = getMethodFromElements(elements);
        TypeSpec clazz = createClassWithMethod(methodSpec);
        writeClassToFile(clazz);
        return true;
    }

    private void writeClassToFile(TypeSpec clazz) {
        JavaFile file = JavaFile.builder("com.king.mobile.testapp.apt", clazz).build();
        try {
            file.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TypeSpec createClassWithMethod(MethodSpec method) {
        TypeSpec.Builder ourClass = TypeSpec.classBuilder("ComponentManager");
        ourClass.addModifiers(Modifier.PUBLIC);
        ourClass.addJavadoc("auto generate class ~\n\n @author ${user name}");
        ourClass.addMethod(method);
        return null;
    }

    private MethodSpec getMethodFromElements(Set<? extends Element> elements) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder("getAllClasses");
        builder.addModifiers(Modifier.PUBLIC, Modifier.STATIC);
        ParameterizedTypeName returnType = ParameterizedTypeName.get(
                ClassName.get(Set.class),
                ClassName.get(Class.class)
        );
        builder.returns(returnType);
        //  "$T" 是占位符，代表一个类型，可以自动 import 包。其它占位符：
        // $L: 字符(Literals)、 $S: 字符串(String)、 $N: 命名(Names)
        builder.addStatement("$T<$T> set = new $T<>();", Set.class,Class.class, HashSet.class);
        for (Element element: elements){
            ElementKind kind = element.getKind();
            if(kind.isClass()){
                builder.addStatement("set.add($T.class);", element.asType());
            }
        }
        builder.addStatement("return set;");
        builder.addModifiers();
        return builder.build();
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new java.util.HashSet<>(Collections.emptySet());
        types.add(Component.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
