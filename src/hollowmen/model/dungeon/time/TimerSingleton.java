package hollowmen.model.dungeon.time;

import java.util.TreeSet;
import java.util.function.Consumer;

import hollowmen.model.Time;
import hollowmen.utilities.ExceptionThrower;

public class TimerSingleton implements Time{

	private class Event<T> implements Comparable<Event<?>>{
		final T subj;
		final double timeAtTrig;
		final Consumer<T> action;
		
		public Event(T event, double duration, Consumer<T> action) {
			this.subj = event;
			this.action = action;
			this.timeAtTrig = this.getOuterType().getLimit() + duration;
		}
		
		public void pullTheTrigger() {
			this.action.accept(subj);
		}
	
		public double getTimeAtTrig() {
			return timeAtTrig;
		}
		
		@Override
		public int compareTo(Event<?> o) {
			return (int) (this.getTimeAtTrig()-o.getTimeAtTrig());
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			long temp;
			temp = Double.doubleToLongBits(timeAtTrig);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			result = prime * result + ((subj == null) ? 0 : subj.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Event<?> other = (Event<?>) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (Double.doubleToLongBits(timeAtTrig) != Double.doubleToLongBits(other.timeAtTrig))
				return false;
			if (subj == null) {
				if (other.subj != null)
					return false;
			} else if (!subj.equals(other.subj))
				return false;
			return true;
		}
		
		private TimerSingleton getOuterType() {
			return TimerSingleton.this;
		}
	}
	//#####################################################################################
	//#####################################################################################
	//#####################################################################################
	
	private TreeSet<Event<?>> queue = new TreeSet<Event<?>>();
	
	private double currentTime;

	private double maxTime;

	
	private TimerSingleton() {}

	private static class Holder {
		public static TimerSingleton INSANCE = new TimerSingleton();
	}
	
	public static TimerSingleton getInstance() {
		return Holder.INSANCE;
	}
	
	public void resetAndLimit(double limit) {
		this.currentTime = 0;
		this.maxTime = limit;
	}
	
	@Override
	public <T> void register(T subj, double duration, Consumer<T> action) {
		this.queue.add(new Event<T>(subj, duration, action));
	}
		

	@Override
	public double getValue() {
		return this.currentTime;
	}

	@Override
	public double getLimit() {
		return this.maxTime;
	}

	@Override
	public void addToValue(double value) throws IllegalArgumentException, IllegalStateException {
		ExceptionThrower.checkIllegalArgument(value, v -> v < 0);
		this.currentTime += value;
		ExceptionThrower.checkIllegalState(this.currentTime, c -> c > this.getLimit());
		this.checkEventTrigger();
	}
	
	@Override
	public void subToValue(double value) throws IllegalArgumentException, IllegalStateException {
		ExceptionThrower.checkIllegalArgument(value, v -> v < 0);
		this.currentTime -= value;
		if(this.currentTime < 0) {
			this.currentTime = 0;
			throw new IllegalStateException();
		}
	}
	
	
	private void checkEventTrigger(){
		if(this.isAnyLeft()){
			double soonerTime = queue.first().getTimeAtTrig();
			while(soonerTime <= currentTime){
				queue.first().pullTheTrigger();;
				queue.remove(queue.first());
				if(!this.isAnyLeft()) {
					return;
				}
			}
		}
	}
	
	
	//true if there's any element left in the queue, false otherwise
	private boolean isAnyLeft() {
		return !this.queue.isEmpty();
	}


}
