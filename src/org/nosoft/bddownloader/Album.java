package org.nosoft.bddownloader;

import java.util.List;

public class Album {
	String artist;
	String title;
	String converUrl;
	
	List<Song> songs;

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getConverUrl() {
		return converUrl;
	}

	public void setConverUrl(String converUrl) {
		this.converUrl = converUrl;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	
}
