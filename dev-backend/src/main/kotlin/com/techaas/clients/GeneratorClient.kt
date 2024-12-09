package com.techaas.clients

import com.techaas.dto.Dish
import com.techaas.dto.requests.GenerateDishRequest
import com.techaas.exceptions.GenerateDishException
import kotlinx.coroutines.reactor.awaitSingle
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.bodyToMono
import org.slf4j.LoggerFactory

@Service
@RequiredArgsConstructor
class GeneratorClient(
    private val generatorDishesClient: WebClient
) {
    private val logger = LoggerFactory.getLogger(GeneratorClient::class.java)

    suspend fun generateBreakfast(generateDishRequest: GenerateDishRequest): Dish {
        logger.info("Sending request for breakfast generation with body: {}", generateDishRequest)
        return generatorDishesClient
            .post()
            .uri("/breakfast")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(generateDishRequest))
            .retrieve()
            .onStatus({ responseStatus ->
                responseStatus == HttpStatus.INTERNAL_SERVER_ERROR
            }) { throw GenerateDishException("ML server not found") }
            .bodyToMono<Dish>()
            .awaitSingle()
    }

    suspend fun generateLunch(generateDishRequest: GenerateDishRequest): Dish {
        logger.info("Sending request for lunch generation with body: {}", generateDishRequest)
        return generatorDishesClient
            .post()
            .uri("/lunch")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(generateDishRequest))
            .retrieve()
            .onStatus({ responseStatus ->
                responseStatus == HttpStatus.INTERNAL_SERVER_ERROR
            }) { throw GenerateDishException("ML server not found") }
            .bodyToMono<Dish>()
            .awaitSingle()
    }

    suspend fun generateDinner(generateDishRequest: GenerateDishRequest): Dish {
        logger.info("Sending request for dinner generation with body: {}", generateDishRequest)
        return generatorDishesClient
            .post()
            .uri("/dinner")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(generateDishRequest))
            .retrieve()
            .onStatus({ responseStatus ->
                responseStatus == HttpStatus.INTERNAL_SERVER_ERROR
            }) { throw GenerateDishException("ML server not found") }
            .bodyToMono<Dish>()
            .awaitSingle()
    }
}