package tools;


@FunctionalInterface				//annoté tel quel
public interface TriFunction<T,U,V,R> {			//création de sa methode
	
	R apply(T t, U u, V v);
	}
