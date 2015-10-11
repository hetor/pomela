package jsonserializer;

public interface IJsonSerializer {

	String serialize(Object target);

	boolean support(Object target);

}
