import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.util.Arrays;

/**
 * @auther liujiji
 * @date 2019/5/31 16:56
 */
@Slf4j
public class CommonGenericTest {

    @Test
    public void testMetadataReaderFactory(){
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();

        try {
            MetadataReader metadataReader = metadataReaderFactory.getMetadataReader("cn.joey.SpringBootLearnRunApplication");
            ClassMetadata classMetadata = metadataReader.getClassMetadata();

            log.info(classMetadata.getClassName());
            log.info(classMetadata.getEnclosingClassName());
            Arrays.asList(classMetadata.getInterfaceNames()).forEach(e -> log.info(e));
            Arrays.asList(classMetadata.getMemberClassNames()).forEach(e -> log.info(e));
            log.info(classMetadata.getSuperClassName());

            AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
            log.info(annotationMetadata.getAnnotationTypes().toString());
            log.info(String.valueOf(annotationMetadata.hasMetaAnnotation(Import.class.getName())));
            log.info(annotationMetadata.getMetaAnnotationTypes(EnableAutoConfiguration.class.getName()).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
