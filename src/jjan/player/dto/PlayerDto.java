package jjan.player.dto;

public class PlayerDto {
	private int point;
	private String name;
	private String tank;
	private String dps;
	private String heal;
	
	public PlayerDto(int point, String name, String tank, String dps, String heal) {
		super();
		this.point = point;
		this.name = name;
		this.tank = tank;
		this.dps = dps;
		this.heal = heal;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTank() {
		return tank;
	}

	public void setTank(String tank) {
		this.tank = tank;
	}

	public String getDps() {
		return dps;
	}

	public void setDps(String dps) {
		this.dps = dps;
	}

	public String getHeal() {
		return heal;
	}

	public void setHeal(String heal) {
		this.heal = heal;
	}
	
}
