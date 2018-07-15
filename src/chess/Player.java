package chess;

import java.util.*;

/**
 * Class for the individual players.
 * @author Alec
 *
 */
public class Player {
	/**
	 * The player's name.
	 */
	private String name;
	/**
	 * The team the player is on.
	 */
	private Team team;
	/**
	 * The score.
	 */
	private int score;
	/**
	 * What pieces the player has captured.
	 */
	private ArrayList<Piece> capturedPieces = new ArrayList<Piece>();
	/**
	 * The last move the player made.
	 */
	private Move move;
	
	/**
	 * Player constructor.
	 * @param name player's name
	 * @param team team the player is on.
	 */
	Player(final String name, final Team team) {
		this.name = name;
		this.team = team;
	}
	
	/**
	 * Get the player's name.
	 * @return player's name
	 */
	public String name() {
		return name;
	}
	
	/**
	 * Get the player's team.
	 * @return name of team (black or white)
	 */
	public String team() {
		return team.toString();
	}
}
