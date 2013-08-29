package org.nosoft.bddownloader;

public class DownloadTask {
	
	public String downloadUrl;
	public String destination;
	
	public DownloadTask() {}
	
	public DownloadTask(String url) {
		this.downloadUrl = url;
	}
	
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}


}
