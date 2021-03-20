package common;

public class GlobalConstants
{
	private GlobalConstants() { }

	public static class GameMessages
	{
		private static final String[] PAWN_PROMOTION_OPTIONS = new String[] 
		{ 
			"[Q]ueen", "[R]ook", "[K]night", "[B]ishop",
		};

		private static final String OPTIONS_SEPARATOR = "  ";
		public static final String CHECK = "CHECKED!";
		public static final String CHECKMATE_FORMAT = "GAME OVER - %s WINS!";
		public static final String PAWN_PROMOTION = String.join(OPTIONS_SEPARATOR, PAWN_PROMOTION_OPTIONS);
		public static final String DRAW_FORMAT = "GAME OVER - %s!";
	}
	
	public static class ErrorMessages
	{
		public static final String CANNOT_OBTAIN_WINNER = "Cannot obtain winner color as the game is still going";
		public static final String CANNOT_OBTAIN_LOSER = "Cannot obtain loser color as the game is still going";
		public static final String CANNOT_MAKE_MOVES = "Cannot make moves because the game is over";
		public static final String UNREACHABLE_POSITION = "Destination position is not reachable";
		public static final String CANNOT_PERFORM_CASTLING = "Castling cannot be performed";
		public static final String KING_NOT_FOUND = "Unable to find the king";
		public static final String OUT_OF_RANGE = "Parameter was out of range";
		public static final String CANNOT_BE_NULL = "Parameter cannot be null";
	}
}
