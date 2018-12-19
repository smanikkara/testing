package ${groupId}.prometheus.library;

/**
 * Prometheus Connection Object to establish connectivity to the server
 **/
public class Connection {

	String hostName;
	int portNumber;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public Connection(String hostName, int portNumber) {
		super();
		this.hostName = hostName;
		this.portNumber = portNumber;
	}

	@Override
	public String toString() {
		return "Connection [hostName=" + hostName + ", portNumber=" + portNumber + "]";
	}

}
