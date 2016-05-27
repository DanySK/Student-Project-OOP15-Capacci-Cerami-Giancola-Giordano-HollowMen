package hollowmen.model.dungeon.time;

import java.util.Collection;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import hollowmen.model.Actor;
import hollowmen.model.Information;
import hollowmen.model.Modifier;
import hollowmen.model.Status;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.utils.Actors;

public class GenericStatus implements Status{

	private Information info;
	private Multimap<String, Modifier> effect = ArrayListMultimap.create();
	private double duration;

	public GenericStatus(String name, double duration, Collection<Modifier> stateEff) {
		this.info = new InfoImpl(name);
		this.duration = duration;
		stateEff.stream().forEach(m -> effect.put(m.getParameter().getInfo().getName(), m));
	}

	@Override
	public Information getInfo() {
		return this.info;
	}

	@Override
	public void attachTo(Actor actor) {
		try{
			effect.entries().stream().forEach(e -> Actors.addModifier(actor, e.getValue()));
		} catch (IllegalArgumentException e){}
		actor.getStatus().add(this);
		TimerSingleton.getInstance().register(actor, duration, 
				x -> effect.entries().stream().forEach(e -> Actors.removeModifier(x, e.getValue())));
	}
}

