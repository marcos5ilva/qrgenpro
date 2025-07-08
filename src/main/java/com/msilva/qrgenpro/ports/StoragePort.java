package com.msilva.qrgenpro.ports;

public interface StoragePort {
    String uploadFile(byte[] fileData, String fileName, String contentType);
}
