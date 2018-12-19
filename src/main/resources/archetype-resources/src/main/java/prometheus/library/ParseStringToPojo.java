package ${groupId}.prometheus.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static ${groupId}.prometheus.constants.PrometheusConstants.METRIC;
import static ${groupId}.prometheus.constants.PrometheusConstants.DATA;
import static ${groupId}.prometheus.constants.PrometheusConstants.RESULT;
import static ${groupId}.prometheus.constants.PrometheusConstants.STATUS;
import static ${groupId}.prometheus.constants.PrometheusConstants.SUCCESS;
import static ${groupId}.prometheus.constants.PrometheusConstants.VALUES;
import static ${groupId}.prometheus.constants.PrometheusConstants.NOT_AVAILABLE;

/**
 * Prometheus util class to convert prometheus String response to an object
 **/
public class ParseStringToPojo {

	/**
	 * Method to parse the prometheus string response and build list of TimeSeries
	 * data object
	 * 
	 * @param String
	 * @return List<PrometheusTimeSeriesData>
	 * @throws JSONException
	 * 
	 **/
	public static List<PrometheusTimeSeriesData> parseJsonResult(String result) throws JSONException {

		JSONObject jsonObjectOfResult = new JSONObject(result);
		String status = jsonObjectOfResult.getString(STATUS);
		List<PrometheusTimeSeriesData> prometheusTimeSeriesDataList = new ArrayList<>();
		if (SUCCESS.equals(status)) {
			JSONArray jsonArrayOfData = jsonObjectOfResult.getJSONObject(DATA).getJSONArray(RESULT);
			for (int i = 0; i < jsonArrayOfData.length(); i++) {
				PrometheusTimeSeriesData prometheusTimeSeriesData = new PrometheusTimeSeriesData();
				JSONObject timeSeries = jsonArrayOfData.getJSONObject(i);

				// Extract Key
				JSONObject timeSeriesKeyJson = timeSeries.getJSONObject(METRIC);
				Map<String, String> timeSeriesKey = extractTimeseriesKeyFromJSON(timeSeriesKeyJson);
				prometheusTimeSeriesData.setTimeSeriesKey(timeSeriesKey);

				// Extract Time Series
				JSONArray timeSeriesValues = timeSeries.getJSONArray(VALUES);
				List<PrometheusTimeSeriesInstantValue> prometheusTimeSeriesInstantValues = extractTimeseriesValuesFromJSON(
						timeSeriesValues);
				prometheusTimeSeriesData.setTimeseries(prometheusTimeSeriesInstantValues);

				prometheusTimeSeriesDataList.add(prometheusTimeSeriesData);
			}

		}
		return prometheusTimeSeriesDataList;

	}

	/**
	 * Method to extract the timeseries values from the JSON and return a list of
	 * promethuesTimeSeriesInstantVaue object
	 * 
	 * @param JSONArray
	 * @return List<PrometheusTimeSeriesInstantValue>
	 * @throws JSONException
	 * 
	 **/
	private static List<PrometheusTimeSeriesInstantValue> extractTimeseriesValuesFromJSON(JSONArray timeSeriesValues)
			throws JSONException {
		
		List<PrometheusTimeSeriesInstantValue> prometheusTimeSeriesInstantValues = new ArrayList<>();
		for (int i = 0; i < timeSeriesValues.length(); i++) {
			PrometheusTimeSeriesInstantValue prometheusTimeSeriesInstantValue = new PrometheusTimeSeriesInstantValue();
			prometheusTimeSeriesInstantValue.setTimestamp(timeSeriesValues.getJSONArray(0).getLong(0));

			// Check for Possible NaNs
			if (timeSeriesValues.getJSONArray(0).getString(1) != NOT_AVAILABLE) {
				prometheusTimeSeriesInstantValue.setValue(timeSeriesValues.getJSONArray(0).getDouble(1));
				prometheusTimeSeriesInstantValues.add(prometheusTimeSeriesInstantValue);
			}
		}
		return prometheusTimeSeriesInstantValues;
	}

	/**
	 * Method to extract the timeseries key and value pairs from the JSONObject
	 * 
	 * @param JSONObject
	 * @return Map<String, String>
	 * @throws JSONException
	 * 
	 **/
	private static Map<String, String> extractTimeseriesKeyFromJSON(JSONObject timeSeriesKeyJson) throws JSONException {
		
		Map<String, String> timeSeriesKey = new HashMap<>();
		Iterator<String> keys = timeSeriesKeyJson.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			String value = timeSeriesKeyJson.getString(key);
			timeSeriesKey.put(key, value);
		}
		return timeSeriesKey;
	}
}
