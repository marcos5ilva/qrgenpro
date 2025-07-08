package com.msilva.qrgenpro.controller;

import com.google.zxing.WriterException;
import com.msilva.qrgenpro.dto.QrCodeGenerateRequest;
import com.msilva.qrgenpro.dto.QrCodeGenerateResponse;
import com.msilva.qrgenpro.service.QrCodeGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    private final QrCodeGeneratorService qrCodeGeneratorService;

    public QRCodeController(QrCodeGeneratorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }


    public ResponseEntity<QrCodeGenerateResponse> generate(@RequestBody QrCodeGenerateRequest request) {
        try {
            var response = qrCodeGeneratorService.generateAndUploadQrCode(request.text());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
