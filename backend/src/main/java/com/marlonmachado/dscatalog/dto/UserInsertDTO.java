package com.marlonmachado.dscatalog.dto;


import com.marlonmachado.dscatalog.services.validation.UserInsertValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@UserInsertValid
public class UserInsertDTO extends UserDTO {

    @NotBlank(message = "Campo obrigatório")
    //@Pattern(regexp = ) e possivel usar explexao regulart tbm
    @Size(min = 8, message = "Deve ter no minimo 8 caracteres")
    private String password;

    public UserInsertDTO() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
