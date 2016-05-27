package hollowmen.model.facade;

import java.util.Map;
import java.util.Optional;

/**
 * Interface used to get object information out of the model
 * (item, mob, achievement)
 * 
 * @author Giordo
 *
 */
public interface InformationDealer {
	
	/**
	 * @return Give the name of the object 
	 */
	public String getName();
	
	/**
	 * @return Give the description of the object
	 */
	public String getDescription();
	
	/**
	 * Item and mob have stats, with that kind of  object
	 * you can get it as a {@code Map}
	 * @return Give a {@code Map<String,Integer>} of the stats
	 * if item or mob, otherwise null
	 */
	public Optional<Map<String,Integer>> getStat();
}
