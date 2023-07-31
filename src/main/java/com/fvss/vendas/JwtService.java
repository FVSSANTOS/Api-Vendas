package com.fvss.vendas;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.fvss.vendas.domain.entity.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
    
    @Value("${security.jwt.expericao}")
    private String expiracao;
    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario){
        long expString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Date date = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());
        return Jwts
                  .builder()
                  .setSubject(usuario.getLogin())
                  .setExpiration(date)
                  .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                  .compact();
                  
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext contexto = SpringApplication.run(VendasApplication.class);
        JwtService service = (JwtService) contexto.getBean(JwtService.class);
        Usuario usuario = Usuario.builder().login("fulano").build();
        String token = service.gerarToken(usuario);
        System.err.println(token);
    }


}
