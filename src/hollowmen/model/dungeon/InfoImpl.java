package hollowmen.model.dungeon;

import java.util.Optional;

import hollowmen.model.Information;

public class InfoImpl implements Information{

	private String name;
	
	private Optional<String> description;
	
	public InfoImpl(final String name) {
		this.name = name;
		this.description = Optional.empty();
	}
	
	public InfoImpl(final String name, final String description) {
		this.name = name;
		this.description = Optional.of(description);
	}
	
	public InfoImpl(Information info) {
		this.name = info.getName();
		this.description = info.getDescription();
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Optional<String> getDescription() {
		return this.description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		InfoImpl other = (InfoImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
