package com.wologic.domainnew;

import java.io.Serializable;

public class SendWave implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3756149088726177161L;

	private String waveCode; 
	
	private String waveName;

	public String getWaveCode() {
		return waveCode;
	}

	public void setWaveCode(String waveCode) {
		this.waveCode = waveCode;
	}

	public String getWaveName() {
		return waveName;
	}

	public void setWaveName(String waveName) {
		this.waveName = waveName;
	} 
	
	
	

}
