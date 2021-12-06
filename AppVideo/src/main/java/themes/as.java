package themes;

import com.formdev.flatlaf.FlatIntelliJLaf;

public class as
	extends FlatIntelliJLaf
{
	public static final String NAME = "as";

	public static boolean setup() {
		return setup( new as() );
	}

	public static void installLafInfo() {
		installLafInfo( NAME, as.class );
	}

	@Override
	public String getName() {
		return NAME;
	}
}
