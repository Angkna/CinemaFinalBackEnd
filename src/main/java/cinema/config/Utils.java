package cinema.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Utils {
	
	@Bean 
	public ModelMapper modelMapper() {
		 return new ModelMapper();
	 }
	
//	public static <T,U,C extends Collection<U>> C collectionFromEntityStream(
//			Stream<T> stream, Class<U> classDto, Supplier<C> supplier) {
//		return stream.map(a -> mapper.map(a, classDto)).collect(Collectors.toCollection(supplier));
//	}


}
