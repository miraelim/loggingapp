import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

public class getCPUTemp {

    public final Double temp;
    public final String time;

    @JsonCreator
	public getCPUTemp(
		@JsonProperty("temp") final Double temp,
		@JsonProperty("time") final String time) {
	    this.temp = temp;
	    this.time = time;
	}

    @Override
	public String toString() {
	    return Objects.toStringHelper(TrackPoint.class)
		.add("temp", temp)
		.add("time", time)
		.toString();
	}
}
