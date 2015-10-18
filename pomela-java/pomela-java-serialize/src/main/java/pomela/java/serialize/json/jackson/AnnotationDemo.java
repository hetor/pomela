package pomela.java.serialize.json.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pomela.java.common.date.DateFormatUtils;
import pomela.java.common.date.DatePattern;
import pomela.java.common.utils.PrintUtil;
import pomela.java.serialize.json.JacksonJsonUtil;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by hetor on 15/10/17.
 */
public class AnnotationDemo {

    public static void main(String[] args) {
        Person person = new Person("nomouse", 25, true, new Date(), "程序员",
                2500.0);

        String s = JacksonJsonUtil.toJson(person);
        PrintUtil.toConsole(s);

        Person person1 = JacksonJsonUtil.fromJson(s, Person.class);
        PrintUtil.toConsole(person1);
    }
}

//表示序列化时忽略的属性
@JsonIgnoreProperties(value = { "word" })
class Person {
    private String name;
    private int age;
    private boolean sex;
    private Date birthday;
    private String word;
    private double salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    // 反序列化一个固定格式的Date
    @JsonDeserialize(using = CustomDateDeserialize.class)
    @JsonSerialize(using = CustomDateSerialize.class)
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    // 序列化指定格式的double格式
    @JsonSerialize(using = CustomDoubleSerialize.class)
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, boolean sex, Date birthday,
                  String word, double salary) {
        super();
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.birthday = birthday;
        this.word = word;
        this.salary = salary;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", sex=" + sex
                + ", birthday=" + birthday + ", word=" + word + ", salary="
                + salary + "]";
    }
}


class CustomDateSerialize extends JsonSerializer<Date>
{
    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException
    {
        jgen.writeString(DateFormatUtils.toStr(value, DatePattern.PATTERN_6));
    }
}

class CustomDateDeserialize extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        return DateFormatUtils.fromStr(jp.getText(), DatePattern.PATTERN_6);
    }
}

class CustomDoubleSerialize extends JsonSerializer<Double> {

    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        DecimalFormat df = new DecimalFormat("##.00");
        gen.writeString(df.format(value));
    }
}

