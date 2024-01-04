package utez.edu.mx.ExamenD.model.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class dtoPerson {

    private Integer id;
    private String name;
    private String lastName;
   private String curp;
   private  String fechanacimiento;


}
