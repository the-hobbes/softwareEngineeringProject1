public class ReworkedPlayer{
	// request an in (rank)
	boolean active = false;
	int playerType; // 0 if human, 1 if AI

	public ReworkedPlayer(playerType){
		this.playerType = playerType;
	}

	public boolean isActive(){
		return this.active;
	}

	public void setActive(){
		this.active = true;
	}

	public boolean isHuman(){
		if (this.playerType == 0){
			return true;
		}
	}
}