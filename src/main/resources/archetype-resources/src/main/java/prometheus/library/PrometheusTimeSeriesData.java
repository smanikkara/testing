package ${groupId}.prometheus.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * POJO class for prometheus Time series data list
 **/
public class PrometheusTimeSeriesData {

	private Map<String, String> timeSeriesKey;
	List<PrometheusTimeSeriesInstantValue> timeseries;

	public PrometheusTimeSeriesData() {
		timeSeriesKey = new HashMap<String, String>();
		timeseries = new ArrayList<>();
	}

	public void setTimeSeriesKey(Map<String, String> timeSeriesKey) {
		this.timeSeriesKey = timeSeriesKey;
	}

	public void setTimeseries(List<PrometheusTimeSeriesInstantValue> timeseries) {
		this.timeseries = timeseries;
	}

	public Map<String, String> getTimeSeriesKey() {
		return timeSeriesKey;
	}

	public List<PrometheusTimeSeriesInstantValue> getTimeseries() {
		return timeseries;
	}

	@Override
	public String toString() {
		return "PrometheusTimeSeriesData [timeSeriesKey=" + timeSeriesKey + ", timeseries=" + timeseries + "]";
	}

}
