package ${groupId}.prometheus.library;

/**
 * POJO class for prometheus Time series data
 **/
public class PrometheusTimeSeriesInstantValue {

	private Long timestamp;
	private Double value;

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "PrometheusTimeSeriesInstantValue [timestamp=" + timestamp + ", value=" + value + "]";
	}

}
