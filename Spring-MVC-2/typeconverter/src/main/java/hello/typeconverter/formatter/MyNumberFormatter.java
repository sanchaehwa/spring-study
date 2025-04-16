package hello.typeconverter.formatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Slf4j
public class MyNumberFormatter implements Formatter<Number> {
    @Override
    //text : 사용자가 입력한 문자열
    //Parse : 문자열을 숫자로 파싱
    public Number parse(String text, Locale locale) throws ParseException {
        log.info(text,locale);
        NumberFormat format = NumberFormat.getInstance(locale);
        return format.parse(text);
    }
    @Override
    //숫자를 문자열로 print
    public String print(Number object, Locale locale) {
        log.info("object={}, locale={}", object, locale);
        return NumberFormat.getInstance(locale).format(object);
    }

}
