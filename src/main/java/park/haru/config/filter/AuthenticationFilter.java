package park.haru.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import park.haru.common.service.LoginService;
import park.haru.config.auth.User;
import park.haru.config.auth.UserAuth;
import park.haru.config.jwt.JwtProperties;
import park.haru.config.jwt.TokenProvider;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

// 인증
@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final LoginService loginService;
    private final JwtProperties jwtProperties;
    String TOKEN_PREFIX = "Bearer ";

    // login : post
    // 인증
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException{
        System.out.println("인증 시도");

        String haruMarket_user_id = request.getParameter("haruMarket_user_id");
        String haruMarket_user_pw = request.getParameter("haruMarket_user_pw");

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        haruMarket_user_id,
                        haruMarket_user_pw);

        Authentication authentication =
                authenticationManager.authenticate(authenticationToken);

        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException
    {
        UserAuth userAuth = (UserAuth) authResult.getPrincipal();
        User user = User.builder()
                .haruMarket_user_index(userAuth.getIndex())
                .build();

        Map<String, String> map = new HashMap<>();
        map.put("haruMarket_user_index", String.valueOf(user.getHaruMarket_user_index()));
        map.put("harumarket_userToken_ActiveToken",TOKEN_PREFIX+tokenProvider.generateToken(user,Duration.ofMinutes(jwtProperties.getActive())));
        map.put("harumarket_userToken_RefreshToken",TOKEN_PREFIX+tokenProvider.generateToken(user,Duration.ofMinutes(jwtProperties.getRefresh())));
        loginService.Authentication_token(map);

        response.addHeader(jwtProperties.getHeader(), map.get("harumarket_userToken_ActiveToken"));
    }
}
