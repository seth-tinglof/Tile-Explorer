import java.awt.Image;


public class Sword 
extends Player {
	
	int swordImage;

	public Sword(int xPos, int yPos) {
		super(xPos, yPos);
		swordImage = 1;
	}

	@Override
	public Image getImage() {
		switch(swordImage){
			case 1: return ImageResources.sword1;
			
			case 2: return ImageResources.sword2;
			
			case 3: return ImageResources.sword3;
			
			case 4: return ImageResources.sword4;
			
			default: return null;
		}
	}

	@Override
	public void damage(Entity entity) {
		if(entity instanceof AIPlayer){
			((AIPlayer) entity).subtractHealth(100);
			Sound.enemyHit();
		}
		else if(entity instanceof InanimateObject){
			entity.setActive(false);
			entity.setShown(false);
			if(entity instanceof Shrubbery)
				Sound.shrubHit();
			if(entity instanceof Rock)
				Sound.rockHit();
		}
	}

}
