package com.coderunning.pipelinedemo.component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class FruitTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!className.equals("com/coderunning/pipelinedemo/component/Fruit"))
            return classfileBuffer;

        String fileName="/Users/chenhaowei/workspace/spring-learning/pipeline-demo/src/main/java/com/coderunning/pipelinedemo/component/Fruit2.java";
        return getClassBytes(fileName);
    }

    public static byte[] getClassBytes(String fileName){
        File file = new File(fileName);
        try(InputStream is = new FileInputStream(file);
            ByteArrayOutputStream bs = new ByteArrayOutputStream()){
            long length = file.length();
            byte[] bytes = new byte[(int) length];

            int n;
            while ((n = is.read(bytes)) != -1) {
                bs.write(bytes, 0, n);
            }
            return bytes;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}