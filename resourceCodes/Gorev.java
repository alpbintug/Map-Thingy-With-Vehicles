package kaynakKodlari;

import java.sql.Time;

public class Gorev {
	private String missionDescription;
	Time bitisZamani= null;
	public Gorev() {
	}
	public Gorev( String missionDescription) {
		this.missionDescription = missionDescription;
		
	}

	public String getMissionDescription() {
		return missionDescription;
	}
	public void setMissionDescription(String missionDescription) {
		this.missionDescription = missionDescription;
	}

}
