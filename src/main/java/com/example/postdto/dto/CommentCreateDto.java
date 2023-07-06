package com.example.postdto.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentCreateDto {
    private Integer id;
    @NotEmpty(message = "Invalid body : Empty body")
    @NotNull(message = "Invalid body : Empty body")
    private String body;
    @Email(message = "Invalid email : Empty email")
    private String email;
    @NotEmpty(message = "Invalid name : Empty name")
    @NotNull(message = "Invalid name : Empty name")
    private String name;
    @NotNull(message = "Invalid name : Empty name")
    private Integer idPost;
}
