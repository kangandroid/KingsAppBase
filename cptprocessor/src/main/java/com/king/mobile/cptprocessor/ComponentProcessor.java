package com.king.mobile.cptprocessor;

import com.google.auto.service.AutoService;
import com.king.mobile.component.Component;

import java.util.Collections;
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
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;


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
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Component.class);
        for (Element element : elements) {
            if (element.getKind() != ElementKind.CLASS) {
                error(element, "Only classes can be annotated with @%s", Component.class.getSimpleName());
                return true;
            }
            TypeElement typeElement = (TypeElement) element;
            try {
                ComponentAnnotatedClass aClass = new ComponentAnnotatedClass(typeElement);
                if (!isValidClass(aClass)) {
                    return true;
                }
                ComponentAnnotatedClass annotatedClass = componentClasses.get(aClass.name);
                if (annotatedClass == null) {
                    componentClasses.put(aClass.name, aClass);
                }


            } catch (IllegalArgumentException e) {
                error(typeElement, e.getLocalizedMessage());
                return true;
            }

        }
        return true;
    }

    private boolean isValidClass(ComponentAnnotatedClass item) {
        TypeElement classElement = item.annotatedClassElement;
        Class<? extends Name> aClass = classElement.getSimpleName().getClass();
        messager.printMessage(Diagnostic.Kind.NOTE, aClass.getCanonicalName());
        return true;
    }

    private void error(Element e, String msg, Object... args) {
        messager.printMessage(
                Diagnostic.Kind.ERROR,
                String.format(msg, args),
                e);
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = Collections.emptySet();
        types.add(Component.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
