package themes;

import com.formdev.flatlaf.FlatIntelliJLaf;

public class a
	extends FlatIntelliJLaf
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String NAME = "a";

	public static boolean setup() {
		return setup( new a() );
	}

	public static void installLafInfo() {
		installLafInfo( NAME, a.class );
	}

	@Override
	public String getName() {
		return NAME;
	}
}
