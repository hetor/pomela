package pomela.java.serialize.hessian;

/**
 * Created by hetor on 2016/10/10.
 */
public class ExtSerializerDemo {
	public static void main(String[] args) {
		ExtSerializerFactory extSerializerFactory = new ExtSerializerFactory();
		extSerializerFactory.addSerializer(class , mySerializer); //添加自定义的序列化接口
		extSerializerFactory.addDeserializer(class , myDeserializer); //添加自定义的反序列化接口

		serializerFactory.addFactory(extSerializerFactory); //注册ext到序列化工厂
	}
}
