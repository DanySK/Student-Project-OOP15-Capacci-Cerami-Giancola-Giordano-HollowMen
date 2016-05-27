package hollowmen.model.facade;

import java.util.Map;
import java.util.Optional;

public interface InformationDealer {
	public String getName();
	public String getDescription();
	public Optional<Map<String,Integer>> getStat();
}
