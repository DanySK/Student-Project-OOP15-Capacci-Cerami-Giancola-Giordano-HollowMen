package hollowmen.model.dungeon;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

import hollowmen.enumerators.ActorState;
import hollowmen.model.Actor;
import hollowmen.model.Attack;
import hollowmen.model.Enemy;
import hollowmen.model.Hero;
import hollowmen.model.Interactable;
import hollowmen.model.roomentity.EnemyAbs;
import hollowmen.model.utils.Algorithms;

public class GameCollisionListener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		int typeA = contact.getFixtureA().getFilterData().categoryBits;
		int typeB = contact.getFixtureB().getFilterData().categoryBits;
		
		if(typeA == FilterType.GROUND.getValue() && typeB == FilterType.HERO.getValue() || typeB == FilterType.ENEMY.getValue()) {
			((Actor) contact.getFixtureB().getBody().getUserData()).setState(ActorState.STANDING.toString());;
		}
		if(typeB == FilterType.GROUND.getValue() && typeA == FilterType.HERO.getValue() || typeA == FilterType.ENEMY.getValue()) {
			((Actor) contact.getFixtureA().getBody().getUserData()).setState(ActorState.STANDING.toString());;
		}
		
		if(typeA == FilterType.LOOTABLE.getValue() && typeB == FilterType.HERO.getValue()) {
			((Interactable) contact.getFixtureA().getBody().getUserData()).changeInteract();
		}
		if(typeB == FilterType.LOOTABLE.getValue() && typeA == FilterType.HERO.getValue()) {
			((Interactable) contact.getFixtureB().getBody().getUserData()).changeInteract();
		}
		
		if(typeA == FilterType.WALL.getValue()) {
			wallAction(contact, false);
		}
		if(typeB == FilterType.WALL.getValue()) {
			wallAction(contact, true);
		}	
		
		if((typeA == FilterType.ENEMYATTACK.getValue() || typeA == FilterType.HEROATTACK.getValue())
				&& (typeB == FilterType.ENEMY.getValue() || typeB == FilterType.HERO.getValue())) {
			attack((Attack) contact.getFixtureA().getBody().getUserData(), contact, false);
		}
		if((typeA == FilterType.ENEMYATTACK.getValue() || typeA == FilterType.HEROATTACK.getValue())
				&& (typeB == FilterType.ENEMY.getValue() || typeB == FilterType.HERO.getValue())) {
			
		}
		
		if(typeA == FilterType.HERO.getValue() && typeB == FilterType.ENEMY.getValue()) {
			Algorithms.combat((Enemy)contact.getFixtureB().getBody().getUserData(), 
					(Hero) contact.getFixtureA().getBody().getUserData());
		}
		if(typeB == FilterType.HERO.getValue() && typeA == FilterType.ENEMY.getValue()) {
			Algorithms.combat((Enemy)contact.getFixtureA().getBody().getUserData(), 
					(Hero) contact.getFixtureB().getBody().getUserData());
		}
	}





	@Override
	public void endContact(Contact contact) {
		int typeA = contact.getFixtureA().getFilterData().categoryBits;
		int typeB = contact.getFixtureB().getFilterData().categoryBits;
		
		if(typeA == FilterType.LOOTABLE.getValue()) {
			interactableAction((Interactable)contact.getFixtureA().getBody().getUserData(), typeB);
		}
		if(typeB == FilterType.LOOTABLE.getValue()) {
			interactableAction((Interactable)contact.getFixtureB().getBody().getUserData(), typeA);
		}
		
	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	private void interactableAction(Interactable userData, int unknownEntity) {
		if(unknownEntity == FilterType.HERO.getValue()) {
			userData.changeInteract();
		}
	}
	
	private void attack(Attack userData, Contact contact, boolean doYouCheckA) {
		boolean userIsEnemy = doYouCheckA ? contact.getFixtureB().getFilterData().categoryBits == FilterType.ENEMY.getValue()
				: contact.getFixtureA().getFilterData().categoryBits == FilterType.ENEMY.getValue(); 
		if(userIsEnemy) {
			Algorithms.combat(userData, (Hero) ((doYouCheckA) ? contact.getFixtureA().getBody().getUserData()
					: contact.getFixtureB().getBody().getUserData()));
		} else {
			Algorithms.combat(userData, (Enemy) ((doYouCheckA) ? contact.getFixtureA().getBody().getUserData()
					: contact.getFixtureB().getBody().getUserData()));
		}
	}
	
	private void wallAction(Contact contact,boolean checkA){
		int unknownEntity = this.unknownCategory(contact, checkA);
		if(unknownEntity == FilterType.ENEMY.getValue()) {
			EnemyAbs e = (EnemyAbs) ((checkA) ? contact.getFixtureA().getBody().getUserData()
					 : contact.getFixtureB().getBody().getUserData());
			e.setHitWall(true);
		}
		if(unknownEntity == FilterType.ENEMYATTACK.getValue() 
				|| unknownEntity == FilterType.HEROATTACK.getValue()) {
			Attack b = (Attack)((checkA) ? contact.getFixtureA().getBody().getUserData()
					: contact.getFixtureB().getBody().getUserData());
			b.dispose();
		}
	}

	private int unknownCategory(Contact contact, boolean checkA) {
		return checkA ? contact.getFixtureA().getFilterData().categoryBits
				: contact.getFixtureB().getFilterData().categoryBits;
	}
}
