package com.nhutin.electric_project.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO implements Serializable {

   private String status;
   private String message;
   private String URL;

}
