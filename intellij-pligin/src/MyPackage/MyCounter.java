package MyPackage;

import com.intellij.openapi.diagnostic.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Alexey.Chursin
 * Date: Aug 13, 2010
 * Time: 3:49:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyCounter {
	protected static final Logger log = Logger.getInstance(MyCounter.class);
	private int Count = 0;
	// Sets the maximum allowed number of opened projects.
	public final int MaxCount = 1;

	public MyCounter() {

	}

	public int IncreaseCounter() {
		Count++;
		log.info("-------> count : " + Count);
		System.out.println("-------> " + Count + " ---  " + log.getClass());
		return (Count > MaxCount) ? -1 : Count;
	}

	public int DecreaseCounter() {
		Count--;
		return (Count > MaxCount) ? -1 : Count;
	}

}
