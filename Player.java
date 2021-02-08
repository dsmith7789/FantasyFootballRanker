/**
 * Represents a player
 */

package application;

public class Player {
	
	/*
	 * Fields
	 */
	private String rank;
	private String name;
	private String team;
	private String position;
	private double pprPoints;
	
	/**
	 * Constructor
	 * @param rank	the fantasy PPR rank of the player
	 * @param name	the player's name
	 * @param team	what team the player is on
	 * @param position	what position the player plays
	 * @param pprPoints	total fantasy PPR (points per reception) points the player scored in 2019 NFL season
	 */
	public Player(String rank, String name, String team, String position, double pprPoints) {
		this.rank = rank;
		this.name = name;
		this.team = team;
		this.position = position;
		this.pprPoints = pprPoints;
	}
	
	/**
	 * Gets player rank
	 * @return the fantasy PPR rank of the player
	 */
	public String getRank() {
		return this.rank;
	}
	
	/**
	 * Gets player's name
	 * @return the player's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets player's team
	 * @return what team the player is on
	 */
	public String getTeam() {
		return this.team;
	}
	
	/**
	 * Gets player position
	 * @return what position the player plays
	 */
	public String getPosition() {
		return this.position;
	}
	
	/**
	 * Gets the PPR points
	 * @return total fantasy PPR (points per reception) points the player scored in 2019 NFL season
	 */
	public double getPprPoints() {
		return this.pprPoints;
	}
	
	/**
	 * Overrides the default toString() method so that a player object may be printed.
	 * Usage: for an Player object named player, System.out.print(player) will print the following to standard output:
	 * [RANK]: [NAME], [TEAM], [POSITION], [POINTS]
	 */
	@Override
	public String toString() {
		return this.rank + ": " + this.name + ", " + this.team + ", " +  this.position + ", " + this.pprPoints;
	}
}
