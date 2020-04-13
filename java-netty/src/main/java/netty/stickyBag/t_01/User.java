package netty.stickyBag.t_01;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class User {
	@Getter
	@Setter
	private Integer age;
	@Getter
	@Setter
	private String name;
}
