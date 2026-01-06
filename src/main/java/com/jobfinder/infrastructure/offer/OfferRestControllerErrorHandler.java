package com.jobfinder.infrastructure.offer;

import com.jobfinder.domain.offer.OfferNotFoundException;
import com.jobfinder.infrastructure.offer.dto.OfferErrorResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Log4j2
class OfferRestControllerErrorHandler {

    @ExceptionHandler(OfferNotFoundException.class)
    @ResponseBody
    public OfferErrorResponseDto handleAllExceptions(Exception ex) {
        String message = ex.getMessage();
        log.error(message);
        return new OfferErrorResponseDto(message, HttpStatus.NOT_FOUND);
    }
}
