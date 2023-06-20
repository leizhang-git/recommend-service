//package com.recommend.bootstrap.util;
//
//import com.caucho.hessian.io.Hessian2Input;
//import com.caucho.hessian.io.Hessian2Output;
//import com.esotericsoftware.kryo.Kryo;
//import com.esotericsoftware.kryo.io.Input;
//import com.esotericsoftware.kryo.io.Output;
//import com.google.common.base.Stopwatch;
//import com.recommend.bootstrap.config.LogProxy;
//import com.recommend.bootstrap.domain.entity.Person;
//import org.apache.thrift.TException;
//import org.apache.thrift.protocol.TBinaryProtocol;
//import org.apache.thrift.transport.TIOStreamTransport;
//import org.nustaq.serialization.FSTConfiguration;
//import org.nustaq.serialization.FSTObjectInput;
//import org.nustaq.serialization.FSTObjectOutput;
//
//import java.io.*;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Author zhanglei
// * @Date 2023/5/111937
// */
//public class Test {
//    public static void main(String[] args) {
//        MyTest myTest = new MyTest();
//        new Thread(myTest).start();
//        new Thread(myTest).start();
//        new Thread(myTest).start();
//        new Thread(myTest).start();
//        new Thread(myTest).start();
//    }
//}
//
//class MyTest implements Runnable {
//
//    private static int current = 0;
//
//    @Override
//    public void run() {
//        while (true) {
//            synchronized (this) {
//                if(current >= 10) {
//                    break;
//                }
//                System.out.println("name:" + Thread.currentThread().getName() + "-" + "current:" + current);
//                current++;
//            }
//        }
//    }
//}
//
//class CglibTest {
//    public static void main(String[] args) {
//        // proxy
//        PrintUtil printUtil = (PrintUtil) LogProxy.getCglibProxy(PrintUtil.class);
//
//        // call
//        String str = printUtil.printHello();
//    }
//}
//
//class SerializableTest {
//    public static void main(String[] args) throws Exception {
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        System.out.println(">>>>>>>>>>开始序列化");
//        for (int i = 0; i < 200000; i++) {
//            serializationInfo();
//        }
//        stopwatch.stop();
//        System.out.println("序列化完毕，全程耗时：" + (stopwatch.elapsed(TimeUnit.MICROSECONDS) / 1000 / 1000) + "s");
//        System.out.println("====================================================================");
//        Stopwatch stopwatch1 = Stopwatch.createStarted();
//        System.out.println(">>>>>>>>>>开始反序列化");
//        for (int i = 0; i < 200000; i++) {
//            unSerializationInfo();
//        }
//        stopwatch1.stop();
//        System.out.println("反序列化完毕，全程耗时：" + (stopwatch1.elapsed(TimeUnit.MICROSECONDS) / 1000 / 1000) + "s");
//    }
//
//    public static void serializationInfo() throws IOException {
//        Person person = new Person();
//        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(new File("D:\\test\\person.txt")));
//        stream.writeObject(person);
//        stream.close();
//    }
//
//    public static void unSerializationInfo() throws Exception {
//        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(new File("D:\\test\\person.txt")));
//        Person stu = (Person)stream.readObject();
//        stream.close();
//    }
//}
//
//class FstTest {
//    public static void main(String[] args) throws Exception {
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        System.out.println(">>>>>>>>>>开始序列化");
//        for (int i = 0; i < 200000; i++) {
//            writeObject();
//        }
//        stopwatch.stop();
//        System.out.println("序列化完毕，全程耗时：" + (stopwatch.elapsed(TimeUnit.MICROSECONDS) / 1000 / 1000) + "s");
//        System.out.println("====================================================================");
//        Stopwatch stopwatch1 = Stopwatch.createStarted();
//        System.out.println(">>>>>>>>>>开始反序列化");
//        for (int i = 0; i < 200000; i++) {
//            readObject();
//        }
//        stopwatch1.stop();
//        System.out.println("反序列化完毕，全程耗时：" + (stopwatch1.elapsed(TimeUnit.MICROSECONDS) / 1000 / 1000) + "s");
//    }
//
//    static FSTConfiguration conf = FSTConfiguration.createAndroidDefaultConfiguration();
//
//    static void writeObject() throws IOException {
//        Person person = new Person();
//        FSTObjectOutput out = conf.getObjectOutput(new FileOutputStream(new File("D:\\test\\person1.txt")));
//        out.writeObject(person);
//        out.close();
//    }
//
//    static void readObject() throws Exception {
//        FSTObjectInput input = conf.getObjectInput(new FileInputStream(new File("D:\\test\\person1.txt")));
//        Person person = (Person) input.readObject(Person.class);
//        input.close();
//    }
//}
//
//class KryoTest {
//    public static void main(String[] args) throws Exception {
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        System.out.println(">>>>>>>>>>开始序列化");
//        Kryo kryo = new Kryo();
//        kryo.register(Person.class);
//        for (int i = 0; i < 200000; i++) {
//            writeObject(kryo);
//        }
//        stopwatch.stop();
//        System.out.println("序列化完毕，全程耗时：" + (stopwatch.elapsed(TimeUnit.MICROSECONDS) / 1000 / 1000) + "s");
//        System.out.println("====================================================================");
//        Stopwatch stopwatch1 = Stopwatch.createStarted();
//        System.out.println(">>>>>>>>>>开始反序列化");
//        for (int i = 0; i < 200000; i++) {
//            readObject(kryo);
//        }
//        stopwatch1.stop();
//        System.out.println("反序列化完毕，全程耗时：" + (stopwatch1.elapsed(TimeUnit.MICROSECONDS) / 1000 / 1000) + "s");
//    }
//
//    static void writeObject(Kryo kryo) throws IOException {
//        Person person = new Person();
//        Output out = new Output(new FileOutputStream("D:\\test\\person2.txt"));
//        kryo.writeObject(out, person);
//        out.close();
//    }
//
//    static void readObject(Kryo kryo) throws Exception {
//        Input input = new Input(new FileInputStream("D:\\test\\person2.txt"));
//        Person person = kryo.readObject(input, Person.class);
//    }
//}
//
//class ProtobufTest {
//    public static void main(String[] args) throws Exception {
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        System.out.println(">>>>>>>>>>开始序列化");
//        com.recommend.bootstrap.domain.protos.Person person = com.recommend.bootstrap.domain.protos.Person.newBuilder()
//                .setName("测试")
//                .setAge(22)
//                .setSex(0)
//                .build();
//        for (int i = 0; i < 200000; i++) {
//            writeObject(person);
//        }
//        stopwatch.stop();
//        System.out.println("序列化完毕，全程耗时：" + (stopwatch.elapsed(TimeUnit.MICROSECONDS) / 1000 / 1000) + "s");
//        System.out.println("====================================================================");
//        Stopwatch stopwatch1 = Stopwatch.createStarted();
//        System.out.println(">>>>>>>>>>开始反序列化");
//        for (int i = 0; i < 200000; i++) {
//            readObject();
//        }
//        stopwatch1.stop();
//        System.out.println("反序列化完毕，全程耗时：" + (stopwatch1.elapsed(TimeUnit.MICROSECONDS) / 1000 / 1000) + "s");
//    }
//
//    static void writeObject(com.recommend.bootstrap.domain.protos.Person person) throws IOException {
//        FileOutputStream outputStream = new FileOutputStream("D:\\test\\person3.txt");
//        person.writeTo(outputStream);
//        outputStream.close();
//    }
//
//    static void readObject() throws Exception {
//        com.recommend.bootstrap.domain.protos.Person person = com.recommend.bootstrap.domain.protos.Person.parseFrom(new FileInputStream("D:\\test\\person3.txt"));
//    }
//}
//
//class ThriftTest {
//    public static void main(String[] args) throws Exception {
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        System.out.println(">>>>>>>>>>开始序列化");
//        com.recommend.bootstrap.domain.thrift.Person person = new com.recommend.bootstrap.domain.thrift.Person();
//        person.setName("测试");
//        person.setAge(22);
//        person.setSex(0);
//        for (int i = 0; i < 200000; i++) {
//            writeObject(person);
//        }
//        stopwatch.stop();
//        System.out.println("序列化完毕，全程耗时：" + (stopwatch.elapsed(TimeUnit.MICROSECONDS) / 1000 / 1000) + "s");
//        System.out.println("====================================================================");
//        Stopwatch stopwatch1 = Stopwatch.createStarted();
//        System.out.println(">>>>>>>>>>开始反序列化");
//        com.recommend.bootstrap.domain.thrift.Person person1 = new com.recommend.bootstrap.domain.thrift.Person();
//        for (int i = 0; i < 200000; i++) {
//            readObject(person1);
//        }
//        stopwatch1.stop();
//        System.out.println("反序列化完毕，全程耗时：" + (stopwatch1.elapsed(TimeUnit.MICROSECONDS) / 1000 / 1000) + "s");
//    }
//
//    static void writeObject(com.recommend.bootstrap.domain.thrift.Person person) throws IOException, TException {
//        FileOutputStream fos = new FileOutputStream(new File("D:\\test\\person4.txt"));
//        person.write(new TBinaryProtocol(new TIOStreamTransport(fos)));
//        fos.close();
//    }
//
//    static void readObject(com.recommend.bootstrap.domain.thrift.Person person) throws Exception {
//        FileInputStream fis = new FileInputStream(new File("D:\\test\\person4.txt"));
//        person.read(new TBinaryProtocol(new TIOStreamTransport(fis)));
//        fis.close();
//    }
//}
//
//class HessianTest {
//    public static void main(String[] args) throws Exception {
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        System.out.println(">>>>>>>>>>开始序列化");
//        Person person = new Person();
//        for (int i = 0; i < 200000; i++) {
//            writeObject(person);
//        }
//        stopwatch.stop();
//        System.out.println("序列化完毕，全程耗时：" + (stopwatch.elapsed(TimeUnit.MICROSECONDS) / 1000 / 1000) + "s");
//        System.out.println("====================================================================");
//        Stopwatch stopwatch1 = Stopwatch.createStarted();
//        System.out.println(">>>>>>>>>>开始反序列化");
//        com.recommend.bootstrap.domain.thrift.Person person1 = new com.recommend.bootstrap.domain.thrift.Person();
//        for (int i = 0; i < 200000; i++) {
//            readObject();
//        }
//        stopwatch1.stop();
//        System.out.println("反序列化完毕，全程耗时：" + (stopwatch1.elapsed(TimeUnit.MICROSECONDS) / 1000 / 1000) + "s");
//    }
//
//    static void writeObject(Person person) throws IOException {
//        FileOutputStream fos = new FileOutputStream(new File("D:\\test\\person5.txt"));
//        Hessian2Output oo = new Hessian2Output(fos);
//        oo.writeObject(person);
//        fos.close();
//    }
//
//    static void readObject() throws Exception {
//        FileInputStream fis = new FileInputStream(new File("D:\\test\\person5.txt"));
//        Hessian2Input input = new Hessian2Input(fis);
//        input.readObject();
//    }
//}