package pomela.java.serialize.hessian;

import com.caucho.hessian.io.*;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by hetor on 2016/10/10.
 */
public class ExtSerializerDemo {
	public static void main(String[] args) {
		ExtSerializerFactory extSerializerFactory = new ExtSerializerFactory();
		extSerializerFactory.addSerializer(Req.class, new MySerializer()); //添加自定义的序列化接口
		extSerializerFactory.addDeserializer(Req.class, new MyDeserializer()); //添加自定义的反序列化接口

		SerializerFactory serializerFactory = null; //TODO get the serializerFactory
		serializerFactory.addFactory(extSerializerFactory); //注册ext到序列化工厂
	}


	static class Req implements Serializable {

	}

	static class MySerializer implements Serializer {

		@Override
		public void writeObject(Object o, AbstractHessianOutput abstractHessianOutput) throws IOException {

		}
	}

	static class MyDeserializer implements Deserializer {

		@Override
		public Class<?> getType() {
			return null;
		}

		@Override
		public boolean isReadResolve() {
			return false;
		}

		@Override
		public Object readObject(AbstractHessianInput abstractHessianInput) throws IOException {
			return null;
		}

		@Override
		public Object readList(AbstractHessianInput abstractHessianInput, int i) throws IOException {
			return null;
		}

		@Override
		public Object readLengthList(AbstractHessianInput abstractHessianInput, int i) throws IOException {
			return null;
		}

		@Override
		public Object readMap(AbstractHessianInput abstractHessianInput) throws IOException {
			return null;
		}

		@Override
		public Object[] createFields(int i) {
			return new Object[0];
		}

		@Override
		public Object createField(String s) {
			return null;
		}

		@Override
		public Object readObject(AbstractHessianInput abstractHessianInput, Object[] objects) throws IOException {
			return null;
		}

		@Override
		public Object readObject(AbstractHessianInput abstractHessianInput, String[] strings) throws IOException {
			return null;
		}
	}
}
