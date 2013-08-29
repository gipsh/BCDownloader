package org.nosoft.bddownloader;

import java.io.IOException;
import java.util.concurrent.ConcurrentMap;

import jline.console.ConsoleReader;

public class MonitorThread extends Thread {
	
	private static final int SLEEP_TIME = 3000;
	private ConcurrentMap<String,String> reports;
	private volatile boolean running = true;
	
	public MonitorThread(ConcurrentMap<String,String> reports) {
		this.reports = reports;
	}
	
	@Override
    public void run() {
		
		while (running) {
		
			try {
				ConsoleReader r = new ConsoleReader();
				r.clearScreen();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			System.out.println(String.format("Downloading tracks.."));

			for (String key : reports.keySet() ) {
			
				this.printProgBar(Integer.parseInt(reports.get(key)));			
				System.out.println(String.format("Downloading song [%s]", key));

			}
			
				
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void printProgBar(int percent){
	    StringBuilder bar = new StringBuilder("[");

	    for(int i = 0; i < 50; i++){
	        if( i < (percent/2)){
	            bar.append("=");
	        }else if( i == (percent/2)){
	            bar.append(">");
	        }else{
	            bar.append(" ");
	        }
	    }

	    bar.append("]   " + percent + "%     ");
	    System.out.print("\r" + bar.toString());
	}

    public void termiante() {
        running = false;
    }
    
    private static void clearConsole()
    {
        try
        {
            String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (Exception exception)
        {
        	System.out.println("ouch");
        }
    }

}
