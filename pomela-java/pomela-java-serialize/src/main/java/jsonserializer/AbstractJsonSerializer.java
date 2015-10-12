package jsonserializer;

public abstract class AbstractJsonSerializer<T> implements IJsonSerializer {

	@SuppressWarnings("unchecked")
	@Override
	public String serialize(Object target) {
		if (!this.support(target)) {
			return "";
		}
		return this.doSerialize((T) target);
	}

	protected abstract String doSerialize(T target);

}
