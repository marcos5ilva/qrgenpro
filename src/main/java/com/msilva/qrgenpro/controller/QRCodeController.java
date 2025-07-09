package com.msilva.qrgenpro.controller;

import com.msilva.qrgenpro.dto.QrCodeGenerateRequest;
import com.msilva.qrgenpro.dto.QrCodeGenerateResponse;
import com.msilva.qrgenpro.service.QrCodeGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    private final QrCodeGeneratorService qrCodeGeneratorService;

    private static final Logger logger = LogManager.getLogger(QRCodeController.class);

    public QRCodeController(QrCodeGeneratorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }


    @PostMapping
    public ResponseEntity<QrCodeGenerateResponse> generate(@RequestBody QrCodeGenerateRequest request) {
        try {
            var response = qrCodeGeneratorService.generateAndUploadQrCode(request.text());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error generating QR code for text:{}", request.text(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
