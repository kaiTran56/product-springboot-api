package com.tranquyet.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UploadFileResponse {
	private String fileName;

	private String fileDownloadUri;

	private String fileType;

	private long size;
}
