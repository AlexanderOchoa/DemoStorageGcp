package com.example.demo.storage.gcp.service.impl;

import com.example.demo.storage.gcp.service.GoogleStorageService;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.channels.Channels;

@Service
public class GoogleStorageServiceImpl implements GoogleStorageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GoogleStorageServiceImpl.class);

	private Storage storage;

	private String bucketName = "myBucket";

	@Autowired
	public GoogleStorageServiceImpl(Storage storage) {
		this.storage = storage;
	}

	@Override
	public ByteArrayInputStream getFile(String fileName) throws Exception {
		Blob file = storage.get(bucketName, "myFolder/" + fileName);

		ReadChannel channel = file.reader();

		InputStream inputStream = Channels.newInputStream(channel);

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(obtenerArregloBytes(inputStream));
		channel.close();
		inputStream.close();
		byteArrayInputStream.close();

		return byteArrayInputStream;
	}

	@Override
	public void saveFile(String fileBase64, String fileName) throws Exception {
		BlobInfo.Builder builder = BlobInfo.newBuilder(bucketName, "myFolder/" + fileName)
				.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

		byte[] bytes = DatatypeConverter.parseBase64Binary(fileBase64);

		Blob resultadoGuaradadoStorage = storage.create(builder.build(), bytes);

		LOGGER.info("url: {}", resultadoGuaradadoStorage.getSelfLink());
	}

	@Override
	public void deleteFile(String fileName) throws Exception {
		storage.get(bucketName, "myFolder/" + fileName).delete();
	}

	private byte[] obtenerArregloBytes(InputStream is) throws Exception {
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		byte[] b = new byte[4096];
		int n = 0;

		while ((n = is.read(b)) != -1) {
			output.write(b, 0, n);
		}

		output.close();
		return output.toByteArray();
	}

}