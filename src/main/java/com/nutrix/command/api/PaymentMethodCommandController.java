package com.nutrix.command.api;

import com.nutrix.command.application.services.PaymentMethodCommandService;
import com.nutrix.command.domain.PaymentMethod;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/payment-method")
@Api(tags="PaymentMethod", value = "Servicio Web RESTFul de PaymentMethod")
public class PaymentMethodCommandController {

    @Autowired
    private PaymentMethodCommandService paymentMethodService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Registro de un PaymentMethod de un Patient", notes ="Método que registra un PaymentMethod" )
    @ApiResponses({
            @ApiResponse(code=201, message = "PaymentMethod creado"),
            @ApiResponse(code=404, message = "PaymentMethod no creado")
    })
    public ResponseEntity<PaymentMethod> insertPaymentMethod(@Valid @RequestBody PaymentMethod paymentMethod){
        try{
            PaymentMethod paymentMethodNew = paymentMethodService.save(paymentMethod);
            return ResponseEntity.status(HttpStatus.CREATED).body(paymentMethodNew);
        }catch (Exception e){
            return new ResponseEntity<PaymentMethod>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Actualización de datos de PaymentMethod", notes = "Método que actualizar los datos de PaymentMethod")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de PaymentMethod actualizados"),
            @ApiResponse(code=404, message = "PaymentMethod no actualizado")
    })
    public ResponseEntity<PaymentMethod> updatePaymentMethod(@PathVariable("id") Integer id, @Valid @RequestBody PaymentMethod paymentMethod){
        try{
            Optional<PaymentMethod> paymentMethodOld = paymentMethodService.getById(id);
            if(!paymentMethodOld.isPresent())
                return new ResponseEntity<PaymentMethod>(HttpStatus.NOT_FOUND);
            else{
                paymentMethod.setId(id);
                paymentMethodService.save(paymentMethod);
                return new ResponseEntity<PaymentMethod>(paymentMethod, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<PaymentMethod>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Eliminación de PaymentMethod por Id", notes = "Método que elimina los datos de un PaymentMethod")
    @ApiResponses({
            @ApiResponse(code=200, message = "Datos de PaymentMethod eliminados"),
            @ApiResponse(code=404, message = "PaymentMethod no eliminados")
    })
    public ResponseEntity<PaymentMethod> deletePaymentMethod(@PathVariable("id") Integer id){
        try{
            Optional<PaymentMethod> paymentMethodDelete = paymentMethodService.getById(id);
            if(paymentMethodDelete.isPresent()){
                paymentMethodService.delete(id);
                return new ResponseEntity<PaymentMethod>(HttpStatus.OK);
            }
            else
                return new ResponseEntity<PaymentMethod>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<PaymentMethod>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
