package org.nosoft.bddownloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;

public class DownloaderConsumer implements Callable<Integer> {

	public static final String END_MESSAGE = "THISISOVER";
	BlockingQueue<DownloadTask> taskQueue; 
	ConcurrentMap<String,String> reports;
	volatile boolean run = true;
	
	@Override
	public Integer call() throws Exception {
		
		while (run) {
			processTask(taskQueue.take());
		}
		
		return 0;
	}

	public DownloaderConsumer(BlockingQueue<DownloadTask> taskQueue, ConcurrentMap<String,String> reports) {
		this.taskQueue = taskQueue;
		this.reports = reports;
	}
	
	public void processTask(DownloadTask task) {
		URL website;
		
		if (task.getDownloadUrl().equals(END_MESSAGE)) {
			this.run = false;
			return;
		}
		
		File f = new File(task.getDestination());
		if(f.exists()) { 
			System.out.println("file alredy exists. not overwriting.");
			return;
		}
		
		try {
			website = new URL(task.getDownloadUrl());
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			
			URLConnection connection = website.openConnection();
			connection.connect();
			int fileLength = connection.getContentLength();	
			
		    FileOutputStream fos = new FileOutputStream(task.getDestination());
		    
		    long count = 0;
		    int chunkSize = 1024 * 10;
		    
		    while (fos.getChannel().transferFrom(rbc, count, chunkSize) !=0) {
		    	count = count + chunkSize;
		    	this.reports.put(task.getDestination(), Long.toString((100*count)/fileLength));
		    }
		    
		    this.reports.remove(task.getDestination());
					
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
		
		
	}
	
	
}
