import java.util.Set;

import static java.util.Collections.singleton;


/**
 * @author Emerson Farrugia
 */
@Component
public class TemperatureeDataPointGenerator
extends AbstractDataPointGeneratorImpl<Temperature> {

    public static final String TEMPERATURE_KEY = "temerature-in-my-per-dl";

    @Override
	public String getName() {
	    return "temperature";
	}

    @Override
	public Set<String> getSupportedValueGroupKeys() {
	    return singleton(TEMPERATURE_KEY);
	}

    @Override
	public Set<String> getRequiredValueGroupKeys() {
	    return singleton(TEMPERATURE_KEY);
	}

    @Override
	public Temperature newMeasure(TimestampedValueGroup valueGroup) {

	    // TODO set the specimen source once the SDK is updated to omh:blood-glucose:2.0
	    return new Temperature.Builder(
		    new TypedUnitValue<>(MILLIGRAMS_PER_DECILITER, valueGroup.getValue(TEMPERATURE_KEY)))
		.setEffectiveTimeFrame(valueGroup.getTimestamp())
		.build();
	}
}
