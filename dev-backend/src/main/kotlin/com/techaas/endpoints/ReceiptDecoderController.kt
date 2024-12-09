package com.techaas.endpoints

import com.techaas.dto.ProductWithDate
import com.techaas.dto.requests.DecodeReceiptRequest
import com.techaas.services.QrAnalyzerService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/receipt")
@CrossOrigin(
    origins = ["*"],           // Allow all origins
    allowedHeaders = ["*"],    // Allow all headers
    methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT], // Allow specific HTTP methods
    maxAge = 3600              // Cache preflight response for 1 hour
)
class ReceiptDecoderController(private val qrAnalyzerService: QrAnalyzerService) {

    @PostMapping("/decode")
    fun getReceipt(@RequestBody decodeReceiptRequest: DecodeReceiptRequest): List<ProductWithDate> =
        qrAnalyzerService.getReceipt(decodeReceiptRequest)

}
