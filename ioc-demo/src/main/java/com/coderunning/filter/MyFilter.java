package com.coderunning.filter;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyFilter implements TypeFilter {
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 类注解信息
        metadataReader.getAnnotationMetadata();
        // 类型信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();

        // 类信息
        metadataReader.getResource();

        String classname = classMetadata.getClassName();
        if (classname.contains("service")) {
            System.out.println("---->>>>>" + classname);
            return true;
        }

        return false;
    }
}
