package com.renke.http;

import java.io.Serializable;
import java.util.Map;

import com.renke.util.collection.TraverseCollection;

public class Response  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String httpStatus;
	private String charset;
	private String encoding;
	private boolean gzip;
	private int length;
	private boolean chunked;
	private Map<String,String> header;
	private byte[] bytes;
	
	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public boolean isGzip() {
		return gzip;
	}

	public void setGzip(boolean gzip) {
		this.gzip = gzip;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isChunked() {
		return chunked;
	}

	public void setChunked(boolean chunked) {
		this.chunked = chunked;
	}

	public Map<String, String> getHeader() {
		return header;
	}

	public void setHeader(Map<String, String> header) {
		this.header = header;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\r\ncharset:").append(charset)
		.append("\r\nencoding:").append(encoding)
		.append("\r\nhttpStatus:").append(httpStatus)
		.append("\r\nlength:").append(length)
		.append("\r\nchunked:").append(chunked);
		if(header!=null){
			sb.append("\r\n").append(TraverseCollection.mapToString(header));
		}
		return sb.toString();
	}
}
