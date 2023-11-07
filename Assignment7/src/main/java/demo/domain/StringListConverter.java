package demo.domain;

import org.apache.logging.log4j.util.Strings;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringListConverter implements AttributeConverter<List<String>, String> {


    @Override
    public String convertToDatabaseColumn(List<String> stringList) {
        return stringList == null ? "" : String.join(",", stringList);
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        return Strings.isEmpty(s) ? new ArrayList<>() : Arrays.asList(s.split(","));
    }
}
