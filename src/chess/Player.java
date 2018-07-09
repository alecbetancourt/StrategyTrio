package chess;

import java.util.*;

public class Player {
	private String name;
	private Team team;
	private int score;
	private ArrayList<Piece> capturedPieces = new ArrayList<Piece>();
	private Move move;
	
	Player(String name, Team team) {
		this.name = name;
		this.team = team;
	}
	
	public String name() {
		return name;
	}
	
	public String team() {
		return team.toString();
	}
}