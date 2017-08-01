package com.renke.util.Browers;
import java.io.Serializable;

public class SiteRule implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String bookname,savepath,catalog_start,catalog_end
	,catalog_path,chapter_href,chapter_title,content_start,content_end,source_encoding;

	public SiteRule(String bookname, String savepath, String catalog_start,
			String catalog_end, String catalog_path, String chapter_href,
			String chapter_title, String content_start, String content_end,
			String source_encoding) {
		this.bookname = bookname;
		this.savepath = savepath;
		this.catalog_start = catalog_start;
		this.catalog_end = catalog_end;
		this.catalog_path = catalog_path;
		this.chapter_href = chapter_href;
		this.chapter_title = chapter_title;
		this.content_start = content_start;
		this.content_end = content_end;
		this.source_encoding = source_encoding;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getSavepath() {
		return savepath;
	}

	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}

	public String getCatalog_start() {
		return catalog_start;
	}

	public void setCatalog_start(String catalog_start) {
		this.catalog_start = catalog_start;
	}

	public String getCatalog_end() {
		return catalog_end;
	}

	public void setCatalog_end(String catalog_end) {
		this.catalog_end = catalog_end;
	}

	public String getCatalog_path() {
		return catalog_path;
	}

	public void setCatalog_path(String catalog_path) {
		this.catalog_path = catalog_path;
	}

	public String getChapter_href() {
		return chapter_href;
	}

	public void setChapter_href(String chapter_href) {
		this.chapter_href = chapter_href;
	}

	public String getChapter_title() {
		return chapter_title;
	}

	public void setChapter_title(String chapter_title) {
		this.chapter_title = chapter_title;
	}

	public String getContent_start() {
		return content_start;
	}

	public void setContent_start(String content_start) {
		this.content_start = content_start;
	}

	public String getContent_end() {
		return content_end;
	}

	public void setContent_end(String content_end) {
		this.content_end = content_end;
	}

	public String getSource_encoding() {
		return source_encoding;
	}

	public void setSource_encoding(String source_encoding) {
		this.source_encoding = source_encoding;
	}
}
