package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class JAXBDemo {
    public static void main(String[] args) throws JAXBException, JAXBException {
 //       создание объекта для сериализации в XML
        Dog cat = new Dog();
        cat.name = "Bobik";
        cat.age = 34;
        cat.weight = 4;
        cat.date = new Date();
        cat.contents = "Im Lox".split(" ");
        cat.cot = new Cot("Barsik");
        cat.lines.add("Hello");
        cat.lines.add(new Cot("Myrzik"));

        //писать результат сериализации будем в Writer(StringWriter)
        JAXBContext context = JAXBContext.newInstance(Dog.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        // сама сериализация
        //преобразовываем в строку все записанное в File
        marshaller.marshal(cat, new File("Jax.html"));
        marshaller.marshal(cat, System.out);


//        // Десериализация из файла
//        File file = new File("Jax.html");
//        JAXBContext context = JAXBContext.newInstance(Dog.class);
//        Unmarshaller unmarshaller = context.createUnmarshaller();
//        Dog cat = (Dog) unmarshaller.unmarshal(file);
//        System.out.println(cat.toString());
    }
}

//@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
class Dog {

    public String name;

    public int weight;
    public int age;

    public Date date;

    public Cot cot;

    public String[] contents;


    public List<Object> lines = new ArrayList<>();

    public Dog() {
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + "\n" +
                ", weight=" + weight +"\n" +
                ", age=" + age +"\n" +
                ", date=" + date +"\n" +
                ", cot=" + cot +"\n" +
                ", contents=" + Arrays.toString(contents) +"\n" +
                ", lines=" + lines +"\n" +
                '}';
    }
}
class DateAdapter extends XmlAdapter<String, Date> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");

    @Override
    public String marshal(Date v) throws Exception {
        return dateFormat.format(v);
    }

    @Override
    public Date unmarshal(String v) throws Exception {
        return dateFormat.parse(v);
    }
}

class Cot {

    public String name;

    public Cot(String name) {
        this.name = name;
    }
}

