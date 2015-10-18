package pomela.java.serialize.json.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import pomela.java.common.utils.PrintUtil;
import pomela.java.serialize.json.FastJsonUtil;

import java.util.Date;

/**
 * Created by hetor on 15/10/18.
 *
 * @JSONField配置在字段或者getter或setter方法上
 * JSONType配置在类上，而不是field或者getter/setter方法上
 */
public class AnnotationDemo {
    public static void main(String[] args) {
        Person person = new Person("nomouse", 25, true, new Date(), "程序员",
                2500.0);
        PrintUtil.toConsole(FastJsonUtil.toJson(person));
    }
}

@JSONType
class Person {
    @JSONField(name="NAME")
    private String name;

    /**
     * 使用ordinal指定字段的顺序
     */
    @JSONField(ordinal = 1)
    private int age;

    @JSONField(ordinal = 2)
    private boolean sex;

    /**
     * 配置date序列化和反序列使用yyyyMMdd日期格式
     */
    @JSONField(format="yyyyMMdd")
    private Date birthday;

    /**
     * 使用serialize/deserialize指定字段不序列化
     */
    @JSONField(serialize=false, deserialize=false)
    private String word;

    @JSONField(ordinal = 3)
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

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

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
