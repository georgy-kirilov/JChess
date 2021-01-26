package common;

public class GlobalConstants
{
	private GlobalConstants() { }

	public static class Messages
	{
		private static final String[] PAWN_PROMOTION_OPTIONS = new String[] 
		{ 
			"[Q]ueen", 
			"[R]ook", 
			"[K]night", 
			"[B]ishop",
		};

		private static final String OPTIONS_SEPARATOR = "  ";
		
		public static final String CHECK = "YOU'RE CHECKED!";
		
		public static final String CHECKMATE_FORMAT = "GAME OVER - %s WINS!";
		
		public static final String PAWN_PROMOTION = String.join(OPTIONS_SEPARATOR, PAWN_PROMOTION_OPTIONS);
	}
}
